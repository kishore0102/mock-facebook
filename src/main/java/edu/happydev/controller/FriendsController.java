package edu.happydev.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.happydev.model.FriendRequestDto;
import edu.happydev.model.FriendsDto;
import edu.happydev.model.FriendsSearch;
import edu.happydev.service.FriendsService;

@RestController
@RequestMapping("/api/friends/")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @GetMapping(value = "/getFriends")
    public ResponseEntity<List<FriendsDto>> getFriendsByEmailId(HttpServletRequest request) throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        List<FriendsDto> friendsList = friendsService.getAllFriendsDetailsByEmail(email);
        return new ResponseEntity<>(friendsList, HttpStatus.OK);
    }

    @PostMapping(value = "/unfriend")
    @ResponseStatus(HttpStatus.OK)
    public void unfriend(@RequestBody String email, HttpServletRequest request) throws Exception {
        String usersEmail = request.getSession().getAttribute("email").toString();
        friendsService.unfriend(usersEmail, email);
    }

    @PostMapping(value = "/sendFriendRequest")
    @ResponseStatus(HttpStatus.OK)
    public void sendFriendRequest(@RequestBody String email, HttpServletRequest request) throws Exception {
        String usersEmail = request.getSession().getAttribute("email").toString();
        friendsService.sendFriendRequest(usersEmail, email);
    }

    @PostMapping(value = "/requestConfirmation")
    @ResponseStatus(HttpStatus.OK)
    public void acceptRejectFriendRequest(@RequestBody FriendRequestDto data, HttpServletRequest request)
            throws Exception {
        String usersEmail = request.getSession().getAttribute("email").toString();
        friendsService.acceptRejectFriendRequest(data.getStatus(), usersEmail, data.getEmail());
    }

    @PostMapping(value = "/search")
    public ResponseEntity<List<FriendsSearch>> searchFriends(@RequestBody String keyword, HttpServletRequest request)
            throws Exception {
        String email = request.getSession().getAttribute("email").toString();
        List<FriendsSearch> response = friendsService.searchFriends(keyword, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/suggest")
    public List<FriendsSearch> friendSuggestions(HttpServletRequest request) throws Exception {
        String usersEmail = request.getSession().getAttribute("email").toString();
        return friendsService.friendSuggestions(usersEmail);
    }

}
