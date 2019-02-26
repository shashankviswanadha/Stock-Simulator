package investment.view;

import java.awt.event.ActionListener;

/**
 * This interface represents the Graphical view for the application.
 */
public interface IGView {

  /**
   * Set a listener to the button by adding an action listener.
   *
   * @param listener which has to be added to the JButton
   */
  void setListener(ActionListener listener);

  /**
   * Display this view.
   */
  void display();

  /**
   * Get the portfolio name from the user.
   *
   * @return the portfolio name as a string
   */

  String getPortfolioName();

  /**
   * Display the status to the usr on the screen.
   *
   * @param message which has to be displayed
   */

  void displayStatus(String message);

  /**
   * Get the arguments for buyStock functionality. The purchasing of the stocks is done on one of
   * the existing list of portfolios which is taken as an argument of String array.
   *
   * @param portfolios from which the required one is to be selected
   * @return the portfolio name, amount, commission, date, time and ticker name as a String array.
   */
  String[] getBuyStockArguments(String[] portfolios);

  /**
   * Get the selected portfolio from the existing list of portfolios.
   *
   * @param portfolios from which one of them has to be selected
   * @return the name of the portfolio selected as a String
   */
  String selectPortfolio(String[] portfolios);

  /**
   * Get the arguments for costBasis functionality. The cost basis is calculated on the existing
   * list of portfolios which are taken as an argument of String array.
   *
   * @param portfolios from which the required one is to be selected
   * @return the portfolio name and date as a String array.
   */
  String[] getCostBasisArguments(String[] portfolios);

  /**
   * Get the arguments for WeightedInvestment functionality.
   *
   * @return the portfolio name, date, amount and commission as a String array.
   */
  String[] getWeightedInvestmentArguments();

  /**
   * Get the arguments for EquallyWeighted Investment functionality.
   *
   * @return the portfolio name, date, amount and commission as a String array.
   */
  String[] getEquallyWeightedArgs();

  /**
   * Get the arguments for Dollar Cost Averaging functionality.
   *
   * @return the portfolio name, start date, end date, amount, frequency and commission as a String
   * array.
   */
  String[] gerDollarCostAvg();

  /**
   * Get the selected strategy which the user has chosen.
   *
   * @return the strategy name as a String
   */
  String getSelectedStrategy();

  /**
   * Get the tickers and weights from the user.
   *
   * @return the tickers and weights provided by the user as a 2D array where the first dimension
   * represents the tickers and the second dimension stores the weights
   */
  String[][] getTickersAndWeights();

  /**
   * Get the arguments for applyStrategy functionality.
   *
   * @param portfolios list from which one of them is to be selected
   * @param strategies list from which one of that is to be selected
   * @return the selected portfolio and strategy selected by the user as a String array
   */
  String[] applyStrategyArgs(String[] portfolios, String[] strategies);

  /**
   * Get the name of the file chosen by the user.
   *
   * @return the file name as a String
   */
  String getFileName();

  /**
   * Get the strategy selected by the user.
   *
   * @param strategies from which one has to be chosen
   * @return the selected strategy as a String
   */
  String selectStrategy(String[] strategies);

  String getDataSource();

  String[] getDates(String []portfolios);

  void examine(String message);
}
