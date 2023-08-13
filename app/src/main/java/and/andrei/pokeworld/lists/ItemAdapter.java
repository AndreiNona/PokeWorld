package and.andrei.pokeworld.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import and.andrei.pokeworld.R;
import and.andrei.pokeworld.model.Item;
import and.andrei.pokeworld.model.Pokemon;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> items;
    Button itemViewButton;
    private ItemAdapter.OnClickListener listener;

    public ItemAdapter(List<Item> items, ItemAdapter.OnClickListener onClickListener){
        this.items=items;
        this.listener = onClickListener;
    }

    public void setItems(List<Item> items){
        this.items=items;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.inventory_item,parent,false);
        return new ItemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        if(items.get(position).getId() ==0)
            holder.status.setText("Available");
        else
            holder.status.setText("Taken");
    }

    @Override
    public int getItemCount() {
        if(items!=null)
            return items.size();
        else
            return 0;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView itemName, status;

        ItemViewHolder(View pokeView){
            super(pokeView);
            itemName = pokeView.findViewById(R.id.text_item_card_name);
            status= pokeView.findViewById(R.id.text_item_card_status);
            itemViewButton =pokeView.findViewById(R.id.item_action_button);
            itemViewButton.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(view.getId() == itemViewButton.getId()){
                listener.onClick(items.get(getBindingAdapterPosition()));
                notifyDataSetChanged();
            }
        }
    }
    public interface OnClickListener{
        void onClick(Item item);
    }
}
