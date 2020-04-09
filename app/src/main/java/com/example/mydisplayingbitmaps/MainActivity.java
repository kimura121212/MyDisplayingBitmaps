package com.example.mydisplayingbitmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LruCache<String, Bitmap> mMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView mGridView = (GridView)findViewById(R.id.gridView);
        mGridView.setNumColumns(3);
        final List<ImageView> listImageView = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320));
            Picasso.with(this)
                    .load(Uri.parse("https://progedu.github.io/asset/spapp-curriculum/images/sample.jpg"))
                    .placeholder(R.mipmap.ic_launcher)
                    .resize(180, 320)
                    .into(imageView);
            listImageView.add(imageView);
        }

        BaseAdapter mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listImageView.size();
            }

            @Override
            public Object getItem(int i) {
                return listImageView.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return listImageView.get(i);
            }
        };
        mGridView.setAdapter(mAdapter);
    }

}