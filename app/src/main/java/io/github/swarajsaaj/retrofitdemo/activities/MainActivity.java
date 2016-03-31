package io.github.swarajsaaj.retrofitdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import io.github.swarajsaaj.retrofitdemo.R;
import io.github.swarajsaaj.retrofitdemo.models.Post;
import io.github.swarajsaaj.retrofitdemo.services.PostService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by swarajpal on 28-03-2016.
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private TextView postId;
    private TextView postTitle;
    private TextView postText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostService postService = retrofit.create(PostService.class);

        Call<List<Post>> getAllPostsCall = postService.getAllPosts();

        getAllPostsCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                displayPost(response.body().get(0));
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "Error occured while fetching post.");
            }
        });

    }

    private void initViews() {
        this.postId = (TextView) findViewById(R.id.postId);
        this.postTitle = (TextView) findViewById(R.id.postTitle);
        this.postText = (TextView) findViewById(R.id.postText);
    }

    private void displayPost(Post post) {
        postId.setText(post.getId().toString());
        postTitle.setText(post.getTitle());
        postText.setText(post.getBody());
    }
}
