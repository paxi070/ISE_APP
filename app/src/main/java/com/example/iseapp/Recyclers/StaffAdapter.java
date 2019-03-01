package com.example.iseapp.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.iseapp.Models.Staff;
import com.example.iseapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.ViewHolder>
{
    private Context context;
    private List<Staff> list;

    public StaffAdapter(Context context, List<Staff> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_staff_line, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Staff staff = list.get(position);

        holder.textview_name.setText(staff.getName());
        holder.textview_designation.setText(staff.getDesignation());
        holder.textview_description.setText(staff.getDescription());
        holder.textview_email.setText(staff.getEmail());

        Picasso.get().load(staff.getPhotoURL()).into(holder.imageview_photo);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

public class ViewHolder extends RecyclerView.ViewHolder
{
    public ImageView imageview_photo;

    public TextView
            textview_name,
            textview_designation,
            textview_description,
            textview_email;

    public ViewHolder(View view)
    {
        super(view);
        textview_name           = (TextView) view.findViewById(R.id.textview_name);
        textview_designation    = (TextView) view.findViewById(R.id.textview_designation);
        textview_description    = (TextView) view.findViewById(R.id.textview_description);
        textview_email          = (TextView) view.findViewById(R.id.textview_email);
        imageview_photo         = (ImageView) view.findViewById(R.id.imageview_photo);;
    }
}
}