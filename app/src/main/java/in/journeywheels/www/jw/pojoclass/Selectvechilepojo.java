package in.journeywheels.www.jw.pojoclass;



import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Selectvechilepojo implements Serializable, Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vehicleslist")
    @Expose
    private List<Vehicleslist> vehicleslist = null;
    public final static Parcelable.Creator<Selectvechilepojo> CREATOR = new Creator<Selectvechilepojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Selectvechilepojo createFromParcel(Parcel in) {
            return new Selectvechilepojo(in);
        }

        public Selectvechilepojo[] newArray(int size) {
            return (new Selectvechilepojo[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3155080842987726220L;

    protected Selectvechilepojo(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.vehicleslist, (in.journeywheels.www.jw.pojoclass.Vehicleslist.class.getClassLoader()));
    }

    public Selectvechilepojo() {
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

    public List<Vehicleslist> getVehicleslist() {
        return vehicleslist;
    }

    public void setVehicleslist(List<Vehicleslist> vehicleslist) {
        this.vehicleslist = vehicleslist;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(vehicleslist);
    }

    public int describeContents() {
        return 0;
    }

}