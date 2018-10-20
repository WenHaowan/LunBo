package as.bwei.com.lunbo.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/17.
 */

public class ShowModel {
    private OkHttpClient okHttpClient;

    public void show(final ShowCallback showCallback){

        okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/ad/getAd")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showCallback.failure("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                showCallback.success(string);
            }
        });
    }

    public interface ShowCallback {

        void failure(String msg);

        void success(String msg);
    }
}
