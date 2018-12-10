package com.qh.xuezhimin.week0220181207.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qh.xuezhimin.week0220181207.R;
import com.qh.xuezhimin.week0220181207.bean.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyListDataAdapter extends BaseAdapter {

    private List<Data> mListData;
    private Context mContext;

    public MyListDataAdapter(List<Data> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.item_list_data, null);

            holder.text_View = convertView.findViewById(R.id.txt_list_data_title);

            holder.image_View = convertView.findViewById(R.id.imageview);
            holder.image_View1 = convertView.findViewById(R.id.imageview1);
            holder.image_View2 = convertView.findViewById(R.id.imageview2);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();

        }

        holder.text_View.setText(mListData.get(position).getTitle());
        Picasso.with(mContext).load(mListData.get(position).getThumbnail_pic_s02()).into(holder.image_View);
        Picasso.with(mContext).load(mListData.get(position).getThumbnail_pic_s03()).into(holder.image_View2);
        Picasso.with(mContext).load(mListData.get(position).getThumbnail_pic_s()).into(holder.image_View1);

        return convertView;
    }

    class Holder {
        TextView text_View;
        ImageView image_View;
        ImageView image_View1;
        ImageView image_View2;


    }

}
