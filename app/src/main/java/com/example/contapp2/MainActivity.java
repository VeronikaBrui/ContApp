package com.example.contapp2;

import static com.example.contapp2.DbManager.TABLECONT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    DbManager dbManager;
    SQLiteDatabase sqLiteDatabase;
    EditText name, phone, email;

    Button add, update, show;
    int id = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DbManager(this);
        findId();
        insertData();
        editdata();
        

    }

    private void editdata() {
        if(getIntent().getBundleExtra("userdata")!=null){
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");
            name.setText(bundle.getString("name"));
            phone.setText(bundle.getString("phone"));
            email.setText(bundle.getString("email"));
            add.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);


        }
    }


    private void insertData() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name", name.getText().toString());
                cv.put("phone", phone.getText().toString());
                cv.put("email", email.getText().toString());
                sqLiteDatabase = dbManager.getWritableDatabase();
                Long recinsert = sqLiteDatabase.insert(TABLECONT, null, cv);
                if (recinsert != null){
                    Toast.makeText(MainActivity.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    phone.setText("");
                    email.setText("");

                }

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShowDataActivity.class));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("name", name.getText().toString());
                cv.put("phone", phone.getText().toString());
                cv.put("email", email.getText().toString());

                sqLiteDatabase = dbManager.getWritableDatabase();
                long rec = sqLiteDatabase.update(TABLECONT, cv, "id = " +id, null);
                if (rec != -1){
                    Toast.makeText(MainActivity.this,"Update Successfully", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    phone.setText("");
                    email.setText("");
                    update.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);
                }


            }
        });
    }


    private void findId(){
        name = (EditText)findViewById(R.id.edit_name);
        phone = (EditText)findViewById(R.id.edit_phone);
        email = (EditText)findViewById(R.id.edit_email);
        add=(Button)findViewById(R.id.btnAdd);
        show = (Button)findViewById(R.id.btn_Display);
        update = (Button)findViewById(R.id.btnUpdate);


    }
}