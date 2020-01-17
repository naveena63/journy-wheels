package in.journeywheels.www.jw.pojoclass;


import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Citypojo implements Serializable, Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;


    Location location=new Location();
    public final static Parcelable.Creator<Citypojo> CREATOR = new Creator<Citypojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Citypojo createFromParcel(Parcel in) {
            return new Citypojo(in);
        }

        public Citypojo[] newArray(int size) {
            return (new Citypojo[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7685153188590912726L;

    protected Citypojo(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.locations, (in.journeywheels.www.jw.pojoclass.Location.class.getClassLoader()));
    }

    public Citypojo() {
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(locations);
    }

    public int describeContents() {
        return 0;
    }

}