package ir.alizeyn.storytel.domain

data class StorytelComment(
    var postId: Int,
    var name: String,
    var email: String,
    var body: String
)