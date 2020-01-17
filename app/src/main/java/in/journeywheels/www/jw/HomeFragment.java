package in.journeywheels.www.jw;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView bike, cars, cycles, hmname;
    ViewPager viewPager;
    int images[] = {R.drawable.offers1, R.drawable.offer2, R.drawable.offet3, R.drawable.offers1};
    SliderAdapter sliderAdapter;
    public String usid;
    Boolean resp = false;
    Session session;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        String response=Sharedprefrence.getLoginResponse(getActivity().getApplicationContext());
//        Log.d(TAG,"resp"+response);
        session = new Session(getActivity());
        bike = (TextView) view.findViewById(R.id.bike);
        cars = (TextView) view.findViewById(R.id.cars);
        cycles = (TextView) view.findViewById(R.id.cycle);
        hmname = (TextView) view.findViewById(R.id.homename);
        load();
        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FromActivity.class);
                i.putExtra("vechile", "bikes");
                startActivity(i);
            }
        });
        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FromActivity.class);
                i.putExtra("vechile", "cars");
                startActivity(i);
            }
        });
        cycles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FromActivity.class);
                i.putExtra("vechile", "cycles");
                startActivity(i);
            }
        });
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(getActivity(), images);
        viewPager.setAdapter(sliderAdapter);
        return view;
    }

    public void load() {
        session = new Session(getActivity());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
//        String response = sharedprefrence.getLoginResponse(getActivity().getApplicationContext());
        Log.d("Response", name);
        if (name.equals("0")) {
            hmname.setText("Login Now");
        } else {
            usid = name;
            Log.d("Nid", String.valueOf(usid));
            Log.d("sid", String.valueOf(usid));
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            final String URL_user = "https://journeywheels.in/api/user.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_USERS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject obj = new JSONObject(response);
                                String message = obj.getString("message");
                                Log.d("Data", String.valueOf(response));

                                if (!obj.getBoolean("error")) {
                                    //  Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
                                    JSONObject userJson = obj.getJSONObject("Profile Users");
                                    Log.d("Dits", String.valueOf(userJson));
                                    hmname.setText(userJson.getString("Name"));
                                    progressDialog.dismiss();

                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("uid", usid);
                    Log.d("hmdos", usid);
                    return params;
                }
            };

            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        }

    }

}
