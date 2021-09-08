package com.onedev.dicoding.superheroapp.ui.fragment.detail.appearence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertNullValue
import com.onedev.dicoding.superheroapp.databinding.FragmentAppearanceBinding
import com.onedev.dicoding.superheroapp.ui.ViewModelFactory
import com.onedev.dicoding.superheroapp.ui.fragment.detail.DetailViewModel

class AppearenceFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var _binding: FragmentAppearanceBinding? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(id: String?) = AppearenceFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppearanceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        viewModel.getSuperheroById(id).observe(viewLifecycleOwner, {
            with(it) {
                binding?.apply {
                    tvGender.text = gender.convertNullValue()
                    tvRace.text = race.convertNullValue()
                    tvHeight.text = height.convertNullValue()
                    tvWeight.text = weight.convertNullValue()
                    tvEyeColor.text = eyeColor.convertNullValue()
                    tvHairColor.text = hairColor.convertNullValue()
                }
            }
        })
    }
}