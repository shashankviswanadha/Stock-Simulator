package investment.model;

import java.util.NoSuchElementException;

import investment.data.Data;

/**
 * This interface represents a single portfolio. It has the option to add a stock, get the total
 * cost basis of a stock, get the total value of the stock and to retrieve the name of the
 * portfolio.
 */
interface Portfolio {
  /**
   * Add a stock to this portfolio by taking in the amount, date, time, tickerName and datasource.
   *
   * @param amount     of stock to be purchased
   * @param date       on which the stock is to be purchased
   * @param time       at which stock is to be purchased
   * @param tickerName of the stock
   * @param datasource from which the data of the stock is to be accessed
   * @throws IllegalArgumentException if the amount is less than or equal to zero, date is not in
   *                                  YYYY-MM-DD format null or empty, time is not in between
   *                                  9:00:00 to 3:59:59 in valid HH:MM:SS
   * @throws NoSuchElementException   if there is no data in the datasource for the given stock
   */
  void addStock(double amount, String date, String time,
                String tickerName, Data datasource) throws IllegalArgumentException,
          NoSuchElementException;

  /**
   * Return the total costBasis of this stock.
   *
   * @param date on which costBasis is to be retrieved
   * @return the totalCostBasis of the portfolio as a double
   */
  double getTotalCostBasis(String date);

  /**
   * Get the total value of the portfolio by taking in the date.
   *
   * @param date       on which the value is to be calculated
   * @param datasource from which the stock value is to be retrieved
   * @return the total value on that particular date as a double
   * @throws IllegalArgumentException if the date is not in specified format, null or empty,
   *                                  datasource is null
   * @throws NoSuchElementException   if there is no data for the given stock on the required date
   */
  double getTotalValue(String date, Data datasource) throws IllegalArgumentException,
          NoSuchElementException;

  /**
   * Return the name of this portfolio as a string.
   *
   * @return the name of this portfolio
   */
  String getName();

  /**
   * Gets all the unique ticker symbols in the portfolio.
   *
   * @return the list of all unique tickers in that portfolio.
   */
  String[] getUniqueTickerNames();

  /**
   * Gets all the ticker symbols in the portfolio.
   *
   * @return the list of all tickers in that portfolio.
   */
  String[] getAllTickerNames();

  /**
   * Gets the number of shares per ticker in the portfolio as a list.
   *
   * @return the number of shares per ticker in the portfolio as a list.
   */
  String[] getNumOfShares();

  /**
   * Gets the date of purchase of all the  tickers in the portfolio as a list.
   *
   * @return the date of purchase of all the  tickers in the portfolio as a list.
   */
  String[] getAllDatesofPurchase();

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
  void addStockWithCommission(double amount, double commission, String date, String time,
                              String tickerName, Data datasource) throws IllegalArgumentException,
          NoSuchElementException;

}
