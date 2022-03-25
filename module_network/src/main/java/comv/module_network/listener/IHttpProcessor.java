package comv.module_network.listener;

import java.util.Map;

public interface IHttpProcessor {

    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, Map<String, Object> params, ICallback callback);

}
