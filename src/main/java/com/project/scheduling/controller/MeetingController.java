package com.project.scheduling.controller;

import com.project.scheduling.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @PostMapping("/create_meeting")
    public ResponseEntity<String> createMeeting(@RequestBody String[] participantsMail, @RequestParam Integer timeSlot, @RequestParam String meetingName) {
        return meetingService.createMeeting(participantsMail, timeSlot, meetingName);
    }
    @PostMapping("/suggest_meeting")
    public ResponseEntity<Set<Integer>> suggestMeetingTime(@RequestBody String[] participantsMail) {
        return meetingService.getAvailableTimes(participantsMail);
    }
}

