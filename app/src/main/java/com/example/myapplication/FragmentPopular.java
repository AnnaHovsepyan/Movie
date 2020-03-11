package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

public class FragmentPopular extends Fragment {
    public static final String API_KEY = "ad91b59ba50186e1f1d29ef5c4577ed2";
    private List<MoviesModel> moviesModels = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private int page;
    private int page_size = 15;
    private boolean isLastPage;
    private boolean isLoading;
    private MoviesAdapter moviesAdapter;
    private RecyclerView movies_recyclerview;
    private Retrofit retrofit;
    Activity context;




    public FragmentPopular() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_popular, container, false);
        context=getActivity();
        movies_recyclerview = root.findViewById(R.id.movies_recyclerview);
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
        mLayoutManager = new LinearLayoutManager(context);
        movies_recyclerview.setLayoutManager(mLayoutManager);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        page = 1;

        try {
            getMoviesResponse();
        } catch (Exception e) {
            Log.d("tag", "Main Ex : " + e.getMessage());
        }
        movies_recyclerview.setAdapter(moviesAdapter);
        movies_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }


            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItem = mLayoutManager.getChildCount();
                int totalItem = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {

                    if ((visibleItem + firstVisibleItemPosition) * 2 >= totalItem
                            && firstVisibleItemPosition >= 0
                            && totalItem >= page_size) {

                        page++;
                        getMoviesResponse();
                    }
                }
            }
        });



        return root;
    }


    private void getMoviesResponse() {

        isLoading = true;

        RequestInterface requestInteface = retrofit.create(RequestInterface.class);
        Call<MovieResponseWrapper> call = requestInteface.getMoviesJson(API_KEY, page);

        call.enqueue(new Callback<MovieResponseWrapper>() {
            @Override
            public void onResponse(Call<MovieResponseWrapper> call, Response<MovieResponseWrapper> response) {
                isLoading = false;

                if (response.body() != null) {

                    if (response.body().getResults().size() > 0) {
                        //moviesModels.clear();

                        moviesModels.addAll(response.body().getResults());
                        moviesAdapter.submitList(new ArrayList<>(moviesModels));
                        isLastPage = moviesModels.size() < page_size;
                    } else {
                        isLastPage = true;
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











