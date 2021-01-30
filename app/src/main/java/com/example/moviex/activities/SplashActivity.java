package com.example.moviex.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moviex.R;


public class SplashActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;

    boolean networkControl(final Context context){ // İNTERNET BAĞLANTISINI KONTROL EDİYORUZ VE DURUMU BOOL DEĞİŞKENİMİZE ATIYORUZ.
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        NetworkController(); // INTERNET KONTROLÜNÜ YAPACAK OLAN METODUMUZ.

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //Hooks
        image = findViewById(R.id.imgMovie);
        logo = findViewById(R.id.txtLogo);
        slogan = findViewById(R.id.txtSlogan);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);
    }
    public void NetworkController() // İNTERNET BAĞLANTISININ KONTROL EDİLDİĞİ METOD.
    {
        if (networkControl(this)) { // EĞER İNTERNET VARSA;
            Thread splashThread;
            splashThread = new Thread() {
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            wait(4000); // 4 SANİYE BEKLEYECEK
                        }
                    } catch (InterruptedException ex) {

                    } finally {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
            };
            splashThread.start();
        } else { // İNTERNETİN OLMADIĞI DURUMDA
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Bağlantı Hatası");
            alert.setMessage("Lütfen internet bağlantınızı kontrol ediniz");
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "TAMAM",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int pid = android.os.Process.myPid();
                            finish();
                            //android.os.Process.killProcess(pid); // UYGULAMAYI SONLANDIRACAK
                            dialog.dismiss();// UYARIYI KAPATACAK
                        }
                    });
            alert.show();
        }
    }
}