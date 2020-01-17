package in.journeywheels.www.jw.pojoclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sublocationpojo {



    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sublocations")
    @Expose
    private List<Sublocation> sublocations = null;
    public final static Parcelable.Creator<Sublocationpojo> CREATOR = new Parcelable.Creator<Sublocationpojo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Sublocationpojo createFromParcel(Parcel in) {
            return new Sublocationpojo(in);
        }

        public Sublocationpojo[] newArray(int size) {
            return (new Sublocationpojo[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3702621593579332868L;

    protected Sublocationpojo(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.sublocations, (in.journeywheels.www.jw.pojoclass.Sublocation.class.getClassLoader()));
    }

    public Sublocationpojo() {
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

    public List<Sublocation> getSublocations() {
        return sublocations;
    }

    public void setSublocations(List<Sublocation> sublocations) {
        this.sublocations = sublocations;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(sublocations);
    }

    public int describeContents() {
        return 0;
    }

}

