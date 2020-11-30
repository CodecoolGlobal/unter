package com.codecool.airbnbclone.menu_bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codecool.airbnbclone.explore.ExploreFragment
import com.codecool.airbnbclone.profile.ProfileFragment
import com.codecool.airbnbclone.R
import com.codecool.airbnbclone.saved.SavedFragment
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            ExploreFragment(),
            SavedFragment(),
            ProfileFragment()
        )

        val myAdapter = MainPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = myAdapter

        return view
    }

}