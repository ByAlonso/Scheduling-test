package com.project.scheduling.service;

import com.project.scheduling.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MeetingService {
    @Autowired
    private UserService userService;
    public ResponseEntity<String> createMeeting(String[] participantsMail, Integer timeSlot, String meetingName) {
        for (String mail : participantsMail) {
            if (!userService.getUserByMail(mail).checkAvailability(timeSlot)) {
                return new ResponseEntity<>("One or more persons are not available.", HttpStatus.CONFLICT);
            }
        }
        for (String mail : participantsMail) {
            userService.getUserByMail(mail).addMeeting(timeSlot, meetingName);
        }
        return new ResponseEntity<>("Meeting created for all participants", HttpStatus.OK);
    }

    public ResponseEntity<Set<Integer>> getAvailableTimes(String[] participantsMail) {
        List<UserDto> participants = userService.getUsersByMail(participantsMail);
        List<Integer> commonAvailableTimes = new ArrayList<>();
        List<Integer> auxList = new ArrayList<>();
        for (UserDto participant : participants) {
            if(commonAvailableTimes.isEmpty())
                commonAvailableTimes.addAll(userService.getUserAvailableTimes(participant));
            else
                commonAvailableTimes.retainAll(userService.getUserAvailableTimes(participant));
        }
        return new ResponseEntity<>(new HashSet<>(commonAvailableTimes), HttpStatus.OK);
    }
}
