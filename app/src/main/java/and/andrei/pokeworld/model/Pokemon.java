package and.andrei.pokeworld.model;

public class Pokemon {
    private long id;
    private String name;
    private int pokedexNumber;
    private String nickname;
    private double CP; //Combat power
    private char gender;

    public Pokemon() {
    }

    public Pokemon(long id, String name, int pokedexNumber, String nickname, double CP, char gender) {
        this.id = id;
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

    public double getCP() {
        return CP;
    }

    public void setCP(double CP) {
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
