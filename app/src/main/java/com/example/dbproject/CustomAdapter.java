package com.example.dbproject;

import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    ArrayList<ItemObject> objects = new ArrayList<>();
    Context ctx = null;
    LayoutInflater inflater = null;

    CustomAdapter(Context ctx, ArrayList<ItemObject> objs)
    {
        this.ctx = ctx;
        objects = objs;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (objects.size()==0)
        {
            MainActivity ma = (MainActivity)ctx;
            TextView tvNoItem = ma.findViewById(R.id.NoItemsText);
            tvNoItem.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view==null)
            view = inflater.inflate(R.layout.custom_list_item , parent, false);

        ItemObject iObject = getObjectItem(position);
        TextView tvName = view.findViewById(R.id.name);
        ToggleButton tbIsFav = view.findViewById(R.id.ButtonAddtoFav);
        ImageView ivImage = view.findViewById(R.id.image);
        TextView tvPrice = view.findViewById(R.id.Price);
        TextView tvDescription = view.findViewById(R.id.Description);

        tvName.setText(iObject.Name);


        tvPrice.setText(iObject.getStringPrice());
        tvDescription.setText(iObject.ShortDescription);

        return view;
    }

    public ItemObject getObjectItem(int position)
    {
        return (ItemObject)getItem(position);
    }
}
