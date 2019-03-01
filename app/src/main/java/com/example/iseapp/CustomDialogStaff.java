package com.example.iseapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.iseapp.Models.Staff;

public class CustomDialogStaff extends Dialog implements android.view.View.OnClickListener
{
    public Activity activity;
    public Staff staff;
    public TextView textview_name, textview_designation, textview_description, textview_email;
    public Button close;

    public CustomDialogStaff(Activity activity, Staff staff)
    {
        super(activity);
        this.activity = activity;
        this.staff = staff;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.staff_custom_dialog);

        textview_name = (TextView) findViewById(R.id.textview_name);
        textview_designation = (TextView) findViewById(R.id.textview_designation);
        textview_description = (TextView) findViewById(R.id.textview_description);
        textview_email = (TextView) findViewById(R.id.textview_email);

        setData(staff);

        close = (Button) findViewById(R.id.button_close);
        close.setOnClickListener(this);
    }

    public void setData(Staff staff)
    {
        textview_name.setText(staff.getName());
        textview_designation.setText(staff.getDesignation());

        String description = staff.getDescription().substring(3, staff.getDescription().length() - 4);
        textview_description.setText(description);

        textview_email.append(staff.getEmail());
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_close:
                this.dismiss();
                break;

            default:
                break;
        }
        dismiss();
    }
}

