package ir.alizeyn.storytel.presentation.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.alizeyn.storytel.databinding.ItemCommentBinding
import ir.alizeyn.storytel.domain.StorytelComment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    var data: List<StorytelComment> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<StorytelComment>) {
        this@CommentAdapter.data = data
        notifyDataSetChanged()
    }

    class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: StorytelComment) {
            binding.comment.text = comment.body
            binding.name.text = comment.name
            binding.email.text = comment.email
        }

        companion object {

            fun from(parent: ViewGroup): CommentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCommentBinding.inflate(layoutInflater, parent, false)
                return CommentViewHolder(binding)
            }
        }
    }
}