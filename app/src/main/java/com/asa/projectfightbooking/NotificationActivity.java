package com.asa.projectfightbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asa.projectfightbooking.adapter.PostAdapter;
import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.data.local.UserSharePreference;
import com.asa.projectfightbooking.models.response.PostsItem;
import com.asa.projectfightbooking.presenters.PostPresenter;
import com.asa.projectfightbooking.ui.PostDetailActivity;
import com.asa.projectfightbooking.views.PostView;

import java.util.List;

public class NotificationActivity extends BaseActivity implements PostView {
    private PostAdapter postAdapter;
    private RecyclerView recyclerViewPost;
    private ProgressBar progressBar;
    private PostPresenter postPresenter;
    private ImageView ivProfile, ivHome, ivTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerViewPost = findViewById(R.id.recyclerViewPost);
        postPresenter = new PostPresenter(this);
        postPresenter.getAllPosts();
        ivProfile = findViewById(R.id.ivProfile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(NotificationActivity.this);
                Intent i = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivHome = findViewById(R.id.ivHm);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(NotificationActivity.this);
                Intent i = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivTicket = findViewById(R.id.ivTick);
        ivTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(NotificationActivity.this);
                Intent i = new Intent(NotificationActivity.this, TicketActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMidingLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void onSuccess(String message) {
        showMessage(message);
    }

    @Override
    public void onGetPostSuccess(List<PostsItem> postsItemList) {
        postAdapter = new PostAdapter(postsItemList, this, new PostAdapter.OnClickListener() {
            @Override
            public void onClickCard(View view, PostsItem postsItem) {
                postPresenter.getPostById(postsItem.getId());
            }
        });
        recyclerViewPost.setAdapter(postAdapter);
        recyclerViewPost.setLayoutManager(new GridLayoutManager(this,1));
    }

    @Override
    public void onGetPostByIdSuccess(PostsItem data) {
        if(data != null){
            Intent intent = new Intent(this, PostDetailActivity.class);
            intent.putExtra("Title",data.getTitle());
            intent.putExtra("Body", data.getBody());
            startActivity(intent);
        }
    }
}