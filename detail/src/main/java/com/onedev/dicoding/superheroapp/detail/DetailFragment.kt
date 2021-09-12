package com.onedev.dicoding.superheroapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage
import com.onedev.dicoding.superheroapp.detail.databinding.FragmentDetailBinding
import com.onedev.dicoding.superheroapp.detail.di.detailViewModelModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class DetailFragment : Fragment() {
    private var mediator: TabLayoutMediator? = null
    private val viewModel: DetailViewModel by viewModel()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(detailViewModelModule)

        populateView()
    }

    private fun populateView() {
        var statusFavorite = false
        args.heroEntity.let {
            binding?.apply {

                val viewPagerDetailHeroAdapter = ViewPagerDetailHeroAdapter(activity as AppCompatActivity, args.heroEntity.id)
                viewPager.adapter = viewPagerDetailHeroAdapter

                mediator = TabLayoutMediator(
                    tabs, viewPager
                ) { tab, position -> tab.text = resources.getString(TAB_TITLES[position]) }
                mediator?.attach()

                viewModel.getSuperheroById(it.id).observe(viewLifecycleOwner, { hero ->
                    statusFavorite = hero.isFavorite
                    fabState(statusFavorite)

                    imgHero.loadImage(hero.url)
                    tvHeroName.text = hero.name
                })

                fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    fabState(statusFavorite)
                    viewModel.updateFavoriteSuperHero(args.heroEntity, statusFavorite)
                }
            }
        }
    }

    private fun fabState(isFavorite: Boolean) {
        if (isFavorite)
            binding?.fabFavorite?.setImageResource(R.drawable.ic_baseline_favorite)
        else
            binding?.fabFavorite?.setImageResource(R.drawable.ic_baseline_favorite_border)
    }

    override fun onDestroyView() {
        mediator?.detach()
        mediator = null
        binding?.viewPager?.adapter = null
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.biography,
            R.string.appearance,
            R.string.powerstat,
            R.string.additional
        )
    }
}