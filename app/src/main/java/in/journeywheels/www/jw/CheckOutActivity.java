package in.journeywheels.www.jw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;

public class CheckOutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, PaymentResultListener {
    SharedPreferences invoicesharepreference;
    TextView vechile_Name, vechile_price;
    ImageView vechile_image;
    @SuppressWarnings("deprecation")
    Button pay, delete_couponbtn;
    SharedPreferences selectpreferences;
    public long totalHours;
    public String inbikes = "1";
    public String incars = "2";
    public String incycles = "3";
    public String type;
    public String ids;
    public String merchantkey = "gMGdXMf4";
    public String merchantid = "6695204";
    public String couponcode;
    public String proname, proemail, prophone;
    private String razorpayKey;
    public String vechile_id, city, cityid, pickup, drop, date1, date2, mounth1, mounth2, time1, time2, year1, year2, mon1, mon2, vechiletype;
    public String amount_ck, rent_ck, cgst_ck, sgst_ck, vechile_name_ck, sucess_pay_ck, order_inserted_ck;
    public String usid;
    public String paymentid;
    public long diffDays, diffHours, diffMinutes;
    String[] helmet = {"1", "2"};
    TextView check_price, checkOutText, bokkingFeeText, cgstText, SgstText, totalPyText, toText, textCoupon, total_price_text, numberOfHelmet_text, textKM, excessKM,
            check_helmet, text_summary, check_limit, check_excess, check_bookingfee, check_cgst, check_sgst, check_totalpayment, check_pickup, check_drop, check_date1, check_date2, check_time1, check_time2, cityname;
    Spinner check_spinner;
    CheckBox check_checkbox;
    public String order_id;
    EditText coupon;
    Button coupon_btn;
    String TAG = "responcepayu";
    Session session;
    final String status = "sucess";
    public String typ;
    final int min = 000000;
    final int max = 1000000;
    public String total;
    public String recipetid;


    private static final String SHARED_PREF_NAME = "invoice";


    private static DecimalFormat df2 = new DecimalFormat(".##");

    PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
    //declare paymentParam object
    PayUmoneySdkInitializer.PaymentParam paymentParam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        textCoupon = findViewById(R.id.textCoupon);
        textCoupon.setTypeface(face);
        text_summary = findViewById(R.id.text_summary);
        bokkingFeeText = findViewById(R.id.bokkingFeeText);
        bokkingFeeText.setTypeface(face);
        cgstText = findViewById(R.id.cgstText);
        cgstText.setTypeface(face);
        SgstText = findViewById(R.id.SgstText);
        SgstText.setTypeface(face);
        totalPyText = findViewById(R.id.totalPyText);
        totalPyText.setTypeface(face);
        delete_couponbtn = findViewById(R.id.check_couponbtn);
        delete_couponbtn.setTypeface(face);
        total_price_text = findViewById(R.id.total_price_text);
        total_price_text.setTypeface(face);
        checkOutText = findViewById(R.id.checkOutText);
        checkOutText.setTypeface(face);
        numberOfHelmet_text = findViewById(R.id.numberOfHelmet_text);
        numberOfHelmet_text.setTypeface(face);

        textKM = findViewById(R.id.textKM);
        textKM.setTypeface(face);

        excessKM = findViewById(R.id.excessKM);
        excessKM.setTypeface(face);

        toText = findViewById(R.id.toText);
        text_summary.setTypeface(face);
        vechile_Name = (TextView) findViewById(R.id.vechileName);
        vechile_Name.setTypeface(face);
        vechile_price = (TextView) findViewById(R.id.vechilePrice);

        vechile_image = (ImageView) findViewById(R.id.vechieImage);
        cityname = findViewById(R.id.city);
        cityname.setTypeface(face);

        coupon = (EditText) findViewById(R.id.check_couponcode);
        coupon.setTypeface(face);
        coupon_btn = (Button) findViewById(R.id.check_couponbtn);
        coupon_btn.setTypeface(face);

        check_price = (TextView) findViewById(R.id.check_price);
        check_helmet = (TextView) findViewById(R.id.check_helmet);
        check_limit = (TextView) findViewById(R.id.check_km);
        check_excess = (TextView) findViewById(R.id.check_excess);


        check_bookingfee = (TextView) findViewById(R.id.check_bookingfee);
        check_bookingfee.setTypeface(face);
        check_cgst = (TextView) findViewById(R.id.check_cgst);
        check_cgst.setTypeface(face);
        check_sgst = (TextView) findViewById(R.id.check_sgst);
        check_sgst.setTypeface(face);
        check_totalpayment = (TextView) findViewById(R.id.check_totalpayment);
        check_totalpayment.setTypeface(face);

        check_pickup = (TextView) findViewById(R.id.check_pickup);
        check_pickup.setTypeface(face);
        check_drop = (TextView) findViewById(R.id.check_drop);
        check_drop.setTypeface(face);
        check_date1 = (TextView) findViewById(R.id.check_date1);
        check_date2 = (TextView) findViewById(R.id.check_date2);
        check_time1 = (TextView) findViewById(R.id.check_time1);
        check_time2 = (TextView) findViewById(R.id.check_time2);
        check_date1.setTypeface(face);
        check_time1.setTypeface(face);
        check_date2.setTypeface(face);
        check_time2.setTypeface(face);
        toText.setTypeface(face);
        amount_ck = check_bookingfee.getText().toString();
        rent_ck = check_bookingfee.getText().toString();
        cgst_ck = check_cgst.getText().toString();
        sgst_ck = check_sgst.getText().toString();
        vechile_name_ck = vechile_Name.getText().toString();

