package in.journeywheels.www.jw.pojoclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private Integer id;
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7921529907563219244L;

    public User(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public User(String string) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}
