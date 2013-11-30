package com.du;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class GenericMenu extends Activity {

	WebView webview;
	String sec;
	ProgressDialog pd;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sec = this.getIntent().getExtras().getString("sec");
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.webview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

		pd = new ProgressDialog(this);
		pd.setMessage("Loading...");

		webview = (WebView) findViewById(R.id.mWebview);

		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setBuiltInZoomControls(true);

		webview.setWebViewClient(new WebViewClient() {

			public void onReceivedError(WebView view, int errorCod,
					String description, String failingUrl) {
				Toast.makeText(GenericMenu.this, "No internet connection, or " + description, Toast.LENGTH_LONG).show();
			}

			public void onPageFinished(WebView view, String url) {
				if (pd.isShowing()) {
					pd.dismiss();
				}
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (url.endsWith(".pdf")) {
					view.loadUrl("https://docs.google.com/gview?embedded=true&url="+ url);
				}

				else if (!url.contains("www.du.ac.in/index.php")) {
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					startActivity(intent);
					return true;
					
				} else {
					getAsyncNetTask().execute("http://plattergre-hrd.appspot.com/duappserver?sec=request&url="+ URLEncoder.encode(url));

				}

				return true;
			}

		});
		
		try {
			long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
			File httpCacheDir = new File(getCacheDir(), "http");
			Class.forName("android.net.http.HttpResponseCache").getMethod("install", File.class, long.class)
					.invoke(null, httpCacheDir, httpCacheSize);
		} catch (Exception httpResponseCacheNotAvailable) {
		}
		
		getAsyncNetTask().execute("http://plattergre-hrd.appspot.com/duappserver?sec="+ sec + "&url=");
	}

	AsyncTask<String, Integer, String> getAsyncNetTask() {
		return new AsyncTask<String, Integer, String>() {

			protected void onPreExecute() {
				if (!pd.isShowing())
					pd.show();
			}

			protected void onPostExecute(String result) {

				webview.loadDataWithBaseURL("http://www.du.ac.in", result, "text/html", "utf-8", null);
			}

			protected String doInBackground(String... urls) {

				String content = "";
				HttpURLConnection urlConnection = null;
				try {
					URL url = new URL(urls[0]);
					urlConnection = (HttpURLConnection) url.openConnection();
					InputStream in = new BufferedInputStream(urlConnection.getInputStream());
					BufferedReader rd = new BufferedReader(new InputStreamReader(in));
					String line;
					String NL = System.getProperty("line.separator");
					StringBuffer sb = new StringBuffer("");

					while ((line = rd.readLine()) != null) {
						sb.append(line + NL);
					}
					rd.close();
					content = sb.toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (null != urlConnection) {
						urlConnection.disconnect();
					}
				}
				return content;
			}
		};
	}
}