        check_spinner = (Spinner) findViewById(R.id.check_spinner);
        check_spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, helmet);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        check_spinner.setAdapter(aa);

        check_checkbox = (CheckBox) findViewById(R.id.check_checkbox);
        check_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check_checkbox.isChecked()) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(
                            CheckOutActivity.this).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("terms and conditions");

                    // Setting Dialog Message
                    alertDialog.setMessage(" Rider needs to present a valid Driving License at the time of picking up the vehicle. Original ID proof (Voter ID, Aadhaar Card or Passport) needs to be submitted at the time of pick up.\n" +
                            " If customer drives the bike above 150 km/day then Rs.2 excess Fee will be levied per Kilometer.\n" +
                            " If the vehicle booked for 5 hrs it will have a limit of 65 kms for bike.\n" +
                            " Late Return fee would be Rs 100/hour plus hourly & daily basis rent.\n" +
                            " 1 Complimentary helmet will be provided.\n" +
                            " Fuel charges are not included.\n" +
                            "Kilometers limit perday/hours\n" +
                            " A Limit of 150Kms/day every Excess kilometer Rs. 2 (below 125cc)\n" +
                            " Above 125cc Rs 3 will be charged per kilometer.\n" +
                            " Above 180cc Rs 4 will be charged per kilometer.\n" +
                            " In case of damage to the Bike on rent due to accident/mishandling/carelessness, appropriate charges will be calculated by the vendor and the customer is liable to pay the same to the vendor along with the daily tariff until the bike is ready for renting again.\n ");


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
                }
            }
        });


