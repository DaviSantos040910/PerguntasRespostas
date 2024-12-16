package com.example.perguntasrespostas;

import android.widget.LinearLayout;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao // Interface para poder manipular a Tabela
public abstract  class DAO {

    @Insert
    public abstract long inserirQuestao(Questoes questoes);

    @Query("SELECT * FROM Questoes")
    public abstract List<Questoes> pesquisarTodasQuestoes();
}
