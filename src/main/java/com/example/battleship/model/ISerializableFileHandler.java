package com.example.battleship.model;

/**
 * Interfaz que define métodos para la serialización y deserialización de objetos.
 *
 * <p>Proporciona una abstracción para manejar archivos serializables en el juego
 * Battleship, permitiendo guardar y cargar objetos desde un archivo.</p>
 *
 * @author Juan Pablo Charry Ramirez
 * @author Juan Esteban Rodriguez Valencia
 * @version 1.0
 */
public interface ISerializableFileHandler {

    /**
     * Serializa un objeto y lo guarda en un archivo.
     *
     * @param fileName el nombre del archivo donde se almacenará el objeto serializado
     * @param element el objeto que se desea serializar
     */
    void serialize(String fileName, Object element);

    /**
     * Deserializa un objeto desde un archivo.
     *
     * @param fileName el nombre del archivo desde el cual se cargará el objeto serializado
     * @return el objeto deserializado
     */
    Object deserialize(String fileName);
}
