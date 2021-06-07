package ir.alizeyn.storytel.presentation.post

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.databinding.FragmentPostsBinding
import ir.alizeyn.storytel.presentation.network.NetworkErrorDialog

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val postsViewModel: PostsViewModel by viewModels()

    private val adapter: PostAdapter by lazy { PostAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setupRecyclerView()

        val networkErrorDialog = NetworkErrorDialog(
            requireContext()
        ) {
            postsViewModel.requestPosts()
        }

        postsViewModel.postList.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.GONE

            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateData(it)
                    }
                    if (networkErrorDialog.isShowing) {
                        networkErrorDialog.dismiss()
                    }
                }
                is Response.Error -> {
                    if (networkErrorDialog.isShowing) {
                        networkErrorDialog.showIdleState()
                    } else {
                        networkErrorDialog.show()
                    }
                }
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