package com.katsuna.infoservices.httpRequests;

/**
 * Created by christosmitatakis on 3/5/17.
 */

public enum ApiResponseCode {
    INVALID_API_KEY(0),
    INVALID_CREDENTIALS(1),
    USER_EXISTS(2),
    USER_NOT_EXISTS(3),
    RESOURCE_CREATED(4),
    VALIDATION_ERROR(5),
    DATABASE_ERROR(6),
    RESOURCE_NOT_FOUND(7),
    APPLICATION_ERROR(8);

    private final int id;

    ApiResponseCode(int id) {
        this.id = id;
    }

    public static ApiResponseCode valueOf(int x) {
        switch (x) {
            case 0:
                return INVALID_API_KEY;
            case 1:
                return INVALID_CREDENTIALS;
            case 2:
                return USER_EXISTS;
            case 3:
                return USER_NOT_EXISTS;
            case 4:
                return RESOURCE_CREATED;
            case 5:
                return VALIDATION_ERROR;
            case 6:
                return DATABASE_ERROR;
            case 7:
                return RESOURCE_NOT_FOUND;
            case 8:
                return APPLICATION_ERROR;

            default:
                throw new RuntimeException("Unrecognized Api Response Code.");
        }
    }

    public int getValue() {
        return id;
    }
}
