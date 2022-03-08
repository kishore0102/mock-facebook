<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
isELIgnored="false" %> <%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
  <head>
    <title>Posts</title>
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
            <a class="nav-link disabled">Posts</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active" href="/MockFb/friendsList">Friends</a>
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
      <div>
        <form class="input-group" id="add-post-form">
          <textarea
            class="form-control custom-control"
            rows="2"
            style="resize: none"
            id="post-content-textarea"
            form="add-post-form"
            maxlength="200"
            placeholder="What's on your mind?"
          ></textarea>
          <input
            class="input-group-addon btn btn-primary"
            type="submit"
            value="Publish my post!"
          />
        </form>
      </div>
      <br />

      <div>
        <button class="btn btn-outline-primary" onclick="loadAllPosts()">
          Refresh posts
        </button>
      </div>
      <br />

      <!-- <table id="all-posts-table">
          <tbody></tbody>
        </table> -->
      <div class="modal-body">
        <ul class="list-group" id="posts-modal-list"></ul>
      </div>
    </div>

    <div class="container mt-3">
      <div class="modal" id="likesModal">
        <div class="modal-dialog modal-dialog modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Likes</h4>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
              ></button>
            </div>
            <div class="modal-body likes-modal"></div>
          </div>
        </div>
      </div>
    </div>

    <div class="container mt-3">
      <div class="modal" id="commentsModal">
        <div class="modal-dialog modal-dialog modal-lg modal-dialog-scrollable">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title">Comments</h4>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
              ></button>
            </div>
            <div class="modal-body">
              <ul class="list-group" id="comments-modal-list"></ul>
            </div>
            <div class="modal-footer add-comment-footer"></div>
          </div>
        </div>
      </div>
    </div>

    <script src="${pageContext.request.contextPath}/resources/posts.js"></script>
  </body>
</html>
