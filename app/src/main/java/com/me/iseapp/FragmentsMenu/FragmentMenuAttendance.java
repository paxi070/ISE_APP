package com.me.iseapp.FragmentsMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.iseapp.R;

public class FragmentMenuAttendance extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_attendance, container, false);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://iseireland.ie/cms/attendance"));
        startActivity(browserIntent);

        return view;
    }
}
