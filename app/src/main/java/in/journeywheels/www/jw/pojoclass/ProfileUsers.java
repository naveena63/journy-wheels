package in.journeywheels.www.jw.pojoclass;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class ProfileUsers implements Serializable, Parcelable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Date Of Birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Nation")
    @Expose
    private String nation;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Member Name")
    @Expose
    private String memberName;
    @SerializedName("Member Mobile")
    @Expose
    private String memberMobile;
    @SerializedName("Member Email")
    @Expose
    private String memberEmail;
    @SerializedName("License Image")
    @Expose
    private String licenseImage;
    @SerializedName("License Image1")
    @Expose
    private String licenseImage1;
    @SerializedName("Id Proof Image")
    @Expose
    private String idProofImage;
    @SerializedName("Id Proof Image2")
    @Expose
    private String idProofImage2;
    public final static Parcelable.Creator<ProfileUsers> CREATOR = new Creator<ProfileUsers>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProfileUsers createFromParcel(Parcel in) {
            return new ProfileUsers(in);
        }

        public ProfileUsers[] newArray(int size) {
            return (new ProfileUsers[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5491222534946751084L;

    public ProfileUsers(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.nation = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.memberName = ((String) in.readValue((String.class.getClassLoader())));
        this.memberMobile = ((String) in.readValue((String.class.getClassLoader())));
        this.memberEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.licenseImage = ((String) in.readValue((String.class.getClassLoader())));
        this.licenseImage1 = ((String) in.readValue((String.class.getClassLoader())));
        this.idProofImage = ((String) in.readValue((String.class.getClassLoader())));
        this.idProofImage2 = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProfileUsers(String string) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(String licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getLicenseImage1() {
        return licenseImage1;
    }

    public void setLicenseImage1(String licenseImage1) {
        this.licenseImage1 = licenseImage1;
    }

    public String getIdProofImage() {
        return idProofImage;
    }

    public void setIdProofImage(String idProofImage) {
        this.idProofImage = idProofImage;
    }

    public String getIdProofImage2() {
        return idProofImage2;
    }

    public void setIdProofImage2(String idProofImage2) {
        this.idProofImage2 = idProofImage2;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(mobile);
        dest.writeValue(dateOfBirth);
        dest.writeValue(gender);
        dest.writeValue(nation);
        dest.writeValue(address);
        dest.writeValue(memberName);
        dest.writeValue(memberMobile);
        dest.writeValue(memberEmail);
        dest.writeValue(licenseImage);
        dest.writeValue(licenseImage1);
        dest.writeValue(idProofImage);
        dest.writeValue(idProofImage2);
    }

    public int describeContents() {
        return 0;
    }

}