package com.me.iseapp.FragmentsMenu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.me.iseapp.Models.Staff;
import com.me.iseapp.R;
import com.me.iseapp.Recyclers.StaffAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenuStaff extends Fragment
{
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private List<Staff> staffList;
    private RecyclerView.Adapter adapter;

    RequestQueue rq;
    String url = "http://iseireland.ie/api/v1/employee/all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_staff, container, false);

        rq = Volley.newRequestQueue(getContext());

        mList = view.findViewById(R.id.recyclerview_staff);
        staffList = new ArrayList<>();

        adapter = new StaffAdapter(getContext(), staffList, getActivity());

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        //mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();

        return view;
    }

    public void getData()
    {
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
                                JSONObject staff = dataset.getJSONObject(i);

                                Staff staff1 = new Staff();
                                staff1.setName(staff.getString("name"));
                                staff1.setDesignation(staff.getString("designation"));
                                staff1.setDescription(staff.getString("description"));
                                staff1.setEmail(staff.getString("email"));
                                staff1.setPhotoURL(staff.getString("photoUrl"));

                                staffList.add(staff1);
                            }

                            adapter.notifyDataSetChanged();
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

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}
