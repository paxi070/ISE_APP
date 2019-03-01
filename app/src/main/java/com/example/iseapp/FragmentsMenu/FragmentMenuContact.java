package com.example.iseapp.FragmentsMenu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.iseapp.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentMenuContact extends Fragment
{
    EditText editText_FirstName, editText_LastName, editText_emailAddress, editText_message;
    SearchableSpinner spinner_nationality;
    Button button_signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_contact, container, false);

        editText_FirstName      = (EditText) view.findViewById(R.id.editText_contact_firstname);
        editText_LastName       = (EditText) view.findViewById(R.id.editText_contact_lastname);
        editText_emailAddress   = (EditText) view.findViewById(R.id.editText_contact_emailaddress);
        editText_message        = (EditText) view.findViewById(R.id.editText_contact_message);
        spinner_nationality     = (SearchableSpinner) view.findViewById(R.id.spinner_contact_nationality);
        button_signup           = (Button) view.findViewById(R.id.button_contact_singup);

        spinner_nationality.setTitle("Select Item");
        spinner_nationality.setPositiveButton("OK");

        //region Validation

        editText_FirstName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_emailAddress.getText().toString().trim().length() != 0
                        && editText_message.getText().toString().trim().length() != 0
                        && !spinner_nationality.getSelectedItem().toString().trim().equals(""))
                {
                    button_signup.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_emailAddress.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_FirstName.getText().toString().trim().length() != 0
                        && editText_message.getText().toString().trim().length() != 0
                        && !spinner_nationality.getSelectedItem().toString().trim().equals(""))
                {
                    button_signup.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_message.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_emailAddress.getText().toString().trim().length() != 0
                        && editText_FirstName.getText().toString().trim().length() != 0
                        && !spinner_nationality.getSelectedItem().toString().trim().equals(""))
                {
                    button_signup.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        spinner_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(editText_emailAddress.getText().toString().trim().length() != 0
                        && editText_FirstName.getText().toString().trim().length() != 0
                        && editText_message.getText().toString().trim().length() != 0)
                {
                    button_signup.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        button_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Validar Email
                //Enviar Datos
            }
        });

        //endregion

        getData();

        return view;
    }

    //region APIgetData

    public void getData()
    {
        String url = "http://iseireland.ie/api/v1/country/all";

        final ArrayList<String> nations = new ArrayList<String>();

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONObject result = response.getJSONObject("result");
                            JSONArray dataset = result.getJSONArray("dataset");

                            for (int i = 0; i < dataset.length(); i++)
                            {
                                JSONObject country = dataset.getJSONObject(i);

                                String nation = country.getString("name");

                                nations.add(nation);
                            }

                            progressDialog.dismiss();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        }
                    }
                },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Log.e("Volley", error.toString());
                                progressDialog.dismiss();
                            }
                        });

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_activated_1,
                        nations);

        adapter.setDropDownViewResource( android.R.layout.simple_list_item_activated_1);

        spinner_nationality.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    //endregion
}
