package in.journeywheels.www.jw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;

public class LoginActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    LinearLayout really1, really2, really3, really4;
    Button create;
    Button sigin;
    TextView signup,tv_login,text_username,text_password,tv_signup,text_name,text_email,text_phone,text_signup_password,text_copaswrd;
    Button login, signup_btn;
    TextView signin;
    ImageView loginlogo;
    EditText lemail, lpassword, signup_name, signup_email, signup_phoneno, signup_password, signup_copassword;
    SharedPreferences selectpreferences;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Raleway-Medium.ttf");

        really1 = (LinearLayout) findViewById(R.id.really1);
        really2 = (LinearLayout) findViewById(R.id.really2);
        really3 = (LinearLayout) findViewById(R.id.really3);
        really4 = (LinearLayout) findViewById(R.id.really4);
        loginlogo=(ImageView)findViewById(R.id.loginlogo);
        tv_login=(TextView) findViewById(R.id.tv_login);
        tv_signup=(TextView) findViewById(R.id.tv_signup);
        text_name=(TextView) findViewById(R.id.text_name);
        text_email=(TextView) findViewById(R.id.text_email);
        text_phone=(TextView) findViewById(R.id.text_phone);
        text_signup_password=(TextView) findViewById(R.id.text_signup_password);
        text_copaswrd=(TextView) findViewById(R.id.text_copaswrd);
        text_copaswrd.setTypeface(face);
        text_email.setTypeface(face);
        text_name.setTypeface(face);
        text_phone.setTypeface(face);
        text_signup_password.setTypeface(face);

        text_username=(TextView) findViewById(R.id.text_username);
        text_username.setTypeface(face);
        tv_login.setTypeface(face);
        sigin = (Button) findViewById(R.id.login_sigin);
        signin = (TextView) findViewById(R.id.signin);
        signin.setTypeface(face);
        login = (Button) findViewById(R.id.login_btn);
        signup_btn = (Button) findViewById(R.id.signup_btn);
        signup = (TextView) findViewById(R.id.signup);

        text_password = (TextView) findViewById(R.id.text_password);
        text_password.setTypeface(face);
        lemail = (EditText) findViewById(R.id.lemail);
        lemail.setTypeface(face);

        lpassword = (EditText) findViewById(R.id.lpassword);
        lpassword.setTypeface(face);
//        signup page
        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_name.setTypeface(face);
        signup_email = (EditText) findViewById(R.id.signup_email);
        signup_email.setTypeface(face);
        signup_phoneno = (EditText) findViewById(R.id.signup_phoneno);
        signup_phoneno.setTypeface(face);
        signup_password = (EditText) findViewById(R.id.signup_password);
        signup_password.setTypeface(face);
        signup_copassword = (EditText) findViewById(R.id.signup_copass);
        signup_copassword.setTypeface(face);
        session = new Session(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginuses();

//                Intent intent = new Intent(getBaseContext(), CheckOutActivity.class);
//                startActivity(intent);
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                really1.setVisibility(View.INVISIBLE);
                really2.setVisibility(View.INVISIBLE);
                really3.setVisibility(View.VISIBLE);
                loginlogo.setVisibility(View.GONE);

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                really1.setVisibility(View.INVISIBLE);
                really2.setVisibility(View.VISIBLE);
                really3.setVisibility(View.INVISIBLE);
            }
        });


        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                really1.setVisibility(View.INVISIBLE);
                really2.setVisibility(View.VISIBLE);
                really3.setVisibility(View.INVISIBLE);
                loginlogo.setVisibility(View.GONE);

            }
        });


//        LoginFragment frag1=new LoginFragment();
//        fragmentTransaction.replace(R.id.log,frag1);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

    }

    private void loginuses() {
        final String email = lemail.getText().toString().trim();
        final String password = lpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            lemail.setError("Please enter your email");
            lemail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            lemail.setError("Enter a valid email");
            lemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            lpassword.setError("Enter a password");
            lpassword.requestFocus();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://journeywheels.in/api/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("urls", Urls.URL_LOGIN);
                        try {

                            JSONObject obj = new JSONObject(response);


                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

//                                     Sharedprefrence.setLoginResponse(getApplicationContext(),response);
                                JSONObject userJson = obj.getJSONObject("user");
                                String Identy = userJson.getString("Id");
                                session.createLoginSession(Identy);
                                Log.d("identy", String.valueOf(Identy));
                                progressDialog.dismiss();
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainscreenActivity.class));
                            } else {
                                progressDialog.dismiss();

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
                        Log.d("identy",error.getMessage());
                        progressDialog.dismiss();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("lemail", email);
                params.put("lpassword", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void registeruser() {
        final String signupname = signup_name.getText().toString().trim();
        final String signupemail = signup_email.getText().toString().trim();
        final String signupphone = signup_phoneno.getText().toString().trim();
        final String signuppassword = signup_password.getText().toString().trim();
        final String signupcopass = signup_copassword.getText().toString().trim();
        Log.d("profile", signupname + signupemail + signuppassword + signupcopass + signupphone);


        if (TextUtils.isEmpty(signupname)) {
            signup_name.setError("Please enter your name");
            signup_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(signupemail)) {
            signup_email.setError("Please enter your email");
            signup_email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(signupemail).matches()) {
            signup_email.setError("Enter a valid email");
            signup_email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(signupphone)) {
            signup_phoneno.setError("Enter a phone no");
            signup_phoneno.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(signuppassword)) {
            signup_password.setError("Enter a password");
            signup_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(signupcopass)) {
            signup_copassword.setError("Enter a co-password");
            signup_copassword.requestFocus();
            return;
        }
        if (signuppassword == signupcopass) {
            signup_password.setError("Enter a password");
            signup_copassword.setError("Enter a co-password");
            signup_password.requestFocus();

            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);


                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

//                                     Sharedprefrence.setLoginResponse(getApplicationContext(),response);
                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");
                                String Identy = userJson.getString("Id");
                                session.createLoginSession(Identy);
                                Log.d("identy", String.valueOf(Identy));
                                progressDialog.dismiss();
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainscreenActivity.class));
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
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("rname", signupname);
                params.put("remail", signupemail);
                params.put("rmobile", signupphone);
                params.put("rpassword", signuppassword);
                params.put("rcpassword", signupcopass);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

