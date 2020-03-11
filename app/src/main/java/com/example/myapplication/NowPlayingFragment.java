package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NowPlayingFragment extends Fragment {
    public static final String API_KEY = "ad91b59ba50186e1f1d29ef5c4577ed2";
    private List<MoviesModel> moviesModels = new ArrayList<>();
    private LinearLayoutManager LayoutManager;
    private MoviesAdapter moviesAdapter;
    private RecyclerView horiz_recyclerview;
    private Retrofit retrofit;
    Activity context;




    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_now_playing, container, false);
        context=getActivity();
        horiz_recyclerview = root.findViewById(R.id.movies_recyclerview_1);
        moviesAdapter = new MoviesAdapter(new ListItemClickListener<MoviesModel>() {
            @Override
            public void onItemClicked(MoviesModel item) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("overview", item.getOverview());
                intent.putExtra("data_release", item.getReleaseDate());
                intent.putExtra("image", item.getPosterPath());
                intent.putExtra("vote_average", item.getVoteAverage());
                startActivity(intent);
            }
        });

        LayoutManager = new LinearLayoutManager(context);
        horiz_recyclerview.setLayoutManager(LayoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        horiz_recyclerview.setAdapter(moviesAdapter);
        getMoviesResponse1();


        return root;
    }


    private void getMoviesResponse1() {


        RequestInterface requestInteface = retrofit.create(RequestInterface.class);
        Call<MovieResponseWrapper> call = requestInteface.getMoviesJson1(API_KEY);

        call.enqueue(new Callback<MovieResponseWrapper>() {
            @Override
            public void onResponse(Call<MovieResponseWrapper> call, Response<MovieResponseWrapper> response) {


                if (response.body() != null) {

                    if (response.body().getResults().size() > 0) {
                        moviesModels.addAll(response.body().getResults());
                        moviesAdapter.submitList(new ArrayList<>(moviesModels));


                    } else {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    }
                }
            }


            @Override
            public void onFailure(Call<MovieResponseWrapper> call, Throwable t) {
                t.printStackTrace();


            }
        });

    }

}



