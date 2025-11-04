package src.context;
import java.sql.Connection;

import src.model.dao.BookingDao;
import src.util.DBConfig;
public class AppContext {
    private AppContext(){

    }

    //db connection
    public static Connection getConnection(){
                return DBConfig.getInstance();

    }
    public static BookingDao getBookingDao(){
        return BookingDao.getInstance();
    }
}
