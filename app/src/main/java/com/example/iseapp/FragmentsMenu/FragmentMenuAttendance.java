package com.example.iseapp.FragmentsMenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iseapp.R;


import java.util.HashMap;
import java.util.Map;

public class FragmentMenuAttendance extends Fragment
{
    private RequestQueue requestQueue;
    EditText editTextID, editTextDateOfBrith;
    Button buttonPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_attendance, container, false);

        editTextID = (EditText) view.findViewById(R.id.edittext_post_studentid);
        editTextDateOfBrith = (EditText) view.findViewById(R.id.edittext_post_dateofbirth);
        buttonPost = (Button) view.findViewById(R.id.button_post_attendance);

        editTextID.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!isEmpty(editTextDateOfBrith))
                {
                    buttonPost.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editTextDateOfBrith.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(!isEmpty(editTextID))
                {
                    buttonPost.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        buttonPost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String id = editTextID.getText().toString().trim();
                String date = editTextDateOfBrith.getText().toString().trim();

                String data =
                        "{"+
                        "\"studentCode\"" + "\"" + id + "\","+
                        "\"birthDate\"" + "\"" + date + "\""+
                        "}";

                postNewComment(getContext(), id, date);
            }
        });

        return view;
    }

    public static void postNewComment(Context context, final String id, final String date)
    {
            String url = "https://iseireland.ie/cms/api/attendance";
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            // error
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("studentCode", id);
                    params.put("birthDate", date);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(postRequest);
    }

    private boolean isEmpty(EditText etText)
    {
        return etText.getText().toString().trim().length() == 0;
    }
}
