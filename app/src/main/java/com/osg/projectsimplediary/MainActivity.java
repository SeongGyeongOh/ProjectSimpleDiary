package com.osg.projectsimplediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtnWrite(View view) {
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }

    public void clickBtnLoad(View view) {
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
    }

}
