package comv.module_network;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import comv.module_network.listener.ICallback;
import comv.module_network.listener.IHttpProcessor;

public class HttpHelper implements IHttpProcessor {
    private static IHttpProcessor mHttpProcessor = null;
    private Map<String, Object> mParams = null;

    private static HttpHelper _instance;

    private HttpHelper() {
        mParams = new HashMap<>();
    }

    public static void init(IHttpProcessor httpProcessor) {
        mHttpProcessor = httpProcessor;
    }

    public static HttpHelper obtain() {
        synchronized (HttpHelper.class) {
            if (_instance == null) {
                _instance = new HttpHelper();
            }
        }

        return _instance;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl = appendParams(url, params);
        mHttpProcessor.post(finalUrl, params, callback);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {
        final String finalUrl = appendParams(url, params);
        mHttpProcessor.get(finalUrl, params, callback);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if(params != null || params.isEmpty()){
            return url;
        }

        StringBuilder builder=new StringBuilder(url);
        if(builder.indexOf("?") <= 0){
            builder.append("?");
        }else{
            if(!builder.toString().endsWith("?")){
                builder.append("&");
            }
        }

        for (Map.Entry<String, Object> entry: params.entrySet()){
            builder.append(entry.getKey()).append("=").append(encode(entry.getValue().toString()));
        }

        return builder.toString();
    }

    private static String encode(String str){
        try {
            return URLEncoder.encode(str, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
