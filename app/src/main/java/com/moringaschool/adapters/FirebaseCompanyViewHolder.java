package com.moringaschool.adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.brewerydb.Constants;
import com.moringaschool.models.BreweriesResponse;

import java.util.ArrayList;
import java.util.List;

public class FirebaseCompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mVeiw;
    Context mContext;

    public FirebaseCompanyViewHolder(View itemView) {
        super(itemView);
        mVeiw = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);

    }
    public void bindCompany(){

    }

    @Override
    public void onClick(View v) {
        final List<BreweriesResponse> TypeOfBreweries = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_COMPANY);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    company.add(snapshot.getValue(BreweriesResponse.class));
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
}
}