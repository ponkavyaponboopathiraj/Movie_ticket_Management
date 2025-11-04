package src.model.dto;

public class BookingDetail {
     private int detailId;
    private int bookingId;
    private double price;
    private String seatNo;
    private String seatType;
    public BookingDetail()
    {

    }
    public BookingDetail(int detailId, int bookingId, double price, String seatNo, String seatType) {
        this.detailId = detailId;
        this.bookingId = bookingId;
        this.price = price;
        this.seatNo = seatNo;
        this.seatType = seatType;
    }
public int getDetailId() {
        return detailId;
    }
    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSeatNo() {
        return seatNo;
    }
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    public String getSeatType() {
        return seatType;
    }
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
}
