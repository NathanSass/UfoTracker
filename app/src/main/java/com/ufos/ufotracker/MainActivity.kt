package com.ufos.ufotracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ufos.ufotracker.databinding.ActivityMainBinding
import com.ufos.ufotracker.ui.main.UfoModulesFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = TABS_TITLES[position]
        }.attach()

        Repository.fetchData()
    }
}

val TABS_TITLES = listOf("STRANGE FLYERS", "MYSTERIOUS LIGHTS")
val TAB_1_TYPES = listOf(UfoType.BLOB, UfoType.LAMPSHADE).map { it.type }
val TAB_2_TYPES = listOf(UfoType.MYSTERIOUS_LIGHTS).map { it.type }
val TAB_TYPES = listOf(TAB_1_TYPES, TAB_2_TYPES)

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return TABS_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return UfoModulesFragment.newInstance(TAB_TYPES[position])
    }
}