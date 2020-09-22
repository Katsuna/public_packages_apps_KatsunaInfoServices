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

public class ServerConstants {

    public static final String WebServer = "http://info.katsuna.com:443/KatsunaInfoServer";

  // public static final String WebServer = "http://devel-14.local.cite.gr:8080";

    // Registration Call
    public static final String Register = "/user/register";

    //Renew Token
    public static final String RenewToken = "/secure/user/renew_token";

    //Renew Token
    public static final String SavePointsOfInterest = "/secure/location/save";




}
