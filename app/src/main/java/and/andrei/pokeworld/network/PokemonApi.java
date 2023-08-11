package and.andrei.pokeworld.network;

import and.andrei.pokeworld.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

public interface PokemonApi {
    @GET("api/v2/pokemon/{name}")
    Call<PokemonResponse> getPokemon(@Part("name") String name);
}
