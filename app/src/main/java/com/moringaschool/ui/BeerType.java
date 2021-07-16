//package com.moringaschool.ui;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.moringaschool.adapters.BeerAdapter;
//import com.moringaschool.brewer_app.R;
//import com.moringaschool.models.BreweriesResponse;
//import com.moringaschool.network.BreweryClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
//public class BeerType extends AppCompatActivity {
//    @BindView(R.id.breweryrecyclerview)RecyclerView mRecyclerView;
//    @BindView(R.id.errorTextView)TextView mErrorTextView;
//    LinearLayoutManager layoutManager;
//    @BindView(R.id.progressBar)ProgressBar mProgressBar;
//
//    private BeerAdapter mAdapter;
//    private List<BreweriesResponse> BreweryType = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//        layoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mAdapter = new BeerAdapter(BreweryType);
//        mRecyclerView.setAdapter(mAdapter);
//        mProgressBar = findViewById(R.id.progressBar);
//
//        fetchPosts();
//    }
//
//        private void fetchPosts(){
//            mProgressBar.setVisibility(View.VISIBLE);
//            BreweryClient.getClient().getBreweriesResponse().enqueue(new Callback<List<BreweriesResponse>>() {
//                @Override
//                public void onResponse(Call<List<BreweriesResponse>> call, Response<List<BreweriesResponse>> response) {
//
//
//                    if (response.isSuccessful() && response.body() !=null)  {
//                        BreweryType.addAll(response.body());
//                        mAdapter.notifyDataSetChanged();
//                        mProgressBar.setVisibility(View.GONE);
//
//                    }
//
//                    }
//
//
//                @Override
//                public void onFailure(Call<List<BreweriesResponse>> call, Throwable t) {
//
//                    mProgressBar.setVisibility(View.GONE);
//                    Toast.makeText(BeerType.this,"Error "+ t.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    private void showFailureMessage() {
//        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//    private void showUnsuccessfulMessage() {
//        mErrorTextView.setText("Something went wrong. Please try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//    private void showBreweriesResponse() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//}
