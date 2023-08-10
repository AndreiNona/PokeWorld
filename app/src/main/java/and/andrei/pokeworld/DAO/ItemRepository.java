package and.andrei.pokeworld.DAO;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import and.andrei.pokeworld.model.Item;

public class ItemRepository implements ItemDao{
    private final ItemDao itemDao;
    private static  ItemRepository instance;
    private LiveData<List<Item>> allItems;
    private final ExecutorService executorService;

    private ItemRepository(Application application){
        PokeWorldDataBase dataBase = PokeWorldDataBase.getInstance(application);
        itemDao = dataBase.itemDao();
        allItems = itemDao.getAllItems();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized ItemRepository getInstance(Application application){
        if (instance==null){
            instance = new ItemRepository(application);
        }
        return instance;
    }
    public LiveData<List<Item>> getAllItems(){
        return allItems;
    }

    public void insert(Item item){
        executorService.execute(() -> itemDao.insert(item));
    }

    @Override
    public void update(Item item) {
        executorService.execute(() -> itemDao.update(item));
    }

    @Override
    public void delete(Item item) {
        executorService.execute(() -> itemDao.delete(item));
    }
}
