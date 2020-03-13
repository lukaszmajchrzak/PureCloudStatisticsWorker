import java.util.Objects;

public class User {
    private String fullName;
    private String tableName;

    public User(String fullName, String tableName) {
        this.fullName = fullName;
        this.tableName = tableName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fullName, user.fullName) &&
                Objects.equals(tableName, user.tableName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fullName, tableName);
    }
}
