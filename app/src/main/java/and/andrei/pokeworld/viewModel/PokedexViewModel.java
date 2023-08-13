package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Pokemon;

import retrofit2.Call;

public class PokedexViewModel extends AndroidViewModel {


    private PokemonRepository repository;
    private Pokemon currentPokemon;

    public PokedexViewModel(@NonNull Application application) {
        super(application);

        repository= PokemonRepository.getInstance(application);
    }

    public void update(Pokemon pokemon){
        repository.update(pokemon);
    }
    public void delete(Pokemon pokemon){
        repository.delete(pokemon);
    }
}
