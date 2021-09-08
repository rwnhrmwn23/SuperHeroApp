package com.onedev.dicoding.superheroapp.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.gone
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.visible
import com.onedev.dicoding.superheroapp.databinding.FragmentFavoriteBinding
import com.onedev.dicoding.superheroapp.ui.ItemClicked
import com.onedev.dicoding.superheroapp.ui.ViewModelFactory

class FavoriteFragment : Fragment(), ItemClicked {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

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
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        favoriteAdapter = FavoriteAdapter(this)

        binding?.toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }
        binding?.rvHeroFavorite?.setHasFixedSize(true)
        binding?.rvHeroFavorite?.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding?.rvHeroFavorite?.adapter = favoriteAdapter

        viewModel.getFavoriteSuperHero.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {
                showData(true)
                favoriteAdapter.setHeroes(response)
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
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(hero: Hero) {
        val action = FavoriteFragmentDirections.actionNavFavoriteToNavDetail(hero)
        findNavController().navigate(action)
    }

}