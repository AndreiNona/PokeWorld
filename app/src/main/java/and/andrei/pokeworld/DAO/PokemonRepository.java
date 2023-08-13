package and.andrei.pokeworld.DAO;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.model.PokemonAndItem;

public class PokemonRepository implements PokemonDao{

    private final PokemonDao pokemonDao;
    private static  PokemonRepository instance;
    private LiveData<List<Pokemon>> allPokemons;
    private final ExecutorService executorService;

    private PokemonRepository(Application application){
        PokeWorldDataBase dataBase = PokeWorldDataBase.getInstance(application);
        pokemonDao = dataBase.pokemonDao();
        allPokemons = pokemonDao.getAllPokemons();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized PokemonRepository getInstance(Application application){
        if (instance==null){
            instance = new PokemonRepository(application);
        }
        return instance;
    }
    public LiveData<List<Pokemon>> getAllPokemons(){
        return allPokemons;
    }

    @Override
    public PokemonAndItem getPokemonAndItem(long PokeID) {
        return new PokemonAndItem();
    }


    public void insert(Pokemon pokemon){
        executorService.execute(() -> pokemonDao.insert(pokemon));
    }

    @Override
    public void update(Pokemon pokemon) {
        executorService.execute(() -> pokemonDao.update(pokemon));
    }

    @Override
    public void delete(Pokemon pokemon) {
        executorService.execute(() -> pokemonDao.delete(pokemon));
    }
}
