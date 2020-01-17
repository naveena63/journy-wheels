package in.journeywheels.www.jw.pojoclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sublocation implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Sub Location Name")
    @Expose
    private String subLocationName;
    public final static Parcelable.Creator<Sublocation> CREATOR = new Creator<Sublocation>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sublocation createFromParcel(Parcel in) {
            return new Sublocation(in);
        }

        public Sublocation[] newArray(int size) {
            return (new Sublocation[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8602886401895510082L;

    public Sublocation(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.subLocationName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Sublocation() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubLocationName() {
        return subLocationName;
    }

    public void setSubLocationName(String subLocationName) {
        this.subLocationName = subLocationName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(subLocationName);
    }

    public int describeContents() {
        return 0;
    }

}