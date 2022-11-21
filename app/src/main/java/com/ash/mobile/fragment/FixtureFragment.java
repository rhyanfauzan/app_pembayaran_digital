package com.ash.mobile.fragment;


import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.ash.mobile.R;

import static android.content.Context.DOWNLOAD_SERVICE;

public class FixtureFragment extends Fragment {


    WebView mWebView;
    ProgressBar pb_riwayat;
    TextView tv_riwayat;
    LinearLayout ll_riwayat;

    public FixtureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fixture, container, false);

        pb_riwayat = (ProgressBar) v.findViewById(R.id.progressBarRiwayat);
        tv_riwayat = v.findViewById(R.id.textViewRiwayat);
        ll_riwayat = v.findViewById(R.id.linearLayoutRiwayat);
        mWebView = (WebView) v.findViewById(R.id.wb_transaksi);





        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.alert_dialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;
            Button btntry = dialog.findViewById(R.id.btn_try);
            btntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
            dialog.show();
        } else {
            mWebView.loadUrl("https://app.liveash.my.id/member/riwayat-transaksi");
        }



        mWebView.setDownloadListener(new DownloadListener() {

            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                //   ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                //for downloading directly through download manager

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                //This three lines will do your work

                CookieManager cookieManager = CookieManager.getInstance();
                String cookie = cookieManager.getCookie("https://app.liveash.my.id");     // which is "http://bookboon.com"
                request.addRequestHeader("Cookie", cookie);
                //................................................
                request.allowScanningByMediaScanner();
                Environment.getExternalStorageDirectory();
                getActivity().getFilesDir().getPath(); //which returns the internal app files directory path
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
                DownloadManager dm = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
            }
        });


        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.setVisibility(View.VISIBLE);
                pb_riwayat.setVisibility(View.GONE);
                tv_riwayat.setVisibility(View.GONE);
                ll_riwayat.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.setVisibility(View.GONE);//hide the webview that will display your dialog
            }
        });
        return v;
    }


    private void recreate() {

    }
}
