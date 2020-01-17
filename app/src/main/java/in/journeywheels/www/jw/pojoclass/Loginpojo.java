package in.journeywheels.www.jw.pojoclass;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Loginpojo implements Serializable, Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private User user;
    public final static Parcelable.Creator<Loginpojo> CREATOR = new Creator<Loginpojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Loginpojo createFromParcel(Parcel in) {
            return new Loginpojo(in);
        }

        public Loginpojo[] newArray(int size) {
            return (new Loginpojo[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6537393358258327686L;

    protected Loginpojo(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.user = ((User) in.readValue((User.class.getClassLoader())));
    }

    public Loginpojo() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(user);
    }

    public int describeContents() {
        return 0;
    }

}