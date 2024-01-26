package com.example.contapp2;

import static com.example.contapp2.DbManager.TABLECONT;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    int singledata;
    ArrayList<Model>modelArrayList;
    SQLiteDatabase sqLiteDatabase;

    public Adapter(Context context, int singledata, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_single_data, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final Model model = modelArrayList.get(position);
        holder.txtname.setText(model.getContname());
        holder.txtphone.setText(model.getContphone());
        holder.txtemail.setText(model.getContemail());

        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.flowmenu);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit_menu:
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", model.getId());
                                bundle.putString("name", model.getContname());
                                bundle.putString("phone", model.getContphone());
                                bundle.putString("email", model.getContemail());
                                Intent intent = new Intent(context, MainActivity.class);
                                intent.putExtra("userData", bundle);
                                context.startActivity(intent);

                                break;
                            case R.id.delete_menu:

                                DbManager dbManager = new DbManager(context);
                                sqLiteDatabase = dbManager.getReadableDatabase();
                                long recdelete = sqLiteDatabase.delete(TABLECONT, "id = " + model.getId(), null);
                                if(recdelete!=-1){
                                    Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
                                    /*modelArrayList.remove(position);
                                    notifyDataSetChanged();*/
                                }

                                break;
                            default:
                                return false;
                        }return true;
                    }
                });

                ///DISPLAY MENU
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtname;
        TextView txtphone;
        TextView txtemail;
        ImageButton flowmenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=(TextView) itemView.findViewById(R.id.textName);
            txtphone=(TextView) itemView.findViewById(R.id.textPhone);
            txtemail=(TextView) itemView.findViewById(R.id.textEmail);
            flowmenu=(ImageButton) itemView.findViewById(R.id.flowMenu);
        }
    }
}
