package and.andrei.pokeworld.view;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }
}