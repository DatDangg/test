package com.example.project155.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.project155.Adapters.ActorsListAdapter;
import com.example.project155.Adapters.CategoryEachFilmListAdapter;
import com.example.project155.Domain.FilmItem;
import com.example.project155.R;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar progressBar;
    private TextView titleTxt, movieRateTxt, movieTimeTxt, movieSummaryInfo, movieActorsInfo;
    private int idFilm;
    private ImageView pic2, backImg;
    private RecyclerView.Adapter adapterActorList, adapterCategory;
    private RecyclerView recyclerViewActors, recyclerViewCategory;
    private NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();

        View imageView5 = findViewById(R.id.imageView5);
        imageView5.setOnClickListener(v -> {
            FavoriteMoviesManager.addFavoriteMovie(idFilm);
            // Optional: Thông báo cho người dùng, ví dụ, hiển thị toast
            //Toast.makeText(DetailActivity.this, "Đã thêm vào Yêu thích", Toast.LENGTH_SHORT).show();

            // Chuyển danh sách phim yêu thích sang FavoriteActivity
            Intent intent = new Intent(DetailActivity.this, FavoriteActivity.class);
            intent.putIntegerArrayListExtra("favoriteMovies", new ArrayList<>(FavoriteMoviesManager.getFavoriteMovies()));
            startActivity(intent);
        });

    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson = new Gson();
            progressBar.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);

            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummaryInfo.setText(item.getPlot());
            movieActorsInfo.setText(item.getActors());
            if (item.getImages() != null) {
                adapterActorList = new ActorsListAdapter(item.getImages());
                recyclerViewActors.setAdapter(adapterActorList);
            }
            if (item.getGenres() != null) {
                adapterCategory = new CategoryEachFilmListAdapter(item.getGenres());
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
        progressBar = findViewById(R.id.progressBarDetail);
        scrollView = findViewById(R.id.scrollView3);
        pic2 = findViewById(R.id.picDetail);
        movieRateTxt = findViewById(R.id.movieStar);
        movieTimeTxt = findViewById(R.id.movieTime);
        movieSummaryInfo = findViewById(R.id.movieSummery);
        movieActorsInfo = findViewById(R.id.movieActorInfo);
        backImg = findViewById(R.id.backfImg);
        recyclerViewCategory = findViewById(R.id.genreView);
        recyclerViewActors = findViewById(R.id.imagesRecycler);
        recyclerViewActors.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());
    }
}