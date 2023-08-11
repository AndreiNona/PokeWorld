package and.andrei.pokeworld.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import and.andrei.pokeworld.DAO.ItemRepository;
import and.andrei.pokeworld.DAO.PokemonRepository;
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;

public class AddItemViewModel extends AndroidViewModel {

    private String message;
    private final MutableLiveData<String> mText;
    private ItemRepository repository;

    public AddItemViewModel(@NonNull Application application) {
        super(application);
        repository= ItemRepository.getInstance(application);
        mText = new MutableLiveData<>();
        mText.setValue("Add Item");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public String getMessage(){
        return message;
    }
    public void addItem(Item item){
        repository.insert(item);
        message="Added:"+item.getName();
    }
    public LiveData<List<Item>> getAllPokemon(){
        return repository.getAllItems();
    }
}
