package edu.happydev.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.happydev.service.UsersService;

@Controller
public class ViewController {

    private static final Logger LOGGER = Logger.getLogger(ViewController.class.getName());

    @Autowired
    UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        if (request.getSession().getAttribute("email") != null) {
            LOGGER.info("redirecting to posts page during login");
            response.sendRedirect("/MockFb/posts");
        }
        ModelAndView loginView = new ModelAndView("login");
        return loginView;
    }

    @RequestMapping(method = RequestMethod.GET, value = { "/login", "/signup" })
    public void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        response.sendRedirect("/MockFb/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String valid = usersService.validateEmailAndPwd(email, pwd);

        if (request.getSession().getAttribute("email") != null) {
            request.getSession().removeAttribute("email");
        }

        if (valid != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("email", email);
            session.setAttribute("name", valid);
            LOGGER.info("redirecting to posts page");
            response.sendRedirect("/MockFb/posts");
            return null;
        }

        LOGGER.info("redirecting to login page because of incorrect credentials");
        ModelAndView loginView = new ModelAndView("login");
        loginView.addObject("error", "Invalid Credentials!");
        return loginView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    public ModelAndView postsPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        ModelAndView postsView = new ModelAndView("posts");
        return postsView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friendsList")
    public ModelAndView friendsListPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        ModelAndView postsView = new ModelAndView("friendsList");
        return postsView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/friendsSearch")
    public ModelAndView friendsSearchPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        ModelAndView postsView = new ModelAndView("friendsSearch");
        return postsView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        request.getSession().removeAttribute("email");
        request.getSession().removeAttribute("name");
        response.sendRedirect("/MockFb/");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ModelAndView validateSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        setCacheConfigResponse(response);
        String email = request.getParameter("semail");
        String pwd = request.getParameter("spwd");
        String firstName = request.getParameter("sfirstName");
        String lastName = request.getParameter("slastName");
        String bio = request.getParameter("sbio");

        String apiResponse = usersService.createNewUser(email, pwd, firstName, lastName, bio);

        if (request.getSession().getAttribute("email") != null) {
            request.getSession().removeAttribute("email");
        }

        ModelAndView loginView = new ModelAndView("login");
        loginView.addObject("signupMessage", apiResponse);
        return loginView;
    }

    public void setCacheConfigResponse(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    }

}
