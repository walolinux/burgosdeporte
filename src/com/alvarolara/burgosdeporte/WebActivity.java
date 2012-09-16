package com.alvarolara.burgosdeporte;

import com.alvarolara.burgosdeporte.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebActivity extends Activity {
	
	private WebView webView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//Si no hay internet, salimos de la aplicación.
        if(!hayInternet(this)){
        	Toast.makeText(this, "Debe disponer de conexión a internet", Toast.LENGTH_LONG).show();
        	finish();
        }
		
		setContentView(R.layout.webview);
 
		webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.burgosdeporte.com/movil");
		webView.setWebViewClient(new verMiWeb());
 
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	 /**
     * Funcion que comprueba si hay internet o no.
     * @param a
     */
    public static boolean hayInternet(Activity a) {
    	boolean hayWifi = false;
    	boolean hayMobile = false;

    	ConnectivityManager cm = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo[] netInfo = cm.getAllNetworkInfo();
    	
    	for (NetworkInfo ni : netInfo) {
	    	if (ni.getTypeName().equalsIgnoreCase("wifi"))
	    	if (ni.isConnected()){
	    		hayWifi = true;
	    	}
	    	if (ni.getTypeName().equalsIgnoreCase("mobile"))
	    	if (ni.isConnected()){
	    		hayMobile = true;
	    	}
    	}
    	
    	//Si no hay wifi o no hay conexión de red, cerramos la aplicación.
    	if(hayWifi==false && hayMobile==false){
    		
    		return false;
    	}
    	return true;
	}
	
	private class verMiWeb extends WebViewClient{
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url){
			view.loadUrl(url);
			return true;
		}
	}
}
