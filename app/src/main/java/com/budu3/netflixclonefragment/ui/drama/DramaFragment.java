package com.budu3.netflixclonefragment.ui.drama;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.budu3.netflixclonefragment.R;

public class DramaFragment extends Fragment {

    private DramaViewModel mViewModel;

    public static DramaFragment newInstance() {
        return new DramaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        WebView webView;
        View root = inflater.inflate(R.layout.drama_fragment, container, false);

        webView = root.findViewById(R.id.drama_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html#drama");

        return root;
    }

}
