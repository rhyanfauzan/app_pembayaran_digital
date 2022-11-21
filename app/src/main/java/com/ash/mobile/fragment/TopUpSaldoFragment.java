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

public class TopUpSaldoFragment extends Fragment {

    WebView wb_topup_saldo;
    ProgressBar pb_topup_saldo;
    TextView tv_topup_saldo;
    LinearLayout ll_topup_saldo;


    public TopUpSaldoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_up_saldo, container, false);

        wb_topup_saldo = v.findViewById(R.id.wb_topup_saldo);
        pb_topup_saldo = v.findViewById(R.id.pb_topup_saldo);
        tv_topup_saldo = v.findViewById(R.id.tv_topup_saldo);
        ll_topup_saldo = v.findViewById(R.id.ll_topup_saldo);

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
            wb_topup_saldo.loadUrl("https://app.liveash.my.id/member/deposit");
        }

        // Enable Javascript
        WebSettings webSettings = wb_topup_saldo.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        wb_topup_saldo.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                wb_topup_saldo.setVisibility(View.VISIBLE);
                pb_topup_saldo.setVisibility(View.GONE);
                tv_topup_saldo.setVisibility(View.GONE);
                ll_topup_saldo.setVisibility(View.GONE);
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