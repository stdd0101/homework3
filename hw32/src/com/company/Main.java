package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static void saveGame(String path, GameProgress gamer) throws IOException, ClassNotFoundException {
        File file = new File("C:\\Users\\stdd0", "savegames");
        if (file.mkdir()) {
            System.out.println("savegames created");
        }
        String filename = gamer.getName() + ".txt";
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\stdd0\\savegames\\" + filename);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(gamer);
        objectOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\stdd0\\savegames\\" + filename);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        GameProgress gamerProgress = (GameProgress) objectInputStream.readObject();

        System.out.println(gamerProgress);
    }

    static void zipFiles(GameProgress gamer) throws IOException {
        String filenameTxt = gamer.getName() + ".txt";
        String filename = "C:\\Users\\stdd0\\savegames\\" + filenameTxt;
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("C:\\Users\\stdd0\\savegames\\saves.zip"));
            FileInputStream fis = new FileInputStream(filename);)
        {
            ZipEntry entry = new ZipEntry(filenameTxt);
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GameProgress gamer1 = new GameProgress("Gamer1", 123, 23, 4, 23.5);
        GameProgress gamer2 = new GameProgress("Gamer2",124, 24, 5, 23.6);
        GameProgress gamer3 = new GameProgress("Gamer3",125, 25, 6, 23.4);

        String path = "C:\\Users\\stdd0";

        saveGame(path, gamer1);
        saveGame(path, gamer2);
        saveGame(path, gamer3);
        zipFiles(gamer1);
        zipFiles(gamer2);
        zipFiles(gamer3);

    }
}

