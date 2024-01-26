package com.example.contapp2;

import static com.example.contapp2.DbManager.TABLECONT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
    DbManager dbManager;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        dbManager = new DbManager(this);
        findId();
        showData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    private void showData() {
        sqLiteDatabase = dbManager.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ TABLECONT+ "", null);
        ArrayList<Model>models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String  name = cursor.getString(1);
            String  phone = cursor.getString(2);
            String  email = cursor.getString(3);
            models.add(new Model(id, name, phone, email));
        }
        cursor.close();
        adapter = new Adapter(this, R.layout.activity_single_data, models, sqLiteDatabase);
        recyclerView.setAdapter(adapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.RecView);

    }
}