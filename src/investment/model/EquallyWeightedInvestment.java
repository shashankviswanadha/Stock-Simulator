package investment.model;

/**
 * This class implements the Weighted Investment Strategy. For this strategy, the name of the
 * strategy, date, time, amount, commission, would be taken and the strategy could be implemented on
 * a required portfolio.
 */

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class EquallyWeightedInvestment implements Strategy {

  private String date;
  private String time;
  private double amount;
  private double commission;
  private String name;

  /**
   * This is a constructor for performing EquallyWeighted investment. The strategy name cannot be
   * empty or null. The purchase of the stocks is done at time 12:00:00.
   *
   * @param date       on which the stocks has to be purchased
   * @param amount     of purchase
   * @param commission for this investment
   */
  public EquallyWeightedInvestment(String name, String date, double amount,
                                   double commission) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Strategy name cannot be empty");
    }
    this.name = name;
    this.date = date;
    this.time = "12:00:00";
    this.amount = amount;
    this.commission = commission;
  }

  @Override
  public void apply(PortfolioManagement m, String portfolioName) throws IllegalArgumentException {
    String[] tickers = m.getUniqueTickers(portfolioName);
    double wt = 1.0 / tickers.length;
    for (int i = 0; i < tickers.length; i++) {
      try {
        m.buyStockWithCommission(portfolioName, this.amount * wt,
                this.commission, this.date,
                this.time, tickers[i]);
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException(e.getLocalizedMessage());
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
    String json = gson.toJson(this);

    try (FileWriter writer = new FileWriter("EI_" + this.name + ".json")) {
      gson.toJson(this, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
