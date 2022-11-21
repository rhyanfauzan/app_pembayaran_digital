package com.ash.mobile.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ash.mobile.R;


public class KirimSaldoFragment extends Fragment {

    WebView wb_kirimsaldo;
    ProgressBar pb_kirimsaldo;
    TextView tv_kirimsaldo;
    LinearLayout ll_kirimsaldo;

    public KirimSaldoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kirim_saldo, container, false);

        wb_kirimsaldo = v.findViewById(R.id.wb_kirimsaldo);
        pb_kirimsaldo = v.findViewById(R.id.pb_kirimsaldo);
        tv_kirimsaldo = v.findViewById(R.id.tv_kirimsaldo);
        ll_kirimsaldo = v.findViewById(R.id.ll_kirimsaldo);

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
            wb_kirimsaldo.loadUrl("https://app.liveash.my.id/member/transfer-saldo");
        }

        // Enable Javascript
        WebSettings webSettings = wb_kirimsaldo.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        wb_kirimsaldo.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                wb_kirimsaldo.setVisibility(View.VISIBLE);
                pb_kirimsaldo.setVisibility(View.GONE);
                tv_kirimsaldo.setVisibility(View.GONE);
                ll_kirimsaldo.setVisibility(View.GONE);
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