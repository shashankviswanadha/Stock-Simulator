package investment.model;

/**
 * This class implements the Weighted Investment Strategy. For this strategy, the name of the
 * strategy, date, time, amount, commission, list of tickers and weights would be taken and the
 * strategy could be implemented on a required portfolio.
 */

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class WeightedInvestment implements Strategy {

  private String date;
  private String time;
  private double amount;
  private String[] tickers;
  private double[] weights;
  private double commission;
  private String name;

  /**
   * A constructor for WeightedInvestment strategy. The weights of the strategy must be provided
   * such that the sum comes to 100. It would throw an IllegalArgumentException if the sum is less
   * than 100. Moreover, the strategy names cannot be empty or null. The purchase of stocks entered
   * by the user would be done at time 12:00:00.
   *
   * @param date       on which the stocks are to be purchased
   * @param amount     of purchase
   * @param commission for this investment
   * @param tickers    which are to be purchased in this portfolio for this strategy
   * @param weights    of the tickers
   * @throws IllegalArgumentException if any of the given parameter for this constructor is invalid
   */
  public WeightedInvestment(String name, String date, double amount, double commission,
                            String[] tickers, String[] weights) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Strategy name cannot be empty");
    }
    this.name = name;

    this.weights = new double[weights.length];
    for (int i = 0; i < weights.length; i++) {
      try {
        this.weights[i] = Double.parseDouble(weights[i]) / 100.00;
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid percentage input.");
      }
    }
    if (Arrays.stream(this.weights).sum() > 1.00) {
      throw new IllegalArgumentException("Sum of percentages cannot be greater than 100.");
    }
    if (weights.length != tickers.length) {
      throw new IllegalArgumentException("Invalid input: Mention percentages for each of the "
              + "stocks you want to buy");
    }
    this.date = date;
    this.time = "12:00:00";
    this.amount = amount;
    this.tickers = tickers;
    this.commission = commission;
  }

  @Override
  public void apply(PortfolioManagement m, String portfolioName) {
    try {
      m.createPortfolio(portfolioName);
    } catch (IllegalArgumentException e) {
      if (!e.getLocalizedMessage().equals("Portfolio already exists, use another name")) {
        throw e;
      }
    }
    for (int i = 0; i < this.tickers.length; i++) {
      try {
        m.buyStockWithCommission(portfolioName, this.amount * this.weights[i],
                this.commission, this.date,
                this.time, this.tickers[i]);
      } catch (NoSuchElementException e) {
        //do nothing
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void saveToFile() {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter("WI_" + this.name + ".json")) {
      gson.toJson(this, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

