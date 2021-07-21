package com.example.user.navigationdrawer.SplashScreen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.navigationdrawer.MainActivity;
import com.example.user.navigationdrawer.R;

/**
 * Created by Matevz on 20. 12. 2016.
 */

public class Splashscreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);// nastavimo barvo nasega ozadja
    }
    /** Poklice, ko je aktiviti ustvarjen */
    Thread splashTread; // ustvarimo nit
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);// layout nasega splascreena
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.aplha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay); //poiscemo nas view z idjon lin_lay, ki se nahaja v activitysplashscreen layoutu
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash); // ta del kode zazene animacijo nasega imageviewa, ki se prav tako nahaja v activity_splashscreenu
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pavza
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splashscreen.this,
                            MainActivity.class); // nas spalsh nas nato forwarda do nasega main aktitivija
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splashscreen.this.finish();
                } catch (InterruptedException e) {
                    // ne anredi nicesar
                } finally {
                    Splashscreen.this.finish();
                }

            }
        };
        splashTread.start();

    }

}