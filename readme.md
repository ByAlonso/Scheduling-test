# Scheduling

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 8 or higher
- Gradle

### Installing and Running

1. Clone the repository to your local machine.
2. Navigate to the root directory of the project. This is the directory that contains the `build.gradle` file.
3. Run the following command to start the application, access it at http://localhost:8080:

```bash
./gradlew bootRun
```

## Running the Tests

To run the automated tests for this system, navigate to the project's root directory and run the following command:

```bash
./gradlew test
```

# UserController Endpoints

## POST /create_user

This endpoint is used to create a new user.

### Request Parameters

- `name`: The name of the user.
- `email`: The email of the user. (unique)

### Responses

- 200 OK: The user was successfully created. The response body contains a message "User created".
- 409 CONFLICT: The email already exists. The response body contains a message "Email already exists".

## GET /user_meetings

This endpoint is used to get the meetings of a user.

### Request Parameters

- `email`: The email of the user.

### Responses

- 200 OK: The request was successful. The response body contains a map where the key is the meeting id and the value is the meeting name.
# MeetingController Endpoints

## POST /create_meeting

This endpoint is used to create a new meeting.

### Body Parameters

- `participantsMail`: An array of emails of the participants.

### Request Parameters

- `timeSlot`: The time slot for the meeting.
- `meetingName`: The name of the meeting.

### Responses

- 200 OK: The meeting was successfully created. The response body contains a message "Meeting created".
- 409 CONFLICT: The meeting could not be created. The response body contains a message explaining the reason.

## POST /suggest_meeting

This endpoint is used to suggest a meeting time based on the availability of the participants.

### Body Parameters

- `participantsMail`: An array of emails of the participants.

### Responses

- 200 OK: The request was successful. The response body contains a set of integers representing the available time slots.