package com.example.iseapp;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by edge on 19/01/18.
 * http://www.androidinterview.com/android-toast-creating-a-android-custom-toast-example/
 */

public class ToastCustomized
{

    public void setInfoToast(LayoutInflater inflater, Context context, String message)
    {

        try
        {

            View customToastroot = inflater.inflate(R.layout.toast_info, null);

            Toast customtoast = new Toast(context);

            TextView messageText = (TextView)customToastroot .findViewById(R.id.textToastInfo);
            messageText.setText(message);

            customtoast.setView(customToastroot);
            customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            customtoast.setDuration(Toast.LENGTH_LONG);
            customtoast.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*public void setWarningToast(LayoutInflater inflater, Context context, String message)
    {

        try
        {
            View customToastroot = inflater.inflate(R.layout.layout_toast_warning, null);

            Toast customtoast = new Toast(context);

            TextView messageText = (TextView)customToastroot .findViewById(R.id.textToastWarning);
            messageText.setText(message);

            customtoast.setView(customToastroot);
            customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            customtoast.setDuration(Toast.LENGTH_SHORT);
            customtoast.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setAlertToast(LayoutInflater inflater, Context context, String message)
    {

        try
        {
            View customToastroot = inflater.inflate(R.layout.layout_toast_alert, null);

            Toast customtoast = new Toast(context);

            TextView messageText = (TextView)customToastroot .findViewById(R.id.textToastAlert);
            messageText.setText(message);

            customtoast.setView(customToastroot);
            customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
            customtoast.setDuration(Toast.LENGTH_SHORT);
            customtoast.show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/
}
