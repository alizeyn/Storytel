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
        val view = binding.root
        setHasOptionsMenu(true)
        setupRecyclerView()

        if (postsViewModel.posts.value == null) {
            postsViewModel.requestPosts()
        }

        postsViewModel.posts.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.GONE
            val errorState = networkErrorViewModel.retry.value

            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateData(it)
                    }
                    if (errorState != null) {
                        networkErrorViewModel.retry.value = NetworkRetryState.RESOLVED
                    }
                }
                is Response.Error -> {
                    if (errorState == null) {
                        findNavController().navigate(R.id.action_postsFragment_to_networkErrorFragment)
                    } else if (errorState == NetworkRetryState.RETRY) {
                        networkErrorViewModel.retry.value = NetworkRetryState.IDLE
                    }
                }
            }
        })

        networkErrorViewModel.retry.observe(viewLifecycleOwner, {
            if (it == NetworkRetryState.RETRY) {
                postsViewModel.requestPosts()
            }
        })

        return view
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