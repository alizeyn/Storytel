package ir.alizeyn.storytel.fragments.post

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.adapter.PostAdapter
import ir.alizeyn.storytel.databinding.FragmentPostsBinding
import ir.alizeyn.storytel.network.NetworkRetryState
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.viewmodel.NetworkErrorViewModel
import ir.alizeyn.storytel.viewmodel.PostsViewModel

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val postsViewModel: PostsViewModel by navGraphViewModels(R.id.storytel_nav) {
        defaultViewModelProviderFactory
    }
    private val networkErrorViewModel: NetworkErrorViewModel by activityViewModels()

    private val adapter: PostAdapter by lazy { PostAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupRecyclerView()

        if (postsViewModel.posts.value == null) {
            postsViewModel.requestPosts()
        }

        postsViewModel.posts.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.GONE
            when (response) {
                is Response.Error -> {
                    when (networkErrorViewModel.retry.value) {
                        NetworkRetryState.ERROR ->
                            networkErrorViewModel.retry.value = NetworkRetryState.RETRY
                        NetworkRetryState.RETRY ->
                            networkErrorViewModel.retry.value = NetworkRetryState.IDLE
                        else ->
                            networkErrorViewModel.retry.value = NetworkRetryState.ERROR
                    }
                }
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateData(it)
                    }
                    networkErrorViewModel.retry.value = NetworkRetryState.NON
                }
            }
        })

        networkErrorViewModel.retry.observe(viewLifecycleOwner, {
            when (it) {
                NetworkRetryState.ERROR ->
                    findNavController()
                        .navigate(R.id.action_postsFragment_to_networkErrorFragment)
                NetworkRetryState.RETRY -> postsViewModel.requestPosts()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.posts_fragment_menu, menu)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}