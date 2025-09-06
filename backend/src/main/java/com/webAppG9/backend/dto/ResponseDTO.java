package com.webAppG9.backend.dto;

public class ResponseDTO<T> { // T respuesta generica
    private T data; // el objeto de éxito (UserDTO, tokens)
    private String error; // mensaje de error si hubo problema

    // constructor para instanciaar la clase
    public ResponseDTO(T data, String error) {
        this.data = data;
        this.error = error;
    }

    // Getters y setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
