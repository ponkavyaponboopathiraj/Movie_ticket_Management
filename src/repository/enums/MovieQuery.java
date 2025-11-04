package src.repository.enums;

public enum MovieQuery {
    
    //Load all movies
    LOADALL("SELECT * FROM movies"),

    //Insert or update movie (using ON CONFLICT for PostgreSQL UPSERT)
    INSERT("""
        INSERT INTO movies 
        (movie_id, title, genre, language, duration, movie_cast, director, release_date, rating, description, status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (movie_id) 
        DO UPDATE SET 
            title = EXCLUDED.title,
            genre = EXCLUDED.genre,
            language = EXCLUDED.language,
            duration = EXCLUDED.duration,
            movie_cast = EXCLUDED.movie_cast,
            director = EXCLUDED.director,
            release_date = EXCLUDED.release_date,
            rating = EXCLUDED.rating,
            description = EXCLUDED.description,
            status = EXCLUDED.status
        """),

    // Delete movie by ID
    DELETE("DELETE FROM movies WHERE movie_id = ?");

    private final String query;

    MovieQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
