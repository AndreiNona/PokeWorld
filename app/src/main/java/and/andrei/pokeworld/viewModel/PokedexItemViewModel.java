package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import and.andrei.pokeworld.DAO.ItemRepository;
import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.model.PokemonAndItem;


public class PokedexItemViewModel extends AndroidViewModel {

    private ItemRepository repositoryItem;


    public PokedexItemViewModel(@NonNull Application application) {
        super(application);


        repositoryItem =ItemRepository.getInstance(application);
    }
    public LiveData<List<Item>> getAllItems(){

        return repositoryItem.getAllItems();
    }
    public Item getItemByPokemon (long id) {
        return repositoryItem.getItemByPokemon(id);
    }
    public void changeItem(Item equipped, Item toEquip, long pokemonID){

        if (equipped!=null){
            equipped.setId(0);
            repositoryItem.update(equipped);
        }
        toEquip.setId(pokemonID);
        repositoryItem.update(toEquip);
    }

}
