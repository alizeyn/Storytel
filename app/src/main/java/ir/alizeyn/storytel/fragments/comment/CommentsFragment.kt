package ir.alizeyn.storytel.fragments.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import ir.alizeyn.storytel.data.model.domain.StorytelPost
import ir.alizeyn.storytel.databinding.FragmentCommentsBinding

class CommentsFragment : Fragment() {

    private val args by navArgs<CommentsFragmentArgs>()

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        val post: StorytelPost = args.currentPost
        binding.postImage.load(post.imageUrl)
        binding.postTitle.text = post.title
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}