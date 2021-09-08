package com.onedev.dicoding.superheroapp.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.loadImage
import com.onedev.dicoding.superheroapp.databinding.FragmentDetailBinding
import com.onedev.dicoding.superheroapp.ui.ViewModelFactory

class DetailFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: DetailViewModel
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

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding?.fabFavorite?.setOnClickListener(this)

        populateView()
    }

    private fun populateView() {
        with(args.heroEntity) {
            binding?.apply {
                fabState(args.heroEntity.isFavorite)
                imgHero.loadImage(url)
                tvHeroName.text = name

                val adapter = ViewPagerDetailHeroAdapter(
                    requireFragmentManager(),
                    requireContext(),
                    args.heroEntity.id
                )
                viewPager.adapter = adapter
                tabs.setupWithViewPager(binding?.viewPager)
            }
        }
    }

    private fun fabState(isFavorite: Boolean) {
        if (isFavorite)
            binding?.fabFavorite?.setImageResource(R.drawable.ic_baseline_favorite)
        else
            binding?.fabFavorite?.setImageResource(R.drawable.ic_baseline_favorite_border)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.fabFavorite -> {
                with(!args.heroEntity.isFavorite) {
                    fabState(this)
                    viewModel.updateFavoriteSuperHero(args.heroEntity, this)
                }
            }
        }
    }
}