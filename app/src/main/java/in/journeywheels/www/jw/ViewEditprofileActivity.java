package in.journeywheels.www.jw;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleyMultipartRequest;
import in.journeywheels.www.jw.volley.VolleySingleton;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class ViewEditprofileActivity extends AppCompatActivity {

    private static final String c_image = "c_image";
    private static final String c_image1 = "c_image1";
    private static final String c_image2 = "c_image2";
    private static final String c_image3 = "c_image3";
    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;
    String selectedPath1 = "NONE";
    String selectedPath2 = "NONE";
    private static final String IMAGE_DIRECTORY = "/ediet_upload_camera";
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final int IMG_REQUEST = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    EditText Name, Email, Phone, doB, nations, addresss, memMail, memName, memPhone, progender, useid;
    ImageView imageLicense1, imageLicense2, imageIDproof1, imageIdproof2, imageview;
    TextView proid;
    Button button;
    private Uri filePath,filePath1,filePath2;
    private Bitmap bitmap,bitmap2,bitmap3,bitmap4;
    String usid;
    Session session;
    String status;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_editprofile);

        imageview = findViewById(R.id.editiImageView);
        requestQueue = Volley.newRequestQueue(this);
        Name = findViewById(R.id.etName);
        Email = findViewById(R.id.etEmail);
        Phone = findViewById(R.id.etPhone);
        doB = findViewById(R.id.etDob);
        progender = findViewById(R.id.etGender);
        nations = findViewById(R.id.etNation);
        addresss = findViewById(R.id.etAddress);
        memMail = findViewById(R.id.etMemberEmail);
        memName = findViewById(R.id.etMemberName);
        memPhone = findViewById(R.id.etMemberMobile);
        imageLicense1 = findViewById(R.id.prolicenseimg1);
        imageLicense2 = findViewById(R.id.prolicenseimg2);
        imageIDproof1 = findViewById(R.id.proidentyimg1);
        imageIdproof2 = findViewById(R.id.proidentyimg2);
        button = findViewById(R.id.updatebutnn);

        imageLicense1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = String.valueOf(1);
//                showFileChooser();

//                openGallery(SELECT_FILE1);
//                Intent i = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        imageLicense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            status = String.valueOf(1);
//                showFileChooser();

//             openGallery(SELECT_FILE2);
            }
        });

        getdetials();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });

    }

