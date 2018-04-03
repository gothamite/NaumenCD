package ru.naumen.naumencd.ui.adapters.list;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ru.naumen.naumencd.AdapterClickListener;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;
import ru.naumen.naumencd.utils.Navigator;

public class ComputersListAdapter extends RecyclerView.Adapter<ComputersViewHolder>
        implements AdapterClickListener {

    private List<PageItemEntity> computers = Collections.emptyList();
    private Navigator navigator;

    public ComputersListAdapter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public ComputersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ComputersViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ComputersViewHolder holder, int position) {
        holder.initCompsList(position, computers);
    }

    public void setComputersList(List<PageItemEntity> computersList) {
        computers = computersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }

    @Override
    public void clickOn(int position) {
        navigator.goToNextFragment(computers.get(position).getId());
    }
}

