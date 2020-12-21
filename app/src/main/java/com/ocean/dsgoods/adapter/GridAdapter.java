package com.ocean.dsgoods.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ocean.dsgoods.R;
import com.ocean.dsgoods.tools.GlideCircleTransform;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;
        private Context context;

        public GridAdapter(Context context) {
            this.context = context;

        }

        public void setData(ArrayList<String> listUrls){
            this.listUrls = listUrls;
            if (listUrls.size() == 7) {
                listUrls.remove(listUrls.size() - 1);
            }
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_image, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);
            if (path.equals("000000")) {     //加载添加图片按钮
                holder.image.setImageResource(R.mipmap.icon_upload_one);
            } else {     //加载图片
                Glide.with(context).load(listUrls.get(position)).into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }