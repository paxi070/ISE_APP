package com.example.iseapp.Recyclers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.iseapp.Models.Course;
import com.example.iseapp.R;


import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder>
{
    private Context context;
    private List<Course> list;

    public CourseAdapter(Context context, List<Course> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(context).inflate(R.layout.recylcerview_courses_line, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Course course = list.get(position);

        holder.textView_tittle.setText(course.getTitle());
        //holder.textView_subTittle.setText(" Sub Tittle: " + course.getSubTittle());
        //holder.textView_fullTittle.setText(" Tittle: " + course.getFullTittle());
        holder.textView_weeklyHours.setText(" Weekly Hours: " + course.getWeeklyHours());
        holder.textView_minAge.setText(" Min Age: " + course.getMinAge());
        holder.textView_minWeeks.setText(" Min Weeks: " + course.getMinWeeks());
        holder.textView_classSize.setText(" Class Size: " + course.getClassSize());
        holder.textView_levelName.setText(" Level: " + course.getLevelName() + " (" + course.getLevelCode() + ")");
        //holder.textView_levelCode.setText(" Level Code: " + course.getLevelCode());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public  TextView
                textView_tittle,
                textView_subTittle,
                textView_fullTittle,
                textView_weeklyHours,
                textView_minAge,
                textView_minWeeks,
                textView_classSize,
                textView_levelName,
                textView_levelCode;

        public ViewHolder(View view)
        {
            super(view);
            textView_tittle         = (TextView) view.findViewById(R.id.textView_tittle);
            //textView_subTittle      = (TextView) view.findViewById(R.id.textView_subTittle);
            //textView_fullTittle     = (TextView) view.findViewById(R.id.textView_fullTittle);
            textView_weeklyHours    = (TextView) view.findViewById(R.id.textView_weeklyHours);
            textView_minAge         = (TextView) view.findViewById(R.id.textView_minAge);
            textView_minWeeks       = (TextView) view.findViewById(R.id.textView_minWeeks);
            textView_classSize      = (TextView) view.findViewById(R.id.textView_classSize);
            textView_levelName      = (TextView) view.findViewById(R.id.textView_levelName);
            //textView_levelCode      = (TextView) view.findViewById(R.id.textView_levelCode);
        }
    }
}