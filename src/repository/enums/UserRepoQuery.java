package src.repository.enums;

public enum UserRepoQuery {

    // Load all users
    LOADALL("SELECT * FROM users"),

    // Insert or update user (PostgreSQL UPSERT)
    INSERT("""
        INSERT INTO users 
        (user_id, full_name, email, password, phone, role, status, created_at)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (user_id) DO UPDATE SET
            full_name = EXCLUDED.full_name,
            email = EXCLUDED.email,
            password = EXCLUDED.password,
            phone = EXCLUDED.phone,
            role = EXCLUDED.role,
            status = EXCLUDED.status,
            created_at = EXCLUDED.created_at
        """),

    // Delete user by ID
    DELETE("DELETE FROM users WHERE user_id = ?");

    private final String query;

    UserRepoQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
