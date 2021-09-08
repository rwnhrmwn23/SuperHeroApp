package com.onedev.dicoding.superheroapp.ui.fragment.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.ui.fragment.detail.additional.AdditionalFragment
import com.onedev.dicoding.superheroapp.ui.fragment.detail.appearence.AppearenceFragment
import com.onedev.dicoding.superheroapp.ui.fragment.detail.biography.BiographyFragment
import com.onedev.dicoding.superheroapp.ui.fragment.detail.powerstat.PowerStatFragment

class ViewPagerDetailHeroAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val heroId: String
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
        Page(
            context.getString(R.string.biography),
            { BiographyFragment.newInstance(heroId) }
        ),
        Page(
            context.getString(R.string.appearance),
            { AppearenceFragment.newInstance(heroId) }
        ),
        Page(
            context.getString(R.string.powerstat),
            { PowerStatFragment.newInstance(heroId) }
        ),
        Page(
            context.getString(R.string.additional),
            { AdditionalFragment.newInstance(heroId) }
        )
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pages[position].title
    }
}
