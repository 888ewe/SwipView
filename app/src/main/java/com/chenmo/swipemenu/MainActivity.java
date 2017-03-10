package com.chenmo.swipemenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {


    private SlideListView sidelistView;
    List list;
    SlideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sidelistView = (SlideListView) findViewById(R.id.slidelistView);

        list = new ArrayList();

        for (int i = 0; i < 4; i++) {
            list.add("shishi" + i);
        }

        adapter = new SlideAdapter();
        sidelistView.setAdapter(adapter);

        sidelistView.setRemoveListener(new SlideListView.RemoveListener() {
            @Override
            public void removeItem(SlideListView.RemoveDirection direction, int position) {
                switch (direction) {
                        case LEFT:
                            Toast.makeText(MainActivity.this, "LEFT", Toast.LENGTH_SHORT).show();
                            break;
                        case RIGHT:
                            Toast.makeText(MainActivity.this, "RIGHT", Toast.LENGTH_SHORT).show();
                            break;
                        case NONE:
                            Toast.makeText(MainActivity.this, "NONE", Toast.LENGTH_SHORT).show();
                            break;
                }
            }
        });
    }

    private class SlideAdapter extends BaseAdapter {
        ViewHolder holder;

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.img_left = (ImageView) convertView.findViewById(R.id.img_left);
                holder.img_right = (ImageView) convertView.findViewById(R.id.img_right);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.title.setText(list.get(position).toString());

            return convertView;
        }

        private class ViewHolder {
            TextView title;
            ImageView img_left, img_right;

        }
    }
}
