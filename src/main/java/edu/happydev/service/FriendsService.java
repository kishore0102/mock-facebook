package edu.happydev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.happydev.entity.Friends;
import edu.happydev.entity.Friends.FriendsId;
import edu.happydev.model.FriendsDto;
import edu.happydev.model.FriendsSearch;
import edu.happydev.repository.FriendsRepository;

@Service
public class FriendsService {

    @Autowired
    FriendsRepository friendsRepository;

    public List<String> getAllFriendsByEmail(String email) {
        return friendsRepository.getAllFriendsByEmail(email);
    }

    public List<FriendsDto> getAllFriendsDetailsByEmail(String email) {
        return friendsRepository.getAllFriendsDetailsByEmail(email);
    }

    public void unfriend(String usersEmail, String friendEmail) {
        friendsRepository.unfriend(usersEmail, friendEmail);
    }

    public void sendFriendRequest(String usersEmail, String friendEmail) throws Exception {
        Integer friendPair = friendsRepository.checkFriendsPairExists(usersEmail, friendEmail);
        if (friendPair > 0) {
            throw new Exception("You're either already friends or request is in pending.");
        }
        Friends friend = new Friends(new FriendsId(usersEmail, friendEmail), "N");
        friendsRepository.save(friend);
    }

    public void acceptRejectFriendRequest(String status, String usersEmail, String friendEmail) throws Exception {
        Optional<Friends> friendOpt = friendsRepository.findById(new FriendsId(friendEmail, usersEmail));
        if (friendOpt.isPresent()) {
            Friends friend = friendOpt.get();
            if (status.equals("accept")) {
                friend.setStatus("Y");
                friendsRepository.save(friend);
            } else {
                friendsRepository.delete(friend);
            }

        } else {
            throw new Exception("No friends requests available");
        }
    }

    public List<FriendsSearch> searchFriends(String keyword, String email) {
        List<FriendsSearch> response = friendsRepository.searchFriends(keyword, email);
        return response;
    }

    public List<FriendsSearch> friendSuggestions(String email) {
        List<Tuple> data = friendsRepository.suggestFriends(email);
        return data.stream().map(d -> new FriendsSearch(
                d.get(0, String.class),
                d.get(1, String.class),
                d.get(2, String.class),
                d.get(3, String.class),
                d.get(4, String.class))).collect(Collectors.toList());
    }

}
