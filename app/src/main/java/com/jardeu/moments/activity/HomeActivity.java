package com.jardeu.moments.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jardeu.moments.DatabaseAdmin;
import com.jardeu.moments.R;
import com.jardeu.moments.adapter.Adapter;
import com.jardeu.moments.model.Memory;

public class HomeActivity extends AppCompatActivity {
    FloatingActionButton btnAddMemory;
    ImageButton btnListCategories;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAddMemory = findViewById(R.id.btnAddMemory);
        btnListCategories = findViewById(R.id.btnListCategories);

        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(Memory.memoriesList);
        recyclerView.setAdapter(adapter);

        btnAddMemory.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddMemoryActivity.class);
            startActivity(intent);
        });

        btnListCategories.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseAdmin admin = new DatabaseAdmin(this, "MemoryDatabase", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        DatabaseAdmin.saveCategories(db);
        DatabaseAdmin.saveMemories(db);
    }
}