package com.codecool.airbnbclone.menu_bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codecool.airbnbclone.R

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initiate ViewPagerFragment
        val fragmentmanager = supportFragmentManager.beginTransaction()
        fragmentmanager.replace(R.id.fragment,ViewPagerFragment())
        fragmentmanager.commit()

        presenter.onAttach(this)
        //presenter.createSampleData()



    }


}