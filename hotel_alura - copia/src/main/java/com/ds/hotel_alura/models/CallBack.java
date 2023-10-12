package com.ds.hotel_alura.models;

public interface CallBack <T>{
    /*
     * El parametro query se usa para identificar que consulta es la que se esta
     * haciendo, 0 es el insert, 1 es el update.
    */
    public void listener(T DTO, byte query);
}
