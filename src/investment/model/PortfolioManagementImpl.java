package investment.model;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import investment.data.AlphaVantage;
import investment.data.Data;
import investment.data.DataFromFile;
import investment.data.DataSources;

/**
 * This class implements the PortfolioManagement Interface. It is a model for the Portfolio Manager
 * which contains multiple portfolios. Each portfolio in turn has multiple stocks which can be
 * purchased in that portfolio. The datasource for getting the data from all the portfolios could be
 * specified using the setDataSource object. Each object of this class has a list of portfolio
 * objects.
 */
public class PortfolioManagementImpl implements PortfolioManagement {
  private List<Portfolio> portfolios;
  private Data datasource;
  private List<Strategy> strategies;

  /**
   * A constructor for PortfolioImpl class which creates an empty list of portfolios.
   */
  public PortfolioManagementImpl() {
    this.portfolios = new ArrayList<>();
    this.strategies = new ArrayList<>();
  }

  private boolean checkPortfolioExists(String name) {
    for (Portfolio p : portfolios) {
      if (p.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  private Portfolio getPortfolioWithName(String name) {
    for (Portfolio p : portfolios) {
      if (p.getName().equals(name)) {
        return p;
      }
    }
    throw new IllegalArgumentException("No such Portfolio exists");
  }

  /**
   * Creates a portfolio within this Portfolio Manager by taking in the name of the portfolio. If
   * the portfolio name already exists, it is not allowed to use the same name to create another
   * portfolio. There must not be any spaces in between the name given to the Portfolio.
   *
   * @param name of the Portfolio
   * @throws IllegalArgumentException if portfolio already exists or null or empty is passed
   */

  @Override
  public void createPortfolio(String name) throws IllegalArgumentException {
    if (this.checkPortfolioExists(name)) {
      throw new IllegalArgumentException("Portfolio already exists, use another name");
    }
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    portfolios.add(new PortfolioImpl(name));
  }

  /**
   * Purchase the stock in the given portfolio name within the portfolio manager.
   *
   * @param portfolioName in which stock must be bought
   * @param amount        of stock to be purchased
   * @param date          on which the stock is to be purchased
   * @param time          at which the stock should be purchased
   * @param tickername    of the stock to be purchased
   * @throws IllegalArgumentException if any of the parameter passed to this method does not follow
   *                                  the specified constraints
   * @throws NoSuchElementException   if there is no data for the specified tickerName in the data
   *                                  source
   */
  @Override
  public void buyStock(String portfolioName, double amount, String date, String time,
                       String tickername) throws IllegalArgumentException, NoSuchElementException {
    Portfolio p = this.getPortfolioWithName(portfolioName);

    p.addStock(amount, date, time, tickername, this.datasource);
  }

  /**
   * Get the costBasis of the given portfolioName and date. Date must be in the specified format and
   * neither of portfolioName or date must be null or empty.x
   *
   * @param portfolioName for which costBasis is to be retrieved
   * @param date          on which costBasis is to be retrieved
   * @return the totalCostBasis of the given portfolio and date
   */

  @Override
  public double getTotalCostBasis(String portfolioName, String date)
          throws IllegalArgumentException {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getTotalCostBasis(date);
  }

  /**
   * Get the total value of the portfolio for the given date and portfolioName.
   *
   * @param portfolioName for which total value is to be retrieved
   * @param dt            on which the total Value is to be calculated
   * @return the total value for the given date and PortfolioName
   * @throws IllegalArgumentException if the date is null, empty or invalid; portfolioName is null,
   *                                  empty or already existing
   * @throws NoSuchElementException   if there is no data for the given date
   */
  @Override
  public double getTotalValue(String portfolioName, String dt)
          throws IllegalArgumentException, NoSuchElementException {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getTotalValue(dt, this.datasource);
  }

  /**
   * Return the list of all the portfolios in the current portfolioManager.
   *
   * @return the list of all portfolios in this Portfolio Manager
   */
  @Override
  public List<String> getAllPortfolioNames() {
    List<String> lt = new ArrayList<>();
    for (Portfolio p : portfolios) {
      lt.add(p.getName());
    }
    return lt;
  }

  /**
   * Set the data source for the portfolio Manager.
   *
   * @param type of data source to be defined for this Portfolio Manager
   */
  @Override
  public void setDataSource(DataSources type) {
    if (type == DataSources.FILE) {
      this.datasource = new DataFromFile();
    }
    if (type == DataSources.ALPHA) {
      this.datasource = new AlphaVantage();
    }
  }

  /**
   * Return the list of all the tickers of the given portfolio name.
   *
   * @param portfolioName for which list of tickers are required
   * @return the list of tickers as a String array
   */
  @Override
  public String[] getAllTickers(String portfolioName) {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getAllTickerNames();
  }

  @Override
  public String[] getUniqueTickers(String portfolioName) {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getUniqueTickerNames();
  }

  @Override
  public String[] getNumberOfShares(String portfolioName) {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getNumOfShares();
  }

  @Override
  public String[] getAllDatesofPurchase(String portfolioName) {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    return p.getAllDatesofPurchase();
  }

  /**
   * Purchase a stock by considering the commission amount.
   *
   * @param portfolioName in which stock must be bought
   * @param amount        of stock to be purchased
   * @param commission    to be set for the stock
   * @param date          on which stock is required to be purchased
   * @param time          at which the stock is to be purchased
   * @param tickerName    of the stock which needs to be bought
   * @throws IllegalArgumentException if any of the parameter passed to this method does not follow
   *                                  the specified constraints
   * @throws NoSuchElementException   if there is no data for the specified tickerName in the data
   *                                  source
   */
  @Override
  public void buyStockWithCommission(String portfolioName, double amount, double commission,
                                     String date, String time, String tickerName)
          throws IllegalArgumentException, NoSuchElementException {
    Portfolio p = this.getPortfolioWithName(portfolioName);

    p.addStockWithCommission(amount, commission, date, time, tickerName, this.datasource);
  }

  @Override
  public void addStrategy(Strategy s) {
    this.strategies.add(s);
  }

  private Strategy getStrategyWithName(String name) {
    for (Strategy s : this.strategies) {
      if (s.getName().equals(name)) {
        return s;
      }
    }
    throw new IllegalArgumentException("No such strategy exists");
  }

  @Override
  public void saveStrategy(String name) {
    this.getStrategyWithName(name).saveToFile();
  }

  @Override
  public void retrieveStrategy(String filename) {
    this.strategies.add(Strategy.retrieveFromFile(filename));
  }

  @Override
  public void applyStrategy(String sName, String portfolioName) throws IllegalArgumentException {
    this.getStrategyWithName(sName).apply(this, portfolioName);
  }

  @Override
  public List<String> getAllStrategyNames() {
    List<String> dummy = new ArrayList<>();
    for (Strategy s : this.strategies) {
      dummy.add(s.getName());
    }
    return dummy;
  }

  @Override
  public void savePortfolio(String portfolioName) {
    Portfolio p = this.getPortfolioWithName(portfolioName);
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter(portfolioName + ".json")) {
      gson.toJson(p, writer);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not save to file");
    }
  }

  @Override
  public void retrievePortfolio(String portfolioName) {
    Gson gson = new Gson();
    if (this.checkPortfolioExists(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists, use another name for the file");
    }
    if (portfolioName == null || portfolioName.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    try (Reader reader = new FileReader(portfolioName)) {
      Portfolio port = gson.fromJson(reader, PortfolioImpl.class);
      portfolios.add(port);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read file");
    }
  }

}