package com.osg.projectsimplediary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    EditText etTitle, etText;
    TextView textNum;
    int num;

    String editedTitle, editedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.et_title);
        etText = findViewById(R.id.et_text);
        textNum = findViewById(R.id.tv_num);

        getSupportActionBar().hide();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String title=bundle.getString("title");
        String text=bundle.getString("text");
        num=bundle.getInt("num");

        etTitle.setText(title);
        etText.setText(text);
    }

    public void clickBtnOk(View view) {

        editedTitle=etTitle.getText().toString();
        editedText=etText.getText().toString();

        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        db.execSQL("UPDATE tb_memo SET title='"+editedTitle+"', text='"+editedText+"' WHERE num="+num);
        Intent intent = new Intent(this, DownloadActivity.class);
        startActivity(intent);
        finish();

    }

    public void clickBtnCancel(View view) {
        finish();
    }
}