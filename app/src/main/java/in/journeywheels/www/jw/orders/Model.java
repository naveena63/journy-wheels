package in.journeywheels.www.jw.orders;

public class Model {

    private  String  custmorId;
    private  String  razorPayOrderId;
    private  String  paymentId;
    private  String  VehicleTitle;
    private  String  VehicleID;
    private  String  pickLocation;
    private  String  pickDateTime;
    private  String  dropLocation;
    private  String  dropDateTime;
    private  String  location;
    private  String  orderDate;
    private  String  amount;

    public String getCustmorId() {
        return custmorId;
    }

    public void setCustmorId(String custmorId) {
        this.custmorId = custmorId;
    }

    public String getRazorPayOrderId() {
        return razorPayOrderId;
    }

    public void setRazorPayOrderId(String razorPayOrderId) {
        this.razorPayOrderId = razorPayOrderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getVehicleTitle() {
        return VehicleTitle;
    }

    public void setVehicleTitle(String vehicleTitle) {
        VehicleTitle = vehicleTitle;
    }

    public String getPickLocation() {
        return pickLocation;
    }

    public void setPickLocation(String pickLocation) {
        this.pickLocation = pickLocation;
    }

    public String getPickDateTime() {
        return pickDateTime;
    }

    public void setPickDateTime(String pickDateTime) {
        this.pickDateTime = pickDateTime;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getDropDateTime() {
        return dropDateTime;
    }

    public void setDropDateTime(String dropDateTime) {
        this.dropDateTime = dropDateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getVehicleID() {
        return VehicleID;

    }

    public void setVehicleID(String vehicleID) {
        VehicleID = vehicleID;
    }
}
