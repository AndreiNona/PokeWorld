package and.andrei.pokeworld.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pokemon_table")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int pokedexNumber;
    private String nickname;
    private int CP; //Combat power
    private char gender;

    @Ignore
    public Pokemon() {
    }
    @Ignore
    public Pokemon(String name, int pokedexNumber, String nickname) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.nickname = nickname;
        //this.CP = CP;
        //this.gender = gender;
    }

    public Pokemon( String name, int pokedexNumber, String nickname, int CP, char gender) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.nickname = nickname;
        this.CP = CP;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    //Should not need
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPokedexNumber() {
        return pokedexNumber;
    }
    //Use in case of evolutions
    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pokedexNumber=" + pokedexNumber +
                ", nickname='" + nickname + '\'' +
                ", CP=" + CP +
                ", gender=" + gender +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemon.id == (this.id);
    }
}
