package sekhar.mitra.dora.blink.java;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 06/05/17.
 */

public class BlinkHttpClient {
  private static BlinkWebService blinkWebService;
  private static String BASE_URL = "https://api.flickr.com/";

  public static BlinkWebService getClient() {
    if (blinkWebService == null) {
      Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
      blinkWebService = retrofit.create(BlinkWebService.class);
    }
    return blinkWebService;
  }
}
