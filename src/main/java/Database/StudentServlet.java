package Database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");

            // Create a statement
            Statement stmt = con.createStatement();

            // Execute a query
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            // Display the data in an HTML table with basic styling
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Students List</title>");
            out.println("<style>");
            out.println("table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: collapse;");
            out.println("}");
            out.println("table, th, td {");
            out.println("    border: 1px solid black;");
            out.println("    padding: 8px;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: #f2f2f2;");
            out.println("}");
            out.println("a.button {");
            out.println("    display: inline-block;");
            out.println("    padding: 10px 20px;");
            out.println("    background-color: #4CAF50;");
            out.println("    color: white;");
            out.println("    text-decoration: none;");
            out.println("    border-radius: 5px;");
            out.println("}");
            out.println("a.button:hover {");
            out.println("    background-color: #45a049;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Students List</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Name</th>");
            out.println("<th>DOB</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Address</th>");
            out.println("<th>Mobile</th>");
            out.println("<th>Email</th>");
            out.println("<th>Course</th>");
            out.println("<th>Study</th>");
            out.println("</tr>");

            while (rs.next()) {
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String course = rs.getString("course");
                String study = rs.getString("study");

                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + dob + "</td>");
                out.println("<td>" + gender + "</td>");
                out.println("<td>" + address + "</td>");
                out.println("<td>" + mobile + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>" + course + "</td>");
                out.println("<td>" + study + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

            // Styled Admin Login link
            out.println("<br><br>");
            out.println("<a href='AdminLogin.html' class='button'>Admin Login</a>");

            out.println("</body>");
            out.println("</html>");

            // Close the connections
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
}
