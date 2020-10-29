package com.oleksandrkarpiuk.pstest.ui.main

import com.oleksandrkarpiuk.pstest.ui.models.FragmentModel

interface MainContract {

    interface View {

        fun setFragmentsToAdapter(fragments: ArrayList<FragmentModel>)

        fun setCurrentItem(position: Int)

        fun setFragmentsNumber(number: String)

        fun hideMinusBtn()

        fun showMinusBtn()

        fun setNotification(fragmentsNumber: Int, fragments: ArrayList<FragmentModel>)

        fun deleteNotification(fragmentsNumber: Int)

        fun insertItem(position: Int)

        fun removedItem(position: Int)
    }


    interface ActionListener {

        fun init()

        fun setData(fragments: ArrayList<FragmentModel>, currentItem: Int)

        fun onPageChanged(newPosition: Int)

        fun onPlusBtnClicked()

        fun onMinusBtnClicked(currentItem: Int)

        fun onCreateNotificationClicked(fragment: FragmentModel)
    }

}