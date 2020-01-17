package in.journeywheels.www.jw;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import in.journeywheels.www.jw.adapters.CityAdapter;
import in.journeywheels.www.jw.adapters.SublocationAdapter;
import in.journeywheels.www.jw.pojoclass.Location;
import in.journeywheels.www.jw.pojoclass.Sublocation;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;

public class FromActivity extends AppCompatActivity {
    protected static Button showvehical;
    protected static TextView fromdate1, fromdate2, frommounth1, frommounth2, selectcity, selectdrop, selectpickup, fromtext, formtime1, formtime2, year1, year2, vechiletype, cityid, pickupid, dropid, mon1, mon2;
    protected static int date, mounth;
    Context context = this;
    ImageView close;
    TextView done, setlocation;
    AlertDialog.Builder builder;
    Dialog dialog;
    ListView subloct;
    public String type;
    public String inbikes = "1";
    public String incars = "2";
    public String incycles = "3";
    SublocationAdapter sublocationAdapter;
    public GridView gridView;
    public SelectcityAdapters adapter;
    public long totalHours;

    ArrayList<Sublocation> dataModelArrayList;
    public ArrayList<Location> list_data;
    public Integer[] mThumbIds = {
            R.drawable.vijayawada, R.drawable.tirupathi,
            R.drawable.nellore, R.drawable.rajamandry,
            R.drawable.vishakapatnam
    };
    public String[] mcity = {
            "Vijayawada",
            "Tirupathi", "Nellore", "Rajamandry", "Vishakapatnam"
    };
    public String[] mids = {
            "1", "2", "3", "4", "5"
    };
    public String[] msubids = {
            "1", "2", "3"

    };
    public String[] msublocation = {
            "railway station", "bus stand", "market"

    };


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);

        year1 = (TextView) findViewById(R.id.year1);
        year2 = (TextView) findViewById(R.id.year2);
        vechiletype = (TextView) findViewById(R.id.vechiletype);
        String vechiles = getIntent().getStringExtra("vechile");
        vechiletype.setText(vechiles);
        selectcity = (TextView) findViewById(R.id.select_city);
      //  final String city = selectcity.getText().toString();


//        String ctyname= getIntent().getStringExtra("cityname");
//        String ctyid= getIntent().getStringExtra("cityid");

//        if (ctyname.isEmpty()&&ctyid.isEmpty()){
//            selectcity.setText("select city");
//            cityid.setText("select city");
//        }
//        else {
//            selectcity.setText(ctyname);
//            cityid.setText(ctyid);
//        }


        cityid = (TextView) findViewById(R.id.select_city_id);
        selectcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(FromActivity.this, SetLocationActivity.class);
//                startActivity(i);
//                finish();
                dialog();
            }
        });
        selectpickup = (TextView) findViewById(R.id.pickuptxt);
        pickupid = (TextView) findViewById(R.id.pickuptxt_id);
        selectpickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(getBaseContext(), Select_location2.class);
//                intent.putExtra("set_location", "pick up location");
//                intent.putExtra("City", city);
//                startActivity(intent);
                String selectct = selectcity.getText().toString();
                if (selectct.equals("select city")) {
                    Toast.makeText(FromActivity.this, "please select city", Toast.LENGTH_LONG).show();
                } else {
                    subdialog();
                }
            }
        });

        selectdrop = (TextView) findViewById(R.id.select_drop);
        dropid = (TextView) findViewById(R.id.select_drop_id);
        selectdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getBaseContext(), Select_location2.class);
//                intent.putExtra("set_location", "pick up location");
//                intent.putExtra("City", city);
//                startActivity(intent);
                String selectct1 = selectcity.getText().toString();
                if (selectct1.equals("select city")) {

                    Toast.makeText(FromActivity.this, "please select city", Toast.LENGTH_LONG).show();

                } else {
                    pickdialog();
                }


            }
        });


        fromdate1 = (TextView) findViewById(R.id.fromdate1);
        fromdate2 = (TextView) findViewById(R.id.fromdate2);
        frommounth1 = (TextView) findViewById(R.id.frommonth1);
        frommounth2 = (TextView) findViewById(R.id.frommonth2);
        mon1 = (TextView) findViewById(R.id.mon1);
        mon2 = (TextView) findViewById(R.id.mon2);

//        String frn1=fromdate1.getText().toString();
//        String frn2=frommounth1.getText().toString();
//        fromdate2.setText(frn1);
//        frommounth2.setText(frn2);


        fromdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerFragment mDatePicker = new DatePickerFragment();
                mDatePicker.show(getSupportFragmentManager(), "Select date");
//                PickerDialog pickerDialog=new PickerDialog();
//                pickerDialog.show(getSupportFragmentManager(),"date_pickers");

            }
        });

        fromdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment1 mDatePicker1 = new DatePickerFragment1();
                mDatePicker1.show(getSupportFragmentManager(), "Select date");

