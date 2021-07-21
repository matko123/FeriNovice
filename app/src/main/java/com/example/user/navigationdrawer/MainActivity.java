package com.example.user.navigationdrawer;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.user.navigationdrawer.Fragmenti.Glavni_fragment;
import com.example.user.navigationdrawer.Fragmenti.Informacije;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //ko se bo program zagnal, se nam bodo na zacetnem zaslonu prikazala obvestila.
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        Glavni_fragment og = new Glavni_fragment();
        bundle.putString("feed", "obvestila");
        og.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , og)
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId(); // dobimo nas item id, glede zavihek, ki smo ga kliknili
        FragmentManager fragmentManager = getFragmentManager();

        Bundle bundle = new Bundle();

        if (id == R.id.nav_first_layout) { //glede na izbran menu, se poslje dolocen parameter, kot je odeska, novice... S pomocjo katerega lahko prikazemo doloceno stran rss
            Glavni_fragment og = new Glavni_fragment();
            bundle.putString("feed", "odeska");
            og.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , og)
                    .commit();
        } else if (id == R.id.nav_second_layout) {
            Glavni_fragment og = new Glavni_fragment();
            bundle.putString("feed", "novice");
            og.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , og)
                    .commit();
        } else if (id == R.id.nav_third_layout) {
            Glavni_fragment og = new Glavni_fragment();
            bundle.putString("feed", "obvestila");
            og.setArguments(bundle);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , og)
                    .commit();
        }else if (id == R.id.nav_fourth_layout) {
                Glavni_fragment og = new Glavni_fragment();
                bundle.putString("feed", "dogodki");
                og.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame
                                , og)
                        .commit();
        }else if (id == R.id.nav_informacije) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new Informacije())
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
