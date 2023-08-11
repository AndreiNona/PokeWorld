package and.andrei.pokeworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import and.andrei.pokeworld.R;

import and.andrei.pokeworld.lists.PokemonAdapter;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.AddPokemonViewModel;

public class HomeFragment extends Fragment {



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
        List<Pokemon> pL = new ArrayList<>();
        pL.add(new Pokemon("Eevee",133,"Verstappen",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Perez",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Alonso",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Hamilton",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Leclerc",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Russell",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Sainz",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Norris",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Stroll",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Ocon",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Piastri",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Gasly",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Albon",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Hulkenberg",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Bottas",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Zhou",240,'M'));
        pL.add(new Pokemon("Eevee",133,"Tsunoda",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Magnussen",380,'M'));
        pL.add(new Pokemon("Eevee",133,"Sargeant",240,'M'));
        pL.add(new Pokemon("Eevee",133,"De Vries",420,'M'));
        pL.add(new Pokemon("Eevee",133,"Ricciardo",380,'M'));




        //PokemonAdapter adapter = new PokemonAdapter( addPokemonViewModel.getAllPokemon().getValue());
        PokemonAdapter adapter = new PokemonAdapter(pL);
        recyclerView.setAdapter(adapter);
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