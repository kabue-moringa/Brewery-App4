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
import com.squareup.picasso.Picasso;
//import com.moringaschool.brewerydb.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<BreweriesResponse> mbrewery_type;
    private Context mContext;


    public BeerAdapter(Context context, String mbrewery_type) {
        mContext = context;
        mbrewery_type = mbrewery_type;
    }

    @Override
    public BeerViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent .getContext()).inflate(R.layout.brewery_type, parent,false);
        BeerViewHolder ViewHolder = new BeerViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder( BeerAdapter.BeerViewHolder holder, int position) {
        holder.bindBeerAdapter(mbrewery_type.get(position));

    }

    @Override
    public int getItemCount() {
        return mbrewery_type.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)  ImageView imageView;

        @BindView(R.id.textView)TextView mTextView;

        @BindView(R.id.textname)TextView mname;

        @BindView(R.id.brewery) TextView mbrewery_type;

//        @BindView(R.id.textphone) TextView mphone;

        @BindView(R.id.textaddress) TextView maddress;



        private Context mcontext;

        public BeerViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBeerAdapter(BreweriesResponse BeerAdapter) {
            mname.setText(BeerAdapter.getName());
            mbrewery_type.setText(BeerAdapter.getBreweryType());
//            maddress.setText(BeerAdapter.getAddress2());
        }
    }
}