import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientDemo {



    public static void main(String[] args) {
        // 创建同步CloseableHttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String api = "";
        String url = String.format("%s%s", "http://www.baidu.com", api);
        // 创建HttpGet请求对象
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 调用execute方法执行请求
            response = httpClient.execute(httpGet);
            // 打印请求响应内容
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
