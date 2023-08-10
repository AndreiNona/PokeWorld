package and.andrei.pokeworld.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.databinding.FragmentHomeBinding;
import and.andrei.pokeworld.lists.PokemonAdapter;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.AddPokemonViewModel;
import and.andrei.pokeworld.viewModel.ViewPokemonViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    private AddPokemonViewModel addPokemonViewModel;

    RecyclerView recyclerView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        addPokemonViewModel = new ViewModelProvider(getActivity()).get(AddPokemonViewModel.class);
        initializeViews(view);

    }

    private void initializeViews(View view){
        recyclerView=view.findViewById(R.id.home_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();
        //For testing recyclerView
//        List<Pokemon> pL = new ArrayList<>();
//        pL.add(new Pokemon("Andrei",2,"Bob"));
//        pL.add(new Pokemon("Andr3i",23,"B3b"));
//        pL.add(new Pokemon("An2ei",4,"Bo1"));




        PokemonAdapter adapter = new PokemonAdapter( addPokemonViewModel.getAllPokemon().getValue());
//        PokemonAdapter adapter = new PokemonAdapter(pL);
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}