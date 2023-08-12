package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Pokemon;

public class AddPokemonViewModel extends AndroidViewModel {
    private String message;
    private final MutableLiveData<String> mText;
    private PokemonRepository repository;

    public AddPokemonViewModel(@NonNull Application application) {
        super(application);
        repository= PokemonRepository.getInstance(application);
        mText = new MutableLiveData<>();
        mText.setValue("Add pokemons");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public String getMessage(){
        return message;
    }

    public void addPokemon(Pokemon pokemon){
        repository.insert(pokemon);
        message="Added:"+pokemon.getName();
    }
    public LiveData<List<Pokemon>> getAllPokemon(){
        return repository.getAllPokemons();
    }

}
