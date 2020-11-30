package com.codecool.airbnbclone.menu_bottom

interface MainContract {

    interface MainPresenter {
        fun onAttach(view: MainView)
        fun onDetach()
        fun createSampleData()
    }

    interface MainView {

    }
}