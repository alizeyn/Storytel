package ir.alizeyn.storytel.presentation.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.alizeyn.storytel.databinding.ItemPostBinding
import ir.alizeyn.storytel.domain.StorytelPost

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    var data: List<StorytelPost> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<StorytelPost>) {
        this@PostAdapter.data = data
        notifyDataSetChanged()
    }

    class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: StorytelPost) {
            binding.postBody.text = post.body
            binding.postTitle.text = post.title
            binding.postThumbnail.load(post.thumbnailUrl)
            binding.root.setOnClickListener {
                val action = PostsFragmentDirections.actionPostsFragmentToCommentsFragment(post)
                binding.root.findNavController().navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding)
            }
        }
    }
}