//    private void showFileChooser() {
//
//        try {
//            FileName = System.currentTimeMillis() + ".jpg";
//            AlertDialog.Builder alertDialog;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
//            } else {
//                alertDialog = new AlertDialog.Builder(this);
//            }
//            // Setting Dialog Message
//            alertDialog.setMessage("Select Camera (OR) Gallery");
//            // Setting Icon to Dialog
//            // Setting Positive "Yes" Button
//            alertDialog.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//
//
//                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//                    {
//                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
//                    }
//                    else
//                    {
//                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                    }
//
//                }
//            });
//
//            // Setting Negative "NO" Button
//            alertDialog.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    // Write your code here to invoke NO event
//                    Intent intent = new Intent(Intent.ACTION_PICK,
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(intent, 2);
//                }
//            });
//            // Showing Alert Message
//            alertDialog.show();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void openGallery(int req_code){
//
//        Intent i = new Intent(
//                        Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//                startActivityForResult(i, req_code);
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            filePath = data.getData();
//            try {
//                imageLicense1.setImageBitmap(bitmap);
//                Log.d("selectcode",""+bitmap);
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            filePath = data.getData();
//            try {
//                imageLicense2.setImageBitmap(bitmap);
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RESULT_LOAD_IMAGE ) {
//            filePath = data.getData();
//            if (requestCode == SELECT_FILE1) {
//                Log.d("selectcode",""+requestCode);
//                try {
//                    selectedPath1 = getPath(filePath);
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                    Log.d("select1",""+bitmap);
//                    Log.d("selectreq1","select_file1");
//                    imageLicense1.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (requestCode == SELECT_FILE2)
//            {
//                try {
//                    selectedPath2 = getPath(filePath);
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                    Log.d("select2",""+selectedPath2);
//                    Log.d("selectreq2","select_file2");
//                    imageLicense2.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//
//    public byte[] imagetostring(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//        return baos.toByteArray();
//    }
//
//
//    public String getPath(Uri uri)
//    {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }

    public void getdetials()
    {
        session = new Session(this);
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
        usid = name;
        Log.d("profileid", String.valueOf(usid));
        Log.d("Nsid", String.valueOf(usid));
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final String URL_user = "https://journeywheels.in/api/user.php";
        if (imageLicense1.equals("")){

        }else {
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
//                                proid.setText(userJson.getString("Id"));
                                    Name.setText(userJson.getString("Name"));
                                    Email.setText(userJson.getString("Email"));
                                    Phone.setText(userJson.getString("Mobile"));
                                    doB.setText(userJson.getString("Date Of Birth"));
                                    progender.setText(userJson.getString("Gender"));
                                    nations.setText(userJson.getString("Nation"));
                                    addresss.setText(userJson.getString("Address"));
                                    memName.setText(userJson.getString("Member Name"));
                                    memPhone.setText(userJson.getString("Member Mobile"));
                                    memMail.setText(userJson.getString("Member Email"));
                                    imageLicense1.setImageResource(0);

                                    if(imageLicense1!=null && imageLicense1.equals("") )
                                    {
                                        Picasso.get().load(userJson.getString("License Image")).into(imageLicense1);
                                    }
                                    else {
                                        Drawable drawable=getResources().getDrawable(R.mipmap.upload);
                                        imageLicense1.setImageDrawable(drawable);
                                    }

                                    if(imageLicense2!=null && imageLicense2.equals("") )
                                    {
                                        Picasso.get().load(userJson.getString("License Image1")).into(imageLicense2);
                                    }
                                    else {
                                        Drawable drawable=getResources().getDrawable(R.mipmap.upload);
                                        imageLicense2.setImageDrawable(drawable);
                                    }

                                    if(imageIDproof1!=null && imageIDproof1.equals("") )
                                    {
                                        Picasso.get().load(userJson.getString("Id Proof Image")).into(imageIDproof1);
                                    }
                                    else {
                                        Drawable drawable=getResources().getDrawable(R.mipmap.upload);
                                        imageIDproof1.setImageDrawable(drawable);
                                    }

                                    if(imageIdproof2!=null && imageIdproof2.equals("") )
                                    {
                                        Picasso.get().load(userJson.getString("Id Proof Image2")).into(imageIdproof2);
                                    }
                                    else {
                                        Drawable drawable=getResources().getDrawable(R.mipmap.upload);
                                        imageIdproof2.setImageDrawable(drawable);
                                    }

//                                    Glide.with(getActivity()).load("http://via.placeholder.com/300.png").into(proliceimg2);



                                    // Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else {
                                    Toast.makeText(ViewEditprofileActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(ViewEditprofileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

            VolleySingleton.getInstance(ViewEditprofileActivity.this).addToRequestQueue(stringRequest);
        }
    }


    public void editProfile() {

        session = new Session(this);
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
        usid = name;
        final String nam = Name.getText().toString().trim();
        // final String eml = Email.getText().toString().trim();
        final String phne = Phone.getText().toString().trim();
        final String dateOfBrth = doB.getText().toString().trim();
        final String genderr = progender.getText().toString().trim();
        final String nationn = nations.getText().toString().trim();
        final String add = addresss.getText().toString().trim();
        final String memmal = memMail.getText().toString().trim();
        final String memnam = memName.getText().toString().trim();
        final String memphn = memPhone.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,Urls.EDIT_URLS,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.i("editprofile","editprofile"+response);
                        Toast.makeText(ViewEditprofileActivity.this, "data is updated", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(ViewEditprofileActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Intent intent=new Intent(ViewEditprofileActivity.this,EditProfileActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewEditprofileActivity.this, "unable to update ", Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nam);
                //params.put("email", eml);
                params.put("gender", genderr);
                params.put("dob", dateOfBrth);
                params.put("nation", nationn);
                params.put("mobile", phne);
                params.put("address", add);
                params.put("mem_name", memnam);
                params.put("mem_mail", memmal);
                params.put("mem_mobile", memphn);
                params.put("uid", usid);
                Log.d("id", usid);
                return params;
            }
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("c_image", new DataPart(imagename + ".jpg", imagetostring(bitmap)));
                return params;
            }
        };
        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
    public byte[] imagetostring(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }

}
