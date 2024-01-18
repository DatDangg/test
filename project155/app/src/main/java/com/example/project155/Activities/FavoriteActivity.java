package com.example.project155.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.project155.Adapters.FavoriteListAdapter;
import com.example.project155.R;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView backImg;
    private FavoriteListAdapter favoriteListAdapter;

    private ScrollView scrollView;
    private TextView emptyTXT;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView = findViewById(R.id.view4);
        backImg = findViewById(R.id.backFavImg);
//        emptyTXT = findViewById(R.id.empTXT);
//        scrollView = findViewById(R.id.scrollView3);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
        // Lấy danh sách phim yêu thích từ Intent
        List<Integer> favoriteMovies = getIntent().getIntegerArrayListExtra("favoriteMovies");

        if (favoriteMovies != null) {
            Log.d("FavoriteActivity", "Favorite Movies: " + favoriteMovies.toString());
        } else {
            Log.e("FavoriteActivity", "Favorite Movies is null");
        }

//         Khởi tạo adapter và thiết lập RecyclerView
        recyclerView.setAdapter(new FavoriteListAdapter(favoriteMovies));



        backImg.setOnClickListener(v -> finish());
    }
}
