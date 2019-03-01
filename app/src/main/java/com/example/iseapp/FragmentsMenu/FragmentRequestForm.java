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

public class FragmentRequestForm extends Fragment
{
    EditText editText_requestform_name,
            editText_requestform_studentid,
            editText_requestform_passportnumber,
            editText_requestform_birthdate,
            editText_requestform_phonenumber,
            editText_requestform_emailaddress,
            editText_requestform_homeaddress,
            editText_requestform_coursestartdate,
            editText_requestform_courseenddate,
            editText_requestform_notes;

    SearchableSpinner spinner_requestform_nationality, spinner_requestform_request;

    Button button_sumbit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_requestform, container, false);

        editText_requestform_name               = (EditText) view.findViewById(R.id.editText_requestform_name);
        editText_requestform_studentid          = (EditText) view.findViewById(R.id.editText_requestform_studentid);
        editText_requestform_passportnumber     = (EditText) view.findViewById(R.id.editText_requestform_passportnumber);
        editText_requestform_birthdate          = (EditText) view.findViewById(R.id.editText_requestform_birthdate);
        editText_requestform_phonenumber        = (EditText) view.findViewById(R.id.editText_requestform_phonenumber);
        editText_requestform_emailaddress       = (EditText) view.findViewById(R.id.editText_requestform_emailaddress);
        editText_requestform_homeaddress        = (EditText) view.findViewById(R.id.editText_requestform_homeaddress);
        editText_requestform_coursestartdate    = (EditText) view.findViewById(R.id.editText_requestform_coursestartdate);
        editText_requestform_courseenddate      = (EditText) view.findViewById(R.id.editText_requestform_courseenddate);
        editText_requestform_notes              = (EditText) view.findViewById(R.id.editText_requestform_notes);
        spinner_requestform_nationality         = (SearchableSpinner) view.findViewById(R.id.spinner_requestform_nationality);
        spinner_requestform_request             = (SearchableSpinner) view.findViewById(R.id.spinner_requestform_request);
        button_sumbit                           = (Button) view.findViewById(R.id.button_requestform_sumbit);

        spinner_requestform_nationality.setTitle("Select Item");
        spinner_requestform_nationality.setPositiveButton("OK");

        //region Validation

        editText_requestform_name.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_requestform_studentid.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_requestform_passportnumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_requestform_emailaddress.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_requestform_phonenumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        editText_requestform_homeaddress.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });

        spinner_requestform_nationality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && !spinner_requestform_request.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spinner_requestform_request.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(editText_requestform_name.getText().toString().trim().length() != 0
                        && editText_requestform_studentid.getText().toString().trim().length() != 0
                        && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                        && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                        && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                        && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                        && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals(""))
                {
                    button_sumbit.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        //endregion

        button_sumbit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Validation and Send Data
            }
        });

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

        spinner_requestform_nationality.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    //endregion

}

