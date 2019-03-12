package com.example.iseapp.FragmentsMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iseapp.R;
import com.example.iseapp.ToastCustomized;

import java.util.HashMap;
import java.util.Map;

import static com.example.iseapp.FragmentsMenu.FragmentMenuContact.editText_FirstName;
import static com.example.iseapp.FragmentsMenu.FragmentMenuContact.editText_emailAddress;

public class FragmentMenuNewsLetter extends Fragment
{
    static EditText editText_newsletter_name,
            editText_newsletter_emailaddres;

    Button button_subscribe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_newsletter, container, false);

        editText_newsletter_name = (EditText) view.findViewById(R.id.editText_newsletter_name);
        editText_newsletter_emailaddres = (EditText) view.findViewById(R.id.editText_newsletter_emailaddres);
        button_subscribe = (Button) view.findViewById(R.id.button_newsletter_subscribe);

        //region Validation

        editText_newsletter_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_newsletter_emailaddres.getText().toString().trim().length() != 0)
                {
                    button_subscribe.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_newsletter_emailaddres.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_newsletter_name.getText().toString().trim().length() != 0)
                {
                    button_subscribe.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        //endregion

        //region Click

        button_subscribe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(editText_newsletter_name.getText().toString().trim().length() == 0)
                {
                    editText_newsletter_name.setError("This field can't be empty");
                }
                else if(editText_newsletter_emailaddres.getText().toString().trim().length() == 0)
                {
                    editText_newsletter_emailaddres.setError("This field can't be empty");
                }
                else if (!editText_newsletter_emailaddres.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                {
                    editText_newsletter_emailaddres.setError("This email is not valid");
                }
                else
                {
                    postNewComment(getContext(), getLayoutInflater());
                }
            }
        });

        //endregion

        return  view;
    }

    //region API_POST_DATA

    public static void postNewComment(final Context context, final LayoutInflater layoutInflater)
    {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST,"https://iseireland.ie/api/v1/subscriber/save", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                //mPostCommentResponse.requestCompleted();

                String a = response;

                new ToastCustomized().setInfoToast(layoutInflater, context, "Thank you for subscribing with us");

                clearForm();
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //mPostCommentResponse.requestEndedWithError(error);
            }
        })
        {
            @Override
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String, String>();
                params.put("firstName", editText_newsletter_name.getText().toString());
                params.put("email", editText_newsletter_emailaddres.getText().toString());

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        queue.add(sr);
    }

    //endregion

    public static void clearForm()
    {
        editText_newsletter_name.setText("");
        editText_newsletter_emailaddres.setText("");
    }
}
