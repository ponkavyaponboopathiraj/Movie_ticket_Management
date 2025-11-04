package src.repository.enums;

public enum BookingQuery {

    LOADALL("SELECT booking_id, user_id, show_id, total_amount, payment_status, booking_status, booked_at FROM bookings"),
    
    INSERT("INSERT INTO bookings (booking_id, user_id, show_id, total_amount, payment_status, booking_status, booked_at) " +
           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
           "ON CONFLICT (booking_id) DO UPDATE SET " +
           "user_id = EXCLUDED.user_id, " +
           "show_id = EXCLUDED.show_id, " +
           "total_amount = EXCLUDED.total_amount, " +
           "payment_status = EXCLUDED.payment_status, " +
           "booking_status = EXCLUDED.booking_status, " +
           "booked_at = EXCLUDED.booked_at"),
    
    DELETE("DELETE FROM bookings WHERE booking_id = ?");

    private final String query;

    BookingQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
