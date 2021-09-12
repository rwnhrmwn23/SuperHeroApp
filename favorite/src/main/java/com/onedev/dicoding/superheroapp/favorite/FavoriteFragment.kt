package com.onedev.dicoding.superheroapp.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.onedev.dicoding.superheroapp.core.ui.HeroAdapter
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.gone
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.visible
import com.onedev.dicoding.superheroapp.favorite.databinding.FragmentFavoriteBinding
import com.onedev.dicoding.superheroapp.favorite.di.favoriteViewModelModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private lateinit var heroAdapter: HeroAdapter
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteViewModelModule)

        heroAdapter = HeroAdapter()
        binding?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }
        binding?.rvHeroFavorite?.setHasFixedSize(true)
        binding?.rvHeroFavorite?.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding?.rvHeroFavorite?.adapter = heroAdapter

        heroAdapter.onItemClick = { hero ->
            val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetail(hero)
            findNavController().navigate(action)
        }

        favoriteViewModel.getFavoriteSuperHero.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {
                showData(true)
                heroAdapter.setHeroes(response)
            } else {
                showData(false)
            }
        })
    }

    private fun showData(state: Boolean) {
        binding?.apply {
            if (state) {
                rvHeroFavorite.visible()
                layoutError.tvError.gone()
                layoutError.imgError.gone()
            } else {
                rvHeroFavorite.gone()
                layoutError.tvError.visible()
                layoutError.imgError.visible()
                layoutError.tvError.text = getString(R.string.data_not_found)
                layoutError.imgError.setImageResource(R.drawable.ic_no_data)
            }
        }
    }

    override fun onDestroyView() {
        binding?.rvHeroFavorite?.adapter = null
        super.onDestroyView()
        _binding = null
    }

}