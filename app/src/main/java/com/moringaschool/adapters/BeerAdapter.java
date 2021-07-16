package com.moringaschool.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.models.BreweriesResponse;
import com.moringaschool.brewer_app.R;
//import com.moringaschool.brewerydb.R;

import org.json.JSONArray;

import java.io.ObjectInputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<BreweriesResponse>mTypeOfBreweries;


    public BeerAdapter(List<BreweriesResponse> TypeOfBreweries) {
//
        this.mTypeOfBreweries = TypeOfBreweries;
    }
    @Override
    public BeerAdapter.BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
//        BeerViewHolder viewHolder = new BeerViewHolder(view);
        return new BeerViewHolder(view);
    }
    @Override
    public void onBindViewHolder(BeerAdapter.BeerViewHolder holder, int position) {
        holder.bindBeerAdapter(mTypeOfBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mTypeOfBreweries.size();
    }


    public class BeerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.TypeOfBreweries)TextView mTypeOfBreweries;

//        private Context mContext;
        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            mContext = itemView.getContext();
        }
        public void bindBeerAdapter(BreweriesResponse BeerAdapter) {
            mTypeOfBreweries.setText(BeerAdapter.getBreweryType());

        }
    }
}
