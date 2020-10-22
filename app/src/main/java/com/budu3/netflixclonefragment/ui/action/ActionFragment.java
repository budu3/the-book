package com.budu3.netflixclonefragment.ui.action;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.budu3.netflixclonefragment.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ActionFragment extends Fragment {

    private ActionViewModel actionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WebView webView;
        View root = inflater.inflate(R.layout.action_fragment, container, false);

        webView = (WebView) root.findViewById(R.id.action_webview);
        webView.getSettings().setJavaScriptEnabled(true );
        webView.loadUrl("file:///android_asset/index.html#action");
        return root;
    }

}
