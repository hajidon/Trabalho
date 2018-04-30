package com.example.renato.trabalho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Renato on 30/03/2018.
 */

public class Dados {

    /**
     *
     * @param v activity
     * @return params do tipo Bundle
     */
    public Bundle buscarDados(Activity v){
        Bundle params = new Bundle();

        EditText nome = (EditText) v.findViewById(R.id.editNome);
        EditText idade = (EditText) v.findViewById(R.id.editIdade);
        EditText endereco = (EditText) v.findViewById(R.id.editEnd);

        params.putString("nome", nome.getText().toString());
        params.putString("idade", idade.getText().toString());
        params.putString("end", endereco.getText().toString());

        return params;
    }

    /**
     *
     * @param v Activity
     * @param intent
     * @param ativo diz se o campo será ativo ou não
     * @return false se não existem parametros
     */
    public boolean receberDados(Activity v, Intent intent,boolean ativo) {
        Bundle params = null;
        if(intent != null) {
            params = intent.getExtras();
            if(params != null) {

                String nome = params.getString("nome");
                String idade = params.getString("idade");
                String endereco = params.getString("end");

                EditText nomeView = (EditText) v.findViewById(R.id.editNome);
                EditText idadeView = (EditText) v.findViewById(R.id.editIdade);
                EditText endView = (EditText) v.findViewById(R.id.editEnd);


                nomeView.setText(nome);
                idadeView.setText(idade);
                endView.setText(endereco);

                nomeView.setEnabled(ativo);
                idadeView.setEnabled(ativo);
                endView.setEnabled(ativo);

                return true;

            }
        }
        return false;
    }
}
