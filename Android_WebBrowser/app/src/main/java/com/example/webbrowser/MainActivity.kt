package com.example.webbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // webView 기본 설정
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
        }

        webView.loadUrl("http://www.google.com/")

        urlEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                webView.loadUrl(urlEditText.text.toString())
                true
            } else {
                false
            }
        }

        // 컨텍스트 메뉴 등록
        registerForContextMenu(webView)

    }

    // 뒤로가기 동작 재정의
    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else super.onBackPressed()
    }

    // 옵션 메뉴 표시
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_google, R.id.action_home -> {
                webView.loadUrl("http://www.google.com/")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://www.naver.com/")
                return true
            }
            R.id.action_daum -> {
                webView.loadUrl("http://www.daum.net/")
                return true
            }
            R.id.action_call -> {
                // Intent 클래스에 정의된 액션 중 하나인 ACTION_DIAL
                // 전화 다이얼을 입력해주는 액션 설정
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-123-4567")

                // 이 인텐트를 수행하는 액티비티가 있는지 검사
                // null 리턴될 경우 수행하는 액티비티 X
                //      -> 전화 앱이 없는 태블릿 같은 경우
                if (intent.resolveActivity(packageManager) != null)
                    startActivity(intent)
                return true
            }
            R.id.action_text -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "보낼 문자열")
                    var chooser = Intent.createChooser(intent, null)
                    if (intent.resolveActivity(packageManager) != null)
                        startActivity(chooser)
                }

                // Anko 라이브러리를 사용할 경우
                // sendSMS(전화번호, [문자열])
                return true
            }
            R.id.action_email -> {
                // Anko 라이브러리를 사용할 경우
                // email(받는 메일 주소, [제목], [내용])
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 컨텍스트 메뉴 표시
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                // Anko 라이브러리를 사용할 경우
                // 문자열 공유
                // share(문자열, [제목])
                return true
            }
            R.id.action_browser -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(webView.url)

                if (intent.resolveActivity(packageManager) != null)
                    startActivity(intent)

                // Anko 라이브러리를 사용할 경우
                // 웹 브라우저에서 열기
                // browse(url)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}