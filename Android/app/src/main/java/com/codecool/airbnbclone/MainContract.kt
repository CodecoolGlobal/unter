package com.codecool.airbnbclone

interface MainContract {

    interface MainPresenter {
        fun onAttach(view:MainContract.MainView)
        fun onDetach()
        fun createSampleData()
    }

    interface MainView {

    }
}