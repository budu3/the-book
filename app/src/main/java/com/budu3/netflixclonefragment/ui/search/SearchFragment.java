package com.budu3.netflixclonefragment.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.budu3.netflixclonefragment.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        WebView webView;

        super.onActivityCreated(savedInstanceState);

        View root = inflater.inflate(R.layout.search_fragment, container, false);

        webView = (WebView) root.findViewById(R.id.search_webview);
        webView.getSettings().setJavaScriptEnabled(true );
        webView.loadUrl("file:///android_asset/search.html");
        return root;
    }

}
