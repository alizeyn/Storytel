package ir.alizeyn.storytel.data.network.model

data class Comment(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var body: String
)
