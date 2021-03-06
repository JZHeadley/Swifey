package com.jzheadley.swifey.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzheadley.swifey.R;
import com.jzheadley.swifey.models.CheckIn;
import com.jzheadley.swifey.ui.OrdersActivity;
import com.jzheadley.swifey.ui.PastCheckInActivity;

import java.util.List;

import timber.log.Timber;

/**
 * Created by mit on 12/7/17.
 */

public class PastCheckInAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CheckIn> items;
    private PastCheckInActivity activity;

    public PastCheckInAdapter(PastCheckInActivity activity, List<CheckIn> items) {
        this.activity = activity;
        this.items = items;
        Timber.v("checkIn adapts init");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View row = inflater.inflate(R.layout.check_in_card, parent, false);
        Item item = new Item(row);

        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((Item) holder).checkInDate.setText((CharSequence) items.get(position).getCheckInDate());

        ((Item) holder).checkInCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, OrdersActivity.class);
                intent.putExtra("checkIn", items.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView checkInDate;
        CardView checkInCard;
        public Item(View itemView) {
            super(itemView);
            checkInDate = itemView.findViewById(R.id.checkInTest);
            checkInCard = itemView.findViewById(R.id.checkInCard);
        }
    }
}
