package and.andrei.pokeworld.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.databinding.FragmentAddpokemonBinding;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.AddPokemonViewModel;

public class AddPokemonFragment extends Fragment {

    private FragmentAddpokemonBinding binding;
    private AddPokemonViewModel addPokemonViewModel;
    private Pokemon placeholderPokemon;

    private EditText name,number,nickname,cp,gender;
    private Button addPokemon, seeLatest;

    private TextView message;

    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        addPokemonViewModel = new ViewModelProvider(getActivity()).get(AddPokemonViewModel.class);
        initializeViews(view);
        placeholderPokemon = new Pokemon();
    }
    private void initializeViews(View view){
        name = view.findViewById(R.id.edit_text_name);
        number = view.findViewById(R.id.edit_text_pokedexNumber);
        nickname = view.findViewById(R.id.edit_text_nickname);
        cp = view.findViewById(R.id.edit_text_CP);
        gender = view.findViewById(R.id.edit_text_gender);
        message=view.findViewById(R.id.text_message);
        addPokemon = view.findViewById(R.id.button_addPokemon);
        addPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name,Nickname;int Number,CP;char Gender;
                Name=name.getText().toString();Nickname=nickname.getText().toString();
                Number=Integer.parseInt(number.getText().toString());CP=Integer.parseInt(cp.getText().toString());
                Gender=gender.getText().charAt(0);
//                placeholderPokemon.setName(name.getText().toString());placeholderPokemon.setPokedexNumber(Integer.parseInt(number.getText().toString()));
//                placeholderPokemon.setNickname(nickname.getText().toString());placeholderPokemon.setCP(Integer.parseInt(cp.getText().toString()));
//                placeholderPokemon.setGender(gender.getText().charAt(0));
                if(Name.isEmpty() ||Nickname.isEmpty()){
                    Toast.makeText(getContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    placeholderPokemon.setName(Name);placeholderPokemon.setNickname(Nickname);
                    placeholderPokemon.setPokedexNumber(Number);placeholderPokemon.setCP(CP);
                    placeholderPokemon.setGender(Gender);
                    addPokemonViewModel.addPokemon(placeholderPokemon);
                    message.setText(addPokemonViewModel.getMessage());
                }
            }
        });
    }
    private void initializeFormSpinner(View view){

    }
    private void initializeUnitSpinner(View view){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addpokemon, container, false);
    }
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        AddPokemonViewModel pokemonViewModel =
//                new ViewModelProvider(this).get(AddPokemonViewModel.class);
//
//        binding = FragmentAddpokemonBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textGallery;
//        pokemonViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}