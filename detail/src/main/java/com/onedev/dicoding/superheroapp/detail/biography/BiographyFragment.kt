package com.onedev.dicoding.superheroapp.detail.biography

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertNullValue
import com.onedev.dicoding.superheroapp.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.detail.databinding.FragmentBiographyBinding
import org.koin.android.viewmodel.ext.android.viewModel

class BiographyFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: FragmentBiographyBinding? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(id: String?) = BiographyFragment().apply {
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
        _binding = FragmentBiographyBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))

        viewModel.getSuperheroById(id).observe(viewLifecycleOwner, {
            with(it) {
                binding?.apply {
                    tvFullName.text = fullName.convertNullValue()
                    tvAlterEgo.text = alterEgos.convertNullValue()
                    tvPlaceOfBirth.text = placeOfBirth.convertNullValue()
                    tvFirstAppearance.text = firstAppearance.convertNullValue()
                    tvPublisher.text = publisher.convertNullValue()
                    tvAlignment.text = alignment.convertNullValue()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}