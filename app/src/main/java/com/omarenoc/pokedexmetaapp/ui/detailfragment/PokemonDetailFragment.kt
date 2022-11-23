package com.omarenoc.pokedexmetaapp.ui.detailfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.omarenoc.pokedexmetaapp.databinding.FragmentPokemonDetailBinding
import com.omarenoc.pokedexmetaapp.viewmodel.PokemonViewModel

class PokemonDetailFragment : Fragment() {
    private lateinit var mBinding: FragmentPokemonDetailBinding
    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        mBinding.clMain.visibility = View.GONE
        mBinding.lifecycleOwner = this
        mBinding.viewModel = viewModel
        mBinding.heightText = viewModel.heightText.value
        mBinding.weightText = viewModel.weightText.value
        mBinding.numMovements = viewModel.numberMovements.value
        viewModel.getInfo("golbat")
        initObserver()
        return mBinding.root
    }

    private fun initObserver() {
        viewModel.response.observe(this.viewLifecycleOwner) {
            if (it.isSuccessful && it.body() != null) {
                viewModel.chargeInfo()
                mBinding.clMain.visibility = View.VISIBLE
                chargeImage()
                chargeSpecs()
            }
        }
    }

    private fun chargeImage() {
        Glide.with(requireContext()).load(viewModel.getImageUrl()).into(mBinding.ivPokemon)
    }

    private fun chargeSpecs() {
        mBinding.inclHp.apply {
            tvStatValue.text = viewModel.hpFormat
            progressBar.progress = viewModel.hpProgress
        }

        mBinding.inclAtk.apply {
            tvStatValue.text = viewModel.atkFormat
            progressBar.progress = viewModel.atkProgress
        }

        mBinding.inclDef.apply {
            tvStatValue.text = viewModel.defFormat
            progressBar.progress = viewModel.defProgress
        }

        mBinding.inclSatk.apply {
            tvStatValue.text = viewModel.spAtkFormat
            progressBar.progress = viewModel.spAtkProgress
        }

        mBinding.inclSdef.apply {
            tvStatValue.text = viewModel.spDefFormat
            progressBar.progress = viewModel.spDefProgress
        }

        mBinding.inclSpd.apply {
            tvStatValue.text = viewModel.spdFormat
            progressBar.progress = viewModel.spdProgress
        }
    }



}