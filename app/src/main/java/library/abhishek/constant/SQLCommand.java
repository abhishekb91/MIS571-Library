package library.abhishek.constant;

/**
 * Created by abhis on 9/26/2016.
 * This file contains list of all sql commands that we will use in the application.
 */
public abstract class SQLCommand {
    //list all Students
    public static String STUDENTS = "SELECT stid, stname " +
            "FROM Student";

    //List of all Books
    public static String BOOKS = "SELECT * FROM LibBook";

    //List the call numbers of books with the title ‘Database Management’
    public static String BOOK_DB = "SELECT lbcallnum " +
            "FROM libbook " +
            "WHERE lbtitle like '%Database Management%'";

    //List book detail with the title ‘Software Engineering’
    public static String BOOK_SE = "SELECT * " +
            "FROM libbook " +
            "WHERE lbtitle like '%Software Engineering%'";

    //List book detail with the title ‘Fifth Discipline’
    public static String BOOK_FD = "SELECT * " +
            "FROM libbook " +
            "WHERE lbtitle like '%Fifth Discipline%'";

    //List of books taken by student = 111
    public static String BOOKS_ISSUED_STUDENT = "SELECT s.*,b.* " +
            "FROM Student s, CheckOut c, Libbook b " +
            "WHERE s.stid = c.stid AND c.lbcallnum = b.lbcallnum AND s.stid = 111";

    //List of Students Who have checked out Books
    public static String STUDENTS_CHECKOUT_BOOKS = "SELECT s.* " +
            "FROM Student s, CheckOut c " +
            "WHERE s.stid = c.stid " +
            "GROUP BY s.stid";

    //Updating book query
    public static String RETURN_BOOK = "UPDATE checkout " +
            "SET coreturned=? " +
            "WHERE stid=? " +
            "AND lbcallnum=?";

    //Insert into checkout table
    public static String CHECK_BOOK = "INSERT INTO checkout(stid,lbcallnum,coduedate,coreturned) " +
            "VALUES(?,?,?,?)";
}
