package com.osg.projectsimplediary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<MemoItem> items;
    final MenuInflater inflater;

    String tablename="memo";
    String dbName="simpleMemo.db";
    SQLiteDatabase db;

    public MyAdapter(Context context, ArrayList<MemoItem> items, MenuInflater inflater) {
        this.context = context;
        this.items = items;
        this.inflater = inflater;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listview_layout, parent, false);

        VH viewholder = new VH(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        MemoItem item = items.get(position);
        vh.title.setText(item.title);
        vh.text.setText(item.text);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VH extends RecyclerView.ViewHolder{
        TextView title, text;

        public VH(@NonNull final View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            text = itemView.findViewById(R.id.text);

//            int no=getLayoutPosition();

//            db=openOrCreateDatabase(dbName,null);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(context, itemView,0);
                    popupMenu.inflate(R.menu.context_menu);

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getTitle().toString()){
                                case "delete":
                                    items.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());

                                    //SQLiteopener?같은 헬퍼클래스를 만드는 방법 검색해서 적용하기
                                    break;
                            }
                            return true;
                        }
                    }); popupMenu.show();

                    return false;
                }
            });
        }
    }
}
