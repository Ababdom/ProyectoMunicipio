package com.arbp.richard.proyectomunicipios.model;

public class DatosRespuesta {
    private boolean success;
    private ResultadoRespuesta result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultadoRespuesta getResult() {
        return result;
    }

    public void setResult(ResultadoRespuesta result) {
        this.result = result;
    }
}