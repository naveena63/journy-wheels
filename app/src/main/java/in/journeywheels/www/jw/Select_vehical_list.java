package in.journeywheels.www.jw;

public class Select_vehical_list {
    String tittle;
    String price;

    public String getSelect_veh_id() {
        return select_veh_id;
    }

    public void setSelect_veh_id(String select_veh_id) {
        this.select_veh_id = select_veh_id;
    }

    String select_veh_id ;
    int Image;
    String date1,date2;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public Select_vehical_list(String tittle, String price, int image, String date1, String date2,String select_veh_id) {
        this.tittle = tittle;
        this.price = price;
        Image = image;
        this.date1 = date1;
        this.date2 = date2;
        this.select_veh_id=select_veh_id;
    }


}
