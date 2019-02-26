package investment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import investment.data.DataSources;

/**
 * This class represents a mock Model for the Portfolio Management class.
 */

public class MockModel implements PortfolioManagement {
  String uniqeCode1;
  double uniqueCode2;
  double uniqueCode3;

  /**
   * A constructor for MockModel which takes in three unique codes.
   */

  public MockModel(String unc1, double unc2, double unc3) {
    this.uniqeCode1 = unc1;
    this.uniqueCode2 = unc2;
    this.uniqueCode3 = unc3;
  }

  @Override
  public void createPortfolio(String name) throws IllegalArgumentException {
    //Do nothing.
  }

  @Override
  public void buyStock(String portfolioName, double amount, String date, String time,
                       String tickername) throws IllegalArgumentException, NoSuchElementException {
    //Do nothing.
  }

  /**
   * Assign the unique code to this method and append the unique code to out in order to test the
   * functionality of the controller.
   *
   * @param portfolioName for which the contents are to be retrieved
   * @param date          on which the costBasis is to be calculated
   * @return the uniqueCode as a double
   */
  @Override
  public double getTotalCostBasis(String portfolioName, String date)
          throws IllegalArgumentException {
    return this.uniqueCode2;
  }

  /**
   * Assign the unique code to this method and append the unique code to out in order to test the
   * functionality of the controller.
   *
   * @param portfolioName for which the contents are to be retrieved
   * @param date          on which the costBasis is to be calculated
   * @return the uniqueCode as a double
   */
  @Override
  public double getTotalValue(String portfolioName, String date) throws IllegalArgumentException,
          NoSuchElementException {
    return this.uniqueCode3;
  }

  @Override
  public List<String> getAllPortfolioNames() {
    List<String> dummy = new ArrayList<>();
    dummy.add(this.uniqeCode1);
    return dummy;
  }

  @Override
  public void setDataSource(DataSources type) {
    //Do nothing.
  }

  @Override
  public String[] getAllTickers(String portfolioName) {
    return new String[0];
  }

  @Override
  public String[] getUniqueTickers(String portfolioName) {
    return new String[0];
  }

  @Override
  public String[] getNumberOfShares(String portfolioName) {
    return new String[0];
  }

  @Override
  public String[] getAllDatesofPurchase(String portfolioName) {
    return new String[0];
  }

  @Override
  public void buyStockWithCommission(String portfolioName, double amount, double commission,
                                     String date, String time, String tickerName)
          throws IllegalArgumentException, NoSuchElementException {
    //Do nothing.
  }

  @Override
  public void addStrategy(Strategy s) {
    //Do nothing.
  }

  @Override
  public void saveStrategy(String name) {
    // Do nothing.
  }

  @Override
  public void retrieveStrategy(String filename) {
    // Do nothing.
  }

  @Override
  public void applyStrategy(String sName, String portfolioName) throws IllegalArgumentException {
    //Do nothing.
  }

  @Override
  public List<String> getAllStrategyNames() {
    return null;
  }

  @Override
  public void savePortfolio(String portfolioName) {
    //Do nothing.
  }

  @Override
  public void retrievePortfolio(String portfolioName) {
    //Do nothing.
  }
}
