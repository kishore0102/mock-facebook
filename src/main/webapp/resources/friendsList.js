$(document).ready(function () {
    loadFriends();

    $(document.body).on('click', '.friendReqBtn', function (e) {
        let id = e.target.id;
        let status = id[0] === 'a' ? 'accept' : "reject";
        let email = id.slice(2);

        $.ajax({
            url: `/MockFb/api/friends/requestConfirmation`,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ email, status }),
            success: function (response) {
                loadFriends();
            },
            error: function (err) {
                console.log("error", err);
                alert("Unexpected error while processing your request");
                loadFriends();
            },
        });
    });

    $(document.body).on('click', '.unfriendBtn', function (e) {
        let id = e.target.id;
        let email = id.slice(2);

        $.ajax({
            url: `/MockFb/api/friends/unfriend`,
            type: "POST",
            contentType: "text/html",
            data: email,
            success: function () {
                alert("Friend has been removed");
                loadFriends();
            },
            error: function (err) {
                console.log("error", err);
                alert("Unexpected error while processing your request");
                loadFriends();
            },
        });
    });

});


function loadFriends() {
    $.ajax({
        url: "/MockFb/api/friends/getFriends",
        type: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            $('#pendingFriendsTable').empty();
            $('#friendsTable').empty();
            let pendingFriends = 0;
            let friends = 0;

            response.forEach(element => {

                if (element.status === 'Pending') {
                    pendingFriends++;
                    $("#pendingFriendsTable:last-child").append(
                        "<tr>" +
                        `<td>${element.name}</td>` +
                        `<td>${element.email}</td>` +
                        `<td>${element.bio}</td>` +
                        `<td>` +
                        `<button class='btn btn-success friendReqBtn' id='a_${element.email}'>Accept</button>` +
                        `&emsp;` +
                        `<button class='btn btn-danger friendReqBtn' id='r_${element.email}'>Reject</button>` +
                        `</td>` +
                        "</tr>");

                } else if (element.status === 'Request sent') {
                    pendingFriends++;
                    $("#pendingFriendsTable:last-child").append(
                        "<tr>" +
                        `<td>${element.name}</td>` +
                        `<td>${element.email}</td>` +
                        `<td>${element.bio}</td>` +
                        `<td>&#8987;&nbsp;Request sent</td>` +
                        "</tr>");

                } else if (element.status === 'Friends') {
                    friends++;
                    $("#friendsTable:last-child").append(
                        "<tr>" +
                        `<td>${element.name}</td>` +
                        `<td>${element.email}</td>` +
                        `<td>${element.bio}</td>` +
                        `<td>` +
                        `<button class='btn btn-warning unfriendBtn' id='u_${element.email}'>Unfriend</button>` +
                        `</td>` +
                        "</tr>");

                }

            });

            if (pendingFriends === 0) {
                $("#pendingFriendsTable:last-child").append(
                    "<tr><td class='text-center' colspan='4'>No Pending friend requests!</td></tr>");
            }

            if (friends === 0) {
                $("#friendsTable:last-child").append(
                    "<tr><td class='text-center' colspan='4'>You are yet to make friends!</td></tr>");
            }

        },
        error: function (err) {
            console.log("error", err);
        },
    });
}
