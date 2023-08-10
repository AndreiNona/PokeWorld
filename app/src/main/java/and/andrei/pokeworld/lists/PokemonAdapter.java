package and.andrei.pokeworld.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemons;
    private View.OnClickListener listener;

    public PokemonAdapter(List<Pokemon> pokemons){
        this.pokemons=pokemons;
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

    }

    @Override
    public int getItemCount() {
        if(pokemons!=null)
            return pokemons.size();
        else
            return 0;
    }
    class PokemonViewHolder extends RecyclerView.ViewHolder{

        TextView pokemonName;
        PokemonViewHolder(View pokeView){
            super(pokeView);
            pokemonName = pokeView.findViewById(R.id.text_pokemon_name);
        }

    }
}
