package com.codecool.airbnbclone.menu_bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codecool.airbnbclone.R
import com.codecool.airbnbclone.explore.ExploreFragment
import com.codecool.airbnbclone.profile.ProfileFragment
import com.codecool.airbnbclone.saved.SavedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val presenter = MainPresenter()
    private val viewPagerFragment = ViewPagerFragment()

    private val navListener : BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment : Fragment? = null

            when (item.itemId){
                R.id.nav_explore -> selectedFragment = ExploreFragment()
                R.id.nav_saved -> selectedFragment = SavedFragment()
                R.id.nav_profile -> selectedFragment = ProfileFragment()
            }

            selectedFragment?.let {
                supportFragmentManager.beginTransaction().replace(R.id.fragment,
                    it
                ).commit()
            }

            return@OnNavigationItemSelectedListener true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initiate ViewPagerFragment
        val fragmentmanager = supportFragmentManager.beginTransaction()
        fragmentmanager.replace(R.id.fragment,ViewPagerFragment())
        fragmentmanager.commit()

        bottom_navigation.setOnNavigationItemSelectedListener(navListener)

        presenter.onAttach(this)
        //presenter.createSampleData()

    }


}