//        String helmet=check_helmet.getText().toString();
//        String totalpayment=check_totalpayment.getText().toString();
//        if (Integer.valueOf(helmet) ==2){
//            int totl=Integer.valueOf(totalpayment)+50;
//            check_totalpayment.setText(totl);
//
//        }else {
//            check_totalpayment.setText(totalpayment);
//
//        }

        Bundle bundle = getIntent().getExtras();
        vechile_id = bundle.getString("myData");
        Log.d("vech", String.valueOf(vechile_id));

        selectpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        city = selectpreferences.getString("selectcity", "0");
        cityid = selectpreferences.getString("city_id", "0");
        pickup = selectpreferences.getString("select_pickup", "0");
        drop = selectpreferences.getString("select_drop", "0");
        date1 = selectpreferences.getString("from_date1", "0");
        date2 = selectpreferences.getString("from_date2", "0");
        mounth1 = selectpreferences.getString("from_mounth1", "0");
        mounth2 = selectpreferences.getString("from_mounth2", "0");
        time1 = selectpreferences.getString("fromtime1", "0");
        time2 = selectpreferences.getString("fromtime2", "0");
        year1 = selectpreferences.getString("year1", "0");
        year2 = selectpreferences.getString("year2", "0");
        vechiletype = selectpreferences.getString("vechile_type", "0");
        mon1 = selectpreferences.getString("mon1", "0");
        mon2 = selectpreferences.getString("mon2", "0");

        getdetials();
        cityname.setText(getString(R.string.city_name) + city);
        check_pickup.setText("Pick Up:" + pickup);
        check_drop.setText("Drop Off:" + drop);
        check_date1.setText(date1 + "/" + mon1 + "/" + year1);
        check_date2.setText(date2 + "/" + mon2 + "/" + year2);
        check_time1.setText(time1);

        check_time2.setText(time2);
        Log.d("sha", city + " " + cityid + " " + pickup + " " + drop + " " + date1 + " " + date2 + " " + mounth1 + " " + mounth2 + " " +
                time1 + " " + time2 + " " + year1 + " " + year2 + " " + vechiletype + " " + mon1 + " " + mon2 + " " + vechile_id);

        if (vechiletype.equals("bikes")) {
            type = inbikes;
        } else if (vechiletype.equals("cars")) {
            type = incars;
        } else if (vechiletype.equals("cycles")) {
            type = incycles;
        }
        Log.d("checktypevec", String.valueOf(type));

        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(mon1 + "/" + date1 + "/" + year1 + " " + time1 + ":" + "00");
            d2 = format.parse(mon2 + "/" + date2 + "/" + year2 + " " + time2 + ":" + "00");

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            diffMinutes = diff / (60 * 1000) % 60;
            diffHours = diff / (60 * 60 * 1000) % 24;
            diffDays = diff / (24 * 60 * 60 * 1000);

            Log.d("checkdiffday", diffDays + " days, ");
            Log.d("checkdiffhour", diffHours + " hours, ");
            Log.d("checkdiffmin", diffMinutes + " minutes, ");

            long inHours = diffDays * 24;
            totalHours = inHours + diffHours;
            Log.d("checktotal", String.valueOf(totalHours));

        } catch (Exception e) {
            e.printStackTrace();
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_VECHILE_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            Log.d("Date", String.valueOf(response));

                            if (!obj.getBoolean("error")) {
                                //  Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                JSONObject userJson = obj.getJSONObject("Vehicle Details");
                                Log.d("Dits", String.valueOf(userJson));
                                vechile_Name.setText(userJson.getString("Vehicle Title"));
                                vechile_price.setText(userJson.getString("Price"));
                                check_limit.setText(userJson.getString("Kmlimit"));
//                                check_bookingfee.setText(userJson.getString("Price"));
                                check_price.setText(userJson.getString("Price"));

                                Picasso.get().load(userJson.getString("Vehicle Image")).into(vechile_image);
                                final Double gst = 2.5 / 100;
                                Log.d("gst", String.valueOf(gst));
                                final String vec_price = vechile_price.getText().toString();
                                Log.d("price", String.valueOf(vec_price));

                                if (totalHours <= 10) {
                                    int hur = 10;
                                    Double fullprice = Double.valueOf(hur) * (Double.valueOf(vec_price) / 10);

                                    Double cgst = fullprice * gst;
                                    Log.d("cgst1", String.valueOf(cgst));
                                    Log.d("fullprice1", String.valueOf(fullprice));
                                    check_cgst.setText(df2.format(cgst));
                                    check_sgst.setText(df2.format(cgst));
                                    check_bookingfee.setText(df2.format(fullprice));
                                    Double totalprc = (Double) (fullprice + cgst + cgst);
                                    check_totalpayment.setText(df2.format(totalprc));
                                    Log.d("totalprice2", String.valueOf(totalprc));

                                } else {
                                    if (diffHours >= 10) {
                                        int diffhour = 10;
                                        int totalhur = (int) ((diffDays * 10) + diffhour);
                                        Log.d("diffday", String.valueOf(diffDays));
                                        Log.d("totalhur", String.valueOf(totalhur));
                                        final Double cal_price = Double.valueOf(vec_price) / 10;
                                        Log.d("call_prices", String.valueOf(cal_price));
                                        final Double totalprice = (Double) (cal_price * totalhur);
                                        Log.d("totalprice2", String.valueOf(totalprice));
                                        final Double cgstt = totalprice * gst;
                                        Log.d("cgstt2", String.valueOf(cgstt));
                                        check_cgst.setText(df2.format(cgstt));
                                        check_sgst.setText(df2.format(cgstt));
                                        check_bookingfee.setText(df2.format(totalprice));
                                        Double totalprc = (Double) (totalprice + cgstt + cgstt);
                                        check_totalpayment.setText(df2.format(totalprc));
                                        Log.d("totalprice", String.valueOf(totalprc));


                                    } else {
                                        Double totalhur = (Double) (Double.valueOf(diffDays * 10) + diffHours);
                                        Log.d("totalhur3", String.valueOf(totalhur));
                                        final Double cal_price = Double.valueOf(vec_price) / 10;
                                        Log.d("calprice3", String.valueOf(cal_price));
                                        final Double totalprice = (Double) (cal_price * totalhur);
                                        Log.d("totalprice3", String.valueOf(totalprice));
                                        final Double cgstt = totalprice * gst;
                                        Log.d("cgstt3", String.valueOf(cgstt));
                                        check_cgst.setText(df2.format(cgstt));
                                        check_sgst.setText(df2.format(cgstt));
                                        check_bookingfee.setText(df2.format(totalprice));
                                        Double totalprc = (Double) (totalprice + cgstt + cgstt);
                                        check_totalpayment.setText(df2.format(totalprc));
                                        Log.d("totalprice3", String.valueOf(totalprc));

                                    }

                                }
                            } else {
                                // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                params.put("id", vechile_id);
                params.put("type_id", type);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = check_totalpayment.getText().toString();
                checkoutdata();
//               orderplacement();
//                sms();
                makepayment();

            }
        });


        coupon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String coupenvalue = coupon.getText().toString();

                final Double totalpayment = Double.valueOf(check_totalpayment.getText().toString());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_COUPON,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject obj = new JSONObject(response);
                                    Log.d("Rate", String.valueOf(response));

                                    if (!obj.getBoolean("error")) {
                                        // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        JSONObject userJson = obj.getJSONObject("Offer Users");
                                        Log.d("Rits", String.valueOf(userJson));
                                        couponcode = userJson.getString("Offer");
                                        Double checktotal = totalpayment - (Double.valueOf(couponcode) * totalpayment);
                                        Log.d("Rhecktotal", String.valueOf(checktotal));
                                        check_totalpayment.setText(df2.format(checktotal));
                                        coupon.setText("coupon applied");
                                        coupon.setEnabled(false);
                                        coupon_btn.setText("Delete coupon");


                                    } else {
                                        final AlertDialog alertDialog = new AlertDialog.Builder(
                                                CheckOutActivity.this).create();

                                        // Setting Dialog Title
                                        alertDialog.setTitle("Coupon already applied");

                                        // Setting Dialog Message
                                        alertDialog.setMessage(" get a valied coupon ");

                                        // Setting OK Button
                                        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Write your code here to execute after dialog closed
                                                coupon.setEnabled(true);
                                                coupon.setText("");
                                                coupon_btn.setText("Apply coupon");
                                                String bkfeettl = check_bookingfee.getText().toString();
                                                String bkfeecgst = check_cgst.getText().toString();
                                                String bkfeesgst = check_sgst.getText().toString();
                                                Double bkfeettol = Double.valueOf(bkfeettl) + Double.valueOf(bkfeesgst) + Double.valueOf(bkfeecgst);

                                                check_totalpayment.setText(df2.format(bkfeettol));
                                                alertDialog.dismiss();

                                            }
                                        });


                                        alertDialog.show();
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
                        params.put("coupon", coupenvalue);
                        return params;
                    }
                };

                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        check_helmet.setText(helmet[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void makepayment() {

        final Activity activity = this;
        razorpayKey = "rzp_live_Et6OrQkQTv0iD2";
        final int random = new Random().nextInt((max - min) + 1) + min;
        final String totalAmount = check_totalpayment.getText().toString();
                            Log.i("totalAmount", "TotalAmount" + totalAmount);
                            recipetid = ids + random + vechile_id;
                            Log.d("recipetid", recipetid);

                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_ORDERID,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {

                                                JSONObject obj = new JSONObject(response);
                                                Log.d("Date", String.valueOf(response));

                            String error = obj.getString("error");

                            if (error.equalsIgnoreCase("false")) {

                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                JSONObject userJson = obj.getJSONObject("user");
                                order_id = userJson.getString("Id");
                                Log.d("orderid", order_id);


                            } else {
                                // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Log.i("error payment", "Error Payment" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("amount", totalAmount);
                params.put("receipt_id", recipetid);
                Log.d("orer amountd", amount_ck);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


        String payment = check_totalpayment.getText().toString();
        String vechilename = vechile_Name.getText().toString();

        final Checkout co = new Checkout();
        co.setKeyID(razorpayKey);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Journey Wheels Payment");
            options.put("description", "Journey Wheels");
            options.put("image", "http://journeywheels.in/images/logo1.png");
            options.put("currency", "INR");
            options.put("order_id", order_id);
//            options.put("payment_capture",true);
//            double total = Double.parseDouble(payment);
//            int total = 1;
//            total = total * 100;
            Log.d("amount", total);
            options.put("amount", total);

//           Log.d("amount", totalAmount);
//            options.put("amount", totalAmount);
            Log.d("checktotal", String.valueOf(total));
            JSONObject noteobject = new JSONObject();
            noteobject.put("Vehicle_Name", vechilename);
            noteobject.put("Vehicle_Id", vechile_id);
            noteobject.put("pick_location", pickup);
            noteobject.put("pick_datetime", date1 + "/" + mounth1 + "/" + year1);
            noteobject.put("drop_location", drop);
            noteobject.put("drop_datetime", date2 + "/" + mounth2 + "/" + year2);
            noteobject.put("Location", city);
            noteobject.put("merchant_order_id", order_id);
            options.put("notes", noteobject);

            JSONObject preFill = new JSONObject();
            preFill.put("email", proemail);
            preFill.put("contact", prophone);
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

    @Override
    public void onPaymentSuccess(String s) {
        orderplacement();
        sms();
        paymentid = s;
        final AlertDialog alertDialog = new AlertDialog.Builder(
                CheckOutActivity.this).create();
        alertDialog.setTitle("Payment Id");
        alertDialog.setMessage(" your id " + paymentid);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                Intent i = new Intent(CheckOutActivity.this, MainscreenActivity.class);
                startActivity(i);
            }
        });
        alertDialog.show();
        Toast.makeText(this, "Payment successfully done!" + paymentid, Toast.LENGTH_SHORT).show();
        Log.d("paymentstatus", paymentid);
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
            Log.i("Payment Error ","Payment Error "+i+s);
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }

    public void getdetials() {

//        String response = sharedprefrence.getLoginResponse(this.getApplicationContext());
//        Gson gson = new Gson();
//        final Loginpojo userObj = gson.fromJson(response, Loginpojo.class);
//        sharedprefrence.getLoginResponse(this.getApplicationContext());
//
//        try {
        session = new Session(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
//     usid = userObj.getUser().getId().toString();

        usid = name;
        Log.d("checkid", String.valueOf(usid));

//        }catch (NullPointerException ignored){
//
//
//        }

        Log.d("Ncheckid", String.valueOf(usid));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            Log.d("Date", String.valueOf(response));

                            if (!obj.getBoolean("error")) {
                                //   Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                JSONObject userJson = obj.getJSONObject("Profile Users");
                                Log.d("Dits", String.valueOf(userJson));
                                proname = userJson.getString("Name");
                                prophone = userJson.getString("Mobile");
                                proemail = userJson.getString("Email");

                            } else {
                                // Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
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

                params.put("uid", usid);
                Log.d("idsos", usid);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void orderplacement() {

        selectpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String vtyp = selectpreferences.getString("vechile_type", "0");
        if (vtyp.equals("bikes")) {
            typ = inbikes;
        } else if (vechiletype.equals("cars")) {
            typ = incars;
        } else if (vechiletype.equals("cycles")) {
            typ = incycles;
        }
        Log.d("typevec", typ);

        invoicesharepreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String id = invoicesharepreference.getString("id", "0");
        final String amount = invoicesharepreference.getString("amount", "0");
        final String email = invoicesharepreference.getString("email", "0");
        final String contact = invoicesharepreference.getString("contact", "0");
        final String name = invoicesharepreference.getString("name", "0");
        final String rent = invoicesharepreference.getString("rent", "0");
        final String tax = invoicesharepreference.getString("tax", "0");
        final String tax1 = invoicesharepreference.getString("tax1", "0");
        final String location = invoicesharepreference.getString("location", "0");
        final String product_tittle = invoicesharepreference.getString("product_tittle", "0");
        final String pick_location = invoicesharepreference.getString("pick_location", "0");
        final String pick_datetime = invoicesharepreference.getString("pick_datetime", "0");
        final String drop_location = invoicesharepreference.getString("drop_location", "0");
        final String drop_datetime = invoicesharepreference.getString("drop_datetime", "0");
        final String hel = invoicesharepreference.getString("hel", "0");
        final String status = invoicesharepreference.getString("status", "0");

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Urls.URL_ORDER_PLACEMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("paymentResponse", String.valueOf(response));
                        Toast.makeText(CheckOutActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("error", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("uid", id);
                params.put("id", id);
                params.put("amount", amount);
                params.put("email", proemail);
                params.put("contact", contact);
                params.put("name", name);
                params.put("rent", rent);
                params.put("tax", tax);
                params.put("tax1", tax1);
                params.put("location", "1");
                params.put("product_title", product_tittle);
                params.put("pick_location", pick_location);
                params.put("pick_datetime", pick_datetime);
                params.put("drop_location", drop_location);
                params.put("drop_datetime", drop_datetime);
                params.put("hel", "1");
                params.put("status", "success");
                params.put("type_id", typ);
                params.put("razorpay_order_id", order_id);
                params.put("payment_id", paymentid);

                Log.d("orderplaceidos", id + amount + email + contact + name + rent + tax + tax1 + location + product_tittle + pick_location +
                        pick_datetime + "/" + drop_location + drop_datetime + status + order_id + paymentid + "/" + typ);

                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest1);
//        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, pay,
//                new Response.Listener<NetworkResponse>() {
//                    @Override
//                    public void onResponse(NetworkResponse response) {
//
//                        try {
//                            JSONObject obj = new JSONObject(new String(response.data));
//                            String message = obj.getString("message");
//                            Log.d("paymentResponse", message);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        Log.d("paymentResponse", "rs"+response);
//                        Toast.makeText(getApplicationContext(), "data is updated", Toast.LENGTH_SHORT).show();
//               }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("error",error.getMessage());
//                        Toast.makeText(getApplicationContext(), "unable to update ", Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//
//
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("id", id);
//                params.put("uid", id);
//                params.put("amount", amount);
//                params.put("email", proemail);
//                params.put("contact", contact);
//                params.put("name", name);
//                params.put("rent", rent);
//                params.put("tax", tax);
//                params.put("tax1", tax1);
//                params.put("location", "1");
//                params.put("product_title", product_tittle);
//                params.put("pick_location", pick_location);
//                params.put("pick_datetime", pick_datetime);
//                params.put("drop_location", drop_location);
//                params.put("drop_datetime", drop_datetime);
//                params.put("hel", "1");
//                params.put("status", status);
//                params.put("type_id", typ);
////                params.put("razorpay_order_id", "1234");
//                params.put("payment_id", "13");
//                Log.d("orderplaceidos", id + amount + proemail + contact + name + rent + tax + tax1 + location + product_tittle + pick_location +
//                        pick_datetime + "/" + drop_location + drop_datetime + status + order_id + paymentid+"/"+typ);
//                return params;
//            }
//
//
//
//        };
//
//
////adding the request to volley
//        Volley.newRequestQueue(this).add(volleyMultipartRequest);


    }

    public void sms() {
        invoicesharepreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        final String id = invoicesharepreference.getString("id", "0");
        final String amount = invoicesharepreference.getString("amount", "0");
        final String email = invoicesharepreference.getString("email", "0");
        final String contact = invoicesharepreference.getString("contact", "0");
        final String name = invoicesharepreference.getString("name", "0");
        final String rent = invoicesharepreference.getString("rent", "0");
        final String tax = invoicesharepreference.getString("tax", "0");
        final String tax1 = invoicesharepreference.getString("tax1", "0");
        final String location = invoicesharepreference.getString("location", "0");
        final String product_tittle = invoicesharepreference.getString("product_tittle", "0");
        final String pick_location = invoicesharepreference.getString("pick_location", "0");
        final String pick_datetime = invoicesharepreference.getString("pick_datetime", "0");
        final String drop_location = invoicesharepreference.getString("drop_location", "0");
        final String drop_datetime = invoicesharepreference.getString("drop_datetime", "0");
        final String hel = invoicesharepreference.getString("hel", "0");
        final String status = invoicesharepreference.getString("status", "0");


//        amount_ck=check_bookingfee.getText().toString();
//        rent_ck=check_bookingfee.getText().toString();
//        cgst_ck=check_cgst.getText().toString();
//        sgst_ck=check_sgst.getText().toString();
//        vechile_name_ck=vechile_Name.getText().toString();
//        final String status="sucess";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_SMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            Log.d("sms", String.valueOf(response));

                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();


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
                        Log.d("smserror", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("contact", contact);
                params.put("name", name);
                params.put("loc", location);
                params.put("product_title", product_tittle);
                params.put("pick_location", pick_location);
                params.put("pick_datetime", pick_datetime);
                params.put("drop_datetime", drop_datetime);
                Log.d("orderplaceidos", email + contact + location + product_tittle + pick_location +
                        pick_datetime + "/" + drop_location + drop_datetime);

                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void checkoutdata() {


//        SharedPreferences invoicesharepreference = PreferenceManager.getDefaultSharedPreferences(this);
////        SharedPreferences.Editor editor = selectpreferences.edit();
        invoicesharepreference = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = invoicesharepreference.edit();
        editor.putString("id", usid);
        editor.putString("uid", usid);
        editor.putString("amount", total);
        editor.putString("emil", proemail);
        editor.putString("contact", prophone);
        editor.putString("name", proname);
        editor.putString("rent", rent_ck);
        editor.putString("tax", cgst_ck);
        editor.putString("tax1", sgst_ck);
        editor.putString("location", city);
        editor.putString("product_title", vechile_name_ck);
        editor.putString("pick_location", pickup);
        editor.putString("pick_datetime", date1 + "/" + mon1 + "/" + year1 + "/" + time1);
        editor.putString("drop_location", drop);
        editor.putString("drop_datetime", date2 + "/" + mon2 + "/" + year2 + "/" + time2);
        editor.putString("hel", "1");
        editor.putString("status", status);
        editor.apply();


    }

    public void razorpayorder() {

    }


//    public void startpay(){
//        amount_ck=check_bookingfee.getText().toString();
//        rent_ck=check_bookingfee.getText().toString();
//        cgst_ck=check_cgst.getText().toString();
//        sgst_ck=check_sgst.getText().toString();
//        vechile_name_ck=vechile_Name.getText().toString();
//        final String status="sucess";
//        order_id=UUID.randomUUID();
//        String payment = check_totalpayment.getText().toString();
//        String vechilename=vechile_Name.getText().toString();
//
//
//        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();
//
//        builder.setAmount(rent_ck)                          // Payment amount
//                .setTxnId(String.valueOf(order_id))                     // Transaction ID
//                .setPhone(prophone)                   // User Phone number
//                .setProductName(vechile_name_ck)                   // Product Name or description
//                .setFirstName(proname)                              // User First name
//                .setEmail(proemail)              // User Email ID
//                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")     // Success URL (surl)
//                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")     //Failure URL (furl)
//                .setUdf1("")
//                .setUdf2("")
//                .setUdf3("")
//                .setUdf4("")
//                .setUdf5("")
//                .setUdf6("")
//                .setUdf7("")
//                .setUdf8("")
//                .setUdf9("")
//                .setUdf10("")
//                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
//                .setKey(merchantkey)                        // Merchant key
//                .setMerchantId(merchantid);
//        Log.d("retrofit",rent_ck+" "+String.valueOf(order_id)+" "+prophone+" "+vechile_name_ck+" "+proname+" "+proemail+" "+merchantkey+" "+merchantid);
//
////
////        try {
////            paymentParam = builder.build();
////            // generateHashFromServer(paymentParam );
////            getHashkey();
////
////        } catch (Exception e) {
////            Log.e(TAG, " error s "+e.toString());
////        }
//
//        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();
//        calculateServerSideHashAndInitiatePayment(paymentParam);
//    }


//    public void makePayment(View view) {
//        amount_ck=check_bookingfee.getText().toString();
//        rent_ck=check_bookingfee.getText().toString();
//        cgst_ck=check_cgst.getText().toString();
//        sgst_ck=check_sgst.getText().toString();
//        vechile_name_ck=vechile_Name.getText().toString();
//        final String status="sucess";
//        order_id=UUID.randomUUID();
//        String payment = check_totalpayment.getText().toString();
//        String vechilename=vechile_Name.getText().toString();
//
//        String phone = prophone;
//        String productName = vechilename;
//        String firstName = proname;
//        String txnId = String.valueOf(order_id);
//        String email=proemail;
//        String sUrl = "https://www.payumoney.com/mobileapp/payumoney/success.php";
//        String fUrl = "https://www.payumoney.com/mobileapp/payumoney/failure.php";
//        String udf1 = "";
//        String udf2 = "";
//        String udf3 = "";
//        String udf4 = "";
//        String udf5 = "";
//        boolean isDebug = true;
//        String key = merchantkey;
//        String merchantId = "merchantid" ;
//
//        PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();
//
//
//        builder.setAmount(Double.parseDouble(rent_ck))
//                .setTnxId(txnId)
//                .setPhone(phone)
//                .setProductName(productName)
//                .setFirstName(firstName)
//                .setEmail(email)
//                .setsUrl(sUrl)
//                .setfUrl(fUrl)
//                .setUdf1(udf1)
//                .setUdf2(udf2)
//                .setUdf3(udf3)
//                .setUdf4(udf4)
//                .setUdf5(udf5)
//                .setIsDebug(isDebug)
//                .setKey(key)
//                .setMerchantId(merchantId);
//
//        PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();
//
////             server side call required to calculate hash with the help of <salt>
////             <salt> is already shared along with merchant <key>
//     /*        serverCalculatedHash =sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|<salt>)
//
//             (e.g.)
//
//             sha512(FCstqb|0nf7|10.0|product_name|piyush|piyush.jain@payu.in||||||MBgjYaFG)
//
//             9f1ce50ba8995e970a23c33e665a990e648df8de3baf64a33e19815acd402275617a16041e421cfa10b7532369f5f12725c7fcf69e8d10da64c59087008590fc
//*/
//
//        // Recommended
//        calculateServerSideHashAndInitiatePayment(paymentParam);
//
////        testing purpose
//
//       /* String salt = "";
//        String serverCalculatedHash=hashCal(key+"|"+txnId+"|"+getAmount()+"|"+productName+"|"
//                +firstName+"|"+email+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+salt);
//
//        paymentParam.setMerchantHash(serverCalculatedHash);
//
//        PayUmoneySdkInitilizer.startPaymentActivityForResult(MyActivity.this, paymentParam);*/
//
//    }
//
//    public static String hashCal(String str) {
//        byte[] hashseq = str.getBytes();
//        StringBuilder hexString = new StringBuilder();
//        try {
//            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
//            algorithm.reset();
//            algorithm.update(hashseq);
//            byte messageDigest[] = algorithm.digest();
//            for (byte aMessageDigest : messageDigest) {
//                String hex = Integer.toHexString(0xFF & aMessageDigest);
//                if (hex.length() == 1) {
//                    hexString.append("0");
//                }
//                hexString.append(hex);
//            }
//        } catch (NoSuchAlgorithmException ignored) {
//        }
//        return hexString.toString();
//    }
//    private void calculateServerSideHashAndInitiatePayment(final PayUmoneySdkInitilizer.PaymentParam paymentParam) {
//
//        // Replace your server side hash generator API URL
//        String url = "https://test.payumoney.com/payment/op/calculateHashForTest";
//
//        Toast.makeText(this, "Please wait... Generating hash from server ... ", Toast.LENGTH_LONG).show();
//        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    if (jsonObject.has(SdkConstants.STATUS)) {
//                        String status = jsonObject.optString(SdkConstants.STATUS);
//                        if (status != null || status.equals("1")) {
//
//                            String hash = jsonObject.getString(SdkConstants.RESULT);
//                            Log.i("app_activity", "Server calculated Hash :  " + hash);
//
//                            paymentParam.setMerchantHash(hash);
//
//                            PayUmoneySdkInitilizer.startPaymentActivityForResult(CheckOutActivity.this, paymentParam);
//                        } else {
//                            Toast.makeText(CheckOutActivity.this,
//                                    jsonObject.getString(SdkConstants.RESULT),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                if (error instanceof NoConnectionError) {
//                    Toast.makeText(CheckOutActivity.this,
//                            CheckOutActivity.this.getString(R.string.connect_to_internet),
//                            Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(CheckOutActivity.this,
//                            error.getMessage(),
//                            Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return paymentParam.getParams();
//            }
//        };
//        Volley.newRequestQueue(this).add(jsonObjectRequest);
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PayUmoneySdkInitilizer.PAYU_SDK_PAYMENT_REQUEST_CODE) {
//
//            /*if(data != null && data.hasExtra("result")){
//              String responsePayUmoney = data.getStringExtra("result");
//                if(SdkHelper.checkForValidString(responsePayUmoney))
//                    showDialogMessage(responsePayUmoney);
//            } else {
//                showDialogMessage("Unable to get Status of Payment");
//            }*/
//
//
//            if (resultCode == RESULT_OK) {
//                Log.i(TAG, "Success - Payment ID : " + data.getStringExtra(SdkConstants.PAYMENT_ID));
//                String paymentId = data.getStringExtra(SdkConstants.PAYMENT_ID);
//                showDialogMessage("Payment Success Id : " + paymentId);
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.i(TAG, "failure");
//                showDialogMessage("cancelled");
//            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_FAILED) {
//                Log.i("app_activity", "failure");
//
//                if (data != null) {
//                    if (data.getStringExtra(SdkConstants.RESULT).equals("cancel")) {
//
//                    } else {
//                        showDialogMessage("failure");
//                    }
//                }
//                //Write your code if there's no result
//            } else if (resultCode == PayUmoneySdkInitilizer.RESULT_BACK) {
//                Log.i(TAG, "User returned without login");
//                showDialogMessage("User returned without login");
//            }
//        }
//    }
//
//    private void showDialogMessage(String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(TAG);
//        builder.setMessage(message);
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
//
//    }
//    public void getHashkey(){
//        amount_ck=check_bookingfee.getText().toString();
//        rent_ck=check_bookingfee.getText().toString();
//        cgst_ck=check_cgst.getText().toString();
//        sgst_ck=check_sgst.getText().toString();
//        vechile_name_ck=vechile_Name.getText().toString();
//        final String status="sucess";
//        order_id=UUID.randomUUID();
//        String payment = check_totalpayment.getText().toString();
//        String vechilename=vechile_Name.getText().toString();
//        ServiceWrapper service = new ServiceWrapper(null);
//        Call<String> call = service.newHashCall(merchantkey, String.valueOf(order_id), amount_ck, vechile_name_ck,
//                proname, proemail);
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
//                Log.e(TAG, "hash res " + response.body());
//                String merchantHash = response.body();
//                if (merchantHash.isEmpty() || merchantHash.equals("")) {
//                    Toast.makeText(CheckOutActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
//                    Log.e(TAG, "hash empty");
//                } else {
//                    // mPaymentParams.setMerchantHash(merchantHash);
//                    paymentParam.setMerchantHash(merchantHash);
//                    // Invoke the following function to open the checkout page.
//                    // PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this,-1, true);
//                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, CheckOutActivity.this, R.style.AppTheme_default, false);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                    Log.e(TAG, "hash error "+ t.toString());
//            }
//            //            @Override
////            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
////                Log.e(TAG, "hash res "+response.body());
////                String merchantHash= response.body();
////                if (merchantHash.isEmpty() || merchantHash.equals("")) {
////                    Toast.makeText(CheckOutActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
////                    Log.e(TAG, "hash empty");
////                } else {
////                    // mPaymentParams.setMerchantHash(merchantHash);
////                    paymentParam.setMerchantHash(merchantHash);
////                    // Invoke the following function to open the checkout page.
////                    // PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, StartPaymentActivity.this,-1, true);
////                    PayUmoneyFlowManager.startPayUMoneyFlow(paymentParam, CheckOutActivity.this, R.style.AppTheme_default, false);
////                }
////            }
//
////            @Override
////            public void onFailure(Call<String> call, Throwable t) {
////                Log.e(TAG, "hash error "+ t.toString());
////            }
//        });
////
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_SMS,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        try {
////                            JSONObject obj = new JSONObject(response);
////                            Log.d("Date", String.valueOf(response));
////
////                            if (!obj.getBoolean("error")) {
////                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
////
//////                                sucess_pay_ck=obj.getString("message");
//////                                order_inserted_ck=obj.getString("order_user");
////////
////
////                            } else {
////                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
////                            }
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                },
////                new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
////                    }
////                }) {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String, String> params = new HashMap<>();
////                ;
////
////
////
////                params.put("key", merchantkey);
////                params.put("txind", String.valueOf(order_id));
////                params.put("amount", amount_ck);
////                params.put("productinfo", vechile_name_ck);
////                params.put("fristname", proname);
////                params.put("email",proemail);
////
////                return params;
////            }
////        };
////
////        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
//
//
//
//
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//// PayUMoneySdk: Success -- payuResponse{"id":225642,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"223013","transaction_fee":"20.00","amount":"20.00","cardCategory":"domestic","discount":"0.00","addedon":"2018-12-31 09:09:43","productinfo":"a2z shop","firstname":"kamal","email":"kamal.bunkar07@gmail.com","phone":"9144040888","hash":"b22172fcc0ab6dbc0a52925ebbd0297cca6793328a8dd1e61ef510b9545d9c851600fdbdc985960f803412c49e4faa56968b3e70c67fe62eaed7cecacdfdb5b3","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","payment_source":"payu","PG_TYPE":"AXISPG","bank_ref_no":"562178","ibibo_code":"VISA","error_code":"E000","Error_Message":"No Error","name_on_card":"payu","card_no":"401200XXXXXX1112","is_seamless":1,"surl":"https://www.payumoney.com/sandbox/payment/postBackParam.do","furl":"https://www.payumoney.com/sandbox/payment/postBackParam.do"}
////PayUMoneySdk: Success -- merchantResponse438104
//// on successfull txn
//        //  request code 10000 resultcode -1
//        //tran {"status":0,"message":"payment status for :438104","result":{"postBackParamId":292490,"mihpayid":"225642","paymentId":438104,"mode":"CC","status":"success","unmappedstatus":"captured","key":"9yrcMzso","txnid":"txt12345","amount":"20.00","additionalCharges":"","addedon":"2018-12-31 09:09:43","createdOn":1546227592000,"productinfo":"a2z shop","firstname":"kamal","lastname":"","address1":"","address2":"","city":"","state":"","country":"","zipcode":"","email":"kamal.bunkar07@gmail.com","phone":"9144040888","udf1":"","udf2":"","udf3":"","udf4":"","udf5":"","udf6":"","udf7":"","udf8":"","udf9":"","udf10":"","hash":"0e285d3a1166a1c51b72670ecfc8569645b133611988ad0b9c03df4bf73e6adcca799a3844cd279e934fed7325abc6c7b45b9c57bb15047eb9607fff41b5960e","field1":"562178","field2":"823386","field3":"2061","field4":"MC","field5":"167227964249","field6":"00","field7":"0","field8":"3DS","field9":" Verification of Secure Hash Failed: E700 -- Approved -- Transaction Successful -- Unable to be determined--E000","bank_ref_num":"562178","bankcode":"VISA","error":"E000","error_Message":"No Error","cardToken":"","offer_key":"","offer_type":"","offer_availed":"","pg_ref_no":"","offer_failure_reason":"","name_on_card":"payu","cardnum":"401200XXXXXX1112","cardhash":"This field is no longer supported in postback params.","card_type":"","card_merchant_param":null,"version":"","postUrl":"https:\/\/www.payumoney.com\/mobileapp\/payumoney\/success.php","calledStatus":false,"additional_param":"","amount_split":"{\"PAYU\":\"20.0\"}","discount":"0.00","net_amount_debit":"20","fetchAPI":null,"paisa_mecode":"","meCode":"{\"vpc_Merchant\":\"TESTIBIBOWEB\"}","payuMoneyId":"438104","encryptedPaymentId":null,"id":null,"surl":null,"furl":null,"baseUrl":null,"retryCount":0,"merchantid":null,"payment_source":null,"pg_TYPE":"AXISPG"},"errorCode":null,"responseCode":null}---438104
//
//        // Result Code is -1 send from Payumoney activity
//        Log.e("CheckoutActivity", "request code " + requestCode + " resultcode " + resultCode);
//        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
//            TransactionResponse transactionResponse = data.getParcelableExtra( PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE );
//
//            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
//
//                if(transactionResponse.getTransactionStatus().equals( TransactionResponse.TransactionStatus.SUCCESSFUL )){
//                    //Success Transaction
//                } else{
//                    //Failure Transaction
//                }
//
//                // Response from Payumoney
//                String payuResponse = transactionResponse.getPayuResponse();
//
//                // Response from SURl and FURL
//                String merchantResponse = transactionResponse.getTransactionDetails();
////                Log.e(TAG, "tran "+payuResponse+"---"+ merchantResponse);
//            } /* else if (resultModel != null && resultModel.getError() != null) {
//                Log.d(TAG, "Error response : " + resultModel.getError().getTransactionResponse());
//            } else {
//                Log.d(TAG, "Both objects are null!");
//            }*/
//        }
//    }


}
