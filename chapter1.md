#Chapter 1
##The Setup

To begin making web apps you need the following;

1. Chrome browser
2. An editor (Sublime Text)
3. Android SDK (You can download this from http://developer.android.com/sdk/index.html)

_Step 1_

Copy and paste the code listing in Exercise 1 into your editor. Save the file as “index.html”.

_Step 2_

Open “index.html” in your Chrome browser. You should see your simple TODO list app.

_Step 3_

Copy “index.html” into the asset folder in Eclipse workspace.

_Step 4_

Copy and paste the following code listing into you Eclipse workspace

	package com.example.mytodolist;

	import android.os.Bundle;
	import android.app.Activity;
	import android.view.Menu;
	import android.webkit.WebView;

	public class MainActivity extends Activity {

		private WebView webView;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        webView = (WebView) findViewById(R.id.webView1);
			webView.getSettings().setJavaScriptEnabled(true);
			//webView.loadUrl("http://www.google.com");
			webView.loadUrl("file:///android_asset/index.html");
	        //webView.loadUrl("file:///android_asset/index.html");
	    }


	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
	}