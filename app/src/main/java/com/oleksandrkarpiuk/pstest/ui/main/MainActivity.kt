package com.oleksandrkarpiuk.pstest.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.viewpager2.widget.ViewPager2
import com.oleksandrkarpiuk.pstest.R
import com.oleksandrkarpiuk.pstest.ui.models.FragmentModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity:
    AppCompatActivity(R.layout.activity_main),
    MainContract.View
{


    private val presenter = MainPresenter(this)
    private lateinit var notificationAdapter: NotificationAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
        initPresenter()
        initButtons()
    }


    private fun initViewPager() = with(viewPager) {
        adapter = NotificationAdapter(mutableListOf()).apply {
            onCreateNotificationListener = { fragment ->
                presenter.onCreateNotificationClicked(fragment)
            }
        }.also {
            notificationAdapter = it
        }

        registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                presenter.onPageChanged(position)

            }
        })
    }


    private fun initPresenter() {

        val fragments = intent.getParcelableArrayListExtra<FragmentModel>("list")
        val currentItem = intent.getIntExtra("item", 1)

        if (fragments != null) {
            presenter.setData(fragments, currentItem)
        } else  {
            presenter.init()
        }
    }


    private fun initButtons() {
        plusBtn.setOnClickListener {
            presenter.onPlusBtnClicked()
        }

        minusBtn.setOnClickListener {
            presenter.onMinusBtnClicked(viewPager.currentItem)

        }
    }


    override fun setFragmentsToAdapter(fragments: ArrayList<FragmentModel>) {
        notificationAdapter.fragments = fragments
    }


    override fun setCurrentItem(position: Int) {
        viewPager.currentItem = position
    }


    override fun setFragmentsNumber(number: String) {
        fragmentsNumberTv.text = number
    }


    override fun hideMinusBtn() {
        minusBtn.visibility = View.INVISIBLE
    }


    override fun showMinusBtn() {
        minusBtn.visibility = View.VISIBLE
    }


    override fun setNotification(fragmentsNumber: Int, fragments: ArrayList<FragmentModel>) {

        val notification = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.blue)
            .setContentTitle("Chat heads active")
            .setContentText("Notification $fragmentsNumber")
            .setContentIntent(createPendingIntent(fragmentsNumber, fragments))
            .build()

        createNotificationManager().notify(fragmentsNumber, notification)
    }


    private fun createNotificationManager(): NotificationManager {
        val notificationManager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "1", "My channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "My channel description"
            notificationManager.createNotificationChannel(channel)
        }

        return notificationManager
    }


    private fun createPendingIntent(fragmentsNumber: Int, fragments: ArrayList<FragmentModel>): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("item", fragmentsNumber)
            putExtra("list", fragments)
        }

        return PendingIntent.getActivity(this, fragmentsNumber, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }


    override fun deleteNotification(fragmentsNumber: Int) {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .cancel(fragmentsNumber)
    }


    override fun insertItem(position: Int) {
        notificationAdapter.notifyItemInserted(position)
    }


    override fun removedItem(position: Int) {
        notificationAdapter.notifyItemRemoved(position)
    }


}