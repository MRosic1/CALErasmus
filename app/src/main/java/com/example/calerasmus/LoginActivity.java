package com.example.calerasmus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login,register;
    DBOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login3);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        login = findViewById(R.id.button);
        register = findViewById(R.id.button3);
        db = new DBOpenHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                if(usernameValue.equals("") || passwordValue.equals(""))
                    Toast.makeText(LoginActivity.this, "Molimo unesite sve podatke!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = db.checkusernamePassword(usernameValue,passwordValue);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Uspješna prijava!", Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        setResult(RESULT_OK,intent);
                        finish();

                    }else{
                        Toast.makeText(LoginActivity.this, "Neuspješna prijava :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
