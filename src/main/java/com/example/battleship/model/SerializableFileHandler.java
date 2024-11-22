package com.example.battleship.model;

import java.io.*;

/**
 * Clase que implementa la interfaz {@link ISerializableFileHandler} para manejar la serialización
 * y deserialización de objetos en el juego Battleship.
 *
 * <p>Los archivos se guardan en un directorio predefinido llamado {@code savedgames}.
 * Si el directorio no existe, se crea automáticamente.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public class SerializableFileHandler implements ISerializableFileHandler {

    /**
     * Nombre del directorio donde se almacenan los archivos serializados.
     */
    private static final String DIRECTORY = "savedgames";

    /**
     * Constructor de la clase {@code SerializableFileHandler}.
     *
     * <p>Comprueba si el directorio {@code savedgames} existe, y lo crea si no está presente.</p>
     */
    public SerializableFileHandler() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Serializa un objeto y lo guarda en un archivo en el directorio {@code savedgames}.
     *
     * @param fileName el nombre del archivo donde se almacenará el objeto
     * @param element el objeto que se desea serializar
     */
    @Override
    public void serialize(String fileName, Object element) {
        File file = new File(DIRECTORY, fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializa un objeto desde un archivo en el directorio {@code savedgames}.
     *
     * @param fileName el nombre del archivo desde el cual se cargará el objeto
     * @return el objeto deserializado, o {@code null} si ocurre un error durante la operación
     */
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
