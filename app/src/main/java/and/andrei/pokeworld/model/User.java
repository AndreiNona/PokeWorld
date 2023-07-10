package and.andrei.pokeworld.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String name;//Might not be used
    private String username;
    private String email;
    private String password;
    private List<Pokemon> pokemons;
    private List<User> friends;
    private boolean isRestricted; //Used for private accounts

    //Empty constructor with initialized array lists to avoid checking for initialized lists
    public User() {
        pokemons = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public User(long id, String name, String username, String email, String password, List<Pokemon> pokemons, List<User> friends, boolean isRestricted) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.pokemons = pokemons;
        this.friends = friends;
        this.isRestricted = isRestricted;
    }
    //Constructor without lists
    public User(long id, String name, String username, String email, String password, boolean isRestricted) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isRestricted = isRestricted;
        pokemons = new ArrayList<>();
        friends = new ArrayList<>();
    }
}
