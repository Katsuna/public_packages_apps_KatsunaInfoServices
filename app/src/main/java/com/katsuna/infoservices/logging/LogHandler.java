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
package com.katsuna.infoservices.logging;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class LogHandler implements Runnable {


    public static final int LINES = 100;
    public static final int DELAY = 700;

    private ArrayList<String> bufferedlines = new ArrayList<String>();
    private String lastline = "null";
    private Thread t;
    private boolean exit = false;

    public LogHandler() {
        t = new Thread(this);
        t.start();
    }

    public void stop() {
        exit = true;
    }

    public abstract void onLineAdd(String line);

    public void run() {
        while (!exit) {
            checkLogEvents();
            try {
                t.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkLogEvents() {
        try {
            ArrayList<String> chkl = new ArrayList<String>();
            //Executes the command.
            //String[] cmd = {"logcat", "-d", "-t",String.valueOf(LINES)};
            String cmd ="logcat ActivityManager:I *:S";
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process
                            .getInputStream()), LINES * 2000);

            String output = "";
            while ((output = reader.readLine()) != null) {
                chkl.add(output);
            }
            reader.close();

            //Waits for the command to finish.
            process.waitFor();

            int id = getLastSend(chkl);
            if (id == -1) {
                //resend complete
                bufferedlines = chkl;
                this.lastline = bufferedlines.get(bufferedlines.size() - 1);
                for (String line : bufferedlines) {
                    onLineAdd(line);
                }
            } else {
                //compute resend part
                ArrayList<String> nl = new ArrayList<String>();
                for (int i = id; i < chkl.size(); i++) {
                    nl.add(chkl.get(i));
                }
                bufferedlines = nl;
                this.lastline = bufferedlines.get(bufferedlines.size() - 1);
                for (String line : bufferedlines) {
                    System.out.println(">>>>>>>>>>>>>>>>>>>"+line);
                   // onLineAdd(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets index of last send line, -1 if not found
     *
     * @return index of lastline
     */
    private int getLastSend(ArrayList<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).equals(lastline)) {
                return i;
            }
        }
        return -1;
    }
}