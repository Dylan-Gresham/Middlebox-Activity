package org.beacon;

import java.util.ArrayList;

public class User {
    public String firstName, lastName, school, issues, improvements;
    public boolean isAnonymous;
    public ArrayList<Integer> responses;

    public User() {
        this.firstName = this.lastName = this.school = this.issues = this.improvements = "";
        this.isAnonymous = true;
        this.responses = new ArrayList<>(4);

        for (int i = 0; i < 4; i++) {
            this.responses.add(-1);
        }
    }

    private static String mapResponse(int response) {
        return switch (response) {
            case 1 -> "Strongly Disagree";
            case 2 -> "Disagree";
            case 3 -> "Neutral";
            case 4 -> "Agree";
            case 5 -> "Strongly Agree";
            default -> "Invalid response";
        };
    }

    @Override
    public String toString() {
        int currResponse = 1;
        StringBuilder respsStr = new StringBuilder();
        for (int resp : this.responses) {
            respsStr.append("\nQuestion ").append(currResponse++).append(": ").append(mapResponse(resp));
        }

        return "User: " + (this.isAnonymous ? "Anonymous" : this.firstName + " " + this.lastName) + "\n" +
                "School: " + this.school + "\n" +
                "Issues: " + this.issues + "\n" +
                "Improvements:\n\n" + this.improvements + "\n" +
                "Responses:\n" + respsStr;
    }
}
