package com.example.coffees.assignmentlistview1;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        w = (WebView) findViewById(R.id.webView);
        w.getSettings().setJavaScriptEnabled(true); //used to load websites which use javascript
        w.setWebViewClient(new WebViewClient());
        boolean b = checkInternet();
        if(b){
            Intent in = getIntent();
            Bundle bb = in.getExtras();
            String website = bb.getString("website");
            Toast.makeText(this, "Wait we are loading...", Toast.LENGTH_SHORT).show();

            w.loadUrl("http://"+website);
        }
        else{
            w.loadData("<html><h1>Failed to load the page</h1></br><p>Please ensure you have internet connectivity</p>","text/html","UTF-8");
        }
    }
    public boolean checkInternet(){
        //ConectivityManagaer is a predefined class
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        //Ask coonectivity manager to give mobile network information
        NetworkInfo info = manager.getActiveNetworkInfo();

        //now check if internet is available or not
        if(info != null && info.isConnected()){
            return true;
        }
        return false;
    }
    public void onBackPressed() {
        //check if webview has previous page


        if(w.canGoBack()){
            w.goBack();//if yes then dont close the activity instead go to the previous page
            return;
        }
        super.onBackPressed();  //if this line is not written back button will not work
    }
}
