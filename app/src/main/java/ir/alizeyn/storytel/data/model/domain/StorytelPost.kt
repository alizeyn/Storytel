package ir.alizeyn.storytel.data.model.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StorytelPost(
    var id: Int,
    var title: String,
    var body: String,
    var thumbnailUrl: String,
    var imageUrl: String
): Parcelable