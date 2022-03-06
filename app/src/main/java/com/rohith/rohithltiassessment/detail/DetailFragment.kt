package com.rohith.rohithltiassessment.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.rohith.rohithltiassessment.databinding.FragmentDetailBinding

/**
 * This [Fragment] will show the detailed information about a selected piece of News.
 */
class DetailFragment : Fragment() {

    private lateinit var mWebView : WebView
    private lateinit var mProgressBar : ProgressBar
    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        mWebView = binding.webView
        mProgressBar = binding.progressBar
        val source = DetailFragmentArgs.fromBundle(requireArguments()).selectedSource
        url = source.url

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val settings = mWebView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true

        mWebView.webViewClient = DefaultWebViewClient()

        // Set web view client.
        mWebView.webChromeClient = object : DefaultWebChromeClient() {

            override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    mProgressBar.visibility = View.VISIBLE
                }
                if (newProgress == 100) {

                    mProgressBar.visibility = View.GONE
                }
            }

        }
        mWebView.loadUrl(url!!)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mWebView.canGoBack()) {
                    mWebView.goBack()
                } else {
                    findNavController().popBackStack()
                }
            }
        })

        Log.d("LOG_TAG", "Loading URL: $url")
    }

    override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Destroy the WebView completely.
        if (mWebView != null) {
            // The WebView must be removed from the view hierarchy before calling destroy to prevent a memory leak.
            (mWebView.parent as ViewGroup).removeView(mWebView)
            mWebView.removeAllViews()
            mWebView.destroy()
        }
    }

    internal open class DefaultWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return false
        }

    }

    internal open class DefaultWebChromeClient : WebChromeClient() {

    }
}