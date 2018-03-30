package ru.naumen.naumencd.ui.adapters.list;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.AdapterClickListener;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.Item;

public class ComputersViewHolder extends RecyclerView.ViewHolder {

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

    public ComputersViewHolder(View v, AdapterClickListener adapterClickListener) {
        super(v);
        ButterKnife.bind(this, itemView);

        relativeLayout.setOnClickListener(view -> {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                adapterClickListener.clickOn(getAdapterPosition());
            }
        });
    }

    public void initCompsList(int position, List<Item> computers) {
        Item comp = computers.get(position);

        if (comp.getName() != null) {
            name.setText(comp.getName());
            name.setVisibility(View.VISIBLE);
            nameDis.setVisibility(View.VISIBLE);
        } else {
            name.setVisibility(View.GONE);
            nameDis.setVisibility(View.GONE);
        }

        if (comp.getCompany() != null) {
            company.setText(comp.getCompany().getName());
            company.setVisibility(View.VISIBLE);
            companyDis.setVisibility(View.VISIBLE);
        } else {
            company.setVisibility(View.GONE);
            companyDis.setVisibility(View.GONE);
        }
    }
}
