package com.santotomas.myaplicacion2;

public class Usuario {
    int Id;
    String Nombre, Apellido, Correo, Contrasenia,Numero;


    public Usuario() {
    }

    public Usuario(String numero, String nombre, String apellido, String correo, String contrasenia) {
        Numero = numero;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Contrasenia = contrasenia;
    }

    public String toString(){
        return "Usuario{" +
                "Id=" +Id +
                ",Correo='" + Correo + '\''+
                ",Nombre='" + Nombre + '\''+
                ",Apellido='" + Apellido + '\''+
                ",Numero='" + Numero + '\''+
                ",Contrasenia='" + Contrasenia + '\''+
                '}';

    }
    public boolean isNull(){

        if(Nombre.equals("")&&Apellido.equals("")&&Correo.equals("")&&Contrasenia.equals("")&&Numero.equals("")){
            return false;
        }else{return true;
        }
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
