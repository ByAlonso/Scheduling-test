package com.project.scheduling.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDto {
    String name;
    String email;
    Map<Integer, String> schedule = new HashMap<>();

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;

        for (int i = 0; i < 24; i++) {
            schedule.put(i, null);
        }
    }

    public boolean checkAvailability(int timeSlot) {
        return schedule.get(timeSlot) == null;
    }

    public void addMeeting(Integer timeSlot, String meetingName) {
        schedule.put(timeSlot, meetingName);
    }

    public Map<Integer, String> getMeetings() {
        return schedule.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> availableTimes() {
        List<Integer> availableTimes = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            if (checkAvailability(i)) {
                availableTimes.add(i);
            }
        }
        return availableTimes;
    }

    public String getEmail() {
        return email;
    }
}
