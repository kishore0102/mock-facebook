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
    <!-- <script src="login.js"></script> -->
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
            <a class="nav-link active" href="/MockFb/friendsList"">Friends</a>
          </li>
          <li class="nav-item">
            <a class="nav-link disabled">Search Friends</a
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
      <h4>Search for your friends</h4>
      <div class="input-group mb-3">
        <form id="searchForm">
          <div style="display: flex;">
            <input type="text" class="form-control" placeholder="Enter search keyword" id="searchInput" required/>
            <button class="btn btn-outline-primary" type="submit" id="searchButton">Search</button>
          </div>
        </form>
      </div>
      <br/>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Bio</th>
            <th>Friend status</th>
          </tr>
        </thead>
        <tbody id="searchFriendsTable">
          <td class='text-center' colspan='4'>No search results!</td>
        </tbody>
      </table>
    </div>
    <br/>

    <div class="container mt-3">
      <h4>Suggested Friends</h4>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Name</th>
            <th>Bio</th>
            <th>Friend status</th>
          </tr>
        </thead>
        <tbody id="suggestFriendsTable">
          <td class='text-center' colspan='4'>No suggestions!</td>
        </tbody>
      </table>
    </div>

    <script src="${pageContext.request.contextPath}/resources/friendsSearch.js"></script>
  </body>
</html>
