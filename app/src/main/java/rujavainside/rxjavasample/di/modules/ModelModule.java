package rujavainside.rxjavasample.di.modules;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import rujavainside.rxjavasample.rest.GitHubApi;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nixy on 26.03.2016.
 */
@Module
public class ModelModule {

    private static final boolean ENABLE_LOG = true;

    private static final boolean ENABLE_AUTH = false;

    private String baseUrl = "https://api.github.com";

    private String token = "636f916956e3420d7d913ff394b5727cc4526b48 ";


    @Provides
    @Singleton
    @Inject
    public Retrofit.Builder provideBuilder(OkHttpClient client) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client);
        return builder;
    }


    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        OkHttpClient okHttpClient;
        if (ENABLE_AUTH) {
            okHttpClient = new OkHttpClient.Builder().addInterceptor(
                    chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Authorization", " token " + token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }).build();

        }

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        }
        return okHttpClient;
    }

    @Provides
    @Singleton
    @Inject
    public GitHubApi provideGitHubApi(Retrofit.Builder builder) {
        return builder.build().create(GitHubApi.class);
    }

    @Provides
    @Singleton
    @Named("UI_THREAD")
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("IO_THREAD")
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }
}
