package com.lkorasik.ktistaclient.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.ui.models.PostModel
import de.hdodenhof.circleimageview.CircleImageView

class PostsRecyclerAdapter : RecyclerView.Adapter<PostsRecyclerAdapter.FeedViewHolder>() {

    private var postList: ArrayList<PostModel> = ArrayList()

    fun setItems(posts: List<PostModel>) {
        clearItems()
        postList.addAll(posts)
        notifyDataSetChanged() // Позже придумаю как это заменить
    }

    private fun clearItems() {
        postList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val postView = layoutInflater.inflate(R.layout.widget_post, parent, false)

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
        private val dislikeCount: TextView = itemView.findViewById(R.id.tv_dislike_count)
        private val likeCount: TextView = itemView.findViewById(R.id.tv_like_count)
        private val commentCount: TextView = itemView.findViewById(R.id.tv_comments_count)
        private val date: TextView = itemView.findViewById(R.id.tv_date)
        private val showComments: TextView = itemView.findViewById(R.id.tv_show_comments)

        fun bind(postModel: PostModel) {
            avatar.setImageBitmap(postModel.user.avatar)
            postPhoto.setImageBitmap(postModel.photo)
            name.text = postModel.user.username
            description.text = postModel.description
            dislikeCount.text = if (postModel.dislikeCount == 0) "" else postModel.dislikeCount.toString()
            likeCount.text = if (postModel.likeCount == 0) "" else postModel.likeCount.toString()
            commentCount.text = if (postModel.commentCount == 0) "" else postModel.commentCount.toString()
            date.text = postModel.date

            if (description.text.isEmpty()) {
                description.visibility = View.GONE
            }
            if (commentCount.text.isEmpty()) {
                showComments.visibility = View.GONE

            }
        }
    }
}
