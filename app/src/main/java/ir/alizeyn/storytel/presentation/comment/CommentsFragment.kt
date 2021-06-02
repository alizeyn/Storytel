package ir.alizeyn.storytel.presentation.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.databinding.FragmentCommentsBinding
import ir.alizeyn.storytel.domain.StorytelPost
import ir.alizeyn.storytel.presentation.network.NetworkErrorDialog

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val args by navArgs<CommentsFragmentArgs>()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val commentsViewModel: CommentsViewModel by viewModels()

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
        binding.postBody.text = post.body

        val networkErrorDialog = NetworkErrorDialog(
            requireContext()
        ) {
            commentsViewModel.requestComments(post.id)
        }

        commentsViewModel.requestComments(post.id)

        commentsViewModel.commentList.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.INVISIBLE
            when (response) {
                is Response.Success -> {
                    binding.postImage.load(post.imageUrl)
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

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}