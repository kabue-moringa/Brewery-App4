package com.moringaschool.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.moringaschool.adapters.FirebaseCompanyViewHolder;
import com.moringaschool.brewer_app.CompanyActivity;
import com.moringaschool.brewer_app.R;
import com.moringaschool.models.BreweriesResponse;
import com.moringaschool.models.Company;
import com.moringaschool.ui.SavedCompanyListActivity;

import java.net.HttpCookie;
import java.text.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import utils.ItemTouchHelperAdapter;
import utils.OnStartDragListener;

public class FirebaseCompanyListAdapter extends FirebaseRecyclerAdapter<BreweriesResponse, FirebaseCompanyViewHolder> implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;


    private ChildEventListener mChildEventListener;
    private ArrayList<BreweriesResponse> mCompany = new ArrayList<>();

    public  FirebaseCompanyListAdapter(FirebaseRecyclerOptions<BreweriesResponse> options, Query ref, SavedCompanyListActivity onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = (OnStartDragListener) onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                mCompany.add(dataSnapshot.getValue(BreweriesResponse.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };


    @Override
    protected void onBindViewHolder( FirebaseCompanyViewHolder holder, int position, @NonNull BreweriesResponse model) {
        FirebaseCompanyViewHolder.bindBreweriesResponse(model);
        FirebaseCompanyViewHolder.mCompanyImageView.setOnTouchListener(new View.OnTouchListener() {

            private Object FirebaseCompanyViewHolder;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag((RecyclerView.ViewHolder) FirebaseCompanyViewHolder);
                }
                return false;
            }
        });
        FirebaseCompanyViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CompanyActivity.class);
//                intent.putExtra("position", FirebaseCompanyViewHolder.getAdapterPosition());
                mContext.startActivity(intent);
            }
        });
    }
    @NonNull
    @Override
    public FirebaseCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_list_item_drag, parent, false);
        return new FirebaseCompanyViewHolder(view);
    }
    
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mCompany, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInForebase();
        return false;
    }

    @Override

    public void onItemDismiss(int position) {
        mCompany.remove(position);
        getRef(position).removeValue();
    }
    private void setIndexInForebase() {

        for(BreweriesResponse BreweriesTesponse: mCompany){
            int index = mCompany.indexOf(BreweriesTesponse);
            DatabaseReference mReference = getRef(index);
            BreweriesTesponse.setIndex(Integer.toString(index));
            mReference.setValue(BreweriesTesponse);
        }
    }
    @Override
    public void stopListening(){
        super.stopListening();
        mRef.removeEventListener(mChildEventListener);
    }
}











