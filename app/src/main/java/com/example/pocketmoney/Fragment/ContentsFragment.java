package com.example.pocketmoney.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.pocketmoney.R;

public class ContentsFragment extends Fragment {

    public ContentsFragment() {}
    private Button economic;
    private WebView mWebview;
    private WebSettings mWebViewSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contents,container, false);

        economic = view.findViewById(R.id.economic);
        mWebview = view.findViewById(R.id.webview);
        mWebview.setWebViewClient(new WebViewClient());
        mWebViewSettings = mWebview.getSettings();
        mWebViewSettings.setJavaScriptEnabled(true);
        mWebview.loadUrl("http://kids.moef.go.kr/");

        economic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebview.setVisibility(View.VISIBLE);
                economic.setVisibility(View.INVISIBLE);

            }
        });


        return view;
    }

}
