package com.onedev.dicoding.superheroapp.detail.additional

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertNullValue
import com.onedev.dicoding.superheroapp.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.detail.databinding.FragmentAdditionalBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AdditionalFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: FragmentAdditionalBinding? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(id: String?) = AdditionalFragment().apply {
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
        _binding = FragmentAdditionalBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = checkNotNull(arguments?.getString("id"))

        viewModel.getSuperheroById(id).observe(viewLifecycleOwner, {
            with(it) {
                binding?.apply {
                    tvOccupation.text = occupation.convertNullValue()
                    tvBase.text = base.convertNullValue()
                    tvGroupAffiliation.text = groupAffiliation.convertNullValue()
                    tvRelatives.text = relatives.convertNullValue()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}