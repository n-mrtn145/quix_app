package com.quix.quix.match;

public enum MatchStatus {
    A("Active"),
    F("Finished"),
    I("Interuppted");

    private String value;
    MatchStatus(String value) {
        this.value = value;
    }
    public static boolean isActive(MatchStatus status) {
        return status == A;
    }
}
