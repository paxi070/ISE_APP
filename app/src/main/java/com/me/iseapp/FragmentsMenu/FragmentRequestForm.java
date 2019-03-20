package com.me.iseapp.FragmentsMenu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.me.iseapp.R;
import com.me.iseapp.ToastCustomized;
import com.thomashaertel.widget.MultiSpinner;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentRequestForm extends Fragment
{
    private MultiSpinner spinner;
    private ArrayAdapter<String> adapter;
    private static ArrayList<String> requestAux;

    static EditText editText_requestform_name,
            editText_requestform_studentid,
            editText_requestform_passportnumber,
            editText_requestform_birthdate,
            editText_requestform_phonenumber,
            editText_requestform_emailaddress,
            editText_requestform_homeaddress,
            editText_requestform_coursestartdate,
            editText_requestform_courseenddate,
            editText_requestform_notes;

    static SearchableSpinner spinner_requestform_nationality;

    Button button_sumbit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        requestAux = new ArrayList<>();

        final DatePickerDialog datePickerDialogBirth = new DatePickerDialog(getContext());
        final DatePickerDialog datePickerDialogStart = new DatePickerDialog(getContext());
        final DatePickerDialog datePickerDialogEnd = new DatePickerDialog(getContext());

        View view = inflater.inflate(R.layout.fragment_menu_requestform, container, false);

        //region Inicialize

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item);
        adapter.add("Insurance");
        adapter.add("Bank Letter");
        adapter.add("Visa Extension");
        adapter.add("Reference Letter");
        adapter.add("Attendance Letter");
        adapter.add("Exit Letter");
        adapter.add("Return to Class");
        adapter.add("Holiday*");
        adapter.add("Attendance Queries");
        adapter.add("Refund");
        adapter.add("Learner Protection");
        adapter.add("Appeal Against Expulsion");

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
        button_sumbit                           = (Button) view.findViewById(R.id.button_requestform_sumbit);

        spinner = (MultiSpinner) view.findViewById(R.id.spinnerMulti);
        spinner.setAdapter(adapter, false, onSelectedListener);


        /*boolean[] selectedItems = new boolean[adapter.getCount()];
        selectedItems[1] = true; // select second item
        spinner.setSelected(selectedItems);*/


        editText_requestform_birthdate.setInputType(InputType.TYPE_NULL);
        editText_requestform_coursestartdate.setInputType(InputType.TYPE_NULL);
        editText_requestform_courseenddate.setInputType(InputType.TYPE_NULL);

        spinner_requestform_nationality.setTitle("Select Item");
        spinner_requestform_nationality.setPositiveButton("OK");

        //endregion

        //region Validation
        /*
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
        */
        //endregion

        //region DatePicker

        editText_requestform_birthdate.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    datePickerDialogBirth.show();
                }
            }
        });

        editText_requestform_coursestartdate.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    datePickerDialogStart.show();
                }
            }
        });

        editText_requestform_courseenddate.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                    datePickerDialogEnd.show();
                }
            }
        });

        //endregion

        //region Click


        datePickerDialogBirth.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                editText_requestform_birthdate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        datePickerDialogStart.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                editText_requestform_coursestartdate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        datePickerDialogEnd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                editText_requestform_courseenddate.setText(dayOfMonth + "/" + month + "/" + year);
            }
        });

        button_sumbit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if(editText_requestform_name.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_name.setError("This field can't be empty");
                    }

                    if(editText_requestform_studentid.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_studentid.setError("This field can't be empty");
                    }

                    if(editText_requestform_passportnumber.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_passportnumber.setError("This field can't be empty");
                    }

                    if(editText_requestform_phonenumber.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_phonenumber.setError("This field can't be empty");
                    }

                    if(editText_requestform_emailaddress.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_emailaddress.setError("This field can't be empty");
                    }

                    if(editText_requestform_homeaddress.getText().toString().trim().length() == 0)
                    {
                        editText_requestform_homeaddress.setError("This field can't be empty");
                    }

                    if (!editText_requestform_emailaddress.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))
                    {
                        editText_requestform_emailaddress.setError("This email is not valid");
                    }

                    if (spinner_requestform_nationality.getSelectedItem().equals(""))
                    {
                        Toast.makeText(getContext(), "You must set your nationality", Toast.LENGTH_LONG).show();
                    }

                    if (requestAux.isEmpty())
                    {
                        spinner.setError("This email is not valid");
                    }

                    if(!requestAux.isEmpty()
                            && !spinner_requestform_nationality.getSelectedItem().toString().trim().equals("")
                            && editText_requestform_homeaddress.getText().toString().trim().length() != 0
                            && editText_requestform_phonenumber.getText().toString().trim().length() != 0
                            && editText_requestform_passportnumber.getText().toString().trim().length() != 0
                            && editText_requestform_studentid.getText().toString().trim().length() != 0
                            && editText_requestform_emailaddress.getText().toString().trim().length() != 0
                            && editText_requestform_emailaddress.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")
                            && editText_requestform_name.getText().toString().trim().length() != 0)
                    {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                switch (which)
                                {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        postNewComment(getContext(), getLayoutInflater());
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //clearForm();
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Are you sure you want to send this request?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                }
                catch (NullPointerException e)
                {
                    Toast.makeText(getContext(), "You must set your nationality", Toast.LENGTH_LONG).show();
                }
            }
        });

        //endregion

        getData();

        return view;
    }

    //region ListaRequest

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener()
    {
        public void onItemsSelected(boolean[] selected)
        {
            requestAux = new ArrayList<>();

            for(int i=0; i<selected.length; i++)
            {
                if(selected[i])
                {
                    requestAux.add(adapter.getItem(i));
                }
            }
        }
    };

    //endregion

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

        adapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);

        spinner_requestform_nationality.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    //endregion

    //region APIpostData

    public static void postNewComment(final Context context, final LayoutInflater layoutInflater)
    {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest sr = new StringRequest(Request.Method.POST,"https://iseireland.ie/api/v1/student-request/save", new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                new ToastCustomized().setInfoToast(layoutInflater, context,
                        "Thank you for your request. We'll get back to you soon as possible");

                clearForm();

                //mPostCommentResponse.requestCompleted();
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

                if(editText_requestform_courseenddate.getText().toString().trim().length() != 0)
                {
                    params.put("courseEndDate", editText_requestform_courseenddate.getText().toString());
                }

                params.put("studentBirthDate", editText_requestform_birthdate.getText().toString());
                params.put("studentCode", editText_requestform_studentid.getText().toString());
                params.put("studentEmail", editText_requestform_emailaddress.getText().toString());
                params.put("studentHomeAddress", editText_requestform_homeaddress.getText().toString());
                params.put("studentName", editText_requestform_name.getText().toString());
                params.put("studentNationality", spinner_requestform_nationality.getSelectedItem().toString());

                if(editText_requestform_notes.getText().toString().trim().length() != 0)
                {
                    params.put("studentNotes", editText_requestform_notes.getText().toString());
                }

                params.put("studentPassportNumber", editText_requestform_passportnumber.getText().toString());
                params.put("studentPhone", editText_requestform_phonenumber.getText().toString());

                String request = "";

                for (int i = 0; i < requestAux.size(); i++)
                {
                    request += requestAux.get(i) + ", ";
                }

                params.put("subject", request);

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

    //region ClearForm

    public static void clearForm()
    {
        editText_requestform_name.setText("");
        editText_requestform_studentid.setText("");
        editText_requestform_passportnumber.setText("");
        editText_requestform_birthdate.setText("");
        editText_requestform_phonenumber.setText("");
        editText_requestform_emailaddress.setText("");
        editText_requestform_homeaddress.setText("");
        editText_requestform_coursestartdate.setText("");
        editText_requestform_courseenddate.setText("");
        editText_requestform_notes.setText("");
    }

    //endregion
}

