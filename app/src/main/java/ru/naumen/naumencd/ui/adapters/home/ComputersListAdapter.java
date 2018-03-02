package ru.naumen.naumencd.ui.adapters.home;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;

public class ComputersListAdapter extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

    private List<Item> computers = Collections.emptyList();
    private CardListener cardListener;

    public ComputersListAdapter() {
    }

    public interface CardListener {
        void onCardClick(int pos);
    }

    public void setListener(CardListener listener) {
        this.cardListener = listener;
    }

    public void setComputersList(List<Item> computersList) {
        computers = computersList;
        notifyDataSetChanged();
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

        if (comp.getIntroduced() != null) {
            holder.introduced.setText(comp.getName());
            holder.introduced.setVisibility(View.VISIBLE);
            holder.introducedDis.setVisibility(View.VISIBLE);
        } else {
            holder.introduced.setVisibility(View.GONE);
            holder.introducedDis.setVisibility(View.GONE);
        }

        if (comp.getDiscounted() != null) {
            holder.discontinued.setText(comp.getName());
            holder.discontinued.setVisibility(View.VISIBLE);
            holder.discontinuedDis.setVisibility(View.VISIBLE);
        } else {
            holder.discontinued.setVisibility(View.GONE);
            holder.discontinuedDis.setVisibility(View.GONE);
        }

        if (comp.getCompany() != null) {
            holder.company.setText(comp.getName());
            holder.company.setVisibility(View.VISIBLE);
            holder.companyDis.setVisibility(View.VISIBLE);
        } else {
            holder.company.setVisibility(View.GONE);
            holder.companyDis.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return computers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.computer_card)
        CardView cardView;

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

        @OnClick(R.id.computer_card)
        void Click() {
            cardListener.onCardClick(getAdapterPosition());
        } //TODO не работает, но работает через RelativeLayout

        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}

