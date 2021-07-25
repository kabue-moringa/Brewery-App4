package com.moringaschool.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

import utils.ItemTouchHelperViewHolder;

public class FirebaseCompanyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public static View mCompanyImageView;
    private static View mVeiw;
    public static View itemView;
    Context mContext;

    public FirebaseCompanyViewHolder(View itemView) {
        super(itemView);
        mVeiw = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);

    }

    public static void bindBreweriesResponse(BreweriesResponse TypeOfBrewery) {
        TextView TypeOfBreweries = (TextView) mVeiw.findViewById(R.id.TypeOfBreweries);
        TextView nameTextView = (TextView) mVeiw.findViewById(R.id.companyNameTextView);
    }

    @Override
    public void onItemSelected() {
//        Log.d("Animation", "onItemSelected");
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();

    }

    @Override
    public void onItemClear() {
//       Log.d("Animation", "onItemClear");

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();


    }
}

//    @Override
//    public void onClick(View v) {
//        final List<BreweriesResponse> TypeOfBreweries = new ArrayList<>();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SEARCHED_COMPANY);
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    TypeOfBreweries.add(snapshot.getValue(BreweriesResponse.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, CompanyActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                mContext.startActivity(intent);
//            }

//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//}
//}