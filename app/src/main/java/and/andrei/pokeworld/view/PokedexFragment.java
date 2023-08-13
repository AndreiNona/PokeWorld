package and.andrei.pokeworld.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.PokedexViewModel;


public class PokedexFragment extends Fragment {
    
    Pokemon currentPokemon;



    EditText name,nickname,pokedexNo,cp,gender;
    Button delete, powerUp,editNickname, evolve,changeGender,assignItem;

    Spinner spinnerGender;

    private PokedexViewModel ViewModel;
    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ViewModel = new ViewModelProvider(getActivity()).get(PokedexViewModel.class);
        navController = Navigation.findNavController(view);

        if(getArguments()!= null){
            Bundle bundle =getArguments();
            currentPokemon = (Pokemon) bundle.getSerializable("pokemon");
        }

        initializeViews(view);

        initializeButtonActions(view);


    }
    private void initializeViews(View view){



        //Edit text
        name = view.findViewById(R.id.edit_text_pokedex_name);
        nickname = view.findViewById(R.id.edit_text_pokedex_nickname);
        pokedexNo = view.findViewById(R.id.edit_text_pokedex_pokedexno);
        cp = view.findViewById(R.id.edit_text_pokedex_cp);
        //gender= view.findViewById(R.id.edit_text_pokedex_gender);

        //Buttons
        delete = view.findViewById(R.id.button_pokedex_release);
        powerUp = view.findViewById(R.id.button_pokedex_powerup);
        editNickname =view.findViewById(R.id.button_pokedex_edit_nickname);
        evolve =view.findViewById(R.id.button_pokedex_edit_name_pokedex); //Used to change Name and PokedexNo
        changeGender= view.findViewById(R.id.button_pokedex_edit_gender);
        assignItem =view.findViewById(R.id.button_pokedex_assign_item);

        //Spinner
        spinnerGender =view.findViewById(R.id.spinner_pokedex);

        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this.getActivity(), R.array.genders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerGender.setAdapter(adapter);

        if(currentPokemon != null){
            name.setText(currentPokemon.getName());
            nickname.setText(currentPokemon.getNickname());
            pokedexNo.setText(String.valueOf(currentPokemon.getPokedexNumber()));
            cp.setText(String.valueOf(currentPokemon.getCP()));
            //gender.setText(String.valueOf(currentPokemon.getGender()));

            //To save variable names we use method calls instead of a holder
            if ('M'== String.valueOf(currentPokemon.getGender()).charAt(0)) {
                spinnerGender.setSelection(0);
            } else if ('F'==String.valueOf(currentPokemon.getGender()).charAt(0)) {
                spinnerGender.setSelection(1);
            } else if ('N' ==String.valueOf(currentPokemon.getGender()).charAt(0)) {
                spinnerGender.setSelection(2);
            }

        }

    }

    private void initializeButtonActions(View view){

        editNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String holder;
                holder=nickname.getText().toString();
                if (holder.isEmpty())
                    Toast.makeText(getContext(), "Please write a nickname", Toast.LENGTH_SHORT).show();
                else if (holder.equals(currentPokemon.getNickname()))
                    Toast.makeText(getContext(), "You new nickname can't be the same as the current one", Toast.LENGTH_SHORT).show();
                else {
                    currentPokemon.setNickname(holder);
                    Toast.makeText(getContext(), "Your buddy has a new nickname!", Toast.LENGTH_SHORT).show();
                    ViewModel.update(currentPokemon);
                }

            }
        });
        powerUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int holder;
                try {
                    holder=Integer.parseInt(cp.getText().toString());
                    if(holder==0)
                        Toast.makeText(getContext(), "Your buddy surely is stronger", Toast.LENGTH_SHORT).show();
                    else{
                        if(holder > currentPokemon.getCP())
                            Toast.makeText(getContext(), "Your buddy powered up!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(), "Your buddy powered down :(", Toast.LENGTH_SHORT).show();
                        currentPokemon.setCP(holder);
                        ViewModel.update(currentPokemon);

                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "Your buddy needs to have some power", Toast.LENGTH_SHORT).show();
                }
            }
        });
        evolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int holderPokedex;
                String holderName;
                try {
                    holderPokedex=Integer.parseInt(pokedexNo.getText().toString());
                    holderName=name.getText().toString();
                    if(holderName.equals(currentPokemon.getName()) || holderPokedex ==currentPokemon.getPokedexNumber())
                        Toast.makeText(getContext(), "Your buddy can't evolve into the same Pokemon", Toast.LENGTH_SHORT).show();
                    else {
                        currentPokemon.setName(holderName);
                        currentPokemon.setPokedexNumber(holderPokedex);
                        ViewModel.update(currentPokemon);
                        Toast.makeText(getContext(), "Your buddy EVOLVED!!", Toast.LENGTH_LONG).show();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(getContext(), "We need a pokedexNo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDecisionDialog();
            }
        });

        changeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char holder= spinnerGender.getSelectedItem().toString().charAt(0);
                if (holder == 'F' || holder =='M' || holder =='N'){
                    currentPokemon.setGender(holder);
                    ViewModel.update(currentPokemon);
                    Toast.makeText(getContext(), "Gender reassignment was successful", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();

            }
        });
        assignItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("pokemon", currentPokemon);
                navController.navigate(R.id.nav_pokedex_item,bundle);
            }
        });
    }

    private void showDecisionDialog() {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this.getActivity());

        builder.setMessage("Do you want to release your buddy?")
                .setCancelable(false)
                .setPositiveButton("Release", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getContext(), "Bye Bye "+currentPokemon.getName(), Toast.LENGTH_LONG).show();
                        ViewModel.delete(currentPokemon);
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        fm.popBackStack();
                    }
                })
                .setNegativeButton("Keep", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getContext(), "Your buddy is safe!", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("To be sure");
        alert.show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }
}