package com.omarenoc.pokedexmetaapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omarenoc.apipokemonlibrary.data.PokemonResponse
import com.omarenoc.pokedexmetaapp.model.PokemonModelImpl
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel: ViewModel() {
    private val model = PokemonModelImpl()

    private var _pokeInfo = MutableLiveData<PokemonResponse>()
    val pokeInfo: LiveData<PokemonResponse> get() = _pokeInfo

    private var _response = MutableLiveData<Response<PokemonResponse>>()
    val response: LiveData<Response<PokemonResponse>> get() = _response

    var heightText = MutableLiveData<String>()
    var weightText = MutableLiveData<String>()
    var numberMovements = MutableLiveData<String>()

    var hpFormat = ""
    var atkFormat = ""
    var defFormat = ""
    var spAtkFormat = ""
    var spDefFormat = ""
    var spdFormat = ""
    var hpProgress = 0
    var atkProgress = 0
    var defProgress = 0
    var spAtkProgress = 0
    var spDefProgress = 0
    var spdProgress = 0

    fun getInfo(pokemon: String) {
        viewModelScope.launch {
            _response.postValue(model.getPokemonData(pokemon))
        }
    }

    fun chargeInfo() {
        _pokeInfo.postValue(_response.value!!.body())
        getSpecs()
    }

    fun getImageUrl(): String {
        return response.value!!.body()!!.sprites!!.other!!.officialArtwork!!.frontDefault!!
    }

    fun getWeightText() {
        val convertedWeight = (pokeInfo.value?.weight?.toDouble()?.div(100)) ?: ""
        val result = "$convertedWeight Kg"
        weightText.postValue(result)
    }

    fun getHeightText() {
        val convertedHeight = (pokeInfo.value?.height?.toDouble()?.div(10)) ?: ""
        val result = "$convertedHeight m"
        heightText.postValue(result)
    }

    fun getNumberMovements() {
        val result = pokeInfo.value?.moves?.size?.toString() ?: ""
        numberMovements.postValue(result)
    }

    fun getSpecs() {
        getWeightText()
        getHeightText()
        getNumberMovements()
        val stats = response.value!!.body()!!.stats ?: listOf()
        hpFormat = if (stats[0].baseStat!!.toString().length == 2) {
            "0${stats[0].baseStat!!}"
        } else {
            stats[0].baseStat!!.toString()
        }
        hpProgress = (stats[0].baseStat!!.toDouble() / 2.55).toInt()

        atkFormat = if (stats[1].baseStat!!.toString().length == 2) {
            "0${stats[1].baseStat!!}"
        } else {
            stats[1].baseStat!!.toString()
        }
        atkProgress = (stats[1].baseStat!!.toDouble() / 2.55).toInt()

        defFormat = if (stats[2].baseStat!!.toString().length == 2) {
            "0${stats[2].baseStat!!}"
        } else {
            stats[2].baseStat!!.toString()
        }
        defProgress = (stats[2].baseStat!!.toDouble() / 2.55).toInt()

        spAtkFormat = if (stats[3].baseStat!!.toString().length == 2) {
            "0${stats[3].baseStat!!}"
        } else {
            stats[3].baseStat!!.toString()
        }
        spAtkProgress = (stats[3].baseStat!!.toDouble() / 2.55).toInt()

        spDefFormat = if (stats[4].baseStat!!.toString().length == 2) {
            "0${stats[4].baseStat!!}"
        } else {
            stats[4].baseStat!!.toString()
        }
        spDefProgress = (stats[4].baseStat!!.toDouble() / 2.55).toInt()

        spdFormat = if (stats[5].baseStat!!.toString().length == 2) {
            "0${stats[5].baseStat!!}"
        } else {
            stats[5].baseStat!!.toString()
        }
        spdProgress = (stats[5].baseStat!!.toDouble() / 2.55).toInt()
    }
}