package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String result = "\n";
        StringBuilder sb = new StringBuilder(result);
        File dir = new File("C:/Games1", "src");
        if(dir.mkdir()) {
            sb.append("\nsrc created");
        }
        File dir1 = new File("C:/Games1", "res");
        if(dir1.mkdir()) {
            sb.append("\nres created");
        }
        File dir2 = new File("C:/Games1", "savegames");
        if(dir2.mkdir()) {
            sb.append("\nsavegames created");
        }
        File dir3 = new File("C:/Games1", "temp");
        if(dir3.mkdir()) {
            sb.append("\ntemp created");
        }

        File dir11 = new File("C:/Games1/src", "main");
        if(dir11.mkdir()) {
            sb.append("\nmain created");
        }
        File dir12 = new File("C:/Games1/src", "test");
        if(dir12.mkdir()) {
            sb.append("\ntest created");
        }

        File newFile = new File("C:/Games1/src/main", "Main.java");
        try {
            if(newFile.createNewFile()) {
                sb.append("\nMain.java created");
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        File newFile1 = new File("C:/Games1/src/main", "Utils.java");
        try {
            if(newFile1.createNewFile()) {
                sb.append("\nUtils.java created");
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        File dir21 = new File("C:/Games1/res", "drawables");
        if(dir21.mkdir()) {
            sb.append("\ndrawables created");
        }
        File dir22 = new File("C:/Games1/res", "vectors");
        if(dir22.mkdir()) {
            sb.append("\nvectors created");
        }
        File dir23 = new File("C:/Games1/res", "icons");
        if(dir23.mkdir()) {
            sb.append("\nicons created");
        }

        File file = new File("C:/Games1/temp", "temp.txt");
        try {
            if(file.createNewFile()) {
                sb.append("\ntemp.txt created");
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        FileWriter writer = new FileWriter(file);
        writer.write(sb.toString());
        writer.close();
        System.out.println(sb.toString());
    }
}
