/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
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
