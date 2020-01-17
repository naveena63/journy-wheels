package in.journeywheels.www.jw;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.volley.Urls;
import in.journeywheels.www.jw.volley.VolleySingleton;


public class EditProfileActivity extends AppCompatActivity {
    ImageView imageview,imageview1;
    private static int RESULT_LOAD_IMAGE = 1;
    private Uri filePath;
    private Bitmap bitmap;
    Session session;
    String usid,status;
    TextView Name, Email, Phone, doB, nations, addresss, memMail, memName, memPhone, progender, useid;
    ImageView imageLicense1, imageLicense2, imageIDproof1, imageIdproof2;
    TextView proid;
    Button button;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private String FileName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        imageview = findViewById(R.id.editiImageView);
        Name = findViewById(R.id.etName);
        Email = findViewById(R.id.etEmail);
        Phone = findViewById(R.id.etPhone);
        doB = findViewById(R.id.etDob);
        proid = (TextView) findViewById(R.id.proid);
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
        button = findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ViewEditprofileActivity.class);
                startActivity(intent);
            }
        });
        load();
        imageLicense1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = String.valueOf(1);

               Intent i = new Intent(
                       Intent.ACTION_PICK,
                       android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

               startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        }); imageLicense2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = String.valueOf(2);

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }
    private void showFileChooser() {

        try {
            FileName = System.currentTimeMillis() + ".jpg";
            AlertDialog.Builder alertDialog;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                alertDialog = new AlertDialog.Builder(this);
            }
            // Setting Dialog Message
            alertDialog.setMessage("Select Camera (OR) Gallery");
            //            // Setting Icon to Dialog
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void onClick(DialogInterface dialog, int which) {


                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                    /*try {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Util.getInstance().DeleteSDFolder(getResources().getString(R.string.app_name) + "/Temp");
                        File file = Util.getInstance().CreateSDFile(FileName, getResources().getString(R.string.app_name) + "/Temp");
                        Uri imgUri = Uri.fromFile(file);
                        CameraImageUri = imgUri;
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                        //intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                        startActivityForResult(intent, 1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            });
            // Showing Alert Message
            alertDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        session = new Session(this);
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.KEY_NAME);
        usid = name;
        Log.d("profileid", String.valueOf(usid));
        Log.d("Nsid", String.valueOf(usid));
        final ProgressDialog progressDialog = new ProgressDialog(this);
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

                                Picasso.get().load(userJson.getString("License Image")).into(imageLicense1);
                                Log.e("image1","image1"+imageLicense1);
//                                    Glide.with(getActivity()).load("http://via.placeholder.com/300.png").into(proliceimg2);
                                Picasso.get().load(userJson.getString("License Image1")).into(imageLicense2);
                                Picasso.get().load(userJson.getString("Id Proof Image1")).into(imageIDproof1);
                                Picasso.get().load(userJson.getString("Id Proof Image2")).into(imageIDproof1);
                                // Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            } else {
                                Toast.makeText(EditProfileActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(EditProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(EditProfileActivity.this).addToRequestQueue(stringRequest);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            filePath = data.getData();
            try {
                imageLicense1.setImageBitmap(bitmap);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       else if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            filePath = data.getData();
            try {
                imageLicense2.setImageBitmap(bitmap);
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

/*
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }*/

}
