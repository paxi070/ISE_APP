package com.me.iseapp.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.iseapp.R;
import com.squareup.picasso.Picasso;

public class ActivitySeeSocial  extends AppCompatActivity
{
    Toolbar toolbar;
    ImageView imageView_social_photo;
    TextView textview_social_tittle,
            textview_social_description,
            textview_social_place,
            textview_social_starttime,
            textview_social_datetime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_see_social);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView_social_photo = (ImageView) findViewById(R.id.imageView_social_photo);
        textview_social_tittle = (TextView) findViewById(R.id.textview_social_tittle);
        textview_social_place = (TextView) findViewById(R.id.textview_social_place);
        textview_social_description = (TextView) findViewById(R.id.textview_social_description);
        textview_social_starttime = (TextView) findViewById(R.id.textview_social_starttime);
        textview_social_datetime = (TextView) findViewById(R.id.textview_social_datetime);

        Bundle extras = getIntent().getExtras();
        String data_social_datetime = extras.getString("data_social_datetime");
        String data_social_description = extras.getString("data_social_description");
        String data_social_photo = extras.getString("data_social_photo");
        String data_social_place = extras.getString("data_social_place");
        String data_social_starttime = extras.getString("data_social_starttime");
        String data_social_tittle = extras.getString("data_social_tittle");

        Picasso.get().load(data_social_photo).into(imageView_social_photo);
        textview_social_tittle.setText(data_social_tittle);
        textview_social_description.setText(data_social_description);
        textview_social_place.setText(data_social_place);
        textview_social_starttime.setText(data_social_starttime);

        String year = data_social_datetime.substring(0, 4);
        String month = data_social_datetime.substring(5, 7);
        String day = data_social_datetime.substring(8, 10);

        String fecha = day + "/" + month + "/" + year;

        textview_social_datetime.setText(fecha);


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

