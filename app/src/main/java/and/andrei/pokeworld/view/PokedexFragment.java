package and.andrei.pokeworld.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.viewModel.PokedexViewModel;


public class PokedexFragment extends Fragment {

TextView response;

    private PokedexViewModel pokedexViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        pokedexViewModel = new ViewModelProvider(getActivity()).get(PokedexViewModel.class);
        initializeViews(view);


    }

    private void initializeViews(View view){
        response = view.findViewById(R.id.text_response);

    }
}