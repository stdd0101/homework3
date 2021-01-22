package com.company;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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

public static class ZipUtils {

    private List<String> fileList;
    private static final String OUTPUT_ZIP_FILE = "C:\\Users\\stdd0\\savegames\\saves.zip";
    private static final String SOURCE_FOLDER = "C:\\Users\\stdd0\\savegames";
    public ZipUtils() {
        fileList = new ArrayList< String >();
    }

    public void zipIt(String zipFile) {
        byte[] buffer = new byte[1024];
        String source = new File(SOURCE_FOLDER).getName();
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);
            FileInputStream in = null;

            for (String file: this.fileList) {
                System.out.println("File Added into saves.zip : " + file);
                ZipEntry ze = new ZipEntry(source + File.separator + file);
                zos.putNextEntry(ze);
                try {
                    in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
                    int len;
                    while ((len = in .read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } finally {
                    in.close();
                }
            }

            zos.closeEntry();
            System.out.println("Saves.zip compressed");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void generateFileList(File node) {
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename: subNote) {
                generateFileList(new File(node, filename));
            }
        }
    }

    private String generateZipEntry(String file) {
        return file.substring(SOURCE_FOLDER.length() + 1, file.length());
    }
}
    public static void main(String[] args) throws Exception {

        GameProgress gamer1 = new GameProgress("Gamer1", 123, 23, 4, 23.5);
        GameProgress gamer2 = new GameProgress("Gamer2",124, 24, 5, 23.6);
        GameProgress gamer3 = new GameProgress("Gamer3",125, 25, 6, 23.4);

        String path = "C:\\Users\\stdd0";

        saveGame(path, gamer1);
        saveGame(path, gamer2);
        saveGame(path, gamer3);
        ZipUtils appZip = new ZipUtils();
        appZip.generateFileList(new File(ZipUtils.SOURCE_FOLDER));
        appZip.zipIt(ZipUtils.OUTPUT_ZIP_FILE);
    }
}

