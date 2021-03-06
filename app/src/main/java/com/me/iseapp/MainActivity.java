package com.me.iseapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.me.iseapp.FragmentsMenu.FragmentMenuAttendance;
import com.me.iseapp.FragmentsMenu.FragmentMenuContact;
import com.me.iseapp.FragmentsMenu.FragmentMenuCourses;
import com.me.iseapp.FragmentsMenu.FragmentMenuNews;
import com.me.iseapp.FragmentsMenu.FragmentMenuNewsLetter;
import com.me.iseapp.FragmentsMenu.FragmentMenuSocial;
import com.me.iseapp.FragmentsMenu.FragmentMenuStaff;
import com.me.iseapp.FragmentsMenu.FragmentRequestForm;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new FragmentMenuNews()).commit();

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //getMenuInflater().inflate(R.menu.corner_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_about)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuNews()).commit();
        }

        if (id == R.id.nav_attendance)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuAttendance()).commit();
        }

        else if (id == R.id.nav_courses)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuCourses()).commit();
        }

        else if (id == R.id.nav_resquestform)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentRequestForm()).commit();
        }

        else if (id == R.id.nav_isesocial)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuSocial()).commit();
        }

        else if (id == R.id.nav_contact)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuContact()).commit();
        }

        else if (id == R.id.nav_staff)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuStaff()).commit();
        }

        else if (id == R.id.nav_newsletter)
        {
            fragmentManager.beginTransaction().replace(
                    R.id.contenedor, new FragmentMenuNewsLetter()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