//                Calendar c = Calendar.getInstance();
//                int year=c.get(Calendar.YEAR);
//                int date = c.get(Calendar.DAY_OF_MONTH);
//                int month = c.get(Calendar.MONTH);
//                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                fromdate1.setText(dayOfMonth);
//                                frommounth1.setText(monthOfYear);
//
//                            }
//                        }, year, month, date);
//                datePickerDialog.show();
//                PickerDialog pickerDialog=new PickerDialog();
//                pickerDialog.show(getSupportFragmentManager(),"date_pickers");

            }
        });

        formtime1 = (TextView) findViewById(R.id.formtime1);
        formtime2 = (TextView) findViewById(R.id.formtime2);
        formtime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker mTimePicker = new TimePicker();
                mTimePicker.show(getSupportFragmentManager(), "Select time");

            }
        });

        formtime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePicker1 mTimePicker1 = new TimePicker1();
                mTimePicker1.show(getSupportFragmentManager(), "Select time");
            }
        });


        showvehical = (Button) findViewById(R.id.showvehical);
        showvehical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectct = selectcity.getText().toString();
                if (selectct.equals("select city")) {
                    Toast.makeText(FromActivity.this, "select city", Toast.LENGTH_LONG).show();
                    return;
                }else {

                }

                String dAte1 = fromdate1.getText().toString();
                String dAte2 = fromdate2.getText().toString();
                String mOnth1 = mon1.getText().toString();
                String mOnth2 = mon2.getText().toString();
                String yEar1 = year1.getText().toString();
                String yEar2 = year2.getText().toString();
                String tIme1 = formtime1.getText().toString();
                String tIme2 = formtime2.getText().toString();
                Log.d("diffdate", mOnth1 + "/" + dAte1 + "/" + yEar1 + " " + tIme1 + ":" + "00" + "--" + mOnth2 + "/" + dAte2 + "/" + yEar2 + " " + tIme2 + ":" + "00");


                SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy HH:mm:ss");

                Date d1 = null;
                Date d2 = null;

                try {
                    d1 = format.parse(mOnth1 + "/" + dAte1 + "/" + yEar1 + " " + tIme1 + ":" + "00");
                    d2 = format.parse(mOnth2 + "/" + dAte2 + "/" + yEar2 + " " + tIme2 + ":" + "00");

                    //in milliseconds
                    long diff = d2.getTime() - d1.getTime();

                    long diffMinutes = diff / (60 * 1000) % 60;
                    long diffHours = diff / (60 * 60 * 1000) % 24;
                    long diffDays = diff / (24 * 60 * 60 * 1000);

                    Log.d("diffday", diffDays + " days, ");
                    Log.d("diffhour", diffHours + " hours, ");
                    Log.d("diffmin", diffMinutes + " minutes, ");

                    long inHours = diffDays * 24;
                    totalHours = inHours + diffHours;
                    Log.d("total", String.valueOf(totalHours));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int total = (int) totalHours;
                Log.d("total", String.valueOf(total));
                int five = 5;
                if (total < five) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(
                            FromActivity.this).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Time Is Lessthen 5 hour");

                    // Setting Dialog Message
                    alertDialog.setMessage("  Min 5 hour need to book ");


                    // Setting OK Button
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed
                            alertDialog.dismiss();

                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();
                } else {
                    passdata();
                }


            }
        });

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dp1 = new DatePickerDialog(getActivity(), this, year, month, day);
            dp1.getDatePicker().setMinDate(c.getTimeInMillis());

            return dp1;

        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            int jan = 1;
            int feb = 2;
            int mar = 3;
            int apr = 4;
            int may = 5;
            int jun = 6;
            int jul = 7;
            int aug = 8;
            int sept = 9;
            int oct = 10;
            int nov = 11;
            int dec = 12;
//            if(getId()==R.id.fromdate1){
            fromdate1.setText(String.valueOf(day));
            year1.setText(String.valueOf(year));
            mon1.setText(String.valueOf(month + 1));
            if (month + 1 == jan) {
                frommounth1.setText("January");
            } else if (month + 1 == feb) {
                frommounth1.setText("February");
            } else if (month + 1 == mar) {
                frommounth1.setText("March");
            } else if (month + 1 == apr) {
                frommounth1.setText("April");
            } else if (month + 1 == may) {
                frommounth1.setText("May");
            } else if (month + 1 == jun) {
                frommounth1.setText("June");
            } else if (month + 1 == jul) {
                frommounth1.setText("July");
            } else if (month + 1 == aug) {
                frommounth1.setText("August");
            } else if (month + 1 == sept) {
                frommounth1.setText("September");
            } else if (month + 1 == oct) {
                frommounth1.setText("October ");
            } else if (month + 1 == nov) {
                frommounth1.setText("November");
            } else if (month + 1 == dec) {
                frommounth1.setText("December");
            } else {
                frommounth1.setText(String.valueOf(month + 1));
            }

            Log.d("Date", month + " " + day);
