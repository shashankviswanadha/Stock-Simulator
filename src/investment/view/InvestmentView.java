package investment.view;

/**
 * This interface represents the view for the Investment Manager.
 */

public interface InvestmentView {

  /**
   * Get the choice from the user in String format. The choice could be one of the options from 1-9
   * or Q for quit. The user will be asked to enter the valid input until he provides valid one.
   *
   * @return the choice provided by the user as a String
   */
  String getChoice();

  /**
   * Get the Portfolio Name from the user in String format. If the portfolio name does not exist,
   * the or invalid, the user will be asked to provide the valid input.
   *
   * @return the Portfolio Name provided by the user as a String
   */
  String getPortfolioName();

  /**
   * Get the Amount from the user in String format. The amount has to be any real valued number.
   *
   * @return the Amount provided by the user as a String
   */
  String getAmount();

  /**
   * Get the Date from the user in String format. The date has to be in YYYY-MM-DD format.
   *
   * @return the Date provided by the user as a String
   */
  String getDate();

  /**
   * Get the Time from the user in String format. The time has to be in HH:MM:SS format.
   *
   * @return the Time provided by the user as a String
   */
  String getTime();

  /**
   * Get the Ticker Name from the user in String format. The ticker name has to be one of the stock
   * which is present in the NYSE stock exchange.
   *
   * @return the Ticker Name provided by the user as a String
   */
  String getTickerName();

  /**
   * Get the Data Source from the user in String format.
   *
   * @return the Data Source Name provided by the user as a String
   */
  String getDataSource();

  /**
   * Get a string from the controller and append to the output.
   *
   * @param st String to be appended(viewed) to the user
   */
  void appendToView(String st);

  /**
   * Get the tickers as an array of Strings. The tickers should be comma separated without any
   * spaces in between them.
   *
   * @return the tickers provided by the user as a String array
   */
  String[] getTickers();

  /**
   * Get the weights as an array of Strings from user. The weights must be comma separated without
   * any spaces in between them.
   *
   * @return the weights provided by the user as a String array
   */
  String[] getWeights();

  /**
   * Get the frequency from the user in String format.
   *
   * @return the frequency provided by the user as a String
   */
  String getFrequency();

  /**
   * Get the End date from the user in String format.
   *
   * @return the End date provided by the user as a String
   */
  String getEndDate();

  /**
   * Get the Commission from the user in String format.
   *
   * @return the Commission provided by the user as a String
   */
  String getCommission();

  /**
   * Get the selected strategy from the user given a list of options.
   *
   * @return the strategy selected as a String
   */
  String selectStrategy();

  /**
   * Get the name of the strategy from the user in order to create a new one.
   *
   * @return the name of the strategy provided by the user in String format
   */
  String getStrategyName();
}
