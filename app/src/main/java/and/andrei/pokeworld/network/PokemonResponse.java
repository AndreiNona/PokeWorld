package and.andrei.pokeworld.network;

import and.andrei.pokeworld.model.Pokemon;

public class PokemonResponse {

    private int id;
    private String name;
    private Sprite sprites;

    public Pokemon getPokemon(){
        return new Pokemon(name,id,sprites.front_default);
    }
    private  class Sprite {
        private String front_default;
    }
}
