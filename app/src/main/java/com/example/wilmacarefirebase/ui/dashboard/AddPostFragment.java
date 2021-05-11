package com.example.wilmacarefirebase.ui.dashboard;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wilmacarefirebase.R;

public class AddPostFragment extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "com.example.WilmaCareFireBase.EXTRA_USERNAME";
    public static final String EXTRA_DESCRIPTION = "com.example.WilmaCareFireBase.EXTRA_DESCRIPTION";

    private AddPostViewModel viewModel;
    private EditText editTextDescription, editTextUserName;
    private Button buttonAddPost;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post);

        editTextDescription = findViewById(R.id.edtinputpost);
        editTextUserName = findViewById(R.id.edtwriteusername);
        buttonAddPost = findViewById(R.id.btnSavePost);

        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePost();
//                Intent intent = new Intent(AddPostFragment.this, DashboardFragment.class);
//                startActivity(intent);

                Fragment mFragment = null;
                mFragment = new DashboardFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, mFragment).commit();
            }
        });
    }

    private void savePost() {
        String username = editTextUserName.getText().toString();
        String description = editTextDescription.getText().toString();

        if(username.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Skriv venligst et brugernavn og et opslag", Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USERNAME, username);
        data.putExtra(EXTRA_DESCRIPTION, description);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnSavePost:
                savePost();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
