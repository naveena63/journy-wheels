package in.journeywheels.www.jw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.journeywheels.www.jw.pojoclass.Vehicleslist;
import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;

public class SelectvehicalActivity extends AppCompatActivity {

    TextView back,vechiles,date1,date2,time1,time2;
    RecyclerView recyclerView;
    SelectvehicalAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Select_vehical_list> dealers=null;
    public String type;
    public String inbikes="1";
    public String incars="2";
    public String incycles="3";
    ArrayList<Vehicleslist>select;
    String usid;
    Session session;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectvehical);
        vechiles=(TextView)findViewById(R.id.vechiles);

        date1=(TextView)findViewById(R.id.bkselect_date1);
        date2=(TextView)findViewById(R.id.select_date2);
        time1=(TextView)findViewById(R.id.bkselect_time1);
        time2=(TextView)findViewById(R.id.select_time2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
        if (name.isEmpty()) {
            usid="0";
        }else {

//            Gson gson = new Gson();
//            final Loginpojo userObj = gson.fromJson(response, Loginpojo.class);
//            sharedprefrence.getLoginResponse(this.getApplicationContext());
//
//            try {
//                usid = userObj.getUser().getId().toString();
            usid=name;
                vechiles.setText(usid);

//
//            }catch (NullPointerException ignored){
//
//
//            }
        }
        Log.d("seleid", String.valueOf(usid));

        final String vechid= vechiles.getText().toString();


        Bundle bundle = getIntent().getExtras();
        final String pcity = bundle.getString("selectcity");
        final String pcityid=bundle.getString("cityid");
        final String ppickup=bundle.getString("selectpickup");
        final String pdropup=bundle.getString("selectdrop");
        final  String pdate1=bundle.getString("fromdate1");
        final String pdate2=bundle.getString("fromdate2");
        final  String pmounth1=bundle.getString("frommounth1");
        final String pmounth2=bundle.getString("frommounth2");
        final String ptime1=bundle.getString("formtime1");
        final  String ptime2=bundle.getString("formtime2");
        final String psyear1=bundle.getString("year1");
        final  String psyear2=bundle.getString("year2");
        final String psvechile=bundle.getString("vechiletype");
        final String pmon1=bundle.getString("mon1");
        final String pmon2=bundle.getString("mon2");


        SharedPreferences selectpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = selectpreferences.edit();
        editor.putString("selectcity",pcity);
        editor.putString("city_id", pcityid);
        editor.putString("select_pickup", ppickup);
        editor.putString("select_drop", pdropup);
        editor.putString("from_date1", pdate1);
        editor.putString("from_date2", pdate2);
        editor.putString("from_mounth1", pmounth1);
        editor.putString("from_mounth2", pmounth2);
        editor.putString("fromtime1", ptime1);
        editor.putString("fromtime2", ptime2);
        editor.putString("year1", psyear1);
        editor.putString("year2", psyear2);
        editor.putString("vechile_type", psvechile);
        editor.putString("mon1", pmon1);
        editor.putString("mon2", pmon2);
        editor.apply();
//

        try{
            date1.setText(pdate1+"-"+pmon1+"-"+psyear1);

        }
        catch (Exception e){

        }
        try{

            date2.setText(pdate2+"-"+pmon2+"-"+psyear2);

        }
        catch (Exception e){

        }
        try{

            time1.setText(ptime1);

        }
        catch (Exception e){

        }
        try{

            time2.setText(ptime2);
        }
        catch (Exception e){

        }

        Log.d("sel",pcity+" "+pcityid+" "+ppickup+" "+pdropup+" "+pdate1+" "+pdate2+" "+pmounth1+" "+pmounth2+" "+ptime1+" "+ptime2+" "+psyear1+" "+psyear2+" "+pmon1+" "+pmon2+" "+vechid);

        back=(TextView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectvehicalActivity.this, MainscreenActivity.class);
                startActivity(i);
                finish();

            }
        });


        recyclerView = (RecyclerView)findViewById(R.id.selesctbikelist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("vechile", psvechile);

        if ( psvechile.equals("bikes")){
            type=inbikes;
        }
        else if (psvechile .equals("cars")){
            type=incars;
        }
        else if (psvechile .equals("cycles")){
            type=incycles;
            Log.d("typevec", String.valueOf(type));
        }
        else {
            Toast.makeText(getApplicationContext(), "select city", Toast.LENGTH_SHORT).show();
        }
        Log.d("type", String.valueOf(type));

        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_VECHILES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Log.d("select", response);
                        try {
                            progressBar.setVisibility(View.GONE);
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                select = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("vehicleslist");
                                if (dataArray == null) {
                                    final AlertDialog alertDialog = new AlertDialog.Builder(
                                            SelectvehicalActivity.this).create();

                                    // Setting Dialog Title
                                    alertDialog.setTitle(" Vehicle is UnAvailable");

                                    // Setting Dialog Message
                                    alertDialog.setMessage(" No  Vehicle is UnAvailable ");


                                    // Setting OK Button
                                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(getApplicationContext(), FromActivity.class);
                                            startActivity(intent);
                                            alertDialog.dismiss();

                                        }
                                    });

                                    // Showing Alert Message
                                    alertDialog.show();

                                }
                                else {



                                for (int i = 0; i < dataArray.length(); i++) {
                                    Vehicleslist vec = new Vehicleslist();

                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    Log.d("vehicalList", String.valueOf(dataobj));
//                                    Sublocation subloc = new Sublocation(dataobj.getString("id"),dataobj.getString("subLocationName"));

                                    vec.setId(dataobj.getString("Id"));
                                    vec.setProductName(dataobj.getString("Product Name"));
                                    vec.setProductImage(dataobj.getString("Product Image"));
                                    vec.setPrice(dataobj.getString("Price"));
                                    vec.setKmLimit(dataobj.getString("Km Limit"));
                                    vec.setExcess(dataobj.getString("Excess"));
                                    vec.setAvailability(dataobj.getString("Availability"));
                                    select.add(vec);
                                }

                                SelectvehicalAdapter sub = new SelectvehicalAdapter(getApplicationContext(), select);
                                recyclerView.setAdapter(sub);
                            }
                            } else {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("loc", pcityid);
                params.put("type_id", type);
                params.put("picktime", pdate1+"/"+pmon1+"/"+psyear1+" "+ptime1);
                params.put("droptime",pdate2+"/"+pmon2+"/"+psyear2+" "+ptime2);


                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
