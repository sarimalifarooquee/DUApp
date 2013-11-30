package com.du;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Cic extends Activity {

	WebView webview;
	ProgressDialog pd;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.webview);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		pd = new ProgressDialog(this);
		pd.setMessage("Loading...");

		webview = (WebView) findViewById(R.id.mWebview);
		webview.getSettings().setBuiltInZoomControls(true);

		try {
			long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
			File httpCacheDir = new File(getCacheDir(), "http");
			Class.forName("android.net.http.HttpResponseCache")
					.getMethod("install", File.class, long.class)
					.invoke(null, httpCacheDir, httpCacheSize);
		} catch (Exception httpResponseCacheNotAvailable) {
		}

		webview.setWebViewClient(new WebViewClient() {

			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				if (!pd.isShowing()) {
					pd.show();
				}
			}

			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				startActivity(intent);
				return true;
			}

			public void onPageFinished(WebView view, String url) {
				view.loadUrl("javascript:document.getElementById('m_timeline_cover_section').style.display='none'");
				view.loadUrl("javascript:document.getElementsByClassName('aclb')[0].style.display='none'");
				if (pd.isShowing()) {
					pd.dismiss();
				}
			}

		});

		webview.getSettings().setJavaScriptEnabled(true);

		webview.loadUrl(this.getResources().getString(R.string.cluster_url));

	}
}