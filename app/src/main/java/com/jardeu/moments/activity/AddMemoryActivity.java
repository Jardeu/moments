package com.jardeu.moments.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.jardeu.moments.R;
import com.jardeu.moments.model.Memory;
import com.jardeu.moments.model.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddMemoryActivity extends AppCompatActivity {
    EditText edTitle, edDescription, edDate;
    ImageView ivImage;
    Button btnSaveMemory;
    ImageButton btnAddImage, btnGoBack;
    RadioGroup radioGroup;

    Memory memory = new Memory();
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);

        edTitle = findViewById(R.id.edTitle);
        edDescription = findViewById(R.id.edDescription);
        edDate = findViewById(R.id.edDate);

        ivImage = findViewById(R.id.ivImage);
        radioGroup = findViewById(R.id.categoriesRadioGroup);

        btnAddImage = findViewById(R.id.btnAddImage);
        btnSaveMemory = findViewById(R.id.btnSaveMemory);
        btnGoBack = findViewById(R.id.btnGoToBack);

        //LISTAR AS TAGS
        for (Category t: Category.categoriesList) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(t.getName());

            radioGroup.addView(radioButton);
        }

        //ESCOLHER UMA IMAGEM DA GALERIA E ADICIONAR NO IMAGEVIEW
        ActivityResultLauncher<Intent> mGetContent = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
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
                    }
                });
        
        btnAddImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            mGetContent.launch(intent);
        });

        btnGoBack.setOnClickListener(v -> {
            finish();
        });

        btnSaveMemory.setOnClickListener(v -> {
            save();
        });
    }

    private void save(){
        int id = Memory.memoriesList.size()+1;
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
        String date = edDate.getText().toString();
        String imagePath;

        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!title.isEmpty() || !imagePath.isEmpty()){
            memory.setId(id);
            memory.setTitle(title);
            memory.setDescription(description);
            memory.setDate(date);
            memory.setImage(imagePath);

            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

            if (selectedRadioButtonId != -1){
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                String selectedCategory = selectedRadioButton.getText().toString();

                for (Category c: Category.categoriesList) {
                    if (c.getName().equals(selectedCategory)){
                        memory.setCategory_id(c.getId());
                    }
                }
            }

            Memory.memoriesList.add(memory);

            Toast.makeText(this, "Nova Memória salva", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(AddMemoryActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Adicione uma imagem e um título", Toast.LENGTH_LONG).show();
        }
    }
}