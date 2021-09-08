package com.onedev.dicoding.superheroapp.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.data.source.local.entity.HeroEntity
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.gone
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.showToast
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.visible
import com.onedev.dicoding.superheroapp.databinding.FragmentHomeBinding
import com.onedev.dicoding.superheroapp.ui.ViewModelFactory
import com.onedev.dicoding.superheroapp.ui.fragment.favorite.FavoriteAdapter


class HomeFragment : Fragment(), HomeAdapter.ItemClicked, FavoriteAdapter.ItemClicked {

    private lateinit var adapter: HomeAdapter
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        adapter = HomeAdapter(this)
        favoriteAdapter = FavoriteAdapter(this)

        if (savedInstanceState != null) {
            performSearch(savedInstanceState.getString(EXTRA_STATE).toString())
        }

        binding?.rvHero?.setHasFixedSize(true)
        binding?.rvHero?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding?.rvHero?.adapter = adapter

        binding?.edtSearchHero?.setOnEditorActionListener(OnEditorActionListener { query, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(query.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        binding?.rvHeroFavorite?.setHasFixedSize(true)
        binding?.rvHeroFavorite?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding?.rvHeroFavorite?.adapter = favoriteAdapter

        loadFavoriteHero()
    }

    private fun loadFavoriteHero() {
        viewModel.getFavoriteSuperHero().observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {
                favoriteAdapter.setHeroes(response)
                binding?.apply {
                    rvHeroFavorite.visible()
                    tvHeroFavorite.visible()
                }
            } else {
                binding?.apply {
                    rvHeroFavorite.gone()
                    tvHeroFavorite.gone()
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val heroName = binding?.edtSearchHero?.text.toString().trim()
        if (heroName.isNotEmpty()) {
            outState.putString(EXTRA_STATE, heroName)
        }
    }

    private fun performSearch(heroName: String) {
        viewModel.getSuperHeroByName(heroName).observe(viewLifecycleOwner, { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {
                        showData(1)
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        if (response.data?.isNotEmpty() == true) {
                            showData(2)
                            adapter.setHeroes(response.data)
                        } else {
                            showData(3)
                            binding?.layoutError?.imgError?.setImageResource(R.drawable.ic_no_data)
                            binding?.layoutError?.tvError?.text = getString(R.string.data_not_found)
                        }
                    }
                    is Resource.Error -> {
                        showData(3)
                        binding?.layoutError?.imgError?.setImageResource(R.drawable.ic_error)
                        binding?.layoutError?.tvError?.text = response.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    private fun showData(position: Int) {
        when (position) {
            1 -> {
                binding?.rvHero?.gone()
                binding?.progressBar?.visible()
                binding?.layoutError?.tvError?.gone()
                binding?.layoutError?.imgError?.gone()
            }
            2 -> {
                binding?.rvHero?.visible()
                binding?.progressBar?.gone()
                binding?.layoutError?.tvError?.gone()
                binding?.layoutError?.imgError?.gone()
            }
            3 -> {
                binding?.rvHero?.gone()
                binding?.progressBar?.gone()
                binding?.layoutError?.tvError?.visible()
                binding?.layoutError?.imgError?.visible()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(hero: HeroEntity) {
        val action = HomeFragmentDirections.actionNavHomeToDetailFragment(hero)
        findNavController().navigate(action)
    }

    companion object {
        private const val EXTRA_STATE = "extra_state"
    }

}