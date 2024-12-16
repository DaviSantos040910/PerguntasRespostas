package com.example.perguntasrespostas;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Questoes.class}, version = 1)

public abstract class DataBaseRoom extends RoomDatabase {

    public abstract DAO getDao(); // Cria uma instância da Interface para poder manipular a Tabela

    private static DataBaseRoom INSTACE;

    public static DataBaseRoom // Cria o banco de Dados
        getDataBaseRoom(final Context context){
    if (INSTACE == null){
        synchronized (DataBaseRoom.class){
            if (INSTACE == null){
                INSTACE = Room.
                        databaseBuilder(context.
                                getApplicationContext(),
                                DataBaseRoom.class,
                                "bd_questoes")
                        .allowMainThreadQueries()
                        .build();
            }
        }
    }
    return INSTACE;
    }
}
