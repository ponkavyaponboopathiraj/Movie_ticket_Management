package src.model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
public class Show {
     private int showId;
    private int movieId;
    private Date showDate;
    private Time startTime;
    private Time endTime;
    private double ticketPrice;
    private int availableSeats;
    private String status;
    private Timestamp createdAt;

    
    public Show()
    {
        
    }
     public Show(int showId, int movieId,  Date showDate, Time startTime, Time endTime, double ticketPrice,
            int availableSeats, String status, Timestamp createdAt) {
        this.showId = showId;
        this.movieId = movieId;
        
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.status = status;
        this.createdAt = createdAt;
    }
    public int getShowId() {
        return showId;
    }
    public void setShowId(int showId) {
        this.showId = showId;
    }
    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
   
    public Date getShowDate() {
        return showDate;
    }
    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }
    public Time getStartTime() {
        return startTime;
    }
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getEndTime() {
        return endTime;
    }
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
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

