package com.me.iseapp.FragmentsMenu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.me.iseapp.Activities.ActivitySeeNew;
import com.me.iseapp.Activities.ActivitySeeSocial;
import com.me.iseapp.Models.Event;
import com.me.iseapp.R;
import com.me.iseapp.Recyclers.NewsAdapter;
import com.me.iseapp.Recyclers.SocialAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentMenuSocial extends Fragment
{
    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Event> eventList;
    private SocialAdapter adapter;

    RequestQueue rq;
    String url = "http://iseireland.ie/api/v1/event/all";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_menu_social, container, false);

        rq = Volley.newRequestQueue(getContext());

        mList = view.findViewById(R.id.recyclerview_social);

        eventList = new ArrayList<>();
        adapter = new SocialAdapter(getContext(), eventList);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        //mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(int position)
            {
                Context context = getContext();
                Intent intent = new Intent(context, ActivitySeeSocial.class);
                intent.putExtra("data_social_datetime", eventList.get(position).getDateTime());
                intent.putExtra("data_social_description", eventList.get(position).getDescription());
                intent.putExtra("data_social_photo", eventList.get(position).getPhoto());
                intent.putExtra("data_social_place", eventList.get(position).getPlace());
                intent.putExtra("data_social_starttime", eventList.get(position).getStartTime());
                intent.putExtra("data_social_tittle", eventList.get(position).getTitle());
                context.startActivity(intent);
            }
        });


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
                                JSONObject event = dataset.getJSONObject(i);

                                String year = event.getString("dateTime").substring(0, 4);
                                String month = event.getString("dateTime").substring(5, 7);
                                String day = event.getString("dateTime").substring(8, 10);

                                String fecha = day + "/" + month + "/" + year;

                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                                String str1 = fecha;
                                Date selectedDate = sdf.parse(str1);

                                Calendar c = Calendar.getInstance();
                                String formattedDate = sdf.format(c.getTime());

                                Date currentDate = sdf.parse(formattedDate);

                                if (selectedDate.compareTo(currentDate) > 0)
                                {
                                    Event event1 = new Event();
                                    event1.setTitle(event.getString("title"));
                                    event1.setPhoto(event.getString("photo"));
                                    event1.setDescription(event.getString("description"));
                                    event1.setPlace(event.getString("place"));
                                    event1.setStartTime(event.getString("startTime"));
                                    event1.setDateTime(event.getString("dateTime"));

                                    eventList.add(event1);
                                }
                            }

                            adapter.notifyDataSetChanged();
                            progressDialog.dismiss();
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            progressDialog.dismiss();
                        } catch (ParseException e) {
                            e.printStackTrace();
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
