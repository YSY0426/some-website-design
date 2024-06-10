import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final String USERNAME = "CSisCoolStuff";
    private static final String PASSWORD = "CSisCoolStuff";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            response.sendRedirect("welcome.html");
        } else {
            showLoginForm(response, false, false);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = "on".equals(request.getParameter("rememberMe"));

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            if (rememberMe) {
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", password);
                usernameCookie.setMaxAge(6 * 30 * 24 * 60 * 60);
                passwordCookie.setMaxAge(6 * 30 * 24 * 60 * 60);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);

                boolean cookiesSet = false;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
                            cookiesSet = true;
                            break;
                        }
                    }
                }
                if (!cookiesSet) {
                    response.sendRedirect("loggedInWithCookieWarning.html");
                    return;
                }
            }
            response.sendRedirect("loggedIn.html");
        } else {
            showLoginForm(response, true, rememberMe);
        }
    }

    private void showLoginForm(HttpServletResponse response, boolean error, boolean rememberMe)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (error) {
            out.println("<p style='color:red;'>Invalid username or password. Please try again.</p>");
        }
        out.println("<form method='post' action='LoginServlet'>");
        out.println("Username: <input type='text' name='username'><br>");
        out.println("Password: <input type='password' name='password'><br>");
        out.println("<input type='checkbox' name='rememberMe'" + (rememberMe ? " checked" : "") + "> Remember me<br>");
        out.println("<input type='submit' value='Login'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
