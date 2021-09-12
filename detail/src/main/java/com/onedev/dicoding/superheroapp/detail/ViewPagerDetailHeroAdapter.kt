package com.onedev.dicoding.superheroapp.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onedev.dicoding.superheroapp.detail.additional.AdditionalFragment
import com.onedev.dicoding.superheroapp.detail.appearence.AppearenceFragment
import com.onedev.dicoding.superheroapp.detail.biography.BiographyFragment
import com.onedev.dicoding.superheroapp.detail.powerstat.PowerStatFragment

class ViewPagerDetailHeroAdapter(activity: AppCompatActivity, private val heroId: String) :
    FragmentStateAdapter(activity) {

    data class Page(val fragment: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page({ BiographyFragment.newInstance(heroId) }),
        Page({ AppearenceFragment.newInstance(heroId) }),
        Page({ PowerStatFragment.newInstance(heroId) }),
        Page({ AdditionalFragment.newInstance(heroId) })
    )

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int) = pages[position].fragment()
}
