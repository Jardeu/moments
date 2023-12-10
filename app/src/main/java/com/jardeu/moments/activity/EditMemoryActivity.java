package com.jardeu.moments.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Category;
import com.jardeu.moments.model.Memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class EditMemoryActivity extends AppCompatActivity {
    EditText edTitle, edDescription, edDate;
    ImageView ivImage;
    Button btnUpdateMemory;
    ImageButton btnEditImage, btnGoBack;
    RadioGroup radioGroup;

    Memory memory;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memory);

        edTitle = findViewById(R.id.edTitle2);
        edDescription = findViewById(R.id.edDescription2);
        edDate = findViewById(R.id.edDate2);

        ivImage = findViewById(R.id.ivImage2);
        radioGroup = findViewById(R.id.categoryRadioGroup2);

        btnEditImage = findViewById(R.id.btnEditImage);
        btnUpdateMemory = findViewById(R.id.btnUpdateMemory);
        btnGoBack = findViewById(R.id.btnGoToBack4);

        Intent intent = getIntent();
        memory = (Memory) intent.getSerializableExtra("memory_id");

        edTitle.setText(memory.getTitle());
        edDescription.setText(memory.getDescription());
        edDate.setText(memory.getDate());

        for (Category c: Category.categoriesList) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(c.getName());

            if (c.getId() == memory.getCategory_id())
                radioButton.setChecked(true);

            radioGroup.addView(radioButton);
        }

        Bitmap image = BitmapFactory.decodeFile(memory.getImage());
        ivImage.setImageBitmap(image);

        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        imageUri = data.getData();

                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            ivImage.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        btnEditImage.setOnClickListener(v -> {
            Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent2.setType("image/*");
            mGetContent.launch(intent2);
        });

        btnGoBack.setOnClickListener(v -> {
            finish();
        });
        btnUpdateMemory.setOnClickListener(v -> {
            save();
        });
    }

    private void save(){
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        String date = edDate.getText().toString();
        int categoryId = memory.getCategory_id();
        String imagePath = memory.getImage();

        try {
            if (imageUri != null) {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                File pasta = new File(getFilesDir(), "memoryImages");
                if (!pasta.exists()) {
                    pasta.mkdir();
                }

                File imageFile = new File(pasta, "image_" + System.currentTimeMillis() + ".png");
                FileOutputStream fos = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();

                imagePath = imageFile.getAbsolutePath();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!title.isEmpty()){

            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            if (selectedRadioButtonId != -1){
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                String selectedCategory = selectedRadioButton.getText().toString();

                for (Category c: Category.categoriesList) {
                    if (c.getName().equals(selectedCategory)){
                        categoryId = c.getId();
                    }
                }
            }

            for (Memory m: Memory.memoriesList) {
                if (m.getId() == memory.getId()) {
                    m.setTitle(title);
                    m.setDescription(description);
                    m.setDate(date);
                    m.setCategory_id(categoryId);
                    m.setImage(imagePath);
                }
            }

            Toast.makeText(this, "Memória alterada com sucesso", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(EditMemoryActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "O título não pode ser vazio", Toast.LENGTH_LONG).show();
        }
    }
}