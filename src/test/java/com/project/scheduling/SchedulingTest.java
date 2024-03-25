package com.project.scheduling;

import com.project.scheduling.controller.MeetingController;
import com.project.scheduling.controller.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class SchedulingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private MeetingController meetingController;

    @BeforeEach
    public void setup() {
        userController.createUser("user1", "user1@gmail");
        userController.createUser("user4", "user4@gmail");
        userController.createUser("user5", "user5@gmail");

        meetingController.createMeeting(new String[]{"user1@gmail"}, 1, "meeting1");
        meetingController.createMeeting(new String[]{"user1@gmail", "user4@gmail"}, 2, "meeting2");
        meetingController.createMeeting(new String[]{"user1@gmail"}, 3, "meeting3");
        meetingController.createMeeting(new String[]{"user4@gmail", "user5@gmail"}, 4, "meeting4");

    }

    @Test
    public void testCreateUser() {
        ResponseEntity<String> responseEntity = userController.createUser("user2", "user2@gmail");
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testUniqueEmails() {
        userController.createUser("user2", "user2@gmail");
        ResponseEntity<String> responseEntity = userController.createUser("user3", "user2@gmail");
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void testGetUserMeetings() {
        ResponseEntity<Map<Integer, String>> responseEntity = userController.getUserMeetings("user1@gmail");

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "meeting1");
        map.put(2, "meeting2");
        map.put(3, "meeting3");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(map, responseEntity.getBody());

    }

    @Test
    public void testMeetingNotAvailable(){
        ResponseEntity<String> responseEntity = meetingController.createMeeting(new String[]{"user1@gmail"}, 1, "meeting1");
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void testSuggestMeetingTime(){
        ResponseEntity<Set<Integer>> responseEntity = meetingController.suggestMeetingTime(new String[]{"user1@gmail", "user4@gmail", "user5@gmail"});
        Set<Integer> result = new HashSet<>(Arrays.asList(0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(result, responseEntity.getBody());
    }


}