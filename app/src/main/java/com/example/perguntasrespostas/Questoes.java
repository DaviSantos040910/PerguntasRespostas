package com.example.perguntasrespostas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity // Mostra que é um entendida (Tabela) do Banco de Dados
public class Questoes {

    //Cada Váriavél é um atributo da Tabela
    @PrimaryKey(autoGenerate = true)
    int id;
    String pergunta;
    String resposta;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


}
