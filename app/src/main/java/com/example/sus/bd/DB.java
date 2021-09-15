package com.example.sus.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.sus.model.Consulta;

public class DB extends SQLiteOpenHelper {

    public DB(Context context) {
        super(context, "Agendamento.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Consulta(telefone int primary key, nome TEXT, dataConsulta TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop Table if exists Consulta");
    }
    public Boolean insertConsulta(Consulta consulta)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("telefone",consulta.getTelefone());
        contentValues.put("nome",consulta.getNome());
        contentValues.put("dataConsulta",consulta.getDataConsulta());
        long result = DB.insert("Consulta",null,contentValues);
        if(result == -1)
        {
            return false;
        }else{
            return true;
        }
    }
    public Boolean updateConsulta(Consulta consulta) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", consulta.getNome());
        contentValues.put("dataConsulta", consulta.getDataConsulta().toString());

        Cursor cursor = DB.rawQuery("Select * from Consulta where telefone = ?", new String[]{consulta.getTelefone()});
        if (cursor.getCount() > 0) {
            long result = DB.update("Consulta", contentValues, "telefone=?", new String[]{consulta.getTelefone()});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
        public Boolean deleteConsulta(String telefone)
        {
            SQLiteDatabase DB = this.getWritableDatabase();

            Cursor cursor = DB.rawQuery("Select * from Consulta where telefone = ?", new String[]{telefone});
            if(cursor.getCount() > 0)
            {
                long result = DB.delete("Consulta","telefone=?",new String[] {telefone});
                if(result == -1)
                {
                    return false;
                }else{
                    return true;
                }
            }else{
                return false;
            }

        }
    public Cursor getConsultas()
    {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Consulta",null);
        return cursor;
    }
}
