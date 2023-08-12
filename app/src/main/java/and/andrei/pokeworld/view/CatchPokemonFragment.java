package and.andrei.pokeworld.view;

import static java.lang.Character.toUpperCase;

import android.os.Bundle;
import android.text.Html;
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
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.AddPokemonViewModel;

public class CatchPokemonFragment extends Fragment {


    private AddPokemonViewModel addPokemonViewModel;
    private Pokemon placeholderPokemon;

    private EditText name,number,nickname,cp,gender;
    private Button addPokemon, seeLatest;

    private TextView message;

    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        addPokemonViewModel = new ViewModelProvider(getActivity()).get(AddPokemonViewModel.class);
        placeholderPokemon = new Pokemon();
        initializeViews(view);

    }
    private void initializeViews(View view){
        name = view.findViewById(R.id.edit_text_catch_name);
        number = view.findViewById(R.id.edit_text_catch_pokedexNumber);
        nickname = view.findViewById(R.id.edit_text_catch_nickname);
        cp = view.findViewById(R.id.edit_text_catch_CP);
        gender = view.findViewById(R.id.edit_text_catch_gender);
        message=view.findViewById(R.id.text_message);
        addPokemon = view.findViewById(R.id.button_addPokemon);
        addPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name,Nickname;int Number,CP;char Gender;
                try {
                    Name=name.getText().toString();Nickname=nickname.getText().toString();
                    Number=Integer.parseInt(number.getText().toString());CP=Integer.parseInt(cp.getText().toString());
                    Gender=toUpperCase(gender.getText().charAt(0));
                    if(Name.isEmpty())
                        Toast.makeText(getContext(), "Your buddy needs a name!", Toast.LENGTH_SHORT).show();
                    else
                        if(Gender == 'F' || Gender =='M' || Gender =='N'){
                            //If we have no nickname we use the name for it
                            if(Nickname.isEmpty()){
                                placeholderPokemon.setName(Name);placeholderPokemon.setNickname(Name);
                            } else{
                                placeholderPokemon.setName(Name);placeholderPokemon.setNickname(Nickname);
                            }
                            placeholderPokemon.setPokedexNumber(Number);placeholderPokemon.setCP(CP);
                            placeholderPokemon.setGender(Gender);
                            addPokemonViewModel.addPokemon(placeholderPokemon);
                            message.setText(addPokemonViewModel.getMessage());
                            //IF everything goes well the action ends here!
                        }
                        else
                            Toast.makeText(getContext(), "Please tell us your buddy's gender", Toast.LENGTH_SHORT).show();

                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Please fill in all the required fields!", Toast.LENGTH_SHORT).show();
                }catch (IndexOutOfBoundsException e){
                    Toast.makeText(getContext(), "Your buddy needs a gender", Toast.LENGTH_SHORT).show();
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
        return inflater.inflate(R.layout.fragment_catchpokemon, container, false);
    }

}