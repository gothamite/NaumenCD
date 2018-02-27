package ru.naumen.naumencd.ui.adapters.home;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Computers;

public class ComputersListAdapter  extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

    private Computers computers;

    public ComputersListAdapter(Computers computersList) {
        computers = computersList;
    }

    public void addComputersList(Computers  computers) {
        
        notifyDataSetChanged();
    }
    @Override
    public ComputersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ComputersListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView computer;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            computer = itemView.findViewById(R.id.computer_card);
            name = itemView.findViewById(R.id.name); //TODO через баттернайф?
        }
    }
}

