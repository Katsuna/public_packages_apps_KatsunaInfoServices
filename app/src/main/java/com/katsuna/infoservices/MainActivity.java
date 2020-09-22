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
package com.katsuna.infoservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.katsuna.commons.utils.DeviceUtils;

public class MainActivity extends Activity {

    private boolean setupFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        while (!setupFinished) {
//
//            if (DeviceUtils.isUserSetupComplete(getApplicationContext())) {
//                try {
//                    Thread.sleep(1000 * 60 * 10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                super.onCreate(savedInstanceState);
//
//                setupFinished = true;
//                Intent activityIntent = new Intent(getApplicationContext(),PermissionsActivity.class);
//                getApplicationContext().startActivity(activityIntent);
//                finish();
//
//            } else {
//                try {
//                    Thread.sleep(1000 * 60 * 1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        Intent activityIntent = new Intent(getApplicationContext(), ReportingService.class);
        getApplicationContext().startService(activityIntent);
        finish();
    }


}
