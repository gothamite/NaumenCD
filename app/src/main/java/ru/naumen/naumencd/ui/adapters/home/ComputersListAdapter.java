package ru.naumen.naumencd.ui.adapters.home;


import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.ui.activities.card.CardActivity;

public class ComputersListAdapter extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

    private List<Item> computers = Collections.emptyList();

    public ComputersListAdapter() {
    }

    public void setComputersList(List<Item> computersList) {
        computers = computersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_detail)
        RelativeLayout relativeLayout;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.name_dis)
        TextView nameDis;

        @BindView(R.id.company)
        TextView company;

        @BindView(R.id.company_dis)
        TextView companyDis;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public ComputersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComputersListAdapter.ViewHolder holder, int position) {
        Item comp = computers.get(position);

        if (comp.getName() != null) {
            holder.name.setText(comp.getName());
            holder.name.setVisibility(View.VISIBLE);
            holder.nameDis.setVisibility(View.VISIBLE);
        } else {
            holder.name.setVisibility(View.GONE);
            holder.nameDis.setVisibility(View.GONE);
        }

        if (comp.getCompany() != null) {
            holder.company.setText(comp.getCompany().getName());
            holder.company.setVisibility(View.VISIBLE);
            holder.companyDis.setVisibility(View.VISIBLE);
        } else {
            holder.company.setVisibility(View.GONE);
            holder.companyDis.setVisibility(View.GONE);
        }

        holder.relativeLayout.setOnClickListener(view -> {
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

