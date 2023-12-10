package com.jardeu.moments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jardeu.moments.R;
import com.jardeu.moments.adapter.AdapterCategory;
import com.jardeu.moments.model.Category;
import com.jardeu.moments.model.Memory;

public class CategoryActivity extends AppCompatActivity {
    FloatingActionButton btnAddCategory;
    ImageButton btnGoBack;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnAddCategory = findViewById(R.id.btnAddCategory);
        btnGoBack = findViewById(R.id.btnGoToBack2);

        recyclerView = findViewById(R.id.recyclerView2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AdapterCategory adapter = new AdapterCategory(Category.categoriesList);
        recyclerView.setAdapter(adapter);

        btnGoBack.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, AddCategoryActivity.class);
            startActivity(intent);
        });
    }
}