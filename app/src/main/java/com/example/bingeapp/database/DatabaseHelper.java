package com.example.bingeapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bingeapp.model.Filme;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bingetrack.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FILMES = "filmes";

    private static final String ID = "id";
    private static final String NOME = "nome";
    private static final String GENERO = "genero";
    private static final String ANO = "ano";
    private static final String STATUS = "status";
    private static final String NOTA = "nota";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_FILMES + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT NOT NULL, " +
                GENERO + " TEXT NOT NULL, " +
                ANO + " INTEGER NOT NULL, " +
                STATUS + " TEXT NOT NULL, " +
                NOTA + " INTEGER NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMES);
        onCreate(db);
    }

    public long inserirFilme(Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, filme.getNome());
        values.put(GENERO, filme.getGenero());
        values.put(ANO, filme.getAno());
        values.put(STATUS, filme.getStatus());
        values.put(NOTA, filme.getNota());

        return db.insert(TABLE_FILMES, null, values);
    }

    public ArrayList<Filme> listarFilmes() {
        ArrayList<Filme> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FILMES + " ORDER BY nome ASC", null);

        if (cursor.moveToFirst()) {
            do {
                Filme filme = new Filme(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(NOME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(GENERO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(ANO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(STATUS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(NOTA))
                );

                lista.add(filme);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }

    public Filme buscarFilmePorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_FILMES + " WHERE " + ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        Filme filme = null;

        if (cursor.moveToFirst()) {
            filme = new Filme(
                    cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(NOME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GENERO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ANO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(STATUS)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(NOTA))
            );
        }

        cursor.close();
        return filme;
    }

    public int atualizarFilme(Filme filme) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, filme.getNome());
        values.put(GENERO, filme.getGenero());
        values.put(ANO, filme.getAno());
        values.put(STATUS, filme.getStatus());
        values.put(NOTA, filme.getNota());

        return db.update(
                TABLE_FILMES,
                values,
                ID + " = ?",
                new String[]{String.valueOf(filme.getId())}
        );
    }

    public boolean excluirFilme(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLE_FILMES,
                ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        return resultado > 0;
    }
}