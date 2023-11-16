package com.santotomas.myaplicacion2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
public class DaoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "BDUsuario";
    String tabla = "CREATE TABLE IF NOT EXISTS usuario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "correo TEXT UNIQUE, " +
            "nombre TEXT, " +
            "apellido TEXT, " +
            "numero TEXT, " +  // Esto agrega un índice único en correo
            "contrasenia TEXT)";

    public DaoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertUsuario(Usuario u) {
        if (buscar(u.getCorreo()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("correo", u.getCorreo());
            cv.put("nombre", u.getNombre());
            cv.put("apellido", u.getApellido());
            cv.put("numero", u.getNumero());
            cv.put("contrasenia", u.getContrasenia());
            return (sql.insert("usuario", null, cv) > 0);

        } else {
            return false;
        }

    }

    public int buscar(String u) {
        int x = 0;
        lista = selectUsuario();
        for (Usuario us : lista) {
            if (us.getCorreo().equals(u)) {
                x++;
            }
        }
        return x;

    }

    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setCorreo(cr.getString(1));
                u.setNombre(cr.getString(2));
                u.setApellido(cr.getString(3));
                u.setNumero(cr.getString(4));
                u.setContrasenia(cr.getString(5));
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public int login(String u, String p) {
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(u) && cr.getString(5).equals(p)) {
                    a++;
                }
            } while (cr.moveToNext());
        }
        return a;
    }
    public Usuario getUsuario(String u, String p) {
        lista = selectUsuario();
        for (Usuario us:lista) {
            if (us.getCorreo().equals(u) && us.getContrasenia().equals(p)) {
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id) {
        lista = selectUsuario();
        for (Usuario us:lista) {
            if (us.getId()==id) {
                return us;
            }
        }
        return null;
    }
}