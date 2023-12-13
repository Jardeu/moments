package com.jardeu.moments.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Category;

public class AddCategoryActivity extends AppCompatActivity {
    EditText edName;
    Button btnSaveCategory;
    ImageButton btnGoBack;
    Category category = new Category();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        edName = findViewById(R.id.edCategoryName);

        btnSaveCategory = findViewById(R.id.btnSaveCategory);
        btnGoBack = findViewById(R.id.btnGoToBack3);

        btnGoBack.setOnClickListener(v -> {
            finish();
        });

        btnSaveCategory.setOnClickListener(v -> {
            save();
        });
    }

    private void save(){
        int id = Category.categoriesList.size()+1;
        String name = edName.getText().toString();

        if(!name.isEmpty()){
            category.setId(id);
            category.setName(name);

            Category.categoriesList.add(category);

            Toast.makeText(this, "Nova Categoria adicionada", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(AddCategoryActivity.this, CategoryActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Adicione um nome para a categoria", Toast.LENGTH_LONG).show();
        }
    }
}