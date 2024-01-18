package com.example.project155.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project155.R;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {

    private List<Integer> favoriteMovieIds;

    public FavoriteListAdapter(List<Integer> favoriteMovieIds) {
        this.favoriteMovieIds = favoriteMovieIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: Hiển thị thông tin phim dựa trên ID phim từ danh sách yêu thích

        // Ví dụ: hiển thị ID phim làm tên phim
        int movieId = favoriteMovieIds.get(position);
        holder.textViewMovieTitle.setText("Movie " + movieId);
    }

    @Override
    public int getItemCount() {
        return favoriteMovieIds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMovieTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
                textViewMovieTitle = itemView.findViewById(R.id.title1);
        }
    }
}
