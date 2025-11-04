package src.repository.implementation;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import src.util.DBConfig;
import src.repository.enums.MovieQuery;
import src.exception.NotFoundExecption;
import src.model.dto.Movie;
import src.repository.interfaces.RepoInterface;

public class MovieRepo implements RepoInterface<Movie> {

    private static MovieRepo instance;

    private final Map<Integer, Movie> movies = new ConcurrentHashMap<>();
    private final Set<Integer> unSyncedMovieIds = ConcurrentHashMap.newKeySet();

    private MovieRepo() { }

    public static MovieRepo getInstance() {
        if (instance == null) {
            instance = new MovieRepo();
        }
        return instance;
    }

    @Override
    public void loadAll() {
        try (PreparedStatement ps = DBConfig.getInstance().getConnection()
                .prepareStatement(MovieQuery.LOADALL.getQuery())) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getString("language"),
                    rs.getInt("duration"),
                    rs.getString("movie_cast"),
                    rs.getString("director"),
                    rs.getDate("release_date"),
                    rs.getDouble("rating"),
                    rs.getString("description"),
                    rs.getString("status")
                );
                movies.put(movie.getMovieId(), movie);
            }

        } catch (SQLException e) {
            System.out.println("Error loading movies: " + e.getMessage());
        }
    }

    @Override
    public List<Movie> getAll() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public void update(Movie movie) {
        addOrUpdate(movie);
    }

    @Override
    public void add(Movie movie) {
        int id = movies.size() + 1;
        movie.setMovieId(id);
        addOrUpdate(movie);
    }

    private void addOrUpdate(Movie movie) {
        movies.put(movie.getMovieId(), movie);
        unSyncedMovieIds.add(movie.getMovieId());
    }

    @Override
    public void deleteById(int id) {
        movies.remove(id);
        unSyncedMovieIds.add(id);
    }

    @Override
    public Movie getById(int id) throws NotFoundExecption {
        Movie movie = movies.get(id);
        if (movie == null) {
            throw new NotFoundExecption("Movie with ID " + id + " not found!");
        }
        return movie;
    }

    @Override
    public void syncChanges() {
        if (unSyncedMovieIds.isEmpty()) return;

        try (Connection conn = DBConfig.getInstance().getConnection()) {
            for (int id : unSyncedMovieIds) {
                Movie movie = movies.get(id);

                if (movie == null) {
                    try (PreparedStatement ps = conn.prepareStatement(MovieQuery.DELETE.getQuery())) {
                        ps.setInt(1, id);
                        ps.executeUpdate();
                    }
                } else {
                    try (PreparedStatement ps = conn.prepareStatement(MovieQuery.INSERT.getQuery())) {
                        ps.setInt(1, movie.getMovieId());
                        ps.setString(2, movie.getTitle());
                        ps.setString(3, movie.getGenre());
                        ps.setString(4, movie.getLanguage());
                        ps.setInt(5, movie.getDuration());
                        ps.setString(6, movie.getMovieCast());
                        ps.setString(7, movie.getDirector());
                        ps.setDate(8, movie.getReleaseDate());
                        ps.setDouble(9, movie.getRating());
                        ps.setString(10, movie.getDescription());
                        ps.setString(11, movie.getStatus());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Sync failed: " + e.getMessage());
        }

        unSyncedMovieIds.clear();
    }

    public boolean checkAlreadyExist(String title) {
        return movies.values().stream()
                .anyMatch(m -> m.getTitle().equalsIgnoreCase(title));
    }
}
