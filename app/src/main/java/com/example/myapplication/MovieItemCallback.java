package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Hakob Tovmasyan on 2/25/20
 * Package com.example.myapplication
 */
public class MovieItemCallback extends DiffUtil.ItemCallback<MoviesModel> {
	@Override
	public boolean areItemsTheSame(@NonNull MoviesModel oldItem, @NonNull MoviesModel newItem) {
		return oldItem.getId() == newItem.getId();
	}

	@Override
	public boolean areContentsTheSame(@NonNull MoviesModel oldItem, @NonNull MoviesModel newItem) {
		return oldItem.equals(newItem);
	}
}