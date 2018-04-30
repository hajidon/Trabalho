package com.example.renato.trabalho;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    final Activity activity = this;
    private final String TAG = "Nome da Activity";
    int click;
    public Dados dados = new Dados();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate");

       final ImageButton scan1 = (ImageButton) findViewById(R.id.scan1);
        scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click = scan1.getId();
                ligarScan();
            }
        });

       final ImageButton scan2 = (ImageButton) findViewById(R.id.scan2);
        scan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click = scan2.getId();
                ligarScan();
            }
        });

        final ImageButton scan3 = (ImageButton) findViewById(R.id.scan3);
        scan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click = scan3.getId();
                ligarScan();
            }
        });

        Intent intent = getIntent();
        dados.receberDados(activity,intent, true);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.config) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }

        return true;
    }

    /**
     *
     * @return true se os campos forem validados
     */
    public boolean validarCampos() {

        boolean status = true;
        EditText eNome = (EditText) findViewById(R.id.editNome);
        EditText eIdade = (EditText) findViewById(R.id.editIdade);
        EditText eEnd = (EditText) findViewById(R.id.editEnd);
        String  nome = eNome.getText().toString();
        String  idade = eIdade.getText().toString();
        String  end = eEnd.getText().toString();

        String error = getApplicationContext().getString(R.string.errorField);


        if (isCampoVazio(nome)){
            eNome.requestFocus();
            eNome.setError(error);
           return false;
        } else {
            eNome.setError(null);
        }
        if (isCampoVazio(idade)){
            eIdade.requestFocus();
            eIdade.setError(error);
            return false;
        } else {
            eIdade.setError(null);
        }
        if (isCampoVazio(end)){
            eEnd.requestFocus();
            eEnd.setError(error);
            return false;

        } else {
            eEnd.setError(null);
        }

         return true;

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.sairAplicacao))
                .setIcon(R.drawable.ic_error_black_24dp)
                .setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        System.exit(0);
                    }
                })
                .setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    /**
     *
     * @param valor
     * @return
     */
    private boolean isCampoVazio(String valor) {

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    /**
     *
     * @param view
     */
    public void enviarForm(View view) {

        if(validarCampos()) {
            Intent intent = new Intent(this, DadosActivity.class);
            intent.putExtras(dados.buscarDados(this));
            startActivity(intent);
        }
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

    /**
     * Ativa o scan
     */
    public void ligarScan() {

        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);

        Context context = getApplicationContext();
        String prompt = context.getString(R.string.scan);

        integrator.setPrompt(prompt);
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity",getApplicationContext().getString(R.string.cancelado));
                Toast.makeText(this,getApplicationContext().getString(R.string.cancelado),Toast.LENGTH_LONG).show();
            }else {
                if(click == R.id.scan1) {
                    EditText nomeView = (EditText) findViewById(R.id.editNome);
                    nomeView.setText(result.getContents());
                    //  Toast.makeText(this, "Result: " + result.getContents(), Toast.LENGTH_LONG).show();
                } else if(click == R.id.scan2) {
                    EditText nomeView = (EditText) findViewById(R.id.editIdade);
                    nomeView.setText(result.getContents());
                    //Toast.makeText(this, "Result: " + result.getContents(), Toast.LENGTH_LONG).show();
                } else {
                    EditText nomeView = (EditText) findViewById(R.id.editEnd);
                    nomeView.setText(result.getContents());
                    //Toast.makeText(this, "Result: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public Activity getActivity() {
        return activity;
    }
}