package com.example.renato.trabalho;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    final Activity activity = this;
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        escreveHtml(R.id.webview);

        Button beep = (Button) findViewById(R.id.beep);

        beep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

    }
    @Override
    public void onBackPressed() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
    /**
     * metodo para tocar um beep
     */
    private void playSound() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.beep);
        mediaPlayer.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.beep)
                .setPositiveButton(R.string.ativouBeep, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mediaPlayer.stop();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     * @return retorna a activity atual
     */
    public Context getActivity() {
        return activity;
    }

    /**
     *
     * @param id passa como parametro o id da webView
     *
     */
    private void escreveHtml(int id){

        mWebView = (WebView) findViewById(id);

        String text = "<html><body>"
                + "<center> <b>" + getString(R.string.universidade) + "</center> </b>"
                + "<p align=\"justify\">"
                + getString(R.string.unipar)
                + "</p> "
                + "<center> <b>" + getString(R.string.curso) + "</center> </b>"
                + "<p align=\"justify\">"
                + getString(R.string.si)
                + "</p> "
                + "<center> <b>" + getString(R.string.aluno) + "</center> </b>"
                + "<p align=\"justify\">"
                + getString(R.string.descricao)
                + "</p> "
                + "<center> <b>" + getString(R.string.ra) + "</center> </b>"
                + "</body></html>";

        mWebView.loadData(text, "text/html", "utf-8");
    }
}
