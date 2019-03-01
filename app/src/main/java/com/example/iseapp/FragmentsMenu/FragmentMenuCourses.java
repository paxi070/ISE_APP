package com.example.iseapp.FragmentsMenu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
import com.example.iseapp.Models.Course;
import com.example.iseapp.R;
import com.example.iseapp.Recyclers.CourseAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenuCourses extends Fragment
{
    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Course> coursesList;
    private RecyclerView.Adapter adapter;

    RequestQueue rq;
    String url = "http://iseireland.ie/api/v1/course/all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_courses, container, false);

        rq = Volley.newRequestQueue(getContext());

        mList = view.findViewById(R.id.recyclerview_courses);

        coursesList = new ArrayList<>();
        adapter = new CourseAdapter(getContext(), coursesList);

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
                        JSONObject course = dataset.getJSONObject(i);

                        Course course1 = new Course();
                        course1.setTitle(course.getString("title"));
                        course1.setSubTittle(course.getString("subtitle"));
                        course1.setFullTittle(course.getString("fullTitle"));
                        course1.setWeeklyHours(course.getString("weeklyHours"));
                        course1.setMinAge(course.getString("minAge"));
                        course1.setMinWeeks(course.getString("minWeeks"));
                        course1.setClassSize(course.getString("classSize"));

                        if(course.has("minLevel"))
                        {
                            JSONObject level = course.getJSONObject("minLevel");

                            course1.setLevelName(level.getString("name"));
                            course1.setLevelCode(level.getString("code"));
                        }
                        else
                        {
                            course1.setLevelName("TBA");
                            course1.setLevelCode("TBA");
                        }

                        coursesList.add(course1);
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

