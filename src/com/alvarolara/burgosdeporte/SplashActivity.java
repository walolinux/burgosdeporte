package com.alvarolara.burgosdeporte;
import com.alvarolara.burgosdeporte.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;


public class SplashActivity extends Activity {
	
	/**
	 * Valores para el timmer.
	 */
    private boolean activo = true;
    private int tiempo = 2000;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        
        
        // Tread que controla el tiempo de la pantalla.
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(activo && (waited < tiempo)) {
                        sleep(100);
                        if(activo) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    //Comenzar el activity PrincipalActivity.
                    Intent in = new Intent(getApplicationContext(), WebActivity.class);
        	        startActivity(in);
                }
            }
        };
        splashTread.start();
    }
    
    /**
     * Aunque toques la pantalla, que siga mostrando el splash.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            activo = true;
        }
        return true;
    }
}
