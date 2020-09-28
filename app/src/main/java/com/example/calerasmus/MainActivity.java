package com.example.calerasmus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivityForResult(intent,1);
        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calendar_view);
    }
    protected void onActivityResult(int reqCode, int resCode, Intent data){
        super.onActivityResult(reqCode,resCode,data);
        if (reqCode == 1){
            if (resCode == RESULT_OK){
                String povratna = data.getStringExtra("ime");
            }
        }
    }

}
