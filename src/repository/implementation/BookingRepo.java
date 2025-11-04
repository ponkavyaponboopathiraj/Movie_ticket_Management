package src.repository.implementation;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import src.util.DBConfig;
import src.repository.enums.BookingQuery;
import src.exception.NotFoundExecption;
import src.model.dto.Booking;
import src.repository.interfaces.RepoInterface;

public class BookingRepo implements RepoInterface<Booking> {

    private static BookingRepo instance;
    private final Map<Integer, Booking> bookings = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedBookingIds = ConcurrentHashMap.newKeySet();

    private BookingRepo() { }

    // Singleton instance
    public static synchronized BookingRepo getInstance() {
        if (instance == null) {
            instance = new BookingRepo();
        }
        return instance;
    }

    // Load all bookings from DB
    @Override
    public void loadAll() {
        try (PreparedStatement ps = DBConfig.getInstance().getConnection()
                .prepareStatement(BookingQuery.LOADALL.getQuery())) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("user_id"),
                        rs.getInt("show_id"),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_status"),
                        rs.getString("booking_status"),
                        rs.getTimestamp("booked_at")
                );
                bookings.put(booking.getBookingId(), booking);
            }

        } catch (SQLException e) {
            System.out.println("Error loading bookings: " + e.getMessage());
        }
    }

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }

    @Override
    public void add(Booking booking) {
        int id = bookings.size() + 1;
        booking.setBookingId(id);
        addOrUpdate(booking);
    }

    @Override
    public void update(Booking booking) {
        addOrUpdate(booking);
    }

    private void addOrUpdate(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
        unSyncedBookingIds.add(booking.getBookingId());
    }

    @Override
    public void deleteById(int id) {
        bookings.remove(id);
        unSyncedBookingIds.add(id);
    }

    @Override
    public Booking getById(int id) throws NotFoundExecption {
        Booking booking = bookings.get(id);
        if (booking == null) {
            throw new NotFoundExecption("Booking with ID " + id + " not found!");
        }
        return booking;
    }

    @Override
    public void syncChanges() {
        if (unSyncedBookingIds.isEmpty()) return;

        try (Connection conn = DBConfig.getInstance().getConnection()) {
            for (int id : unSyncedBookingIds) {
                Booking booking = bookings.get(id);

                if (booking == null) {
                    try (PreparedStatement ps = conn.prepareStatement(BookingQuery.DELETE.getQuery())) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement(BookingQuery.INSERT.getQuery())) {
                        ps.setInt(1, booking.getBookingId());
                        ps.setInt(2, booking.getUserId());
                        ps.setInt(3, booking.getShowId());
                        ps.setDouble(4, booking.getTotalAmount());
                        ps.setString(5, booking.getPaymentStatus());
                        ps.setString(6, booking.getBookingStatus());
                        ps.setTimestamp(7, booking.getBookedAt());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Sync failed: " + e.getMessage());
        }

        unSyncedBookingIds.clear();
    }

    // Extra helper
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings.values()) {
            if (booking.getUserId() == userId) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
}
