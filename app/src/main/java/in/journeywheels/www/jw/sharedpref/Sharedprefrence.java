package in.journeywheels.www.jw.sharedpref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import in.journeywheels.www.jw.MainscreenActivity;
import in.journeywheels.www.jw.pojoclass.User;

public class Sharedprefrence {

    //the constants
    private static final String SHARED_PREF_NAME = "jounerywheels";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_INST_NAME = "keyinstutename";
    private static final String KEY_ID = "keyid";
    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_PHONE_NO = "keyphone";
    private static final String KEY_ACTIVE="keyactive";
    private static final String KEY_CATEGORY_ID = "keycategoryid";
    public static final String LOGINRESPONSE = "LOGINRESPONSE";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";

    private static SharedPreferences mInstance;
    private static Context mCtx;

    private Sharedprefrence(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPreferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = (SharedPreferences) new Sharedprefrence(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(int id,int category_id, String institute_name ,String active,String address,String email,String user_id,String phone) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, id);
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null)
        );
    }

    public static void setLoginResponse(Context context, String response) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGINRESPONSE, response);
        editor.commit();
    }
    public static String getLoginResponse(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager	.getDefaultSharedPreferences(context);
        String response = null;
        if (sharedPreferences != null) {
            response = sharedPreferences.getString(LOGINRESPONSE, null);
        }
        return response;
    }
    public static void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent i = new Intent(mCtx, MainscreenActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        mCtx.startActivity(i);
    }
    public boolean isUserLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }


}
