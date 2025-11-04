package src.repository.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import src.repository.enums.UserRepoQuery;
import src.repository.interfaces.RepoInterface;
import src.util.DBConfig;
import src.model.dto.User;
import src.exception.AlreadyExistsException;
import src.exception.NotFoundExecption;

public class UserRepo implements RepoInterface<User> {

    private static UserRepo instance;

    private UserRepo() {}

    public static synchronized UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedUserIds = ConcurrentHashMap.newKeySet();

    @Override
    public void loadAll() {
        try (PreparedStatement ps = DBConfig.getInstance().getConnection()
                .prepareStatement(UserRepoQuery.LOADALL.getQuery());
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );
                users.put(user.getUserId(), user);
            }

            System.out.println("Loaded " + users.size() + " users from DB.");

        } catch (SQLException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void add(User user) {
        try {
            checkAlreadyExist(user.getEmail());
            int id = users.size() + 1;
            user.setUserId(id);
            addOrUpdate(user);
        } catch (AlreadyExistsException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        addOrUpdate(user);
    }

    private void addOrUpdate(User user) {
        users.put(user.getUserId(), user);
        unSyncedUserIds.add(user.getUserId());
    }

    @Override
    public void deleteById(int id) {
        users.remove(id);
        unSyncedUserIds.add(id);
    }

    @Override
    public User getById(int id) throws NotFoundExecption {
        User user = users.get(id);
        if (user == null) {
            throw new NotFoundExecption("User with ID " + id + " not found!");
        }
        return user;
    }

    @Override
    public void syncChanges() {
        if (unSyncedUserIds.isEmpty()) return;

        try (Connection conn = DBConfig.getInstance().getConnection()) {
            for (int id : unSyncedUserIds) {
                User user = users.get(id);

                if (user != null) {
                    try (PreparedStatement ps = conn.prepareStatement(UserRepoQuery.INSERT.getQuery())) {
                        ps.setInt(1, user.getUserId());
                        ps.setString(2, user.getFullName());
                        ps.setString(3, user.getEmail());
                        ps.setString(4, user.getPassword());
                        ps.setString(5, user.getPhone());
                        ps.setString(6, user.getRole());
                        ps.setString(7, user.getStatus());
                        ps.setTimestamp(8, user.getCreatedAt());
                        ps.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement(UserRepoQuery.DELETE.getQuery())) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                }
            }

            System.out.println("User data synchronized successfully!");
            unSyncedUserIds.clear();

        } catch (SQLException e) {
            System.err.println("Sync failed: " + e.getMessage());
        }
    }

  
    private boolean userExists(String email) {
        return users.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    public void checkAlreadyExist(String email) throws AlreadyExistsException {
        if (userExists(email)) {
            throw new AlreadyExistsException("User already exists with email: " + email);
        }
    }

    public List<User> getUsersByRole(String role) {
        List<User> list = new ArrayList<>();
        for (User u : users.values()) {
            if (u.getRole().equalsIgnoreCase(role)) {
                list.add(u);
            }
        }
        return list;
    }
}
