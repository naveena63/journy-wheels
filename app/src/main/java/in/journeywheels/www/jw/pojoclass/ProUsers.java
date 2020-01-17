package in.journeywheels.www.jw.pojoclass;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProUsers implements Serializable, Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Profile Users")
    @Expose
    private ProfileUsers profileUsers;
    public final static Parcelable.Creator<ProUsers> CREATOR = new Creator<ProUsers>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProUsers createFromParcel(Parcel in) {
            return new ProUsers(in);
        }

        public ProUsers[] newArray(int size) {
            return (new ProUsers[size]);
        }

    }
            ;
    private final static long serialVersionUID = 776384641051490173L;

    protected ProUsers(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.profileUsers = ((ProfileUsers) in.readValue((ProfileUsers.class.getClassLoader())));
    }

    public ProUsers() {
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

    public ProfileUsers getProfileUsers() {
        return profileUsers;
    }

    public void setProfileUsers(ProfileUsers profileUsers) {
        this.profileUsers = profileUsers;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(profileUsers);
    }

    public int describeContents() {
        return 0;
    }

}
