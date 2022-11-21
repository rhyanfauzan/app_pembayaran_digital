package com.ash.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ash.mobile.R;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;


public class BonusActivity extends AppCompatActivity {
    WebView webView;
    ProgressBar bar;
    private TextView norek1, norek2;
    TextView loading, nama1, nama2;
    LinearLayout lyoutbonus;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);
        getSupportActionBar().hide();


        bar= (ProgressBar) findViewById(R.id.pb_bonus);
        loading = findViewById(R.id.loading);
        lyoutbonus = findViewById(R.id.lyoutbonus);
        back = findViewById(R.id.bb_bonus);
        webView = (WebView) findViewById(R.id.wb_bonusreferal);

        nama1 = findViewById(R.id.nama1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("nama_instansi1");
        nama1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        nama1.setSelected(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);

                nama1.setText(m);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nama2 = findViewById(R.id.nama2);
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference("nama_instansi2");
        nama2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        nama2.setSelected(true);
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String m=snapshot.getValue(String.class);

                nama2.setText(m);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        norek1 = findViewById(R.id.norek1);
        FirebaseDatabase firebaseDatabaseNorek1 = FirebaseDatabase.getInstance();
        DatabaseReference Norek1 = firebaseDatabaseNorek1.getReference("norek1");
        Norek1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n=snapshot.getValue(String.class);
                norek1.setText(n);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        norek2 = findViewById(R.id.norek2);
        FirebaseDatabase firebaseDatabaseNorek2 = FirebaseDatabase.getInstance();
        DatabaseReference Norek2 = firebaseDatabaseNorek2.getReference("norek1");
        Norek2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String n=snapshot.getValue(String.class);
                norek2.setText(n);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        webView.setWebViewClient(new myWebclient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        if (Build.VERSION.SDK_INT>=19 && Build.VERSION.SDK_INT<21)
        {
            SetWindowsFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT>=19)
        {
            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT>=21)
        {
            SetWindowsFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            Dialog dialog = new Dialog(this);
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
            webView.loadUrl("https://app.liveash.my.id/member/transfer-saldo");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void SetWindowsFlag(Activity activity, final int Bits, Boolean on)
    {
        Window win = activity.getWindow();
        WindowManager.LayoutParams Winparams = win.getAttributes();
        if (on) {
            Winparams.flags |=Bits;
        }else {
            Winparams.flags &= ~Bits;
        }
        win.setAttributes(Winparams);
    }

    private class myWebclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            lyoutbonus.setVisibility(View.GONE);

        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView wv, String url) {
            if(url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            }
            return false;
        }
    }
}