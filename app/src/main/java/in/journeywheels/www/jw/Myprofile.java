package in.journeywheels.www.jw;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.sharedpref.Sharedprefrence;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Myprofile extends Fragment {
    Sharedprefrence sharedprefrence;
    Session session;
    TextView proid, proname, proemail, prophone, prodob, progender, pronation, proaddress, promemname, promemphone, promememail, useid,
            moredetials, secondcontact, licensedetials, logout, noimage1;
    LinearLayout moredetials_lay, seconddetials_lay, license_lay;
    ImageView proliceimg1, proliceimg2, proidenimg1, proidenimg2;
    ScrollView proprofile;
    LinearLayout prolog;
    String usid;
    Boolean resp = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile, container, false);


        proprofile = (ScrollView) view.findViewById(R.id.proprofile);
        prolog = (LinearLayout) view.findViewById(R.id.myprolog);
        proid = (TextView) view.findViewById(R.id.proid);
        useid = (TextView) view.findViewById(R.id.usid);
        proname = (TextView) view.findViewById(R.id.proname);
        proemail = (TextView) view.findViewById(R.id.proemail);
        prophone = (TextView) view.findViewById(R.id.prophone);
        prodob = (TextView) view.findViewById(R.id.prodob);
        progender = (TextView) view.findViewById(R.id.progender);
        pronation = (TextView) view.findViewById(R.id.pronation);
        proaddress = (TextView) view.findViewById(R.id.proaddress);
        promemname = (TextView) view.findViewById(R.id.promamname);
        promemphone = (TextView) view.findViewById(R.id.promemphone);
        promememail = (TextView) view.findViewById(R.id.promememail);
        logout = (TextView) view.findViewById(R.id.profile_logout);

        proliceimg1 = (ImageView) view.findViewById(R.id.prolicenseimg1);
        proliceimg2 = (ImageView) view.findViewById(R.id.prolicenseimg2);
        proidenimg1 = (ImageView) view.findViewById(R.id.proidentyimg1);
        proidenimg2 = (ImageView) view.findViewById(R.id.proidentyimg2);

        moredetials = (TextView) view.findViewById(R.id.txt_moredetials);
        secondcontact = (TextView) view.findViewById(R.id.txt_secondcontactdetials);
        licensedetials = (TextView) view.findViewById(R.id.txt_licence);
        moredetials_lay = (LinearLayout) view.findViewById(R.id.moredetials);
        seconddetials_lay = (LinearLayout) view.findViewById(R.id.secondcontact);
        license_lay = (LinearLayout) view.findViewById(R.id.licenseproof);

        proname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewEditprofileActivity.class);
                startActivity(intent);

            }
        });
        prolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        moredetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moredetials_lay.getVisibility() != View.VISIBLE) {
                    moredetials_lay.setVisibility(View.VISIBLE);
                } else {
                    moredetials_lay.setVisibility(View.GONE);
                }

            }
        });

        secondcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seconddetials_lay.getVisibility() != View.VISIBLE) {
                    seconddetials_lay.setVisibility(View.VISIBLE);
                } else {
                    seconddetials_lay.setVisibility(View.GONE);
                }

            }
        });

        licensedetials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (license_lay.getVisibility() != View.VISIBLE) {
                    license_lay.setVisibility(View.VISIBLE);
                } else {
                    license_lay.setVisibility(View.GONE);
                }

            }
        });
        load();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (proid.equals("0")){
//                    Toast.makeText(getActivity(),"Your are not login", Toast.LENGTH_SHORT).show();
//                }else {
//                try {
                session = new Session(getActivity());
                session.logoutUser();
                startActivity(new Intent(getActivity(), MainscreenActivity.class));
//                }
//                catch (NullPointerException e){
//
//                }
//                }
            }
        });

        return view;

    }

    public void load() {
//        String response = sharedprefrence.getLoginResponse(this.getActivity());
        session = new Session(getActivity());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);

        if (name.equals("0")) {
            proprofile.setVisibility(View.GONE);
            prolog.setVisibility(View.VISIBLE);
        } else {

            usid = name;
            Log.d("profileid", String.valueOf(usid));

            prolog.setVisibility(View.GONE);
            proprofile.setVisibility(View.VISIBLE);
            //  Toast.makeText(getActivity(), "u r in else if", Toast.LENGTH_SHORT).show();
            Log.d("Nsid", String.valueOf(usid));

            final String URL_user = "https://journeywheels.in/api/user.php";
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_USERS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                Log.d("Date", String.valueOf(response));
                                if (!obj.getBoolean("error")) {
                                    JSONObject userJson = obj.getJSONObject("Profile Users");
                                    Log.d("Dits", String.valueOf(userJson));
                                    proid.setText(userJson.getString("Id"));
                                    proname.setText(userJson.getString("Name"));
                                    proemail.setText(userJson.getString("Email"));
                                    prophone.setText(userJson.getString("Mobile"));
                                    prodob.setText(userJson.getString("Date Of Birth"));
                                    progender.setText(userJson.getString("Gender"));
                                    pronation.setText(userJson.getString("Nation"));
                                    proaddress.setText(userJson.getString("Address"));
                                    promemname.setText(userJson.getString("Member Name"));
                                    promemphone.setText(userJson.getString("Member Mobile"));
                                    promememail.setText(userJson.getString("Member Email"));
                                    if (proliceimg1 != null && proliceimg1.equals("")) {
                                        Picasso.get().load(userJson.getString("License Image")).into(proliceimg1);
                                    } else {
                                        Drawable myDrawable = getResources().getDrawable(R.mipmap.upload);
                                        proliceimg1.setImageDrawable(myDrawable);
                                    }

                                    if (proliceimg2 != null && proliceimg2.equals("")) {
                                        Picasso.get().load(userJson.getString("License Image1")).into(proliceimg2);
                                    } else {
                                        Drawable myDrawable = getResources().getDrawable(R.mipmap.upload);
                                        proliceimg2.setImageDrawable(myDrawable);
                                    }

                                    if (proidenimg1 != null && proidenimg1.equals("")) {
                                        Picasso.get().load(userJson.getString("Id Proof Image")).into(proidenimg1);
                                    } else {
                                        Drawable myDrawable = getResources().getDrawable(R.mipmap.upload);
                                        proidenimg1.setImageDrawable(myDrawable);
                                    }

                                    if (proidenimg2 != null && proidenimg2.equals("")) {
                                        Picasso.get().load(userJson.getString("Id Proof Image2")).into(proidenimg2);
                                    } else {
                                        Drawable myDrawable = getResources().getDrawable(R.mipmap.upload);
                                        proidenimg2.setImageDrawable(myDrawable);
                                    }

                                    progressDialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
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
                    Log.d("idsos", usid);
                    return params;
                }
            };

            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


        }
    }
}


