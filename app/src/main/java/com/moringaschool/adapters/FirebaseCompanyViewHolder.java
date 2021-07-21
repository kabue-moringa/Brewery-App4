package com.moringaschool.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.R;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static View mVeiw;
//    View mVeiw;
    Context mContext;

    public FirebaseCompanyViewHolder(View itemView) {
        super(itemView);
        mVeiw = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }
    public static void bindBreweriesResponse(BreweriesResponse TypeOfBrewery){
        TextView TypeOfBreweries = (TextView) mVeiw.findViewById(R.id.TypeOfBreweries);

    }

    @Override
    public void onClick(View v) {
        final List<BreweriesResponse> TypeOfBreweries = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_COMPANY);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TypeOfBreweries.add(snapshot.getValue(BreweriesResponse.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, CompanyActivity.class);
                intent.putExtra("position", itemPosition + "");
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
}
}