package com.lmhy.bcos.ext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SolCompile {
    private boolean exec(String bash) {
        boolean rs = false;
        BufferedReader input = null;
        List<String> processList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(bash);
            rs = process.waitFor() == 0;
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (String s : processList) {
            System.out.println(s);
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
