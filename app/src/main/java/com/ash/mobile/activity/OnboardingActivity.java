package com.ash.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cuberto.liquid_swipe.LiquidPager;
import com.ash.mobile.R;
import com.ash.mobile.adapter.ViewPagerAdapter;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class OnboardingActivity extends AppCompatActivity {

    Button buttonskip;
    LiquidPager pager;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);


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

       /* buttonskip = findViewById(R.id.skipbtn);
        buttonskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });*/

        pager = findViewById(R.id.pager);
        getSupportActionBar().hide();

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), 1);
        pager.setAdapter(adapter);





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