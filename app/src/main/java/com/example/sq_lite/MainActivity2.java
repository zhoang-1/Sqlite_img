package com.example.sq_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import com.example.Adapter.WarrantyAdapter;
import com.example.database.WarratyDB;
import com.example.sq_lite.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

import Models.Warranty;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    WarrantyAdapter adapter;
    ArrayList<Warranty> warranties;
    public  static WarratyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
    }

    private void getData() {
        adapter =new WarrantyAdapter(MainActivity2.this , R.layout.item_list , loadDataFromDb());
        binding.lvWarraty.setAdapter(adapter);
    }

    private List<Warranty> loadDataFromDb() {
        warranties =new ArrayList<>();
        Cursor cursor = MainActivity.db.getData("SELECT *FROM" + WarratyDB.TBL_NAME);
        if(cursor !=null){
            while (cursor.moveToNext()){
                warranties.add(new Warranty(cursor.getInt(0) , cursor.getString(1),cursor.getString(2) , cursor.getBlob(3)));
            }
        cursor.close();
             }
    return warranties;
    }
}