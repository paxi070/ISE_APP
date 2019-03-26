package com.me.iseapp.FragmentsMenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.me.iseapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentMenuAttendance extends Fragment
{
    WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_attendance, container, false);

        /*Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://iseireland.ie/cms/attendance"));
        startActivity(browserIntent);*/

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        final Timer t = new Timer();
        t.schedule(new TimerTask()
        {
            public void run()
            {
                progressDialog.dismiss();
                t.cancel();
            }
        }, 2000);


        mWebView = (WebView) view.findViewById(R.id.activity_main_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://iseireland.ie/cms/attendance");

        return view;
    }
}
