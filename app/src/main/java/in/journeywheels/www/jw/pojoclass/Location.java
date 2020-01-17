package in.journeywheels.www.jw.pojoclass;



        import java.io.Serializable;
        import android.os.Parcel;
        import android.os.Parcelable;
        import android.os.Parcelable.Creator;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Location implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Location Name")
    @Expose
    private String locationName;
    @SerializedName("Location Image")
    @Expose
    private String locationImage;
    public final static Parcelable.Creator<Location> CREATOR = new Creator<Location>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return (new Location[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4622740533029138310L;

    protected Location(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.locationName = ((String) in.readValue((String.class.getClassLoader())));
        this.locationImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Location() {
    }

    public Location(String id, String locationname, String locationimage) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(String locationImage) {
        this.locationImage = locationImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(locationName);
        dest.writeValue(locationImage);
    }

    public int describeContents() {
        return 0;
    }

}