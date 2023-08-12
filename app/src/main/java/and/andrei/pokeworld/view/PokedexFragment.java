package and.andrei.pokeworld.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.PokedexViewModel;


public class PokedexFragment extends Fragment {
    
    Pokemon currentPokemon;

    TextView response;

    EditText name,nickname,pokedexNo,cp,gender;
    Button delete, edit;

    private PokedexViewModel pokedexViewModel;

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        pokedexViewModel = new ViewModelProvider(getActivity()).get(PokedexViewModel.class);

        if(getArguments()!= null){
            Bundle bundle =getArguments();
            currentPokemon = (Pokemon) bundle.getSerializable("pokemon");
        }

        initializeViews(view);


    }
    private void initializeViews(View view){

        response = view.findViewById(R.id.text_response);

        //Edit text
        name = view.findViewById(R.id.edit_text_pokedex_name);
        nickname = view.findViewById(R.id.edit_text_pokedex_nickname);
        pokedexNo = view.findViewById(R.id.edit_text_pokedex_pokedexno);
        cp = view.findViewById(R.id.edit_text_pokedex_cp);
        gender= view.findViewById(R.id.edit_text_pokedex_gender);

        //Buttons
        delete = view.findViewById(R.id.button_pokedex_release);
        edit = view.findViewById(R.id.button_pokedex_edit);

        if(currentPokemon != null){
            name.setText(currentPokemon.getName());
            nickname.setText(currentPokemon.getNickname());
            gender.setText(String.valueOf(currentPokemon.getGender()));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }
}