package com.jardeu.moments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
    ImageButton btnDelete, btnGoBack;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        btnAddCategory = findViewById(R.id.btnAddCategory);
        btnDelete = findViewById(R.id.btnDelete2);
        btnGoBack = findViewById(R.id.btnGoToBack2);

        recyclerView = findViewById(R.id.recyclerView2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AdapterCategory adapter = new AdapterCategory(Category.categoriesList);
        recyclerView.setAdapter(adapter);

        btnGoBack.setOnClickListener(v -> {
            finish();
        });

    }
}