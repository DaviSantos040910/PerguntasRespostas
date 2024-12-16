package com.example.perguntasrespostas;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentJogo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentJogo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Button mbtnResposta, mbtnPular, mbtnFragmentCadastrar;
    private TextView mTextPergunta, mTextResposta;

    private List<Questoes> mListQuestoes;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentJogo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentJogo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentJogo newInstance(String param1, String param2) {
        FragmentJogo fragment = new FragmentJogo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jogo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mbtnResposta = getActivity().findViewById(R.id.btnResposta);
        mbtnPular = getActivity().findViewById(R.id.btnPular);
        mbtnFragmentCadastrar = getActivity().findViewById(R.id.btnFragmentCadastrar);

        mTextPergunta = getActivity().findViewById(R.id.viewPergunta);
        mTextResposta = getActivity().findViewById(R.id.viewResposta);

        mbtnResposta.setOnClickListener(mbtnRespostaListener);
        mbtnPular.setOnClickListener(mbtnPularListener);
        mbtnFragmentCadastrar.setOnClickListener(mbtnFragmentListener);

        //Cria um instância do banco de Dados e amazena toda a lista de Questões
        mListQuestoes = DataBaseRoom.getDataBaseRoom(getActivity())
                .getDao()
                .pesquisarTodasQuestoes();
        proximaQuestao(); // Exibe uma Questão logo ao entrar

    }

    private void exibirResposta(){
            mTextResposta.setVisibility(View.VISIBLE);
    }
    private void proximaQuestao(){
            if (!mListQuestoes.isEmpty()){ // Verifica se a Lista Não está vazia
                int total = mListQuestoes.size(); // Pega o total de Questões para Randomizar
                int indexAleatorio = new Random().nextInt(total); // Pega um index aleatório
                Questoes questoes = mListQuestoes.get(indexAleatorio); // Pega a questão relaionada a essa index
                Toast.makeText(getActivity(), ""+questoes.getPergunta(), Toast.LENGTH_SHORT).show();

                mTextPergunta.setText(questoes.getPergunta()); // Exibe a Pergunta relaionado ao index
                mTextResposta.setText(questoes.getResposta()); // Exibe a resposta da pergunta em Questão
                mTextResposta.setVisibility(View.GONE); // Deixa a resposta invisevel

            }
    }

    private View.OnClickListener mbtnRespostaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            exibirResposta();
        }
    };
    private View.OnClickListener mbtnPularListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            proximaQuestao();
        }
    };
    private View.OnClickListener mbtnFragmentListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContainer, new FragmentCadastro())
                    .commit();
        }
    };
}