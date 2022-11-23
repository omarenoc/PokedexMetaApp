package com.omarenoc.pokedexmetaapp.model

import com.omarenoc.apipokemonlibrary.data.PokemonResponse
import com.omarenoc.apipokemonlibrary.remote.PokemonApi
import retrofit2.Response

class PokemonModelImpl {
    suspend fun getPokemonData(pokemon: String): Response<PokemonResponse> {
        return PokemonApi.retrofitService.getPokemon(pokemon)
    }
}