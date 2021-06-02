package ir.alizeyn.storytel.presentation.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.storytel.R
import ir.alizeyn.storytel.data.network.NetworkRetryState
import ir.alizeyn.storytel.data.network.model.Response
import ir.alizeyn.storytel.databinding.FragmentCommentsBinding
import ir.alizeyn.storytel.domain.StorytelPost
import ir.alizeyn.storytel.presentation.network.NetworkErrorViewModel
import ir.alizeyn.storytel.presentation.post.PostsViewModel

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val args by navArgs<CommentsFragmentArgs>()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    private val postsViewModel: PostsViewModel by viewModels()
    private val networkErrorViewModel: NetworkErrorViewModel by activityViewModels()

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

        postsViewModel.requestComments(post.id)

        postsViewModel.comments.observe(viewLifecycleOwner, { response ->
            binding.progressBar.visibility = View.INVISIBLE
            when (response) {
                is Response.Success -> {
                    response.data?.let {
                        adapter.updateData(it)
                    }
                    networkErrorViewModel.retry.value = NetworkRetryState.NON
                }
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
            }
        })

        networkErrorViewModel.retry.observe(viewLifecycleOwner, {
            when (it) {
                NetworkRetryState.ERROR ->
                    findNavController()
                        .navigate(R.id.action_commentsFragment_to_networkErrorFragment)

                NetworkRetryState.RETRY -> {
                    postsViewModel.requestComments(post.id)
                    binding.postImage.load(post.imageUrl)
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