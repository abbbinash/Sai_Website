import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class book_form extends HttpServlet {
    //private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("phone");
        String address = request.getParameter("address");
        String location = request.getParameter("location");
        String guests = request.getParameter("guests");
        String arrivals = request.getParameter("arrivals");
        String leaving = request.getParameter("leaving");

        String jdbcUrl = "jdbc:mysql://localhost:3306/booking";
        String username = "root";
        String password = "saiadmin";

        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database");

            String sql = "INSERT INTO booking_info (name, email, mobile, address, location, guests, arrivals, leaving) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, mobile);
            pstmt.setString(4, address);
            pstmt.setString(5, location);
            pstmt.setString(6, guests);
            pstmt.setString(7, arrivals);
            pstmt.setString(8, leaving);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking successful!");
                response.sendRedirect("package.html");
            } else {
                System.out.println("Booking failed!");
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}


