package com.codecool.airbnbclone

class MainPresenter : MainContract.MainPresenter{

    private var view: MainContract.MainView? = null
    private val dataManager = DataManager()


    override fun onAttach(view: MainContract.MainView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun createSampleData() {
        dataManager.createSampleData()
    }

}
