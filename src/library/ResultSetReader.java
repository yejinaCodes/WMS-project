package library;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetReader {
    public  <T> T getColumnValue(ResultSet rs, String value, Class<T> type) {
        try {
            rs.findColumn(value);
            if (type.equals(String.class)) {
                return type.cast(rs.getString(value));
            } else if (type.equals(Integer.class)) {
                int i = rs.getInt(value);
                return rs.wasNull() ? null : type.cast(i);
            }

        } catch (SQLException e) {
            return null;
        }
        return null;
    }

}
