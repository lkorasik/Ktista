package com.lkorasik.ktistaclient.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.models.PostModel
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class FeedRecyclerAdapter : RecyclerView.Adapter<FeedRecyclerAdapter.FeedViewHolder>() {

    private var postList: ArrayList<PostModel> = ArrayList()

    fun setItems(posts: ArrayList<PostModel>) {
        postList.addAll(posts)
    }

    fun clearItems() {
        postList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val postView = layoutInflater.inflate(R.layout.fragment_post, parent, false)

        return FeedViewHolder(itemView = postView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(postModel = postList[position])
    }

    override fun getItemCount(): Int = postList.size


    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircleImageView = itemView.findViewById(R.id.ci_post_avatar)
        private val postPhoto: ImageView = itemView.findViewById(R.id.iv_post_photo)
        private val description: TextView = itemView.findViewById(R.id.tv_post_description)
        private val name: TextView = itemView.findViewById(R.id.tv_user_name)
        private val login: TextView = itemView.findViewById(R.id.tv_login)
        private val dislikeCount: TextView = itemView.findViewById(R.id.tv_dislike_count)
        private val likeCount: TextView = itemView.findViewById(R.id.tv_like_count)
        private val commentCount: TextView = itemView.findViewById(R.id.tv_comments_count)

        fun bind(postModel: PostModel) {
            postModel.user.avatarUrl?.let { url ->
                Picasso.get().load(url).into(avatar)
            }

            Picasso.get().load(postModel.photoUrl).into(postPhoto)

            name.text = postModel.user.name
            login.text = postModel.user.login
            description.text = postModel.description
            dislikeCount.text = postModel.dislikeCount
            likeCount.text = postModel.likeCount
            commentCount.text = postModel.commentCount
        }
    }
}
