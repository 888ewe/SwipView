package com.chenmo.swipemenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
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
            public void removeItem(SlideListView.RemoveDirection direction, int position, boolean startSlide) {
                Log.e("main", "direction" + direction + "   position" + position + "  startSlide" + startSlide);
                ImageView img_left= (ImageView) findViewById(R.id.img_left);//获得listview
                if (startSlide) {
                    RotateAnimation rotate = new RotateAnimation(0, 90f, Animation.RELATIVE_TO_SELF,
                            0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(3000);
                    img_left.setAnimation(rotate);
                    rotate.start();
                }else {
                }
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

//    Toast.makeText(MainActivity.this, "you", Toast.LENGTH_SHORT).show();
//    RotateAnimation rotate2=new RotateAnimation(0,180f,Animation.RELATIVE_TO_SELF,
//            0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//    rotate2.setFillAfter(true);
//    rotate2.setDuration(100);
//    holder.img_left.setAnimation(rotate2);            //设置动画
//    rotate2.startNow();

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
