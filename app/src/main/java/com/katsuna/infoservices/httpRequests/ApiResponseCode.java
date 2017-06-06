package com.katsuna.infoservices.httpRequests;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public enum ApiResponseCode {
    TOKEN_EXPIRED(0),
    USER_EXISTS(1),
    USER_NOT_EXISTS(2),
    RESOURCE_CREATED(3),
    VALIDATION_ERROR(4),
    DATABASE_ERROR(5),
    RESOURCE_NOT_FOUND(6),
    APPLICATION_ERROR(7);

    private final int id;

    ApiResponseCode(int id) {
        this.id = id;
    }

    public static ApiResponseCode valueOf(int x) {
        switch (x) {
            case 0:
                return TOKEN_EXPIRED;
            case 1:
                return USER_EXISTS;
            case 2:
                return USER_NOT_EXISTS;
            case 3:
                return RESOURCE_CREATED;
            case 4:
                return VALIDATION_ERROR;
            case 5:
                return DATABASE_ERROR;
            case 6:
                return RESOURCE_NOT_FOUND;
            case 7:
                return APPLICATION_ERROR;

            default:
                throw new RuntimeException("Unrecognized Api Response Code.");
        }
    }

    public int getValue() {
        return id;
    }
}
