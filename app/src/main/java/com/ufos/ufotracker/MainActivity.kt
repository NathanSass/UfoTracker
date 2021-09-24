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

    companion object {
        val TABS = listOf("STRANGE FLYERS", "MYSTERIOUS LIGHTS")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = TABS[position]
        }.attach()

        Repository.fetchData()
    }
}

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm, lifecycle) {

    private val tab1Types = listOf(UfoType.BLOB, UfoType.LAMPSHADE).map { it.type }
    private val tab2Types = listOf(UfoType.MYSTERIOUS_LIGHTS).map { it.type }
    private val tabTypes = listOf(tab1Types, tab2Types)

    override fun getItemCount(): Int {
        return MainActivity.TABS.size
    }

    override fun createFragment(position: Int): Fragment {
        return UfoModulesFragment.newInstance(tabTypes[position])
    }
}