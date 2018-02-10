package com.hillman.currencytest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hillman.currencytest.R;
import com.hillman.currencytest.api.model.Currency;

import java.util.List;

/**
 * Created by hllman on 10.02.18.
 */

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrenciesAdapter.ItemViewHolder> {
    private List<Currency> currencies;

    public CurrenciesAdapter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{


        protected TextView name, price, amount;


        public ItemViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price =  itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);

        }
    }

    @Override
    public CurrenciesAdapter.ItemViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.curren_item,parent,false);
        final ItemViewHolder viewHolder=new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CurrenciesAdapter.ItemViewHolder holder, final int position) {
        Currency currency = currencies.get(position);
        holder.name.setText(currency.getName());
        holder.price.setText("Price : " + currency.getVolume());
        holder.amount.setText("Vol : " + currency.getPrice().getAmount());
    }
    @Override
    public int getItemCount() {
        return currencies.size();
    }
}