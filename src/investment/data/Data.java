package investment.data;

import java.util.NoSuchElementException;

/**
 * This interface represents the data of this application. It has one method to get the current
 * price of a particular stock, given the tickerSymbol and date on which it is to be purchased.
 */
public interface Data {

  /**
   * Get the current price of the stock by taking in the tickerSymbol and date.
   *
   * @param tickerSymbol of the stock
   * @param date         on which the price is to be retrieved
   * @return the current price of the stock on this date as a double
   * @throws IllegalArgumentException if it is not possible to open the file or retrieve the data
   *                                  from the datasource
   * @throws NoSuchElementException   if there exists a file but there is no data for the ticker
   *                                  symbol in the file
   */
  double getCurrentPrice(String tickerSymbol, String date)
          throws IllegalArgumentException, NoSuchElementException;

}
