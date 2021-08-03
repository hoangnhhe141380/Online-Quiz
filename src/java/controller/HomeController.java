package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("USERMODEL");

            /* Check servlet path */
            String url = request.getServletPath();

            switch (url) {
                case "/register":
                    request.getRequestDispatcher("./jsp/register.jsp").forward(request, response);
                    break;
                case "/logout":
                    request.getSession().removeAttribute("USERMODEL");
                    response.sendRedirect("home");
                    return;     
                default:
                    if (user != null) {
                        response.sendRedirect("takequiz");
                        return;
                    }   
                    break;
            }
                
            request.getRequestDispatcher("./jsp/home.jsp").forward(request, response);
            
        } catch (IOException | ServletException e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            String url = request.getServletPath();
            UserDAO userDAO = new UserDAO();

            if (url.equals("/login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                User user = userDAO.login(username, password);
                
                if (user != null) {
                    request.getSession().setAttribute("USERMODEL", user);
                    response.sendRedirect("home");
                } else {
                    request.setAttribute("messageFail", "Username or Password is incorrect!");
                    request.getRequestDispatcher("./jsp/home.jsp").forward(request, response);
                }

            } else if (url.equals("/register")) {

                String username = request.getParameter("username");
                String email = request.getParameter("email");

                /* Check if username or email is already existed in database */
                if (userDAO.checkUserExisted(username)) {
                    request.setAttribute("messageFailed", "This username is already existed!");
                } else if (userDAO.checkEmailExisted(email)) {
                    request.setAttribute("messageFailed", "This email is already existed!");
                } else {
                    /* If username and email are okay , new user can be saved */
                    String password = request.getParameter("password");
                    int role = Integer.parseInt(request.getParameter("roleid"));
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    user.setRoleId(role);
                    userDAO.register(user);
                    /* Save new user in database */
                    request.setAttribute("messageSuccess", "Register successfully");
                }

                request.getRequestDispatcher("./jsp/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("./jsp/error.jsp").forward(request, response);
        }

    }

}
