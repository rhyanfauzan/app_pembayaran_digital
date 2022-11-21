package com.ash.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ash.mobile.R;
import com.ash.mobile.databinding.ActivityMainBinding;
import com.ash.mobile.fragment.HistoryFragment;
import com.ash.mobile.fragment.HomeFragment;
import com.ash.mobile.fragment.InboxFragment;
import com.ash.mobile.fragment.LainnyaFragment;
import com.ash.mobile.fragment.ProfileFragment;
import com.ash.mobile.fragment.TopUpFragment;
import com.ash.mobile.utilities.MyServices;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static final String BroadcastStringForAction="checkinternet";

    private IntentFilter mIntenFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        LainnyaFragment lainnyaFragment = new LainnyaFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.Container, lainnyaFragment).commit();



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


        mIntenFilter=new IntentFilter();
        mIntenFilter.addAction(BroadcastStringForAction);
        Intent serviceIntent= new Intent(this,MyServices.class);
        startService(serviceIntent);

        binding.tvNotconnected.setVisibility(View.GONE);
        if (isOnline(getApplicationContext()))
        {
            Set_Visibility_ON();
        }
        else {

            Set_Visibility_OFF();
        }


        BottomNavigationView bottomnav = findViewById(R.id.bottomAppBar);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.Container, new HomeFragment()).commit();

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


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.history:
                            selectedFragment = new HistoryFragment();
                            break;
                        case R.id.topup:
                            selectedFragment = new TopUpFragment();
                            break;
                        case R.id.profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.inbox:
                            selectedFragment = new InboxFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.Container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Mohon Izinkan Penyimpanan", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BroadcastStringForAction))
            {
                if (intent.getStringExtra("online_status").equals("true"))
                {
                    Set_Visibility_ON();
                }
                else
                {
                    Set_Visibility_OFF();
                }
            }
        }
    };

    public boolean isOnline(Context c)
    {
        ConnectivityManager cm=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();

        if (ni !=null && ni.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

    public void Set_Visibility_ON()
    {
        binding.tvNotconnected.setVisibility(View.GONE);
    }
    public void Set_Visibility_OFF()
    {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(MyReceiver,mIntenFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyReceiver,mIntenFilter);
    }
}
