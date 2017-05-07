package sekhar.mitra.dora.blink.java;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sekhar.mitra.dora.blink.model.search.Search;

/**
 * Created by user on 06/05/17.
 */

public interface BlinkWebService {
  /**
   * Search Photos
   */
  @GET("services/rest?&format=json&nojsoncallback=1&method=flickr.photos.search&tags=apparel&per_page=10&safe_search=1&content_type=1")
  Call<Search> searchPhotos(@Query("text") String text, @Query("page") String page,
      @Query("api_key") String apikey);
}
