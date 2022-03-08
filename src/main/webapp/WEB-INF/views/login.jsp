<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
isELIgnored="false"%> <%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
  <head>
    <title>MockFb</title>
    <script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </head>
  <body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
      <div class="container-fluid">
        <span class="navbar-text">HappyDev Facebook</span>
      </div>
    </nav>
    <br />

    <div class="container">
      <div class="row">
        <div class="col-sm-6 col1" style="padding: 30px">
          <div class="container mt-3" style="border: 1px black">
            <h6>Login</h6>
            <form action="/MockFb/login" method="post">
              <div class="mb-3 mt-3">
                <label for="email">Email:</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  placeholder="Enter email"
                  name="email"
                />
              </div>
              <div class="mb-3">
                <label for="pwd">Password:</label>
                <input
                  type="password"
                  class="form-control"
                  id="pwd"
                  placeholder="Enter password"
                  name="pwd"
                />
              </div>
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <p style="color: red">${error}</p>
          </div>
        </div>

        <div class="col-sm-6 col2" style="padding: 30px">
          <div class="container mt-3" style="border: 1px black">
            <h6>Sign up</h6>
            <form action="/MockFb/signup" method="post">
              <div class="mb-3 mt-3">
                <label for="email">Email:</label>
                <input
                  type="email"
                  class="form-control"
                  id="semail"
                  placeholder="Enter your email"
                  name="semail"
                  required
                />
              </div>
              <div class="mb-3">
                <label for="pwd">Password:</label>
                <input
                  type="password"
                  class="form-control"
                  id="spwd"
                  placeholder="Enter new password"
                  name="spwd"
                  required
                />
              </div>
              <div class="mb-3 mt-3">
                <label for="email">First name:</label>
                <input
                  type="text"
                  class="form-control"
                  id="sfirstName"
                  placeholder="Enter your first name"
                  name="sfirstName"
                  required
                />
              </div>
              <div class="mb-3 mt-3">
                <label for="email">Last name:</label>
                <input
                  type="text"
                  class="form-control"
                  id="slastName"
                  placeholder="Enter your last name"
                  name="slastName"
                  required
                />
              </div>
              <div class="mb-3 mt-3">
                <label for="email">Bio:</label>
                <input
                  type="text"
                  class="form-control"
                  id="sbio"
                  placeholder="One line about you!"
                  name="sbio"
                  required
                />
              </div>
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <p style="color: red">${signupMessage}</p>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
