package com.rappasocial.routinemanager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class WebRequest {
  public enum PostType{
    GET, POST;
  }

  public String _url;
  public String response = "";
  public PostType _postType;
  CookieStore _cookieStore = new BasicCookieStore();

  public WebRequest(String url) {
    _url = url;
    _postType = PostType.POST;
  }

  public WebRequest(String url, CookieStore cookieStore) {
    _url = url;
    _cookieStore = cookieStore;
    _postType = PostType.POST;
  }

  public WebRequest(String url, PostType postType) {
    _url = url;
    _postType = postType;
  }

  public String Get() {
    HttpClient httpclient = new DefaultHttpClient();

    try {
      // Create local HTTP context
      HttpContext localContext = new BasicHttpContext();

      // Bind custom cookie store to the local context
      localContext.setAttribute(ClientContext.COOKIE_STORE, _cookieStore);

      HttpResponse httpresponse;
      if (_postType == PostType.POST)
      {
        HttpPost httppost = new HttpPost(_url);
        httpresponse = httpclient.execute(httppost, localContext);
      }
      else
      {
        HttpGet httpget = new HttpGet(_url);
        httpresponse = httpclient.execute(httpget, localContext);
      }

      StringBuilder responseString = inputStreamToString(httpresponse.getEntity().getContent());

      response = responseString.toString();
    }
    catch (UnknownHostException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      // When HttpClient instance is no longer needed,
      // shut down the connection manager to ensure
      // immediate deallocation of all system resources
      httpclient.getConnectionManager().shutdown();
    }

    return response;
  }

  private StringBuilder inputStreamToString(InputStream is) throws IOException {
    String line = "";
    StringBuilder total = new StringBuilder();

    // Wrap a BufferedReader around the InputStream
    BufferedReader rd = new BufferedReader(new InputStreamReader(is,Charset.forName("iso-8859-9")));
    // Read response until the end
    while ((line = rd.readLine()) != null) {
      total.append(line);
    }

    // Return full string
    return total;
  }
}