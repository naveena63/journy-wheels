package in.journeywheels.www.jw.orders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import in.journeywheels.www.jw.R;
import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;


public class BikeFragment extends Fragment {
    List<Model> modelList;
    Model model;
    RecyclerView recyclerView;
    Adapter adapter;
    TextView no_packages_available;
    Session session;
    public String usid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bike, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));

        no_packages_available = view.findViewById(R.id.no_packages_available);

        loadData();

        return view;
    }

    private void loadData() {

        session = new Session(getContext());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
//             usid = userObj.getUser().getId().toString();

        usid=name;
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));

        StringRequest sr = new StringRequest(Request.Method.POST, Urls.BikePreviousOrders, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("Responce", "" + response);
                modelList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.equals(null)) {
                    String error = jsonObject.getString("error");

                    if (error.equalsIgnoreCase("false")) {
                        JSONArray jsonArray=jsonObject.getJSONArray("Orders");
                        Log.v("jsonArray Responce", "" + jsonArray);
                        for (int i=0;i<jsonArray.length();i++){

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String vehicleTitle = jsonObject1.getString("Vehicle Title");
                            String pickLocation = jsonObject1.getString("Pick Location");
                            String pickDateTime = jsonObject1.getString("Pick Datetime");
                            String dropLocation = jsonObject1.getString("Drop Location");
                            String dropDatetime = jsonObject1.getString("Drop Datetime");
                            String Orderdate = jsonObject1.getString("Order date");
                            String amount = jsonObject1.getString("Amount");

                            model =new Model();
                            model.setVehicleTitle(vehicleTitle);
                            model.setPickLocation(pickLocation);
                            model.setPickDateTime(pickDateTime);
                            model.setDropLocation(dropLocation);
                            model.setDropDateTime(dropDatetime);
                            model.setOrderDate(Orderdate);
                            model.setAmount(amount);
                            modelList.add(model);

                        }


                    }
                        adapter = new Adapter(getActivity(), modelList);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                        no_packages_available.setVisibility(View.GONE);
                    }else {
                        no_packages_available.setText("no data found");
                        no_packages_available.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("customer_id", usid);
                params.put("type_id", "1");
                return params;
            }


        };
        requestQueue.add(sr);


    }


}
