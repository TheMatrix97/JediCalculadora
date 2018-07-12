package catrisse.marc.utils;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://178.128.166.139:4000/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}