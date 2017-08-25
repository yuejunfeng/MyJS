package yuejunfeng20170821.bw.com.myapplication1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView show_wb;
    private String HTML_URL = "http://huixinguiyu.cn/test.html";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_wb = (WebView) findViewById(R.id.show_wb);
        WebSettings settings = show_wb.getSettings();
        settings.setJavaScriptEnabled(true);
        show_wb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        /*android定义一个方法，给JS调用
          使用webView对象，调用addjavascriptinterface方法（）,
          第一个参数是写一个类，在这里面提供要暴露的方法，方法前最好加一个注解
          第二个参数是标识字符串，js通过这个表示，调用我们的方法，在js里面是这样使用的：android.showToast(content)
         */
        show_wb.addJavascriptInterface(new Object() {
            public void showToast(String content) {
                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        }, "Android");
    }

    public void load(View v) {
        show_wb.loadUrl(HTML_URL);
    }

    //调用JS暴露的方法，格式固定：WebView对象。loadurl("javasccript:js方法名(参数)")
    public void call(View v) {
        show_wb.loadUrl("javascript:changeInputValue('哈哈哈')");
    }
}
