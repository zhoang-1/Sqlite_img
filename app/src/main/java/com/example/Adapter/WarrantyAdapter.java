package com.example.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sq_lite.R;

import java.util.List;

import Models.Warranty;

public class WarrantyAdapter extends BaseAdapter {

    Context context;
    int item_layout;
    List<Warranty> warranties;
    //contructer


    public WarrantyAdapter(Context context, int item_layout, List<Warranty> warranties) {
        this.context = context;
        this.item_layout = item_layout;
        this.warranties = warranties;
    }

    @Override
    public int getCount() {
            return warranties.size();
    }

    @Override
    public Object getItem(int position) {
        return warranties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder ;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater(item_layout , null);

            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtDes = view.findViewById(R.id.txtDes);
            holder.invPhoto = view.findViewById(R.id.imvPhoto);

            view .setTag(holder);
        }else {
            holder =(ViewHolder) view.getTag();
        }


        // biding data
        Warranty w = warranties.get(position);
        holder.txtName.setText(w.getWarrantyName());
        holder.txtDes.setText(w.getWarrantyDescription());

        byte[] photo =w.getWarrantyPhoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0 , photo.length);
        holder.invPhoto.setImageBitmap(bitmap);
        return view;
    }

    public static class ViewHolder{
        ImageView invPhoto;
        TextView txtMenuName ,txtDes;
    }
}
