package com.onedev.dicoding.superheroapp.detail.appearence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertNullValue
import com.onedev.dicoding.superheroapp.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.detail.databinding.FragmentAppearanceBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AppearenceFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
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