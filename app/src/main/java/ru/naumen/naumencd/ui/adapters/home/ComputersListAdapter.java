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

public class ComputersListAdapter  extends RecyclerView.Adapter<ComputersListAdapter.ViewHolder> {

    private List<Item> computers;

    public ComputersListAdapter(List<Item> computersList) {
        computers = computersList;
    }

    public void addComputersList(List<Item>  itemList) {
        computers.addAll(itemList);
        notifyDataSetChanged();
    }
    @Override
    public ComputersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ComputersListAdapter.ViewHolder holder, int position) {
        //TODO отображение id, name, company для главной активити
        Item comp = computers.get(position);
        holder.name.setText(comp.getName());
//        holder.company.setText(comp.getCompany().getName());
      //  holder.description.setText(comp.getDescription());
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //TODO Картинку через Пикасо тут обрабатывать или в кард активити?
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.company)
        TextView company;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.computer_card)
        CardView computer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

