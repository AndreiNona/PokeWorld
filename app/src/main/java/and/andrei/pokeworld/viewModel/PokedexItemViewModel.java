package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import and.andrei.pokeworld.DAO.ItemRepository;
import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.model.PokemonAndItem;
import and.andrei.pokeworld.network.PokemonApi;
import and.andrei.pokeworld.network.ServiceGenerator;

public class PokedexItemViewModel extends AndroidViewModel {

    private PokemonApi pokemonApi;
    private PokemonRepository repositoryPokemon;
    private ItemRepository repositoryItem;
    private Pokemon currentPokemon;
    private Item currentItem;

    public PokedexItemViewModel(@NonNull Application application) {
        super(application);
        pokemonApi= ServiceGenerator.getPokemonApi();
        repositoryPokemon= PokemonRepository.getInstance(application);
        repositoryItem =ItemRepository.getInstance(application);
    }
    public LiveData<List<Item>> getAllItems(){

        return repositoryItem.getAllItems();
    }
    public LiveData<Item>  getItemByPokemon (long id){

        return repositoryItem.getItemByPokemon(id);
    }

}
