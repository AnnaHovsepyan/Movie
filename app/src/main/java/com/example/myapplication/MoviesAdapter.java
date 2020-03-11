package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends ListAdapter<MoviesModel, MoviesAdapter.ViewHolder> {

    private final ListItemClickListener<MoviesModel> listItemClickListener;

    public MoviesAdapter(ListItemClickListener<MoviesModel> listItemClickListener) {
        super(new MovieItemCallback());
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movies_list_item, viewGroup, false);
        final ViewHolder holder = new MoviesAdapter.ViewHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemClickListener.onItemClicked(getItem(holder.getAdapterPosition()));
            }
        });
        return holder;
    }


    @Override
    public void submitList(@Nullable List<MoviesModel> list) {
        super.submitList(list);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesAdapter.ViewHolder viewHolder, int i) {

        MoviesModel item = getItem(i);

        viewHolder.movie_title.setText(item.getTitle());
        viewHolder.movie_overview.setText(item.getOverview());
        String poster = "https://image.tmdb.org/t/p/w500" + item.getPosterPath();


        Picasso.get().load(poster).into(viewHolder.movie_image);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView movie_image;
        private TextView movie_title, movie_overview;
        public ViewGroup layout;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            layout = itemView.findViewById(R.id.layout);

            movie_image = itemView.findViewById(R.id.movie_image);
            movie_title = itemView.findViewById(R.id.movie_title);
            movie_overview = itemView.findViewById(R.id.movie_overview);


        }
    }


  
}