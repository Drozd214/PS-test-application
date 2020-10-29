package com.oleksandrkarpiuk.pstest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.oleksandrkarpiuk.pstest.R
import com.oleksandrkarpiuk.pstest.ui.models.FragmentModel
import kotlinx.android.synthetic.main.item_view_pager.view.*

class NotificationAdapter(
    fragments: List<FragmentModel>
): RecyclerView.Adapter<NotificationHolder>() {


    var fragments = fragments
    var onCreateNotificationListener: ((FragmentModel) -> Unit)? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false))
    }


    override fun getItemCount(): Int {
        return fragments.size
    }


    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.setOnCreateNotificationListener(
            fragments[position],
            onCreateNotificationListener
        )
    }


}


class NotificationHolder(itemView: View): RecyclerView.ViewHolder(itemView) {


    fun setOnCreateNotificationListener(fragment: FragmentModel,
                                        listener: ((FragmentModel) -> Unit)?) {
        itemView.createNotificationBtn.setOnClickListener {
            listener?.invoke(fragment)
        }
    }


}