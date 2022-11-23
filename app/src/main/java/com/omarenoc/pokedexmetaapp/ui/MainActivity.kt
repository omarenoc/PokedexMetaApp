package com.omarenoc.pokedexmetaapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.omarenoc.apipokemonlibrary.data.PokemonResponse
import com.omarenoc.apipokemonlibrary.remote.PokemonApi
import com.omarenoc.pokedexmetaapp.R
import com.omarenoc.pokedexmetaapp.databinding.ActivityMainBinding
import com.omarenoc.pokedexmetaapp.ui.detailfragment.PokemonDetailFragment
import com.omarenoc.pokedexmetaapp.viewmodel.PokemonViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentDetail = PokemonDetailFragment()
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, fragmentDetail)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}