package in.journeywheels.www.jw.pojoclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Registerpojo implements Serializable, Parcelable
    {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("Id")
        @Expose
        private Integer id;
        public final static Parcelable.Creator<Registerpojo> CREATOR = new Creator<Registerpojo>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Registerpojo createFromParcel(Parcel in) {
                return new Registerpojo(in);
            }

            public Registerpojo[] newArray(int size) {
                return (new Registerpojo[size]);
            }

        }
                ;
        private final static long serialVersionUID = 2576862993888802981L;

        protected Registerpojo(Parcel in) {
            this.code = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.msg = ((String) in.readValue((String.class.getClassLoader())));
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Registerpojo() {
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(code);
            dest.writeValue(msg);
            dest.writeValue(id);
        }

        public int describeContents() {
            return 0;
        }

    }

