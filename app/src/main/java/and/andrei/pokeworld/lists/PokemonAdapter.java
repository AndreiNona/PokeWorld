package and.andrei.pokeworld.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;
    Button pokemonViewButton;
    private OnClickListener listener;

    public PokemonAdapter(List<Pokemon> pokemons, OnClickListener onClickListener){
        this.pokemons=pokemons;
        this.listener = onClickListener;
    }

    public void setPokemons(List<Pokemon> pokemons){
        this.pokemons=pokemons;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.pokemon_item,parent,false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {


        holder.pokemonName.setText(pokemons.get(position).getName());
        holder.pokemonNickName.setText(pokemons.get(position).getNickname());
        holder.pokemonPokedexNo.setText(String.valueOf(pokemons.get(position).getPokedexNumber()));
        holder.pokemonCombatPower.setText(String.valueOf(pokemons.get(position).getCP()));

    }

    @Override
    public int getItemCount() {
        if(pokemons!=null)
            return pokemons.size();
        else
            return 0;
    }
    class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView pokemonName;
        TextView pokemonNickName;
        TextView pokemonPokedexNo;
        TextView pokemonCombatPower;
        PokemonViewHolder(View pokeView){
            super(pokeView);
            pokemonName = pokeView.findViewById(R.id.text_pokemon_card_name);
            pokemonPokedexNo = pokeView.findViewById(R.id.text_pokemon_card_pokedex_number);
            pokemonNickName =pokeView.findViewById(R.id.text_pokemon_card_nickname);
            pokemonCombatPower = pokeView.findViewById(R.id.text_pokemon_card_combat_power);
            pokemonViewButton =pokeView.findViewById(R.id.pokemon_action_button);
            pokemonViewButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(view.getId() == pokemonViewButton.getId()){
                listener.onClick(pokemons.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }
    public interface OnClickListener{
        void onClick(Pokemon pokemon);
        void onButtonClick(Pokemon pokemon);
    }
}
