package com.oleksandrkarpiuk.pstest.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oleksandrkarpiuk.pstest.R
import kotlinx.android.synthetic.main.item_view_pager.*

class NotificationFragment:
    Fragment(),
    NotificationContract.View
{


    private val presenter = NotificationPresenter(this)




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_view_pager,container, false)
    }


}