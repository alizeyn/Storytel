package ir.alizeyn.storytel.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StorytelPost(
    var id: Int,
    var title: String,
    var body: String,
    var thumbnailUrl: String,
    var imageUrl: String
): Parcelable