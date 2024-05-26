package com.jardinazuayo.APIJardinAzuayo.Model;

public class SuccessResponse {
    private String mensaje;
    private int codigo;
    private Object datos;

    public SuccessResponse(String mensaje, int codigo, Object datos) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.datos = datos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}
