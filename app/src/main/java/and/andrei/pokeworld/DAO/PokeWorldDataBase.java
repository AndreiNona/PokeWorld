package and.andrei.pokeworld.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;

@Database(entities = {Pokemon.class, Item.class},version = 6, exportSchema = false)
public abstract class PokeWorldDataBase extends RoomDatabase {

    private static PokeWorldDataBase instance;
    public abstract PokemonDao pokemonDao();
    public abstract ItemDao itemDao();

    public static synchronized PokeWorldDataBase getInstance(Context context){
        if (instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext(),PokeWorldDataBase.class,"PokeWorld_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
