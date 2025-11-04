package src.model.dto;
import java.sql.Timestamp;
public class Booking {
    private int bookingId;
    private int userId;
    private int showId;
    private double totalAmount;
    private String paymentStatus;
    private String bookingStatus;
    private Timestamp bookedAt;
    
    public Booking()
    {
        
    }
      public Booking(int bookingId, int userId, int showId, double totalAmount, String paymentStatus,
            String bookingStatus, Timestamp bookedAt) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.bookingStatus = bookingStatus;
        this.bookedAt = bookedAt;
    }
public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getShowId() {
        return showId;
    }
    public void setShowId(int showId) {
        this.showId = showId;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public String getBookingStatus() {
        return bookingStatus;
    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
    public Timestamp getBookedAt() {
        return bookedAt;
    }
    public void setBookedAt(Timestamp bookedAt) {
        this.bookedAt = bookedAt;
    }
}
