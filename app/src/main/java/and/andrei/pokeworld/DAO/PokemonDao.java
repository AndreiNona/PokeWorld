package and.andrei.pokeworld.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.model.PokemonAndItem;

@Dao
public interface PokemonDao {

    @Insert
    void insert(Pokemon pokemon);


    @Update
    void update(Pokemon pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Query("SELECT * FROM pokemon_table ORDER BY pokedexNumber,name")
    LiveData<List<Pokemon>> getAllPokemons();

    @Transaction
    @Query("SELECT * FROM pokemon_table WHERE id = :PokeID")
    public PokemonAndItem getPokemonAndItem(long PokeID);
}
