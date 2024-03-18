import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class CoinbaseController {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * Get the current price of BTC from Coinbase.
     * API is explained in https://docs.cloud.coinbase.com/sign-in-with-coinbase/docs/api-prices#http-request
     * @return the current BTC buy price in USD.
     * @throws IOException
     */
    public int getBTCPriceToBuyInUSD() throws IOException {
        URL url = new URL("https://api.coinbase.com/v2/prices/BTC-USD/buy");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonTestString = "{\"data\":{\"amount\":\"66964.93\",\"base\":\"BTC\",\"currency\":\"USD\"}}";
            JSONObject jsonTest = new JSONObject(jsonTestString);

            System.out.println(jsonTestString);
            System.out.println(response);
            System.out.println(jsonTest.getJSONObject("data").getString("amount"));


            JSONObject jsonResponse = new JSONObject(response);
            String responseString = String.valueOf(jsonResponse);
            JSONObject jsonResponseString = new JSONObject(responseString);
            String price = jsonResponseString.getJSONObject("data").getString("amount");
            System.out.println(price);

        } else {
            System.out.println(ANSI_RED + "ERROR GET response code is " + connection.getResponseCode() + ", but should be " + HttpURLConnection.HTTP_OK + ANSI_RESET);
            System.out.println(connection.getResponseMessage());
        }
        return 5000;
    }
}
