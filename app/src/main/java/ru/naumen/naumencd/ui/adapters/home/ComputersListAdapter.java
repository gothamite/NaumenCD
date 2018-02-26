package ru.naumen.naumencd.ui.adapters.home;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ComputersListAdapter  extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

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
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

