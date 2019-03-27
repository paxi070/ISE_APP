package com.me.iseapp.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.me.iseapp.Models.News;
import com.me.iseapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>
{
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    private Context context;
    private List<News> list;

    public NewsAdapter(Context context, List<News> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.recylcerview_news_line, parent, false);
        return new NewsAdapter.ViewHolder(v, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        News news = list.get(position);

        holder.textview_news_shortdescp.setText(news.getShortDesc());

        Picasso.get().load(news.getImagePath()).into(holder.imageView_news_photo);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textview_news_shortdescp;
        public ImageView imageView_news_photo;

        public ViewHolder(View view, final OnItemClickListener onItemClickListener)
        {
            super(view);
            textview_news_shortdescp    = (TextView) view.findViewById(R.id.textview_news_shortdescp);
            imageView_news_photo        = (ImageView) view.findViewById(R.id.imageView_news_photo);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(onItemClickListener != null)
                    {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION)
                        {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
