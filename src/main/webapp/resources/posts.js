$(document).ready(function () {
    loadAllPosts();

    $(document.body).on('click', '.likeButton', function (e) {
        $.ajax({
            url: `/MockFb/api/posts/likePost/${e.target.id}`,
            type: "GET",
            success: function (response) {
                if (response === 'liked') {
                    $(`#${e.target.id}`).html("&#128078;&nbsp;Unlike");
                    let likeCount = parseInt($(`#like_${e.target.id}`).html()) + 1;
                    $(`#like_${e.target.id}`).html(likeCount);
                } else {
                    $(`#${e.target.id}`).html("&#128077;&nbsp;Like");
                    let likeCount = parseInt($(`#like_${e.target.id}`).html()) - 1;
                    $(`#like_${e.target.id}`).html(likeCount);
                }
            },
            error: function (err) {
                console.log("error", err);
            },
        });
    });

    $(document.body).on('click', '.likesdisplay', function () {
        console.log("likes display is clicked", $(this).data("id"));
        let likePostId = $(this).data("id");
        $.ajax({
            url: `/MockFb/api/posts/getLikes/${likePostId}`,
            type: "GET",
            success: function (response) {

                $('.likes-modal').html("");
                response.forEach(data => {
                    $(".likes-modal").append(`<p>${data}</p>`)
                });

                if (response.length === 0) {
                    response.forEach(data => {
                        $(".likes-modal").append(`<p>No Likes</p>`)
                    });
                }

                $("#likesModal").modal("show");
                $(`#like_${likePostId}`).html(response.length);

            },
            error: function (err) {
                console.log("error", err);
            },
        });
    });

    $(document.body).on('click', '.commentsdisplay', function () {
        console.log("comments display is clicked", $(this).data("id"));
        let commentPostId = $(this).data("id");
        loadComments(commentPostId);
    });

    $(document.body).on('click', '.add-comment-button', function (e) {
        let comment = $('#add-comment-text').val();
        if (comment.trim() === '') {
            return;
        }

        $.ajax({
            url: `/MockFb/api/posts/addComment/${e.target.id}`,
            type: "POST",
            contentType: "text/html",
            data: comment.trim(),
            success: function (response) {
                loadComments(e.target.id);
            },
            error: function (err) {
                console.log("error", err);
                alert("Unable to add comment");
            },
        });
    });

});

function loadAllPosts() {
    $.ajax({
        url: "/MockFb/api/posts/getPosts",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            console.log(response);
            $('#posts-modal-list').html("");
            response.forEach(element => {
                let createdTime = new Date(element.createdTs);
                let createdTimeFormatted = createdTime.getDate() +
                    "/" + (createdTime.getMonth() + 1) +
                    "/" + createdTime.getFullYear() +
                    " " + String(createdTime.getHours()).padStart(2, '0') +
                    ":" + String(createdTime.getMinutes()).padStart(2, '0') +
                    ":" + String(createdTime.getSeconds()).padStart(2, '0');

                $("#posts-modal-list").append(
                    "<li class='list-group-item'>" +
                    `<div class="row">` +
                    `<div class="col-sm-6"><b>${element.name}</b> added a post.</div>` +
                    `<div class="col-sm-6 text-end"><small><i>${createdTimeFormatted}</i></small></div>` +
                    `</div><br/>` +
                    `<p>${element.contentTxt}</p>` +
                    `<p><button class='btn btn-primary likeButton' id=${element.postId}> ${element.userLiked ? "&#128078;&nbsp;Unlike" : "&#128077;&nbsp;Like"} </button>&emsp;` +
                    `<button class='btn btn-outline-primary likesdisplay' data-id=${element.postId}><span id='like_${element.postId}' >${element.likesCount}</span>&nbsp;likes</button>&emsp;` +
                    `<button class='btn btn-outline-primary commentsdisplay' data-id=${element.postId}><span id='cmt_${element.postId}' >${element.commentsCount}</span>&nbsp;comments</button></p>` +
                    "</li>");
            });

            if (response.length === 0) {
                $("#posts-modal-list").append(
                    "<li class='list-group-item'>" +
                    `<p>No posts available</p>` +
                    "</li>");
            }

        },
        error: function (err) {
            console.log("error", err);
        },
    });
}

$("#add-post-form").submit(function (e) {
    if ($("#post-content-textarea").val().trim() === '') {
        return;
    }
    e.preventDefault();

    var request = {
        contentTxt: $("#post-content-textarea").val(),
    };

    $.ajax({
        url: "/MockFb/api/posts/addPost",
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(request),
        success: function (response) {
            console.log("success", response);
            alert("post added");
            loadAllPosts();
        },
        error: function (err) {
            console.log("error", err);
        },
    });

    $("#post-content-textarea").val('');
});

function loadComments(commentPostId) {
    $.ajax({
        url: `/MockFb/api/posts/getComments/${commentPostId}`,
        type: "GET",
        success: function (response) {
            console.log("get comments", response);

            $("#comments-modal-list").html("");
            $("#commentsModal").modal("show");

            response.forEach(data => {
                let commentTs = new Date(data.createdTs);
                let commentTsFormatted = commentTs.getDate() +
                    "/" + (commentTs.getMonth() + 1) +
                    "/" + commentTs.getFullYear() +
                    " " + String(commentTs.getHours()).padStart(2, '0') +
                    ":" + String(commentTs.getMinutes()).padStart(2, '0') +
                    ":" + String(commentTs.getSeconds()).padStart(2, '0');

                $("#comments-modal-list").append(
                    `<li class="list-group-item">` +
                    `<div class="d-flex w-100 justify-content-between">` +
                    `<h5 class="mb-1">${data.name}</h5>` +
                    `<small>${commentTsFormatted}</small>` +
                    `</div>` +
                    `<p class="mb-1">${data.comment}</p>` +
                    `</li>`
                );
            });

            $(".add-comment-footer").html("");
            $(".add-comment-footer").append(
                `<div class="input-group mb-3">` +
                `<input type="text" class="form-control" placeholder="Enter your comment"` +
                `aria-label="Enter your comment" aria-describedby="add-comment-button"` +
                `id="add-comment-text" />` +
                `<button class="btn btn-outline-primary add-comment-button"` +
                `type="button" id="${commentPostId}" >` +
                `Add Comment` +
                `</button>` +
                `</div>`
            );

            $(`#cmt_${commentPostId}`).html(response.length);

        },
        error: function (err) {
            console.log("error", err);
        },
    });
}