package com.onedev.dicoding.superheroapp.detail.powerstat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertNullValue
import com.onedev.dicoding.superheroapp.core.utils.ExtHelper.convertToInt
import com.onedev.dicoding.superheroapp.detail.DetailViewModel
import com.onedev.dicoding.superheroapp.detail.databinding.FragmentPowerStatBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PowerStatFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: FragmentPowerStatBinding? = null
    private val binding get() = _binding

    companion object {
        fun newInstance(id: String?) = PowerStatFragment().apply {
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
        _binding = FragmentPowerStatBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = checkNotNull(arguments?.getString("id"))

        viewModel.getSuperheroById(id).observe(viewLifecycleOwner, {
            with(it) {
                binding?.apply {
                    tvIntelligence.text = intelligence.convertNullValue()
                    tvStrength.text = strength.convertNullValue()
                    tvSpeed.text = speed.convertNullValue()
                    tvDurability.text = durability.convertNullValue()
                    tvPower.text = power.convertNullValue()
                    tvCombat.text = combat.convertNullValue()

                    progressBarIntelligence.progress = intelligence.convertToInt()
                    progressBarStrength.progress = strength.convertToInt()
                    progressBarSpeed.progress = speed.convertToInt()
                    progressBarDurability.progress = durability.convertToInt()
                    progressBarPower.progress = power.convertToInt()
                    progressBarCombat.progress = combat.convertToInt()
                }
            }
        })
    }
}