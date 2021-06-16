package com.azhar.infopendakian.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.azhar.infopendakian.fragment.FragmentPeralatan
import com.azhar.infopendakian.fragment.FragmentTips

/**
 * Created by Azhar Rivaldi on 03-06-2021
 * Youtube Channel : https://bit.ly/2PJMowZ
 * Github : https://github.com/AzharRivaldi
 * Twitter : https://twitter.com/azharrvldi_
 * Instagram : https://www.instagram.com/azhardvls_
 * Linkedin : https://www.linkedin.com/in/azhar-rivaldi
 */

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = null
        when (position) {
            0 -> fragment = FragmentPeralatan()
            1 -> fragment = FragmentTips()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        when (position) {
            0 -> title = "Peralatan"
            1 -> title = "Tips"
        }
        return title
    }
}