package com.onedev.dicoding.superheroapp.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage
import com.onedev.dicoding.superheroapp.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

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


        populateView()
    }

    private fun populateView() {
        var statusFavorite = false
        with(args.heroEntity) {
            binding?.apply {
                val adapter = ViewPagerDetailHeroAdapter(requireFragmentManager(), requireContext(), id)
                viewPager.adapter = adapter
                tabs.setupWithViewPager(binding?.viewPager)

                viewModel.getSuperheroById(id).observe(viewLifecycleOwner, { hero ->
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
}