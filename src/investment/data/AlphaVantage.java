package investment.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This class retrieves the data of a stock from the Alpha Vantage API. It has six API keys and each
 * time a call is made to Alpha Vantage, these keys would be selected in round-robin fashion. Once
 * the data for a particular stock is retrieved, it would be stored in a local cache which is a
 * HashMap.
 */
public class AlphaVantage implements Data {
  private Map<String, StringBuilder> cache;
  private String[] apiKeys;
  private Queue<String> queue;
  private int cacheSize;
  private int count;

  /**
   * A constructor for AlphaVantage class. It initializes the cache keys and size of the cache.
   */
  public AlphaVantage() {
    this.queue = new LinkedList<>();
    this.cache = new HashMap<>();
    this.apiKeys = new String[6];
    this.apiKeys[0] = "1CN6ZV0ZF8WKAENN";
    this.apiKeys[1] = "PWLUY5UI4QAQZNB1";
    this.apiKeys[2] = "56CC4556RG01B8UF";
    this.apiKeys[3] = "W0M1JOKC82EZEQA8";
    this.apiKeys[4] = "HUWMAW6CD4UJZ8VO";
    this.apiKeys[5] = "ZDT0VELZ7KW7QXYS";
    this.cacheSize = 100;
    this.count = 0;
  }

  private void callAPI(String stockSymbol) {
    URL url = null;
    String apiKey = this.apiKeys[this.count % 6];
    this.count++;
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    if (this.queue.size() == this.cacheSize) {
      String removeElement = this.queue.remove();
      this.cache.remove(removeElement);
    } else {
      this.queue.add(stockSymbol);
      this.cache.put(stockSymbol, output);
    }

  }

  private double getPrice(String date, String data) throws IllegalArgumentException,
          NoSuchElementException {
    String[] lines = data.split("\n");
    for (String line : lines) {
      String[] dataOnADay = line.split(",");
      if (dataOnADay[0].equals(date)) {
        try {
          double out = Double.parseDouble(dataOnADay[4]);
          return out;
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Bad Data.");
        }
      }
    }

    throw new NoSuchElementException("There is no such entry in file");
  }

  @Override
  public double getCurrentPrice(String tickerSymbol, String date) throws IllegalArgumentException,
          NoSuchElementException {
    StringBuilder data;
    if (!this.queue.contains(tickerSymbol)) {
      this.callAPI(tickerSymbol);
    }
    data = this.cache.get(tickerSymbol);
    return this.getPrice(date, data.toString());
  }
}
