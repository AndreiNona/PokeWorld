package and.andrei.pokeworld.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;


//Not used but: Possible database implication for deleting
public class PokemonAndItem {
    @Embedded public Pokemon pokemon;
    @Relation(
            parentColumn = "id",
            entityColumn = "itemID"
    )
    public Item item;

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
