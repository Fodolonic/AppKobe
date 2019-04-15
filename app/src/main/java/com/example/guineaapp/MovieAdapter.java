package com.example.guineaapp;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class MovieAdapter extends ArrayAdapter<ResultMovie>{
    private List<ResultMovie> itemList;
    private Context context;
    private Integer Int;


    public MovieAdapter(List<ResultMovie> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }
    public List<ResultMovie> getItemList() {
        return itemList;
    }

    public void setItemList(List<ResultMovie> itemList) {
        this.itemList = itemList;
    }

    public void addItem(ResultMovie item) {this.itemList.add(item);}

    public List<ResultMovie> appendList(List<ResultMovie> itemList){
        this.itemList.addAll(itemList);
        return this.itemList;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View rowView= inflater.inflate(R.layout.list_single , null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(itemList.get(position).getName());

        imageView.setImageBitmap(itemList.get(position).bmPoster);
        return rowView;
    }
}
