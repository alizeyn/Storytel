package ir.alizeyn.storytel.fragments.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.adapter.CommentAdapter
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.databinding.FragmentCommentsBinding
import ir.alizeyn.storytel.network.Response
import ir.alizeyn.storytel.viewmodel.PostsViewModel

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val args by navArgs<CommentsFragmentArgs>()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val postsViewModel: PostsViewModel by viewModels()
    private val adapter: CommentAdapter by lazy { CommentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        setupRecyclerView()

        val post: StorytelPost = args.currentPost
        binding.postImage.load(post.imageUrl)
        binding.postTitle.text = post.title

        postsViewModel.requestComments(post.id)
        postsViewModel.comments.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.GONE
            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        Log.i("TAG", "onCreateView: Updating commens")
                        adapter.updateData(it) }
                }
                is Response.Error -> {

                }
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}