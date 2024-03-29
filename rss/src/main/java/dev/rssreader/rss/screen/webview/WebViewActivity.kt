package dev.rssreader.rss.screen.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.BindView
import butterknife.ButterKnife
import dagger.hilt.android.AndroidEntryPoint
import dev.rssreader.rss.R
import dev.rssreader.rss.R2

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    @BindView(R2.id.webview)
    lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view)
        ButterKnife.bind(this)

        webView.loadUrl(getIntent().getData().toString())
        webView.setWebViewClient(WebViewClient())
    }
}