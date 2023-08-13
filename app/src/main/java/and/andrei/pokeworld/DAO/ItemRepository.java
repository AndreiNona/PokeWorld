package and.andrei.pokeworld.DAO;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import and.andrei.pokeworld.model.Item;

public class ItemRepository implements ItemDao{
    private final ItemDao itemDao;
    private static  ItemRepository instance;
    private LiveData<List<Item>> allItems;
    private Item queryItem;
    private final ExecutorService executorService;
    CountDownLatch latch = new CountDownLatch(2);

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

    @Override
    public Item getItemByPokemon(long id) {
        queryItem=itemDao.getItemByPokemon(id);
        return queryItem;
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
