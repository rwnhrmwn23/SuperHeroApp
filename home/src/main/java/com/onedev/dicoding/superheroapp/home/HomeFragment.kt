package com.onedev.dicoding.superheroapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.onedev.dicoding.superheroapp.core.data.Resource
import com.onedev.dicoding.superheroapp.core.ui.HeroAdapter
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.gone
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.visible
import com.onedev.dicoding.superheroapp.home.databinding.FragmentHomeBinding
import com.onedev.dicoding.superheroapp.home.di.homeViewModelModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var heroAdapter: HeroAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModel()

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

        loadKoinModules(homeViewModelModule)

        if (savedInstanceState != null) {
            performSearch(savedInstanceState.getString(EXTRA_STATE).toString())
        }

        heroAdapter = HeroAdapter()
        binding?.rvHero?.setHasFixedSize(true)
        binding?.rvHero?.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding?.rvHero?.adapter = heroAdapter

        heroAdapter.onItemClick = { hero ->
            val action = HomeFragmentDirections.actionNavHomeToDetailFragment(hero)
            findNavController().navigate(action)
        }

        homeViewModel.getFavoriteSuperHero.observe(viewLifecycleOwner, { response ->
            if (response.isNotEmpty())
                binding?.cartBadge?.text = response.size.toString()
            else
                binding?.cartBadge?.text = getString(R.string.zero)
        })

        binding?.edtSearchHero?.setOnEditorActionListener(OnEditorActionListener { query, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(query.text.toString())
                return@OnEditorActionListener true
            }
            false
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
                    }
                    is Resource.Success -> {
                        if (response.data != null) {
                            showData(2)
                            heroAdapter.setHeroes(response.data)
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

    override fun onClick(v: View?) {
        when (v) {
            binding?.layoutFavorite -> {
                val action = HomeFragmentDirections.actionNavHomeToNavFavorite()
                findNavController().navigate(action)
            }
        }
    }


    override fun onDestroyView() {
        binding?.rvHero?.adapter = null
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_STATE = "extra_state"
    }

}