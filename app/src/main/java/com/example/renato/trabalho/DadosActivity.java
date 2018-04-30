package com.example.renato.trabalho;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



public class DadosActivity extends AppCompatActivity {
    private static final String TAG ="Nome da Activity";
    /**
     *
     */
    final Activity activity = this;
    public Dados dados = new Dados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_formulario);

        Intent intent = getIntent();

        dados.receberDados(activity,intent,false);

    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    /**
     *
     * @param view
     */
    public void dadosCertos(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,R.string.cadastro_salvo,Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

    /**
     *
     * @param view
     */
    public void dadosErrados(View view) {

        Bundle params = dados.buscarDados(activity);

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtras(params);
        startActivity(intent);
        finish();

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

}
