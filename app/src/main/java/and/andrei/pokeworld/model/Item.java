package and.andrei.pokeworld.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "item_table",foreignKeys = {@ForeignKey(entity = Pokemon.class,
        parentColumns = "id",
        childColumns = "itemID",

        onDelete = ForeignKey.CASCADE)
})
public class Item implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long itemID;
    private long id;
    private String name;
    private String description;
    @Ignore
    public Item() {
    }

    public Item(long itemID, String name, String description) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item pokemon = (Item) o;
        return pokemon.itemID == (this.itemID);
    }
}
