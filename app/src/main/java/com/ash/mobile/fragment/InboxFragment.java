package com.ash.mobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ash.mobile.R;
import com.ash.mobile.activity.LogoutActivity;
import com.ash.mobile.activity.MembershipActivity;
import com.ash.mobile.activity.MutasiActivity;
import com.ash.mobile.activity.RedeemActivity;
import com.ash.mobile.activity.ReferalActivity;
import com.ash.mobile.activity.TagihanActivity;


public class InboxFragment extends Fragment {

    private LinearLayout tagihanbtn, referalbtn, redeembtn, mutasibtn, membershipbtn, bonusbtn, keluarbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View getView = inflater.inflate(R.layout.fragment_inbox, container, false);

        tagihanbtn = getView.findViewById(R.id.tagihan);
        referalbtn = getView.findViewById(R.id.referal);
        redeembtn = getView.findViewById(R.id.redeem);
        mutasibtn = getView.findViewById(R.id.mutasi);
        membershipbtn = getView.findViewById(R.id.membership);
        bonusbtn = getView.findViewById(R.id.bonusreferal);
        keluarbtn = getView.findViewById(R.id.keluar);


        keluarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogoutActivity.class);
                startActivity(intent);
            }
        });
        bonusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReferalActivity.class);
                startActivity(intent);
            }
        });
        membershipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MembershipActivity.class);
                startActivity(intent);
            }
        });
        mutasibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MutasiActivity.class);
                startActivity(intent);
            }
        });
        redeembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RedeemActivity.class);
                startActivity(intent);
            }
        });
        referalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReferalActivity.class);
                startActivity(intent);
            }
        });
        tagihanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TagihanActivity.class);
                startActivity(intent);
            }
        });

        return getView;
    }
}