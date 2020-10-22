package com.budu3.netflixclonefragment.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.budu3.netflixclonefragment.R;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel mViewModel;

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root;
        WebView webView;

        root = inflater.inflate(R.layout.favorites_fragment, container, false);
        webView = root.findViewById(R.id.favorites_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html#favorites");

        return root;
    }
}
