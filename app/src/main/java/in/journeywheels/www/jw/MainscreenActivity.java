package in.journeywheels.www.jw;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import in.journeywheels.www.jw.orders.OrdersMainFragment;

import in.journeywheels.www.jw.pojoclass.Loginpojo;
import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.sharedpref.Sharedprefrence;

public class MainscreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView nav_email,nav_name;
    Session session;
    Sharedprefrence sharedprefrence;
    public String usid;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    HomeFragment frag1=new HomeFragment();
    Myprofile profile=new Myprofile();

    AboutUsFragment about=new AboutUsFragment();
    ContactFragment contact=new ContactFragment();
    OrdersMainFragment ordersFragment=new OrdersMainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
//        nav_name=(TextView)findViewById(R.id.nav_profilename);
//        nav_email=(TextView)findViewById(R.id.nav_profilemail);
//        nav_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"name", Toast.LENGTH_SHORT).show();
//
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        fragmentTransaction.add(R.id.contant,frag1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainscreenActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainscreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String response = sharedprefrence.getLoginResponse(this.getApplicationContext());
        Gson gson = new Gson();
        final Loginpojo userObj = gson.fromJson(response, Loginpojo.class);
        sharedprefrence.getLoginResponse(this.getApplicationContext());
        try {
             usid = userObj.getUser().getId().toString();
            Log.d("Nid", String.valueOf(usid));
        }
        catch (NullPointerException ignored){
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
            fragmentTransaction2.replace(R.id.contant,frag1);
            fragmentTransaction2.commit();

        } else if (id == R.id.nav_myprofile) {
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
            fragmentTransaction1.replace(R.id.contant,profile);
            fragmentTransaction1.commit();
        }
        else if (id == R.id.nav_ourOrders)
        {
            FragmentManager fragmentManager3 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
            fragmentTransaction4.replace(R.id.contant,ordersFragment);
            fragmentTransaction4.commit();
        }
        else if (id == R.id.nav_aboutus) {
            FragmentManager fragmentManager4 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
            fragmentTransaction4.replace(R.id.contant,about);
            fragmentTransaction4.commit();
        }
        else if (id == R.id.nav_passengers) {

        }
        else if (id == R.id.nav_client) {

        }
        else if (id == R.id.nav_partner) {

        }
        else if (id == R.id.nav_contact) {
            FragmentManager fragmentManager5 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction5 = fragmentManager.beginTransaction();
            fragmentTransaction5.replace(R.id.contant,contact);
            fragmentTransaction5.commit();
        }
        else if (id==R.id.nav_Logout){
            session = new Session(this);
            session.logoutUser();
            startActivity(new Intent(this, MainscreenActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}