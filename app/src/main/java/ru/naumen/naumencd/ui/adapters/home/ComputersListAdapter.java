package ru.naumen.naumencd.ui.adapters.home;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;
import timber.log.Timber;

public class ComputersListAdapter extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

    private List<Item> computers;

    public ComputersListAdapter(List<Item> computersList) {
        computers = computersList;
    }

    public void addComputersList(List<Item> itemList) {
        computers.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ComputersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComputersListAdapter.ViewHolder holder, int position) {
        Item comp = computers.get(position);
        if (comp.getName() != null) {
            holder.name.setText(comp.getName());
            holder.name.setVisibility(View.VISIBLE);
            holder.nameDis.setVisibility(View.VISIBLE);
        }
        if (comp.getIntroduced() != null) {
            holder.introduced.setText(comp.getName());
            holder.introduced.setVisibility(View.VISIBLE);
            holder.introducedDis.setVisibility(View.VISIBLE);
        }
        if (comp.getDiscounted() != null) {
            holder.discontinued.setText(comp.getName());
            holder.discontinued.setVisibility(View.VISIBLE);
            holder.discontinuedDis.setVisibility(View.VISIBLE);
        }
        if (comp.getCompany() != null) {
            holder.company.setText(comp.getName());
            holder.company.setVisibility(View.VISIBLE);
            holder.companyDis.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //TODO Картинку через Пикасо тут обрабатывать или в кард активити?
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.name_dis)
        TextView nameDis;

        @BindView(R.id.introduced)
        TextView introduced;

        @BindView(R.id.introduced_dis)
        TextView introducedDis;

        @BindView(R.id.discontinued)
        TextView discontinued;

        @BindView(R.id.discontinued_dis)
        TextView discontinuedDis;

        @BindView(R.id.company)
        TextView company;

        @BindView(R.id.company_dis)
        TextView companyDis;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

