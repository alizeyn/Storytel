package ir.alizeyn.storytel.fragments.post

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.adapter.PostAdapter
import ir.alizeyn.storytel.databinding.FragmentPostsBinding
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.viewmodel.PostsViewModel

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    private val postsViewModel: PostsViewModel by navGraphViewModels<PostsViewModel>(R.id.storytel_nav) {
        defaultViewModelProviderFactory
    }
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
            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateData(it)
                    }
                }
                is Response.Error -> {

                }
            }
        })

        return view
    }

    override fun onDestroyView() {
        Log.i("PostsFragment", "onDestroyView: ")
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