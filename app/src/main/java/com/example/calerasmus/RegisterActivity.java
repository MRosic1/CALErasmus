package com.example.calerasmus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password;
    Button register,natrag;
    DBOpenHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.editText5);
        password = findViewById(R.id.editText6);
        register = findViewById(R.id.button4);
        natrag = findViewById(R.id.button5);
        DB = new DBOpenHelper(this);
        natrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Molimo unesite sve podatke!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user,pass);
                        if(insert==true)
                            Toast.makeText(RegisterActivity.this, "Registracija uspješna!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(RegisterActivity.this, "Registracija neuspješna, molimo probajte opet!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
