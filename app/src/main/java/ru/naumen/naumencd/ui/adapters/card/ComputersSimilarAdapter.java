package ru.naumen.naumencd.ui.adapters.card;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.ui.activities.card.CardActivity;

public class ComputersSimilarAdapter extends RecyclerView.Adapter<ComputersSimilarAdapter.ViewHolder> {

    private List<Item> computers = Collections.emptyList();

    public ComputersSimilarAdapter() {
    }

    public void setComputersList(List<Item> computersList) {
        computers = computersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_similar)
        TextView name;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ComputersSimilarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similiar, parent, false);
        return new ComputersSimilarAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComputersSimilarAdapter.ViewHolder holder, int position) {
        Item comp = computers.get(position);
        if (comp.getName() != null) {
            holder.name.setText(comp.getName());
        }

        holder.name.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CardActivity.class);
            intent.putExtra("SELECTED_COMPUTER_ID", comp.getId());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }
}
