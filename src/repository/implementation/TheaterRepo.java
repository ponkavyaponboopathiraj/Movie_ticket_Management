package src.repository.implementation;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import src.util.DBConfig;
import src.repository.enums.TheaterQuery;
import src.exception.AlreadyExistsException;
import src.exception.NotFoundExecption;
import src.model.dto.Theater;
import src.repository.interfaces.RepoInterface;

public class TheaterRepo implements RepoInterface<Theater> {

    private static TheaterRepo instance;
    private final Map<Integer, Theater> theaters = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedTheaterIds = ConcurrentHashMap.newKeySet();

    private TheaterRepo() {}

    public static synchronized TheaterRepo getInstance() {
        if (instance == null) {
            instance = new TheaterRepo();
        }
        return instance;
    }

    // Load all theaters from DB into memory
    @Override
    public void loadAll() {
        try (Connection conn = DBConfig.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(TheaterQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Theater theater = new Theater(
                        rs.getInt("theater_id"),
                        rs.getInt("owner_id"),
                        rs.getString("theater_name"),
                        rs.getString("city"),
                        rs.getString("pincode"),
                        rs.getString("contact_number"),
                        rs.getInt("total_seat"),
                        rs.getInt("total_screens"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );
                theaters.put(theater.getTheaterId(), theater);
            }

        } catch (SQLException e) {
            System.err.println("Error loading theaters: " + e.getMessage());
        }
    }

    @Override
    public List<Theater> getAll() {
        return new ArrayList<>(theaters.values());
    }

    @Override
    public void add(Theater theater) {
        int id = theaters.size() + 1;
        theater.setTheaterId(id);
        addOrUpdate(theater);
    }

    @Override
    public void update(Theater theater) {
        addOrUpdate(theater);
    }

    private void addOrUpdate(Theater theater) {
        theaters.put(theater.getTheaterId(), theater);
        unSyncedTheaterIds.add(theater.getTheaterId());
    }

    @Override
    public void deleteById(int id) {
        theaters.remove(id);
        unSyncedTheaterIds.add(id);
    }

    @Override
    public Theater getById(int id) throws NotFoundExecption {
        Theater theater = theaters.get(id);
        if (theater == null) {
            throw new NotFoundExecption("Theater with ID " + id + " not found!");
        }
        return theater;
    }

    @Override
    public void syncChanges() {
        if (unSyncedTheaterIds.isEmpty()) return;

        try (Connection conn = DBConfig.getInstance().getConnection()) {
            for (int id : unSyncedTheaterIds) {
                Theater theater = theaters.get(id);

                if (theater == null) {
                    // Delete from DB
                    try (PreparedStatement ps = conn.prepareStatement(TheaterQuery.DELETE.getQuery())) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                } else {
                    // Insert or update in DB
                    try (PreparedStatement ps = conn.prepareStatement(TheaterQuery.INSERT.getQuery())) {
                        ps.setInt(1, theater.getTheaterId());
                        ps.setInt(2, theater.getOwnerId());
                        ps.setString(3, theater.getTheaterName());
                        ps.setString(4, theater.getCity());
                        ps.setString(5, theater.getPincode());
                        ps.setString(6, theater.getContactNo());
                        ps.setInt(7, theater.getTotalScreens());
                        ps.setString(8, theater.getStatus());
                        ps.setTimestamp(9, theater.getCreatedAt());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Sync failed: " + e.getMessage());
        }

        unSyncedTheaterIds.clear();
    }

    // Custom helper methods
    public List<Theater> getTheatersByCity(String city) {
        List<Theater> list = new ArrayList<>();
        for (Theater t : theaters.values()) {
            if (t.getCity().equalsIgnoreCase(city)) {
                list.add(t);
            }
        }
        return list;
    }
private boolean theaterExists(String theaterName) {
    return theaters.containsKey(theaterName);
}
public void checkAlreadyExist(String theaterName) throws AlreadyExistsException {
    if (theaterExists(theaterName)) {
        throw new AlreadyExistsException("Theater already exists: " + theaterName);
    }
}


}
