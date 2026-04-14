import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation for Leap Year Checker (Problem 54)
 * This servlet validates the input year, checks for leap year status,
 * and lists the next 5 leap years.
 * 
 * Concept Practiced: doGet/doPost
 * 
 * @author Amol
 */
@WebServlet("/LeapYearServlet")
public class LeapYearServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST request to check leap year status
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String yearStr = request.getParameter("year");

        // CSS Styles for the result page
        String styles = "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f7f6; display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + ".result-card { background: white; padding: 2.5rem; border-radius: 12px; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); width: 100%; max-width: 500px; text-align: center; border-top: 5px solid #007bff; }"
                + "h2 { color: #333; margin-bottom: 1rem; }"
                + ".success { color: #28a745; font-size: 1.25rem; font-weight: bold; background: #e9f7ef; padding: 1rem; border-radius: 8px; border: 1px solid #c3e6cb; margin: 1.5rem 0; }"
                + ".error { color: #dc3545; font-size: 1.25rem; font-weight: bold; background: #fdeaea; padding: 1rem; border-radius: 8px; border: 1px solid #f5c6cb; margin: 1.5rem 0; }"
                + "ul { list-style: none; padding: 0; margin-top: 1rem; }"
                + "li { padding: 0.5rem; background: #f8f9fa; margin-bottom: 0.5rem; border-radius: 4px; color: #444; border: 1px solid #e9ecef; transition: transform 0.2s; }"
                + "li:hover { transform: scale(1.02); background: #e2e6ea; }"
                + ".back-link { display: inline-block; margin-top: 2rem; color: #007bff; text-decoration: none; font-weight: 500; border: 1px solid #007bff; padding: 0.5rem 1rem; border-radius: 4px; transition: all 0.2s; }"
                + ".back-link:hover { background-color: #007bff; color: white; }"
                + "h3 { margin-top: 2rem; color: #555; border-bottom: 1px solid #eee; padding-bottom: 0.5rem; }"
                + "</style>";

        out.println("<!DOCTYPE html><html><head><title>Result - Leap Year Checker</title>" + styles + "</head><body>");
        out.println("<div class='result-card'>");
        out.println("<h2>Analysis Result</h2>");

        try {
            if (yearStr == null || yearStr.trim().isEmpty()) {
                throw new NumberFormatException("Empty input");
            }

            int year = Integer.parseInt(yearStr);

            if (year <= 0) {
                out.println("<div class='error'>Invalid year entered! Please enter a year greater than 0.</div>");
            } else {
                // Leap year logic: divisible by 4 but not 100, or divisible by 400
                boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

                if (isLeap) {
                    out.println("<div class='success'>" + year + " is a Leap Year! 🎉</div>");
                } else {
                    out.println("<div class='error'>" + year + " is NOT a Leap Year.</div>");
                }

                // Calculate next 5 leap years
                out.println("<h3>Next 5 Leap Years After " + year + ":</h3>");
                out.println("<ul>");
                int count = 0;
                int nextYear = year + 1;
                while (count < 5) {
                    if ((nextYear % 4 == 0 && nextYear % 100 != 0) || (nextYear % 400 == 0)) {
                        out.println("<li>" + nextYear + "</li>");
                        count++;
                    }
                    nextYear++;
                }
                out.println("</ul>");
            }

        } catch (NumberFormatException e) {
            out.println("<div class='error'>Invalid Input! Please enter a valid numerical year.</div>");
        }

        out.println("<a href='index.html' class='back-link'>← Back to Checker</a>");
        out.println("</div></body></html>");
    }

    /**
     * Redirects GET requests to index page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}