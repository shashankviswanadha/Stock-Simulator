package investment.model;

import java.util.List;
import java.util.NoSuchElementException;

import investment.data.DataSources;

/**
 * This is the interface of Portfolio Management. It provides an option to create multiple
 * portfolios, purchase stocks for a given portfolio, examine the contents of a portfolio, get the
 * total cost basis of a given portfolio and date, get the total value of the given portfolio given
 * a name of portfolio and date. It also has the option to get the names of all the portfolio names
 * in the Portfolio Manager.
 */
public interface PortfolioManagement {

  /**
   * Create a portfolio in the Portfolio Manager given the name of the portfolio. The portfolio name
   * is not allowed to have spaces in between the names. The portfolio name cannot be null or empty.
   * If a null object is sent as the portfolio name, an IllegalArgumentException would be thrown. If
   * there is already an existing portfolio with the given name, an Exception would be thrown
   * hinting that a portfolio with the same name is already existing. Thus, this application does
   * not allow to have duplicate names of portfolios.
   *
   * @param name of the Portfolio
   * @throws IllegalArgumentException if portfolio name is null or empty
   */
  void createPortfolio(String name) throws IllegalArgumentException;

  /**
   * Purchase a stock in the given portfolio by taking in the portfolioName, amount, date, time and
   * tickerName. The portfolioName has to be the one which is already created. The amount has to be
   * non-negative. The date format must be in YYYY-MM-DD. The time must be in hh:mm:ss format. If
   * the tickerName is not available in the datasource, an exception would be thrown.
   *
   * @param portfolioName in which stock must be bought
   * @param amount        of stock to be purchased
   * @param date          on which the stock is to be purchased
   * @param time          at which the stock should be purchased
   * @param tickerName    of the stock
   * @throws IllegalArgumentException if portfolioName is null or empty, amount is less than or
   *                                  equal to zero, date is null or empty, time is null or empty,
   *                                  ticker name is null or empty or if there is no ticker symbol
   *                                  available, if the date or time is not in the specified format,
   *                                  if the date entered is a holiday or a weekend, time is not
   *                                  between 9:00:00 to 15:59:59 inclusive, if amount is too low to
   *                                  buy at least one share
   * @throws NoSuchElementException   if for a valid date and time, there is no data in the
   *                                  datasource for the given stock
   */

  void buyStock(String portfolioName, double amount, String date, String time,
                String tickerName) throws IllegalArgumentException, NoSuchElementException;

  /**
   * Takes in a PortfolioName and date on which costBasis is to be calculated and returns the total
   * costBasis of the given portfolio. The portfolio name should not be null or empty. It has to be
   * one of the existing portfolios otherwise an exception would be thrown. The date has to be
   * entered in YYYY-MM-DD format. If the date is null, empty or in other format specified, an
   * exception would be thrown. If the date is later than the purchase date of the stock, then all
   * the stocks would be returned. If the date is before the purchase date, a value of zero would be
   * returned.
   *
   * @param portfolioName for which costBasis is to be retrieved
   * @param date          on which costBasis is to be retrieved
   * @return the total costBasis of the given Portfolio
   * @throws IllegalArgumentException if the portfolioName is null or empty, Date is null, empty or
   *                                  invalid.
   */

  double getTotalCostBasis(String portfolioName, String date) throws IllegalArgumentException;

  /**
   * Takes in a portfolioName and date on which the total value of the Portfolio is to be calculated
   * on the given date. The portfolioName should not be null or empty. IT has to be one of the
   * existing portfolios in the Portfolio Manager. In either case, if the portfolioName is not as
   * expected, an IllegalArgumentException would be thrown. The date must be specified in YYYY-MM-DD
   * format. If it is given in any other format, then an exception would be thrown. The date cannot
   * be null or empty. If the given date is before the purchase date of any stock, then a value of
   * zero would be returned. If for a given date, there is no value existing in the data source,
   * then NoSuchElementException would be thrown.
   *
   * @param portfolioName for which total value is to be retrieved
   * @param date          on which total value is required
   * @return the total value for the given portfolio for the given date
   * @throws IllegalArgumentException if the portfolioName is null, empty or if it is already
   *                                  existing. If the date is null, empty or invalid.
   * @throws NoSuchElementException   if for the given valid date format, the value is not found in
   *                                  the data source
   */

