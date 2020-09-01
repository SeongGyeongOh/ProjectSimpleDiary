package com.osg.projectsimplediary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {

    EditText etTitle, etText;
    TextView textNum;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        etTitle = findViewById(R.id.et_title);
        etText = findViewById(R.id.et_text);
        textNum = findViewById(R.id.tv_num);

        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        editTextNum();

    }


    public void clickBtnCancel(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    //DB에 메모 저장!
    public void clickBtnOk(View view) {
        String title = etTitle.getText().toString();
        String text = etText.getText().toString();

        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        if(text.length()==0) {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        } else{
            db.execSQL("INSERT INTO tb_memo (title, text) VALUES (?,?)", new String[]{title, text});
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

    }



    public void editTextNum(){
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textNum.setText(etText.getText().length()+"/500");
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void clickLayout(View view) {
        imm.hideSoftInputFromWindow(etText.getWindowToken(),0);
        imm.hideSoftInputFromWindow(etTitle.getWindowToken(),0);
    }

}

