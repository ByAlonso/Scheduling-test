package com.project.scheduling.service;

import com.project.scheduling.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private Set<UserDto> users = new HashSet<>();

    public boolean emailExists(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public void addUser(UserDto user) {
        users.add(user);
    }

    public UserDto getUserByMail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<UserDto> getUsersByMail(String[] emails) {
        List<UserDto> participants = new ArrayList<>();
        for (String mail : emails) {
            participants.add(getUserByMail(mail));
        }
        return participants;
    }

    public List<Integer> getUserAvailableTimes(UserDto user) {
        return user.availableTimes();
    }
    public Map<Integer, String> getUserMeetings(UserDto user) {
        return user.getMeetings();
    }
}