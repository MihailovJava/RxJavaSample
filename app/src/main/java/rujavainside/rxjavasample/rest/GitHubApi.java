package rujavainside.rxjavasample.rest;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Nixy on 26.03.2016.
 */
public interface GitHubApi {

    @GET("users/{user}")
    Observable<User> user(@Path("user") String user);

}
