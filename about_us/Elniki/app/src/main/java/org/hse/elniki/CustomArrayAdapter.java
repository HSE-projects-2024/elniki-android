package org.hse.elniki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;


public class CustomArrayAdapter extends ArrayAdapter<ListItemClass> {
    private LayoutInflater inflater;
    private List<ListItemClass> listItem = new ArrayList<>();
    private Context context;


    public CustomArrayAdapter(@NonNull Context context, int resource, List<ListItemClass> listItem,LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemClass listItemMain = listItem.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_view_item_1, null, false);
            viewHolder = new ViewHolder();
            viewHolder.data1 = convertView.findViewById(R.id.tvData1);
            viewHolder.data2 = convertView.findViewById(R.id.tvData2);
            viewHolder.data3 = convertView.findViewById(R.id.tvData3);
            viewHolder.data4 = convertView.findViewById(R.id.tvData4);
            viewHolder.data5 = convertView.findViewById(R.id.tvData5);
            viewHolder.data6 = convertView.findViewById(R.id.tvData6);
            viewHolder.data7 = convertView.findViewById(R.id.tvData7);
            convertView.setTag(viewHolder);



        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.data1.setText(listItemMain.getData_1());
        viewHolder.data2.setText(listItemMain.getData_2());
        viewHolder.data3.setText(listItemMain.getData_3());
        viewHolder.data4.setText(listItemMain.getData_4());
        viewHolder.data5.setText(listItemMain.getData_5());
        viewHolder.data6.setText(listItemMain.getData_6());
        viewHolder.data7.setText(listItemMain.getData_7());

        return convertView;
    }
    private class ViewHolder{
        TextView data1;
        TextView data2;
        TextView data3;
        TextView data4;
        TextView data5;
        TextView data6;
        TextView data7;
    }
}