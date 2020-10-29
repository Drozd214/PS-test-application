package com.oleksandrkarpiuk.pstest.ui.main

import com.oleksandrkarpiuk.pstest.ui.models.FragmentModel

class MainPresenter(
    private val view: MainContract.View
): MainContract.ActionListener {


    private lateinit var fragments: ArrayList<FragmentModel>




    override fun init() {
        fragments = arrayListOf()
        fragments.add(FragmentModel(1))

        view.setFragmentsToAdapter(fragments)
        view.setCurrentItem(1)
    }


    override fun setData(fragments: ArrayList<FragmentModel>, currentItem: Int) {
        this.fragments = fragments

        view.setFragmentsToAdapter(fragments)
        view.setCurrentItem(fragments.indexOf(fragments.find { it.number == currentItem }))
        setMinusBtnVisible()
    }


    override fun onPlusBtnClicked() {
        val newNumber = fragments.last().number + 1
        fragments.add(FragmentModel(newNumber))

        with(view) {
            setFragmentsToAdapter(fragments)
            insertItem(fragments.size)
            setCurrentItem(fragments.size)
        }
        setMinusBtnVisible()
    }


    override fun onMinusBtnClicked(currentItem: Int) {
        var index = currentItem
        view.deleteNotification(fragments[index].number)
        fragments.removeAt(index)

        if(index == fragments.size) { index -= 1 }

        with(view) {
            setFragmentsToAdapter(fragments)
            removedItem(index)
            setFragmentsNumber(fragments[index].number.toString())
        }
        setMinusBtnVisible()
    }


    override fun onPageChanged(newPosition: Int) {
        view.setFragmentsNumber(fragments[newPosition].number.toString())
    }


    override fun onCreateNotificationClicked(fragment: FragmentModel) {
        view.setNotification(fragment.number, fragments)
    }


    private fun setMinusBtnVisible() {
        if(fragments.size > 1) {
            view.showMinusBtn()
        } else {
            view.hideMinusBtn()
        }
    }


}