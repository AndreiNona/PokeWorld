package and.andrei.pokeworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.lists.ItemAdapter;
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.PokedexItemViewModel;

public class PokedexItemFragment extends Fragment {

    Pokemon currentPokemon;
    Item currentItem;

    private PokedexItemViewModel viewModel;

    TextView pokeName, itemName;

    ItemAdapter adapter;
    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(PokedexItemViewModel.class);

        if(getArguments()!= null){
            Bundle bundle =getArguments();
            currentPokemon = (Pokemon) bundle.getSerializable("pokemon");
        }
        try {
            currentItem =viewModel.getItemByPokemon(currentPokemon.getId());
        }catch (Exception e){
            currentItem=null;
        }

        initializeViews(view);
        initializeList();

    }

    private void initializeViews(View view){
        recyclerView=view.findViewById(R.id.pokedex_recyclerView);
        pokeName= view.findViewById(R.id.text_pokedex_poke_name);
        itemName = view.findViewById(R.id.text_pokedex_item_name);

        pokeName.setText(currentPokemon.getName());
        try {
            itemName.setText(currentItem.getName());
        }catch (Exception e){
            itemName.setText("No item");
        }

    }

    private void initializeList(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new ItemAdapter( viewModel.getAllItems().getValue(), new ItemAdapter.OnClickListener(){
            @Override
            public void onClick(Item item) {
                if (item.equals(currentItem))
                    Toast.makeText(getContext(), currentPokemon.getName()+" already has this item", Toast.LENGTH_SHORT).show();
                else{
                    viewModel.changeItem(currentItem,item,currentPokemon.getId());
                    currentItem=item;
                    itemName.setText(currentItem.getName());
                    Toast.makeText(getContext(), currentItem.getName()+" equipped", Toast.LENGTH_SHORT).show();
                }

            }
        });
        recyclerView.setAdapter(adapter);
        viewModel.getAllItems().observe(getViewLifecycleOwner(),items -> {
            adapter.setItems(items);
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_pokedex_itemselect, container, false);
    }
}
