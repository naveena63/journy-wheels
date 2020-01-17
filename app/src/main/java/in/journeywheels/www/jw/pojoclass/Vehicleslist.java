package in.journeywheels.www.jw.pojoclass;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicleslist implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Product Name")
    @Expose
    private String productName;
    @SerializedName("Product Image")
    @Expose
    private String productImage;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Km Limit")
    @Expose
    private String kmLimit;
    @SerializedName("Excess")
    @Expose
    private String excess;
    @SerializedName("Availability")
    @Expose
    private String availability;
    public final static Parcelable.Creator<Vehicleslist> CREATOR = new Creator<Vehicleslist>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Vehicleslist createFromParcel(Parcel in) {
            return new Vehicleslist(in);
        }

        public Vehicleslist[] newArray(int size) {
            return (new Vehicleslist[size]);
        }

    }
            ;
    private final static long serialVersionUID = -2805636134781191339L;

    protected Vehicleslist(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.productName = ((String) in.readValue((String.class.getClassLoader())));
        this.productImage = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.kmLimit = ((String) in.readValue((String.class.getClassLoader())));
        this.excess = ((String) in.readValue((String.class.getClassLoader())));
        this.availability = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Vehicleslist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKmLimit() {
        return kmLimit;
    }

    public void setKmLimit(String kmLimit) {
        this.kmLimit = kmLimit;
    }

    public String getExcess() {
        return excess;
    }

    public void setExcess(String excess) {
        this.excess = excess;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(productName);
        dest.writeValue(productImage);
        dest.writeValue(price);
        dest.writeValue(kmLimit);
        dest.writeValue(excess);
        dest.writeValue(availability);
    }

    public int describeContents() {
        return 0;
    }

}
