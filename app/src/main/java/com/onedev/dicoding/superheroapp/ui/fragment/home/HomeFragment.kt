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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onedev.dicoding.superheroapp.R
import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.domain.model.Hero
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.gone
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.visible
import com.onedev.dicoding.superheroapp.databinding.FragmentHomeBinding
import com.onedev.dicoding.superheroapp.ui.ItemClicked
import com.onedev.dicoding.superheroapp.ui.ViewModelFactory
import com.onedev.dicoding.superheroapp.ui.fragment.favorite.FavoriteAdapter
import com.onedev.dicoding.superheroapp.ui.fragment.favorite.FavoriteViewModel

class HomeFragment : Fragment(), ItemClicked, View.OnClickListener {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
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
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
        homeAdapter = HomeAdapter(this)

        if (savedInstanceState != null) {
            performSearch(savedInstanceState.getString(EXTRA_STATE).toString())
        }

        binding?.rvHero?.setHasFixedSize(true)
        binding?.rvHero?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding?.rvHero?.adapter = homeAdapter

        binding?.edtSearchHero?.setOnEditorActionListener(OnEditorActionListener { query, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(query.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        favoriteViewModel.getFavoriteSuperHero.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty()) {
                binding?.cartBadge?.text = response.size.toString()
            } else {
                binding?.cartBadge?.text = getString(R.string.zero)
            }
        })

        binding?.layoutFavorite?.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val heroName = binding?.edtSearchHero?.text.toString().trim()
        if (heroName.isNotEmpty()) {
            outState.putString(EXTRA_STATE, heroName)
        }
    }

    private fun performSearch(heroName: String) {
        homeViewModel.getSuperHeroByName(heroName).observe(viewLifecycleOwner, { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {
                        showData(1)
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        if (response.data?.isNotEmpty() == true) {
                            showData(2)
                            homeAdapter.setHeroes(response.data)
                        } else {
                            showData(3)
                            binding?.layoutError?.imgError?.setImageResource(R.drawable.ic_no_data)
                            binding?.layoutError?.tvError?.text = getString(R.string.data_not_found)
                        }
                    }
                    is Resource.Error -> {
                        showData(3)
                        binding?.layoutError?.imgError?.setImageResource(R.drawable.ic_error)
                        binding?.layoutError?.tvError?.text =
                            response.message ?: getString(R.string.something_wrong)
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
    override fun itemClicked(hero: Hero) {
        val action = HomeFragmentDirections.actionNavHomeToDetailFragment(hero)
        findNavController().navigate(action)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding?.layoutFavorite -> {
                val action = HomeFragmentDirections.actionNavHomeToNavFavorite()
                findNavController().navigate(action)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_STATE = "extra_state"
    }

}