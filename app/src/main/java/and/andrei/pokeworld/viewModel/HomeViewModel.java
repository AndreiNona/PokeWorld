package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Pokemon;

public class HomeViewModel extends AndroidViewModel {

    private PokemonRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository= PokemonRepository.getInstance(application);
    }

    public LiveData<List<Pokemon>> getAllPokemon(){
        return repository.getAllPokemons();
    }
}
