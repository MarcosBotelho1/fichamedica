package com.example.fichamedica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class FichaDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fichas_saude.db";
    private static final String TABLE_FICHAS = "fichas";

    // Colunas
    private static final String COL_ID = "id";
    private static final String COL_NOME = "nome";
    private static final String COL_IDADE = "idade";
    private static final String COL_PESO = "peso";
    private static final String COL_ALTURA = "altura";
    private static final String COL_PRESSAO = "pressao";

    public FichaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_FICHAS + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NOME + " TEXT,"
                + COL_IDADE + " INTEGER,"
                + COL_PESO + " REAL,"
                + COL_ALTURA + " REAL,"
                + COL_PRESSAO + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FICHAS);
        onCreate(db);
    }

    // CRUD Operations
    public void addFicha(FichaSaude ficha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOME, ficha.getNome());
        values.put(COL_IDADE, ficha.getIdade());
        values.put(COL_PESO, ficha.getPeso());
        values.put(COL_ALTURA, ficha.getAltura());
        values.put(COL_PRESSAO, ficha.getPressaoArterial());
        db.insert(TABLE_FICHAS, null, values);
        db.close();
    }

    public FichaSaude getFicha(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FICHAS,
                new String[]{COL_ID, COL_NOME, COL_IDADE, COL_PESO, COL_ALTURA, COL_PRESSAO},
                COL_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) cursor.moveToFirst();

        FichaSaude ficha = new FichaSaude(
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getDouble(3),
                cursor.getDouble(4),
                cursor.getString(5));
        ficha.setId(cursor.getInt(0));
        cursor.close();
        return ficha;
    }

    public List<FichaSaude> getAllFichas() {
        List<FichaSaude> fichas = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FICHAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FichaSaude ficha = new FichaSaude();
                ficha.setId(cursor.getInt(0));
                ficha.setNome(cursor.getString(1));
                ficha.setIdade(cursor.getInt(2));
                ficha.setPeso(cursor.getDouble(3));
                ficha.setAltura(cursor.getDouble(4));
                ficha.setPressaoArterial(cursor.getString(5));
                fichas.add(ficha);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return fichas;
    }


    public int getTotalFichas() {
        String countQuery = "SELECT * FROM " + TABLE_FICHAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public double getMediaIdade() {
        String avgQuery = "SELECT AVG(" + COL_IDADE + ") FROM " + TABLE_FICHAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(avgQuery, null);
        cursor.moveToFirst();
        double avg = cursor.getDouble(0);
        cursor.close();
        return avg;
    }

    public double getMediaIMC() {
        String avgQuery = "SELECT AVG(" + COL_PESO + "/(" + COL_ALTURA + "*" + COL_ALTURA + ")) FROM " + TABLE_FICHAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(avgQuery, null);
        cursor.moveToFirst();
        double avg = cursor.getDouble(0);
        cursor.close();
        return avg;
    }
}