//        if ( useid.equals("0")) {
//            proprofile.setVisibility(View.INVISIBLE);
//            prolog.setVisibility(View.VISIBLE);
//
//        } else {
//            prolog.setVisibility(View.INVISIBLE);
//            proprofile.setVisibility(View.VISIBLE);
//            Toast.makeText(getActivity(), "u r in else if", Toast.LENGTH_SHORT).show();
//            Log.d("Nsid", String.valueOf(ussid));
//
//            final String URL_user = "https://journeywheels.in/api/user.php";
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_USERS,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//
//                                JSONObject obj = new JSONObject(response);
//                                Log.d("Date", String.valueOf(response));
//
//                                if (!obj.getBoolean("error")) {
//
//                                    JSONObject userJson = obj.getJSONObject("Profile Users");
//                                    Log.d("Dits", String.valueOf(userJson));
//                                    proid.setText(userJson.getString("Id"));
//                                    proname.setText(userJson.getString("Name"));
//                                    prophone.setText(userJson.getString("Mobile"));
//                                    prodob.setText(userJson.getString("Date Of Birth"));
//                                    progender.setText(userJson.getString("Gender"));
//                                    pronation.setText(userJson.getString("Nation"));
//                                    proaddress.setText(userJson.getString("Address"));
//                                    promemname.setText(userJson.getString("Member Name"));
//                                    promemphone.setText(userJson.getString("Member Mobile"));
//                                    promememail.setText(userJson.getString("Member Email"));
//
//
//
//
//                                    Picasso.get().load(userJson.getString("License Image")).into(proliceimg1);
////                                    Glide.get().load("http://via.placeholder.com/300.png").into(proliceimg2);
//                                    Picasso.get().load(userJson.getString("License Image1")).into(proliceimg2);
//                                    Picasso.get().load(userJson.getString("Id Proof Image")).into(proidenimg1);
//                                    Picasso.get().load(userJson.getString("Id Proof Image2")).into(proidenimg2);
//
//
//                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                                } else {
//                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    ;
//                    params.put("uid", ussid);
//                    Log.d("idsos", ussid);
//                    return params;
//                }
//            };
//
//            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
//        }


//            profiledata();

