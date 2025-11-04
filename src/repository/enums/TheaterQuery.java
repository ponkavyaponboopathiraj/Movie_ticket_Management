package src.repository.enums;

public enum TheaterQuery {

    LOADALL("SELECT theater_id, name, location, contact_number, email, total_seat,total_screens, status FROM theaters"),

    INSERT("INSERT INTO theaters (theater_id, name, location, contact_number, email, total_screens, status) " +
           "VALUES (?, ?, ?, ?, ?, ?, ?) " +
           "ON CONFLICT (theater_id) DO UPDATE SET " +
           "name = EXCLUDED.name, " +
           "location = EXCLUDED.location, " +
           "contact_number = EXCLUDED.contact_number, " +
           "email = EXCLUDED.email, " +
           "total_screens = EXCLUDED.total_screens, " +
           "status = EXCLUDED.status"),

    DELETE("DELETE FROM theaters WHERE theater_id = ?");

    private final String query;

    TheaterQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
