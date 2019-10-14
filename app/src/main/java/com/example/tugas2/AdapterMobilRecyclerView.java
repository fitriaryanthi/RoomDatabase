package com.example.tugas2;

import android.app.Activity;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class AdapterMobilRecyclerView extends RecyclerView.Adapter<AdapterMobilRecyclerView.ViewHolder> {
    private ArrayList<Mobil> daftarMobil;
    private Context context;
    private AppDatabase db;

    public AdapterMobilRecyclerView(ArrayList<Mobil> mobils, Context ctx) {
        daftarMobil = mobils;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "mobildb").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v){
            super(v);
                tvTitle = v.findViewById(R.id.tv_namamobil);
                cvMain = v.findViewById(R.id.cv_main);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobil, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = daftarMobil.get(position).getNamaMobil();
            holder.cvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    Mobil mobil = db.mobilDAO().selectMobilDetail(daftarMobil.get(position).getMobilId());
                        context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", mobil));
                }
            });
            holder.cvMain.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.view_dialog);
                        dialog.setTitle("Pilih Aksi");
                        dialog.show();

                     Button editButton = dialog.findViewById(R.id.bt_edit_data);
                     Button delButton = dialog.findViewById(R.id.bt_delete_data);

                        editButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        onEditMobil(position);
                                    }
                                }
                        );

                        delButton.setOnClickListener(
                                new View.OnClickListener(){
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        onDeleteMobil(position);
                                    }
                                }
                        );
                        return true;
                }
            });
            holder.tvTitle.setText(name);
    }

    private void onDeleteMobil(int position){
        db.mobilDAO().deleteMobil(daftarMobil.get(position));
        daftarMobil.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarMobil.size());
    }


    private void onEditMobil(int position){
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarMobil.get(position)));
    }

    @Override
    public int getItemCount(){
        return daftarMobil.size();
    }

}
