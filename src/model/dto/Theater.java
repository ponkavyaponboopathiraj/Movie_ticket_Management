package src.model.dto;
import java.sql.Timestamp;
public class Theater {
        private int theaterId;
    private int ownerId;
    private String theaterName;
    private String city;
    private String pincode;
    private String contactNo;
    private int total_seat;
    private int totalScreens;
    private String status;
    private Timestamp createdAt;

    public Theater()
    {
        
    }

    public Theater(int theaterId, int ownerId, String theaterName, String city, String pincode, String contactNo,
            int total_seat,int totalScreens, String status, Timestamp createdAt) {
        this.theaterId = theaterId;
        this.ownerId = ownerId;
        this.theaterName = theaterName;
        this.city = city;
        this.pincode = pincode;
        this.contactNo = contactNo;
        this.total_seat=total_seat;
        this.totalScreens = totalScreens;
        this.status = status;
        this.createdAt = createdAt;
    }
    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
       public int getTotal_seat() {
        return total_seat;
    }

    public void setTotal_seat(int total_seat) {
        this.total_seat = total_seat;
    }

    public int getTotalScreens() {
        return totalScreens;
    }

    public void setTotalScreens(int totalScreens) {
        this.totalScreens = totalScreens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
