package com.example.battleship.model;

public interface ISerializableFileHandler {
    void serialize(String fileName, Object element);
    Object deserialize(String fileName);
}