  double getTotalValue(String portfolioName, String date) throws IllegalArgumentException,
          NoSuchElementException;

  /**
   * Get all the portfolio names in the portfolio manager in list format.
   *
   * @return the list of all portfolio names
   */
  List<String> getAllPortfolioNames();

  /**
   * Set the datasource of this Portfolio Manager.
   *
   * @param type of data source to be defined for this Portfolio Manager
   */

  void setDataSource(DataSources type);

  /**
   * Get the list of all the tickers of a particular portfolio within the Portfolio Manager.
   *
   * @param portfolioName for which list of tickers are required
   * @return the list of all the tickers as a String array
   */
  String[] getAllTickers(String portfolioName);

  /**
   * Gets all the unique ticker symbols in a portfolio with the given name.
   *
   * @param portfolioName the name of the Portfolio.
   * @return the list of all unique tickers in that portfolio.
   */
  String[] getUniqueTickers(String portfolioName);

  /**
   * Gets all the number of shares for each ticker in a portfolio with the given name.
   *
   * @param portfolioName the name of the Portfolio.
   * @return the list of number of shares per ticker in that portfolio.
   */
  String[] getNumberOfShares(String portfolioName);

  /**
   * Gets all the date of purchase for each ticker in a portfolio with the given name.
   *
   * @param portfolioName the name of the Portfolio.
   * @return the list of date of purchase for ticker in that portfolio.
   */
  String[] getAllDatesofPurchase(String portfolioName);

  /**
   * Purchase a stock in the given portfolio by taking in the portfolioName, amount, commission,
   * date, time and tickerName. The portfolioName has to be the one which is already created. The
   * amount has to be non-negative. The date format must be in YYYY-MM-DD. The time must be in
   * hh:mm:ss format. If the tickerName is not available in the datasource, an exception would be
   * thrown.
   *
   * @param portfolioName in which stock must be bought
   * @param amount        of stock to be purchased
   * @param commission    amount for the stock
   * @param date          on which the stock is to be purchased
   * @param time          at which the stock should be purchased
   * @param tickerName    of the stock
   * @throws IllegalArgumentException if portfolioName is null or empty, amount is less than or
   *                                  equal to zero, date is null or empty, time is null or empty,
   *                                  ticker name is null or empty or if there is no ticker symbol
   *                                  available, if the date or time is not in the specified format,
   *                                  if the date entered is a holiday or a weekend, time is not
   *                                  between 9:00:00 to 15:59:59 inclusive, if amount is too low to
   *                                  buy at least one share
   * @throws NoSuchElementException   if for a valid date and time, there is no data in the
   *                                  datasource for the given stock
   */
  void buyStockWithCommission(String portfolioName, double amount, double commission, String date,
                              String time, String tickerName)
          throws IllegalArgumentException, NoSuchElementException;

  /**
   * Add a given strategy to the portfolio.
   *
   * @param s strategy to be added to this portfolio
   */
  void addStrategy(Strategy s);

  /**
   * Save the given investment strategy to a file by taking in the name of the strategy.
   *
   * @param name of the strategy to be saved
   */
  void saveStrategy(String name);

  /**
   * Retrieve an investment strategy from a file by taking in the name of the strategy.
   *
   * @param fileName from which the strategy needs to be retrieved
   */
  void retrieveStrategy(String fileName);

  /**
   * Apply the strategy to a portfolio.
   *
   * @param sName         the name of the strategy
   * @param portfolioName on which the strategy has to be applied
   * @throws IllegalArgumentException if either of the strategy name or portfolio name is invalid
   */

  void applyStrategy(String sName, String portfolioName) throws IllegalArgumentException;

  /**
   * Get the list of all the strategy names.
   *
   * @return all the strategy names as a list of strings
   */
  List<String> getAllStrategyNames();

  /**
   * Save a portfolio to file.
   *
   * @param portfolioName which has to be saved
   */
  void savePortfolio(String portfolioName);

  /**
   * Retrieve a portfolio from a file.
   *
   * @param portfolioName which has to be retrieved
   */
  void retrievePortfolio(String portfolioName);
}