//        final String URL_user = "https://journeywheels.in/api/user.php";
//            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST,URL_user, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d("Idss", String.valueOf(response));
//                            try {
//
//                                String ID = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("id");
//                                String NAME = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("name");
//                                String EMAIL = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("email");
//                                String PHONE = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("mobile");
//                                String DOB = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("dateOFBirth");
//                                String GENDER = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("gender");
//                                String NATION = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("nation");
//                                String MEMBERNAME = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("memberName");
//                                String MEMBERMOBILE = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("memberMobile");
//                                String MEMBEREMAIL = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("memberEmail");
//                                String LICENSE1 = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("licenseImage");
//                                String LICENSE2 = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("licenseImage1");
//                                String IDPROOFIMG1 = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("idProofImage");
//                                String IDPROOFIMG2 = (String) response.getJSONArray("Profile Users").getJSONObject(0).get("idProofImage1");
//
//                                Log.d("NAME", String.valueOf(ID) + String.valueOf(NAME) + String.valueOf(EMAIL) + String.valueOf(PHONE) + String.valueOf(DOB));
//
//                                proid.setText(ID);
//                                proname.setText(NAME);
//                                prophone.setText(PHONE);
//                                proemail.setText(EMAIL);
//                                prodob.setText(DOB);
//                                progender.setText(GENDER);
//                                pronation.setText(NATION);
//                                promemname.setText(MEMBERNAME);
//                                promemphone.setText(MEMBERMOBILE);
//                                promememail.setText(MEMBEREMAIL);
//                                Picasso.get().load(LICENSE1).into(proliceimg1);
//                                Picasso.get().load(LICENSE2).into(proliceimg2);
//                                Picasso.get().load(IDPROOFIMG1).into(proidenimg1);
//                                Picasso.get().load(IDPROOFIMG2).into(proidenimg2);
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                            Log.d("lerror", String.valueOf(error));
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String,String>params=new HashMap<String,String>();
//
//                    params.put("uid", ussid);
//                    Log.d("Dit", ussid);
//                    return params;
//                }
//                //                @Override
////                protected Map<String, String> getParams() throws AuthFailureError {
////                    Map<String, String> params = new HashMap<String, String>();
////
////                    return params;
////                }
//
//                ;
//            };
//            VolleySingleton.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
//
//        }

//            try {
//                userid = sharedprefrence.getUser().toString();
//            }catch (NullPointerException ignored){
//
//            }


//            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.URL_USERS,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//
//                                JSONObject obj = new JSONObject(response);
//
//                                Log.d("tials", String.valueOf(obj));
////                                if (!obj.getBoolean("error")) {
//                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                    JSONObject userJson = obj.getJSONObject("Profile Users");
//
////                                    JSONArray dataArray = obj.getJSONArray("Profile Users");
////                                    Myprofile myprofile=new Myprofile(obj.getString("id"),obj.getString("name"),
////                                            obj.getString("email"),obj.getString("mobile"),obj.getString("dateOFBirth")
////                                            ,obj.getString("gender"),obj.getString("nation"),
////                                            obj.getString("address"),obj.getString("memberName"),obj.getString("memberMobile"),
////                                            obj.getString("memberEmail"),obj.getString("licenseImage"),
////                                            obj.getString("licenseImage1"),obj.getString("idProofImage"),
////                                            obj.getString("idProofImage1"));
//
////                                Log.d("try", String.valueOf(myprofile));
////                            for (int i = 0; i < dataArray.length(); i++) {
//
//
////                                JSONObject dataobj = dataArray.getJSONObject(i);
////                                proid.setText(dataobj.getString("id"));
////                                proname.setText(dataobj.getString("name"));
////                                proemail.setText(dataobj.getString("email"));
////                                prophone.setText(dataobj.getString("mobile"));
////                                prodob.setText(dataobj.getString("dateOfBirth"));
////                                progender.setText(dataobj.getString("gender"));
////                                pronation.setText(dataobj.getString("nation"));
////                                proaddress.setText(dataobj.getString("address"));
////                                promemname.setText(dataobj.getString("memberName"));
////                                promemphone.setText(dataobj.getString("memberMobile"));
////                                promememail.setText(dataobj.getString("memberEmail"));
////                               proliceimg1.setImageResource(Integer.parseInt(dataobj.getString("licenseImage")));
////                               proliceimg2.setImageResource(Integer.parseInt(dataobj.getString("licenseImage1")));
////                               proidenimg1.setImageResource(Integer.parseInt(dataobj.getString("idProofImage")));
////                               proidenimg2.setImageResource(Integer.parseInt(dataobj.getString("idProofImage1")));
////
////                                Log.d("Deti", String.valueOf(dataobj));
//////                                    Sublocation subloc = new Sublocation(dataobj.getString("id"),dataobj.getString("subLocationName"));
////
////                                sublo.setSubLocationName(dataobj.getString("Sub Location Name"));
////                                sublo.setId(dataobj.getString("Id"));
////                                dataModelArrayList.add(sublo);
////                            }
//
////                                SublocationAdapter sub=new SublocationAdapter(dataModelArrayList,getApplicationContext());
////                                subloct.setAdapter( sub);
//
////                                } else {
////                                    Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
////                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("uid", String.valueOf(userid));
//
//
//                    return params;
//                }
//            };
//
//            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

//        }

//    public void profiledata(){

//    }

