package com.lkorasik.ktistaclient.ui.follow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.WidgetFollowerBinding
import com.lkorasik.ktistaclient.ui.models.FollowModel
import com.squareup.picasso.Picasso


class FollowRecyclerAdapter : RecyclerView.Adapter<FollowRecyclerAdapter.FollowingViewHolder>() {

    private var listItems: ArrayList<FollowModel> = ArrayList()

    fun setItems(items: ArrayList<FollowModel>) {
        clearItems()
        listItems.addAll(items)
        notifyDataSetChanged()  //Позже придумаю как написать нормально
    }

    private fun clearItems() {
        listItems.clear()
    }

    override fun getItemCount(): Int = listItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val binding =
            WidgetFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FollowingViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(listItems[position])
    }


    class FollowingViewHolder(var binding: WidgetFollowerBinding) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: FollowModel) {
            with(binding) {
                model.user.avatarUrl?.let { url ->
                    Picasso.get().load(url).into(includeUser.ciPostAvatar)
                }

                includeUser.tvUserName.text = model.user.name
                includeUser.tvLogin.text = model.user.login

                if (model.isFollow) {
                    btFollow.setText(R.string.unfollow)
                    btFollow.setBackgroundResource(R.drawable.button_secondary)
                    btFollow.setTextAppearance(R.style.SecondaryButton)
                } else {
                    btFollow.setText(R.string.follow)
                    btFollow.setBackgroundResource(R.drawable.button_primary)
                    btFollow.setTextAppearance(R.style.PrimaryButton)
                }
            }
        }
    }
}