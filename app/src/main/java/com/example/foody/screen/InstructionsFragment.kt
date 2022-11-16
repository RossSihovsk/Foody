package com.example.foody.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.foody.R
import com.example.foody.RECIPES_RESULT_KEY
import com.example.foody.models.Result
import kotlinx.android.synthetic.main.fragment_instructions.view.*

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPES_RESULT_KEY)

        view.instructions_webView.webViewClient = object : WebViewClient() {}
        view.instructions_webView.loadUrl(myBundle!!.sourceUrl)

        return view
    }

}