package com.example.utsgithubuserapp.data.retrofit;

import com.example.utsgithubuserapp.data.response.GithubSearchResponse;
import com.example.utsgithubuserapp.data.response.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Authorization: token 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"})
    @GET("search/users")
    Call<GithubSearchResponse> searchUsers(@Query("q") String query);

    @Headers({"Authorization: token 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"})
    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);

}
