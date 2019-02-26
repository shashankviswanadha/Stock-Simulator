package investment.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import investment.data.Data;

/**
 * This class implements the portfolio Interface. Each portfolio has an option to add a stock, get
 * the total cost basis of this portfolio, get the total value of the portfolio on a specified date
 * and get the name of this portfolio. Each portfolio has a unique name in String format and a list
 * of stocks within the portfolio.
 */
class PortfolioImpl implements Portfolio {

  private List<Stock> stocks;

  private String name;

  /**
   * A constructor for PortfolioImpl class which initializes the list of stocks to be empty. It
   * takes in a unique name of the portfolio and assigns that name to this portfolio.
   *
   * @param name of this portfolio
   */
  PortfolioImpl(String name) {
    this.stocks = new ArrayList<Stock>();
    this.name = name;
  }

  /**
   * Purchase a stock in this portfolio by taking in the amount, date, time, tickerName and
   * dataSource. The amount cannot be less than or equal to zero, date must be in specified format
   * and not null or empty, time must be only during the business hours, ticker name should be the
   * one which is already existing in the datasource and datasource is the one from which the stock
   * data is received.
   *
   * @param amount     of stock to be purchased
   * @param date       on which the stock is to be purchased
   * @param time       at which stock is to be purchased
   * @param tickerName of the stock
   * @param datasource from which the data of the stock is to be accessed
   * @throws IllegalArgumentException if any of the parameters sent is invalid
   * @throws NoSuchElementException   if there is no data for the requested stock in the datasource
   */
  @Override
  public void addStock(double amount, String date, String time,
                       String tickerName, Data datasource) throws IllegalArgumentException,
          NoSuchElementException {

    this.stocks.add(new Stock(tickerName, amount, date, time, datasource));
  }

  /**
   * Get the total costBasis of the portfolio for a specified date.
   *
   * @param date on which costBasis is to be retrieved
   * @return the totalCostBasis of the portfolio on the given date as a double
   */
  @Override
  public double getTotalCostBasis(String date) {
    double costBasis = 0.0;
    for (Stock stock : stocks) {
      costBasis += stock.getTotalCostBasis(date);
    }
    return costBasis;
  }

  /**
   * Get the total value of the portfolio for a specified date.
   *
   * @param date on which total value is to be retrieved
   * @return the total value of the portfolio on the given date as a double
   * @throws IllegalArgumentException if the date is null or empty or not in specified format or
   *                                  datasource is invalid
   * @throws NoSuchElementException   if for the given date there is no data in the datasource for a
   *                                  particular stock
   */
  @Override
  public double getTotalValue(String date, Data datasource) throws IllegalArgumentException,
          NoSuchElementException {
    double value = 0.0;
    for (Stock stock : stocks) {
      value += stock.getHoldings(date, datasource);
    }
    return value;
  }

  /**
   * Get the name of this portfolio.
   *
   * @return the name of the portfolio in String format
   */

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Get the list of all the ticker names for this portfolio.
   *
   * @return the ticker names in this
   */
  @Override
  public String[] getUniqueTickerNames() {
    String[] out = new String[this.stocks.size()];
    for (int i = 0; i < this.stocks.size(); i++) {
      out[i] = this.stocks.get(i).getTickerSymbol();
    }
    Set<String> mySet = new HashSet<String>(Arrays.asList(out));
    String[] tickers = mySet.toArray(new String[mySet.size()]);
    if (this.stocks.size() == 0) {
      String[] temp = {""};
      return temp;
    }
    return tickers;
  }

  /**
   * Get the list of all ticker names.
   *
   * @return all the ticker names as a String array
   */
  @Override
  public String[] getAllTickerNames() {
    String[] out = new String[this.stocks.size()];
    for (int i = 0; i < this.stocks.size(); i++) {
      out[i] = this.stocks.get(i).getTickerSymbol();
    }
    if (this.stocks.size() == 0) {
      System.out.println("dksd");
      String[] temp = {""};
      return temp;
    }
    return out;
  }

  /**
   * Gets the number of shares per ticker in the portfolio as a list.
   *
   * @return the number of shares per ticker in the portfolio as a list.
   */

  @Override
  public String[] getNumOfShares() {
    String[] out = new String[this.stocks.size()];
    for (int i = 0; i < this.stocks.size(); i++) {
      out[i] = this.stocks.get(i).getNumOfShares();
    }
    if (this.stocks.size() == 0) {
      String[] temp = {""};
      return temp;
    }
    return out;
  }

  /**
   * Gets the date of purchase of all the  tickers in the portfolio as a list.
   *
   * @return the date of purchase of all the  tickers in the portfolio as a list.
   */
  @Override
  public String[] getAllDatesofPurchase() {
    String[] out = new String[this.stocks.size()];
    for (int i = 0; i < this.stocks.size(); i++) {
      out[i] = this.stocks.get(i).getDateOfPurchase();
    }
    if (this.stocks.size() == 0) {
      String[] temp = {""};
      return new String[0];
    }
    return out;
  }

  /**
   * Add a stock to this portfolio by taking in the amount, commission, date, time, tickerName and
   * datasource.
   *
   * @param amount     of stock to be purchased
   * @param commission to be charged for this stock
   * @param date       on which the stock is to be purchased
   * @param time       at which stock is to be purchased
   * @param tickerName of the stock
   * @param datasource from which the data of the stock is to be accessed
   * @throws IllegalArgumentException if the amount is less than or equal to zero, date is not in
   *                                  YYYY-MM-DD format null or empty, time is not in between
   *                                  9:00:00 to 3:59:59 in valid HH:MM:SS
   * @throws NoSuchElementException   if there is no data in the datasource for the given stock
   */
  @Override
  public void addStockWithCommission(double amount, double commission, String date, String time,
                                     String tickerName, Data datasource)
          throws IllegalArgumentException, NoSuchElementException {
    this.stocks.add(new Stock(tickerName, amount, commission, date, time, datasource));
  }
}
