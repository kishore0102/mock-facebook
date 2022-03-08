<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
isELIgnored="false"%> <%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
  <head>
    <title>Friends</title>
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
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" href="/MockFb/posts">Posts</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Friends</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="/MockFb/friendsSearch"
              >Search Friends</a
            >
          </li>
          <li class="nav-item">
            <span class="nav-link disabled active"
              ><b><u>${name}</u></b></span
            >
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="/MockFb/logout">(logout)</a>
          </li>
        </ul>
      </div>
    </nav>
    <br />

    <div class="container mt-3">
      <h4>Pending friends requests</h4>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Bio</th>
            <th>Approve / Reject</th>
          </tr>
        </thead>
        <tbody id="pendingFriendsTable"></tbody>
      </table>
    </div>
    <br />

    <div class="container mt-3">
      <h4>Your Friends</h4>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Bio</th>
            <th>Unfriend</th>
          </tr>
        </thead>
        <tbody id="friendsTable"></tbody>
      </table>
    </div>

    <script src="${pageContext.request.contextPath}/resources/friendsList.js"></script>
  </body>
</html>
