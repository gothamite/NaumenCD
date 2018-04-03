package ru.naumen.naumencd.ui.adapters.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ru.naumen.naumencd.AdapterClickListener;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;
import ru.naumen.naumencd.utils.Navigator;

public class ComputersSimilarAdapter extends RecyclerView.Adapter<SimilarViewHolder>
        implements AdapterClickListener {

    private List<? extends SimilarItemEntity> computers = Collections.emptyList();
    private Navigator navigator;

    public ComputersSimilarAdapter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void clickOn(int position) {
        navigator.goToNextFragment(computers.get(position).getId());
    }

    @Override
    public SimilarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similiar, parent, false);
        return new SimilarViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(SimilarViewHolder holder, int position) {
        holder.initListSimilar(computers.get(position));
    }

    public void setComputersList(List<? extends SimilarItemEntity> computersList) {
        computers = computersList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }
}
