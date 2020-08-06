package com.osg.projectsimplediary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {
    TextView tv;
    SQLiteDatabase db;
    String dbName = "simpleMemo.db";
    String tableName = "memo";

    RecyclerView recyclerView;
    ArrayList<MemoItem> item = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tv = findViewById(R.id.tv);
        recyclerView = findViewById(R.id.recyclerview);

        dbLoad();

        myAdapter = new MyAdapter(this, item, getMenuInflater());
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();

    }

    void dbLoad(){
        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tb_memo", null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    int num=cursor.getInt(0);
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String text = cursor.getString(cursor.getColumnIndex("text"));

                    item.add(0, new MemoItem(num, title, text));
                }while(cursor.moveToNext());

            }
        }
        db.close();
    }
}
