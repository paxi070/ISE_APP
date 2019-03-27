package com.me.iseapp.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.iseapp.FragmentsMenu.FragmentMenuNews;
import com.me.iseapp.R;
import com.squareup.picasso.Picasso;

public class ActivitySeeNew extends AppCompatActivity
{
    Toolbar toolbar;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_see_new);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView) findViewById(R.id.imageView_news_photo);
        textView = (TextView) findViewById(R.id.textview_news_shortdescp);

        Bundle extras = getIntent().getExtras();
        String data_news_shortDesc = extras.getString("data_news_shortDesc");
        String data_news_description = extras.getString("data_news_description");
        String data_news_thumbnailPath = extras.getString("data_news_thumbnailPath");
        String data_news_imagePath = extras.getString("data_news_imagePath");

        Picasso.get().load(data_news_thumbnailPath).into(imageView);
        textView.setText(data_news_description);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

    }
}
