package com.example.perguntasrespostas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadastro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadastro extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button mbtnJogar, mbtnCadastrar;
    EditText mEditPergunta, mEditResposta;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCadastro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCadastro.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCadastro newInstance(String param1, String param2) {
        FragmentCadastro fragment = new FragmentCadastro();
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

        return inflater.inflate(R.layout.fragment_cadastro, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        mbtnJogar =  getActivity().findViewById(R.id.btnJogar);
        mbtnJogar.setOnClickListener(mbtnJogarListener);

        mEditPergunta = getActivity().findViewById(R.id.editPergunta);
        mEditResposta = getActivity().findViewById(R.id.editResposta);

        mbtnCadastrar = getActivity().findViewById(R.id.btnCadastrar);
        mbtnCadastrar.setOnClickListener(mbtnCadastrarListener);

    }
    private View.OnClickListener mbtnJogarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameContainer, new FragmentJogo())
                    .commit();
        }
    };
    private View.OnClickListener mbtnCadastrarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                String pergunta = mEditPergunta.getText().toString();
                String resposta = mEditResposta.getText().toString();

                if ((!pergunta.isEmpty()) && (!resposta.isEmpty())){
                    Questoes questoes = new Questoes();
                    questoes.setPergunta(pergunta); // Inseri a Pergunta relacionada a essa objeto específico
                    questoes.setResposta(resposta); // Inseri a Reposta relacionado a esse objeto específico

                    // Instância do Banco de Dados
                   long numQuestao =  DataBaseRoom.getDataBaseRoom(getActivity())
                            .getDao()
                            .inserirQuestao(questoes); // Inseri os dados da Tabela no banco de Dados, com as perguntas e respostas relacionadas a esse objeto específico
                    Toast.makeText(getActivity(), numQuestao + "Adionadas", Toast.LENGTH_SHORT).show();
                    mEditPergunta.setText("");
                    mEditResposta.setText("");
                }else{
                    Toast.makeText(getActivity(), "Nenhuma Questão Adicionada", Toast.LENGTH_SHORT).show();

                }
        }
    };

}