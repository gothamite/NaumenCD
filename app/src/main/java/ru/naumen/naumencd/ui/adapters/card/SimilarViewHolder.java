package ru.naumen.naumencd.ui.adapters.card;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.naumen.naumencd.AdapterClickListener;
import ru.naumen.naumencd.R;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;

public class SimilarViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_similar)
    TextView name;

    public SimilarViewHolder(View itemView, AdapterClickListener adapterClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        name.setOnClickListener(view -> {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                adapterClickListener.clickOn(getAdapterPosition());
            }
        });
    }

    public void initListSimilar(SimilarItemEntity item) {
        if (item.getName() != null) {
            name.setText(item.getName());
        }
    }
}
