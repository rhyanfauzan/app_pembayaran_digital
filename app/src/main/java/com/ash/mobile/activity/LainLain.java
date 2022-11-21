package com.ash.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ash.mobile.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class LainLain extends AppCompatActivity {

    LinearLayout vouchergamebtn,ewalletbtn, televisi, gasnegara, pakettelkomsel, voucherinternet,grabdriver, leasing, vouchertv, telppascabayar,lainnya;
    ImageView bb_menulainnya;
    private SlidingUpPanelLayout mLayout;
    private static final String TAG = "DemoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lain_lain);
        getSupportActionBar().hide();


        vouchertv = findViewById(R.id.vouchertv);
        voucherinternet = findViewById(R.id.voucherinternet);
        pakettelkomsel = findViewById (R.id.pakettelkomsel);
        gasnegara = findViewById(R.id.pln);
        bb_menulainnya = findViewById(R.id.bb_menulainnya);
        vouchergamebtn = findViewById(R.id.vouchergame);
        ewalletbtn = findViewById(R.id.ewallet);
        televisi = findViewById(R.id.televisi);
        leasing = findViewById(R.id.leasing);
        lainnya = findViewById(R.id.lainnya);
        grabdriver = findViewById(R.id.grabdriver);


        vouchertv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, VoucherTvActivity.class));
            }
        });
        pakettelkomsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, PaketTelkomselActivity.class));
            }
        });
        grabdriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, GrabDriverActivity.class));
            }
        });
        lainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, ETollActivity.class));
            }
        });
        leasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, LeasingActivity.class));
            }
        });
        voucherinternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, VoucherInternetActivity.class));
            }
        });
        gasnegara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, GasNegaraActivity.class));
            }
        });
        bb_menulainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        televisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (LainLain.this, TelevisiActivity.class));
            }
        });
        ewalletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this, EwalletActivity.class));
            }
        });
        vouchergamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LainLain.this,VoucherGameActivity.class));
            }
        });

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
}