//            }
//            else if (getId()==R.id.fromdate2){
//                fromdate2.setText(String.valueOf(day));
//                frommounth2.setText(String.valueOf(month)+1);
//            }
        }
    }

    public static class DatePickerFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dp2 = new DatePickerDialog(getActivity(), this, year, month, day);
            dp2.getDatePicker().setMinDate(c.getTimeInMillis());
            return dp2;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            int jan = 1;
            int feb = 2;
            int mar = 3;
            int apr = 4;
            int may = 5;
            int jun = 6;
            int jul = 7;
            int aug = 8;
            int sept = 9;
            int oct = 10;
            int nov = 11;
            int dec = 12;
//            if(getId()==R.id.fromdate1){
            fromdate2.setText(String.valueOf(day));
            year2.setText(String.valueOf(year));
            mon2.setText(String.valueOf(month + 1));
            String mn = mon2.getText().toString();
            if (month + 1 == jan) {
                frommounth2.setText("January");
            } else if (month + 1 == feb) {
                frommounth2.setText("February");
            } else if (month + 1 == mar) {
                frommounth2.setText("March");
            } else if (month + 1 == apr) {
                frommounth2.setText("April");
            } else if (month + 1 == may) {
                frommounth2.setText("May");
            } else if (month + 1 == jun) {
                frommounth2.setText("June");
            } else if (month + 1 == jul) {
                frommounth2.setText("July");
            } else if (month + 1 == aug) {
                frommounth2.setText("August");
            } else if (month + 1 == sept) {
                frommounth2.setText("September");
            } else if (month + 1 == oct) {
                frommounth2.setText("October ");
            } else if (month + 1 == nov) {
                frommounth2.setText("November");
            } else if (month + 1 == dec) {
                frommounth2.setText("December");
            } else {
                frommounth2.setText(String.valueOf(month + 1));
            }

            Log.d("Date2", month + " " + day + " " + mn);
//            }
//            else if (getId()==R.id.fromdate2){
//                fromdate2.setText(String.valueOf(day));
//                frommounth2.setText(String.valueOf(month)+1);
//            }
        }
    }

    public static class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            formtime1.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }
    }

    public static class TimePicker1 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        @Override
        public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
            formtime2.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }
    }

    public void passdata() {

        Intent i = new Intent(FromActivity.this, SelectvehicalActivity.class);

        String scity = selectcity.getText().toString();
        String scityid = cityid.getText().toString();
        String spickup = selectpickup.getText().toString();
        String sdropup = selectdrop.getText().toString();
        String date1 = fromdate1.getText().toString();
        String date2 = fromdate2.getText().toString();
        String mounth1 = frommounth1.getText().toString();
        String mounth2 = frommounth2.getText().toString();
        String time1 = formtime1.getText().toString();
        String time2 = formtime2.getText().toString();
        String syear1 = year1.getText().toString();
        String syear2 = year2.getText().toString();
        String smon1 = mon1.getText().toString();
        String smon2 = mon2.getText().toString();
        String svechile = vechiletype.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("selectcity", scity);
        bundle.putString("cityid", scityid);
        bundle.putString("selectpickup", spickup);
        bundle.putString("selectdrop", sdropup);
        bundle.putString("fromdate1", date1);
        bundle.putString("fromdate2", date2);
        bundle.putString("frommounth1", mounth1);
        bundle.putString("frommounth2", mounth2);
        bundle.putString("formtime1", time1);
        bundle.putString("formtime2", time2);
        bundle.putString("year1", syear1);
        bundle.putString("year2", syear2);
        bundle.putString("vechiletype", svechile);
        bundle.putString("mon1", smon1);
        bundle.putString("mon2", smon2);
        i.putExtras(bundle);
        startActivity(i);

    }

    public void dialog() {
        AlertDialog.Builder builder;
        final Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.location_dialog, (ViewGroup) findViewById(R.id.root));
        LinearLayout liner=layout.findViewById(R.id.linearLayout);
        ImageView close_setlocation=layout.findViewById(R.id.close_setlocation);


        GridView gridview = (GridView) layout.findViewById(R.id.grid_view);



        gridview.setAdapter(new CityAdapter(FromActivity.this, mThumbIds, mcity, mids));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectcity.setText(mcity[+position]);
                cityid.setText(mids[+position]);
                dialog.dismiss();

            }
        });
        close_setlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        dialog.show();

    }

    public void pickdialog() {
        AlertDialog.Builder builder;
        final Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.sub_location_dialog, (ViewGroup) findViewById(R.id.root));
        subloct = (ListView) layout.findViewById(R.id.sub_location);
        sublocationlist();
        subloct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = dataModelArrayList.get(position).getSubLocationName().toString();
                String valueid = dataModelArrayList.get(position).getId().toString();
                selectdrop.setText(value);
                dropid.setText(valueid);
                //Toast.makeText(FromActivity.this, "Item: " + value+valueid, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
    }

    public void subdialog() {

        AlertDialog.Builder builder;
        final Context mContext = this;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.sub_location_dialog, (ViewGroup) findViewById(R.id.root));
