import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        CoinbaseController coinbaseController = new CoinbaseController();

        System.out.println("BTC = " + coinbaseController.getBTCPriceToBuyInUSD() + " USD");
    }
}