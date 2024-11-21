package com.example.battleship.model;

import java.io.*;

public class SerializableFileHandler implements ISerializableFileHandler {

    private static final String DIRECTORY = "savedgames";


    public SerializableFileHandler() {
        // Crear el directorio si no existe
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void serialize(String fileName, Object element) {
        File file = new File(DIRECTORY, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object deserialize(String fileName) {
        File file = new File(DIRECTORY, fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