//            TextView id=(TextView)findViewById(R.id.catog_id);
//            TextView city=(TextView)findViewById(R.id.gridtxt);
//            ImageView icon=(ImageView)findViewById(R.id.gridimg) ;
        subloct = (ListView) layout.findViewById(R.id.sub_location);
        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);
        sublocationlist();
        subloct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = dataModelArrayList.get(position).getSubLocationName().toString();
                String valueid = dataModelArrayList.get(position).getId().toString();
                Log.d("subloc", value + " " + valueid);
                selectpickup.setText(value);
                pickupid.setText(valueid);
                dialog.dismiss();
//                Toast.makeText(FromActivity.this, "Item: " + msublocation[+position]+"  "+msubids[+position], Toast.LENGTH_LONG).show();
            }
        });

        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        dialog = builder.create();
        dialog.show();

    }

    public void sublocationlist() {
        final String scity = cityid.getText().toString();
        Log.d("id", String.valueOf(scity));
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_SUB_LOCATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressBar.setVisibility(View.GONE);
                            JSONObject obj = new JSONObject(response);
                            Log.d("Detials", String.valueOf(obj));
                            if (!obj.getBoolean("error")) {
                                // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("sublocations");
                                for (int i = 0; i < dataArray.length(); i++) {
                                    Sublocation sublo = new Sublocation();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    Log.d("Deti", String.valueOf(dataobj));
//                                   Sublocation subloc = new Sublocation(dataobj.getString("id"),dataobj.getString("subLocationName"));
                                    sublo.setSubLocationName(dataobj.getString("Sub Location Name"));
                                    sublo.setId(dataobj.getString("Id"));
                                    dataModelArrayList.add(sublo);
                                }
//                                SublocationAdapter sub=new SublocationAdapter(dataModelArrayList,getApplicationContext());
//                                subloct.setAdapter( sub);
                                setupListview();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("loc", scity);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void setupListview() {
        sublocationAdapter = new SublocationAdapter(this, dataModelArrayList);
        subloct.setAdapter(sublocationAdapter);
    }


}
//    private void getData(){
//        //Showing a progress dialog while our app fetches the data from url
//        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);
//
//        //Creating a json array request to get the json from our api
//        StringRequest stringRequest =new StringRequest(Request.Method.POST,  Urls.URL_LOCATION, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                try {
//                    JSONObject jsonObject=new JSONObject(response);
//                    JSONArray array=jsonObject.getJSONArray("data");
//                    for (int i=0; i<array.length(); i++){
//                        JSONObject ob=array.getJSONObject(i);
//                        Location list= new Location(ob.getString("id"),ob.getString("Locationname"),ob.getString("Locationimage"));
//                        list_data.add(list);
//                    }
//                    adapter=(getApplicationContext(),R.layout.selectlocat_gridview , list_data);
//                    gridView.setAdapter(adapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
////        RequestQueue requestQueue= Volley.newRequestQueue(this);
////        requestQueue.add(stringRequest);JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Urls.URL_LOCATION,
////                new Response.Listener<JSONArray>() {
////                    @Override
////                    public void onResponse(JSONArray response) {
////                        //Dismissing the progressdialog on response
////                        loading.dismiss();
////
////                        //Displaying our grid
////                        showGrid(response);
////                    }
////                },
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////
////                    }
////                }
////        );
////
////        //Creating a request queue
////        RequestQueue requestQueue = Volley.newRequestQueue(this);
////        //Adding our request to the queue
////        requestQueue.add(jsonArrayRequest);
//    }


//    private void showGrid(JSONArray jsonArray){
//        //Looping through all the elements of json array
//        for(int i = 0; i<jsonArray.length(); i++){
//            //Creating a json object of the current index
//            JSONObject obj = null;
//            try {
//                //getting json object from current index
//                obj = jsonArray.getJSONObject(i);
//
//                //getting image url and title from json object
//                images.add(obj.getString(TAG_IMAGE_URL));
//                names.add(obj.getString(TAG_NAME));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        //Creating GridViewAdapter Object
//        SelectcityAdapters selectcityAdapters = new SelectcityAdapters(this,images,names);
//
//        //Adding adapter to gridview
//        gridView.setAdapter(gridViewAdapter);
//    }