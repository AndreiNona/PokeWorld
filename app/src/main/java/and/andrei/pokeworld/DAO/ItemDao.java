package and.andrei.pokeworld.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;

@Dao
public interface ItemDao {
    @Insert
    void insert(Item item);

    @Update
    void update(Item item);

    @Delete
    void delete(Item item);

    @Query("SELECT * FROM item_table ORDER BY name")
    LiveData<List<Item>> getAllItems();

    @Query("SELECT * FROM item_table WHERE id = :id")
    LiveData<Item> getItemByPokemon(long id);
}
