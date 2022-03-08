$(document).ready(function () {
    loadFriendSuggestions();

    $("#searchForm").submit(function (e) {
        e.preventDefault();
        let keyword = $('#searchInput').val();

        $.ajax({
            url: `/MockFb/api/friends/search`,
            type: "POST",
            contentType: "text/html",
            data: keyword,
            success: function (response) {
                console.log("search response", response);

                $('#searchFriendsTable').empty();

                response.forEach(element => {

                    let statusIcon;
                    if (element.status === 'Friends')
                        statusIcon = '&#9989;';
                    else
                        statusIcon = '&#8987;';

                    if (element.status === 'Add Friend') {
                        $("#searchFriendsTable:last-child").append(
                            "<tr>" +
                            `<td>${element.name}</td>` +
                            `<td>${element.bio}</td>` +
                            `<td>` +
                            `<button class='btn btn-warning addFriendReq' id='add_${element.email}'>Add Friend</button>` +
                            `</td>` +
                            "</tr>");

                    } else {
                        $("#searchFriendsTable:last-child").append(
                            "<tr>" +
                            `<td>${element.name}</td>` +
                            `<td>${element.bio}</td>` +
                            `<td>${statusIcon}&nbsp;${element.status}</td>` +
                            "</tr>");

                    }

                });

                if (response.length === 0) {
                    $("#searchFriendsTable:last-child").append(
                        "<tr><td class='text-center' colspan='3'>No search results found!</td></tr>");
                }

            },
            error: function (err) {
                console.log("error", err);
                alert("Unexpected error while processing your request");
            },
        });

    });

    $(document.body).on('click', '.addFriendReq', function (e) {
        console.log("add friend clicked", e.target.id);
        let id = e.target.id;
        let email = id.slice(4);
        console.log("sending friend request", e.target, email);

        $.ajax({
            url: `/MockFb/api/friends/sendFriendRequest`,
            type: "POST",
            contentType: "text/html",
            data: email,
            success: function (response) {
                alert("Friend request has been sent!");
                $("#searchForm").submit();
            },
            error: function (err) {
                console.log("error", err);
                alert("Unexpected error while processing your request - " + err);
            },
        });

    });

    $(document.body).on('click', '.addFriendReqSuggest', function (e) {
        let id = e.target.id;
        let email = id.slice(4);
        console.log("sending friend request", e.target, email);

        $.ajax({
            url: `/MockFb/api/friends/sendFriendRequest`,
            type: "POST",
            contentType: "text/html",
            data: email,
            success: function (response) {
                alert("Friend request has been sent!");
                loadFriendSuggestions();
            },
            error: function (err) {
                console.log("error", err);
                alert("Unexpected error while processing your request - " + err);
            },
        });

    });

});

function loadFriendSuggestions() {
    $.ajax({
        url: `/MockFb/api/friends/suggest`,
        type: "GET",
        success: function (response) {
            console.log("suggest response", response);

            $('#suggestFriendsTable').empty();

            response.forEach(element => {

                let statusIcon;
                if (element.status === 'Friends')
                    statusIcon = '&#9989;';
                else
                    statusIcon = '&#8987;';

                if (element.status === 'Add Friend') {
                    $("#suggestFriendsTable:last-child").append(
                        "<tr>" +
                        `<td>${element.name}</td>` +
                        `<td>${element.bio}</td>` +
                        `<td>` +
                        `<button class='btn btn-warning addFriendReqSuggest' id='add_${element.email}'>Add Friend</button>` +
                        `</td>` +
                        "</tr>");

                } else {
                    $("#suggestFriendsTable:last-child").append(
                        "<tr>" +
                        `<td>${element.name}</td>` +
                        `<td>${element.bio}</td>` +
                        `<td>${statusIcon}&nbsp;${element.status}</td>` +
                        "</tr>");

                }

            });

            if (response.length === 0) {
                $("#suggestFriendsTable:last-child").append(
                    "<tr><td class='text-center' colspan='3'>No suggestions found!</td></tr>");
            }

        },
        error: function (err) {
            console.log("error", err);
            alert("Unexpected error while processing your request");
        },
    });
}
