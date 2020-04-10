package multi.android.datamanagementpro.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import multi.android.datamanagementpro.R;

public class BasicPermissionTest extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baisc_permission_test);
        webView = findViewById(R.id.webview);

        WebSettings settings = webView.getSettings(); //정보를 하나하나 셋팅하고 싶을 때
        settings.setJavaScriptEnabled(true);  //js 허용
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 캐시 설정
        webView.loadUrl("https://m.naver.com"); //로컬 파일. 웹페이지 등록 가능

    }
}
