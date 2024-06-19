package com.example.sq_lite;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.sq_lite.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;

import com.example.database.WarratyDB;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> launcher;
    static WarratyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        // db
        db = new WarratyDB(MainActivity.this);
        launcher =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == RESULT_OK && o.getData() != null){
                    Bitmap bitmap = (Bitmap) o.getData().getExtras().get("data");
                    binding.imvPhoto.setImageBitmap(bitmap);
                }
            }
        });
        addEvent();
    }

    private void addEvent() {
        binding.btnCapturePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //insert data
                String name = binding.edtName.getText().toString();
                String des = binding.edtDescription.getText().toString();
                boolean inserted = db.insertData(name, des, convertPhoto());
                if (inserted){
                    Toast.makeText(MainActivity.this, "Succes", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, )
                }
                else {
                    Toast.makeText(MainActivity.this, " Fail!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private byte[] convertPhoto(){
        BitmapDrawable drawable = (BitmapDrawable) binding.imvPhoto.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }


}