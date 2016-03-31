package io.github.swarajsaaj.retrofitdemo.services;

import java.util.List;

import io.github.swarajsaaj.retrofitdemo.models.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by swarajpal on 28-03-2016.
 */
public interface PostService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{id}")
    Call<List<Post>> getPostById(@Path("id") int id);

    @POST("/posts")
    Call<Post> createPost(@Body Post post);

}
