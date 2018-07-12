package catrisse.marc.utils;

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://178.128.166.139:4000/";

    public static ApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
