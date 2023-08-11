package and.andrei.pokeworld.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import and.andrei.pokeworld.model.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class ServiceGenerator {
    public MutableLiveData<Pokemon> searchedPokemon;
    private static PokemonApi pokemonApi;

    public static PokemonApi getPokemonApi() {
        if (pokemonApi == null) {
            pokemonApi = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PokemonApi.class);
        }
        return pokemonApi;
    }

    public void searchForPokemon(String pokemonName) {
        PokemonApi pokemonApi = ServiceGenerator.getPokemonApi();
        Call<PokemonResponse> call = pokemonApi.getPokemon(pokemonName);
        call.enqueue(new Callback<PokemonResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    searchedPokemon.setValue(response.body().getPokemon());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }


}
