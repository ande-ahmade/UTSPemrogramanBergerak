package com.example.utsgithubuserapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utsgithubuserapp.data.response.GithubSearchResponse;
import com.example.utsgithubuserapp.data.response.GithubUser;
import com.example.utsgithubuserapp.data.retrofit.ApiConfig;
import com.example.utsgithubuserapp.data.retrofit.ApiService;
import com.example.utsgithubuserapp.ui.AdapterUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterUser adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubSearchResponse> call = apiService.searchUsers("Ande");

        call.enqueue(new Callback<GithubSearchResponse>() {
            @Override
            public void onResponse(Call<GithubSearchResponse> call, Response<GithubSearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<GithubUser> users = response.body().getUsers();
                    adapter = new AdapterUser(users);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    Toast.makeText(MainActivity.this, "Tidak Mendapatkan User", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubSearchResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
