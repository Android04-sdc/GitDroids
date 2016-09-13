package com.fuicui.gitdroid.gitdroid.gank.network;

import com.fuicui.gitdroid.gitdroid.gank.model.GankResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

/**
 * 对 Retrofit 简单的配置
 */
public class GankClient implements GankApi {

    /**
     * 使用：GankClient.getInstance().gankApi.getDailyData()
     * 实现了 GankApi 以后使用：GankClient.getInstance().getDailyData()
     */

    private GankApi gankApi;
    private static GankClient gankClient;

    public static GankClient getInstance() {
        if (gankClient == null) {
            gankClient = new GankClient();
        }
        return gankClient;
    }

    private GankClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GankApi.BASE_URL)
                .client(okHttpClient)

                // Retrofit 强大的功能：Gson 转换器----将我们的数据请求的结果进行 json 转换，
                // 转换为我们需要的类型,例如类或者集合
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gankApi = retrofit.create(GankApi.class);
    }

    @Override
    public Call<GankResult> getDailyData(@Path("year") int year, @Path("month") int month, @Path("day") int day) {
        return gankApi.getDailyData(year, month, day);
    }
}


