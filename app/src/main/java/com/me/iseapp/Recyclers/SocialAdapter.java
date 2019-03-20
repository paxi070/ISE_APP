package com.me.iseapp.Recyclers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.me.iseapp.Models.Event;
import com.me.iseapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder>
{

    private Context context;
    private List<Event> list;
    private Activity activity;

    public SocialAdapter(Context context, List<Event> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recylcerview_social_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Event event = list.get(position);

        holder.textview_social_title.setText(event.getTitle());
        holder.textview_social_description.setText(event.getDescription());
        holder.textview_social_place.setText(event.getPlace());
        holder.textview_social_starttime.setText(event.getStartTime());

        String year = event.getDateTime().substring(0, 4);
        String month = event.getDateTime().substring(5, 7);
        String day = event.getDateTime().substring(8, 10);

        String fecha = day + "/" + month;

        holder.textview_social_datetime.setText(fecha);

        Picasso.get().load(event.getPhoto()).into(holder.imageView_social_photo);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView
                textview_social_title,
                textview_social_description,
                textview_social_place,
                textview_social_starttime,
                textview_social_datetime;

        public ImageView imageView_social_photo;

        public ViewHolder(View view)
        {
            super(view);
            imageView_social_photo      = (ImageView) view.findViewById(R.id.imageView_social_photo);
            textview_social_title       = (TextView) view.findViewById(R.id.textview_social_title);
            textview_social_description = (TextView) view.findViewById(R.id.textview_social_description);
            textview_social_place       = (TextView) view.findViewById(R.id.textview_social_place);
            textview_social_starttime   = (TextView) view.findViewById(R.id.textview_social_starttime);
            textview_social_datetime    = (TextView) view.findViewById(R.id.textview_social_datetime);
        }
    }
}
