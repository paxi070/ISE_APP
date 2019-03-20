package com.me.iseapp.Recyclers;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.me.iseapp.CustomDialogStaff;
import com.me.iseapp.Models.Staff;
import com.me.iseapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder>
{

    private Context context;
    private List<Staff> list;
    private Activity activity;

    public StaffAdapter(Context context, List<Staff> list, Activity activity)
    {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_staff_line, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Staff staff = list.get(position);

        Picasso.get().load(staff.getPhotoURL()).into(holder.imageview_photo);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageview_photo;

        public ViewHolder(View view)
        {
            super(view);
            imageview_photo = (ImageView) view.findViewById(R.id.imageview_photo);

            imageview_photo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    CustomDialogStaff customDialogStaff = new CustomDialogStaff(activity, list.get(getAdapterPosition()));
                    customDialogStaff.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    customDialogStaff.show();
                }
            });
        }
    }

}