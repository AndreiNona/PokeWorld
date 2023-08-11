package and.andrei.pokeworld.view;

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
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;
import and.andrei.pokeworld.viewModel.AddItemViewModel;
import and.andrei.pokeworld.viewModel.AddPokemonViewModel;

public class AddIemFragment extends Fragment {

    private AddItemViewModel addItemViewModel;
    private Item placeholderitem;

    private EditText name,description;
    private Button addItem, seeLatest;

    private TextView message;

    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        addItemViewModel = new ViewModelProvider(getActivity()).get(AddItemViewModel.class);
        placeholderitem = new Item();
        initializeViews(view);
    }

    private void initializeViews(View view){
        name = view.findViewById(R.id.edit_text_item_name);
        description = view.findViewById(R.id.edit_text_item_description);
        message=view.findViewById(R.id.text_item_confirmation);
        addItem = view.findViewById(R.id.button_addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name,Description;
                Name=name.getText().toString();Description=description.getText().toString();
                if (Name.isEmpty() || Description.isEmpty())
                    Toast.makeText(getContext(), "We need more information about your item", Toast.LENGTH_SHORT).show();
                else {
                    placeholderitem.setName(Name);placeholderitem.setDescription(Description);
                    addItemViewModel.addItem(placeholderitem);
                    message.setText(addItemViewModel.getMessage());
                }
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_additem, container, false);
    }
}
