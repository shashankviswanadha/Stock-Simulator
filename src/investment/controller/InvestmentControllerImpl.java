package investment.controller;

import java.util.NoSuchElementException;

import investment.data.DataSources;
import investment.model.DollarCostAveraging;
import investment.model.EquallyWeightedInvestment;
import investment.model.PortfolioManagement;
import investment.model.Strategy;
import investment.model.WeightedInvestment;
import investment.view.InvestmentView;

/**
 * This class implements the InvestmentController Interface.It takes any Readable interface object
 * as an input and transmits out an Appendable interface object. This controller takes in a model
 * upon which it performs all the operations.
 */

public class InvestmentControllerImpl implements InvestmentController {

  private InvestmentView view;
  private PortfolioManagement model;

  /**
   * A constructor for the InvestmentControllerImpl class. It takes the model and view as the
   * arguments.
   *
   * @param model for this implementation
   * @param view  for this implementation
   */
  public InvestmentControllerImpl(PortfolioManagement model, InvestmentView view) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("View or Model cannot be null");
    }
    this.view = view;
    this.model = model;
  }

  private boolean quit(String st) {
    return st.toLowerCase().equals("q");
  }

  /**
   * It provides different options to the user where the user can perform the operations. Entering a
   * "Q" or "q" quits the application. 1 - Creates a new portfolio 2 - Buys a Stock 3 - Examine
   * Portfolio 4 - Get Cost Basis of a Portfolio 5 - Get current value of a portfolio 6 - Get all
   * portfolio names 7.Invest in weighted stocks 8 - Invest equally in existing stocks 9.Dollar Cost
   * Averaging.
   *
   * @throws IllegalStateException    if the controller is unable to read input or transmit output
   * @throws IllegalArgumentException if the datasource or model is null
   */
  @Override
  public void execute()
          throws IllegalStateException, IllegalArgumentException {
    String portfolioName;
    DataSources dSource = setDataSourceForModel();
    model.setDataSource(dSource);
    Strategy cmd = null;

    while (true) {
      String in = this.view.getChoice();
      switch (in) {
        case "q":
        case "Q":
          this.view.appendToView("Quitting the application");
          return;
        case "1":
          portfolioName = this.view.getPortfolioName();
          if (this.quit(portfolioName)) {
            this.addToOut("Quitting the application");
            return;
          }
          createPortfolio(model, portfolioName);
          break;
        case "2":
          portfolioName = this.view.getPortfolioName();
          if (this.quit(portfolioName)) {
            this.addToOut("Quitting the application");
            return;
          }
          String amt = this.view.getAmount();
          if (this.quit(amt)) {
            this.addToOut("Quitting the application");
            return;
          }
          double amount;
          try {
            amount = Double.parseDouble(amt);
          } catch (NumberFormatException e) {
            this.view.appendToView("Amount should be a number\n");
            continue;
          }
          String cm = this.view.getCommission();
          if (this.quit(cm)) {
            this.addToOut("Quitting the application");
            return;
          }
          double commission;
          try {
            commission = Double.parseDouble(cm);
          } catch (NumberFormatException e) {
            this.view.appendToView("Commission amount should be a number\n");
            continue;
          }
          String date = this.view.getDate();
          if (this.quit(date)) {
            this.addToOut("Quitting the application");
            return;
          }
          String time = this.view.getTime();
          if (this.quit(time)) {
            this.addToOut("Quitting the application");
            return;
          }
          String tickerName = this.view.getTickerName();
          if (this.quit(tickerName)) {
            this.addToOut("Quitting the application");
            return;
          }
          buyStockWithCommission(model, portfolioName, amount, commission, date, time, tickerName);
          break;
        case "3":
          portfolioName = this.view.getPortfolioName();
          if (this.quit(portfolioName)) {
            this.addToOut("Quitting the application");
            return;
          }
          examinePortfolio(model, portfolioName);
          break;
        case "4":
          portfolioName = this.view.getPortfolioName();
          if (this.quit(portfolioName)) {
            this.addToOut("Quitting the application");
            return;
          }
          String dt = this.view.getDate();
          if (this.quit(dt)) {
            this.addToOut("Quitting the application");
            return;
          }
          getCostBasis(model, portfolioName, dt);
          break;
        case "5":
          portfolioName = this.view.getPortfolioName();
          if (this.quit(portfolioName)) {
            this.addToOut("Quitting the application");
            return;
          }
          date = this.view.getDate();
          if (this.quit(date)) {
            this.addToOut("Quitting the application");
            return;
          }
          getValue(model, portfolioName, date);
          break;
        case "6":
          this.addToOut("" + model.getAllPortfolioNames() + "\n");
          break;
        case "7":
          String ch = this.view.selectStrategy();
          switch (ch) {
            case "1":
              String name = this.view.getStrategyName();
              if (this.quit(name)) {
                this.addToOut("Quitting the application");
                return;
              }
              amt = this.view.getAmount();
              if (this.quit(amt)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                amount = Double.parseDouble(amt);
              } catch (NumberFormatException e) {
                this.view.appendToView("Amount should be a number\n");
                continue;
              }
              cm = this.view.getCommission();
              if (this.quit(cm)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                commission = Double.parseDouble(cm);
              } catch (NumberFormatException e) {
                this.view.appendToView("Commission amount should be a number\n");
                continue;
              }
              date = this.view.getDate();
              if (this.quit(date)) {
                this.addToOut("Quitting the application");
                return;
              }
              String[] tickers = view.getTickers();
              String[] weights = view.getWeights();
              try {
                cmd = new WeightedInvestment(name, date, amount, commission, tickers, weights);
                this.model.addStrategy(cmd);
                this.addToOut("Strategy " + name + " created successfully.\n");
              } catch (IllegalArgumentException e) {
                this.addToOut(e.getLocalizedMessage() + "\n");
              }
              break;
            case "2":
              name = this.view.getStrategyName();
              if (this.quit(name)) {
                this.addToOut("Quitting the application");
                return;
              }
              amt = this.view.getAmount();
              if (this.quit(amt)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                amount = Double.parseDouble(amt);
              } catch (NumberFormatException e) {
                this.view.appendToView("Amount should be a number\n");
                continue;
              }
              cm = this.view.getCommission();
              if (this.quit(cm)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                commission = Double.parseDouble(cm);
              } catch (NumberFormatException e) {
                this.view.appendToView("Commission amount should be a number\n");
                continue;
              }
              date = this.view.getDate();
              if (this.quit(date)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                cmd = new EquallyWeightedInvestment(name, date, amount, commission);
                model.addStrategy(cmd);
                this.addToOut("Strategy " + name + " created successfully.\n");
              } catch (IllegalArgumentException e) {
                this.addToOut(e.getLocalizedMessage() + "\n");
              }
              break;
            case "3":
              name = this.view.getStrategyName();
              String startDate = this.view.getDate();
              String endDate = this.view.getEndDate();
              amt = this.view.getAmount();
              if (this.quit(amt)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                amount = Double.parseDouble(amt);
              } catch (NumberFormatException e) {
                this.view.appendToView("Amount should be a number\n");
                continue;
              }
              cm = this.view.getCommission();
              if (this.quit(cm)) {
                this.addToOut("Quitting the application");
                return;
              }
              try {
                commission = Double.parseDouble(cm);
              } catch (NumberFormatException e) {
                this.view.appendToView("Commission amount should be a number\n");
                continue;
              }
              String freq = this.view.getFrequency();
              if (this.quit(freq)) {
                this.addToOut("Quitting the application");
                return;
              }
              int frequency;
              try {
                frequency = Integer.parseInt(freq);
              } catch (NumberFormatException e) {
                this.view.appendToView("Frequency should be a number\n");
                continue;
              }
              tickers = view.getTickers();
              weights = view.getWeights();
              try {
                if (endDate.toLowerCase().equals("n")) {
                  cmd = new DollarCostAveraging(name, startDate, amount, commission,
                          frequency, tickers, weights);
                } else {
                  cmd = new DollarCostAveraging(name, startDate, endDate, amount, commission,
                          frequency, tickers, weights);
                }
                this.model.addStrategy(cmd);
                this.addToOut("Strategy " + name + " created successfully.\n");
              } catch (IllegalArgumentException e) {
                this.addToOut(e.getLocalizedMessage() + "\n");
              }
              break;
            default:
              // nothing
          }
          break;
        case "8":
          String sName = this.view.getStrategyName();
          if (this.quit(sName)) {
            this.addToOut("Quitting the application");
            return;
          }
          String pName = this.view.getPortfolioName();
          if (this.quit(pName)) {
            this.addToOut("Quitting the application");
            return;
          }
          try {
            this.model.applyStrategy(sName, pName);
            this.addToOut("Strategy " + sName + " applied successfully to " + pName + "\n");
          } catch (IllegalArgumentException e) {
            this.addToOut(e.getLocalizedMessage());
          }
          break;
        case "9":
          pName = this.view.getPortfolioName();
          if (this.quit(pName)) {
            this.addToOut("Quitting the application");
            return;
          }
          try {
            this.model.savePortfolio(pName);
            this.addToOut("Portfolio " + pName + " saved successfully.\n");
          } catch (IllegalArgumentException e) {
            this.addToOut(e.getLocalizedMessage());
          }
          break;
        case "10":
          pName = this.view.getPortfolioName();
          if (this.quit(pName)) {
            this.addToOut("Quitting the application");
            return;
          }
          try {
            this.model.retrievePortfolio(pName + ".json");
            this.addToOut("Portfolio " + pName + " retrieved successfully.\n");
          } catch (IllegalArgumentException e) {
            this.addToOut(e.getLocalizedMessage());
          }
          break;
        case "11":
          sName = this.view.getStrategyName();
          if (this.quit(sName)) {
            this.addToOut("Quitting the application");
            return;
          }
          try {
            this.model.saveStrategy(sName);
            this.addToOut("Strategy " + sName + " saved successfully.\n");
          } catch (IllegalArgumentException e) {
            this.addToOut(e.getLocalizedMessage());
          }
          break;
        case "12":
          sName = this.view.selectStrategy();
          if (this.quit(sName)) {
            this.addToOut("Quitting the application");
            return;
          }
          try {
            this.model.retrieveStrategy(sName + ".json");
            this.addToOut("Portfolio " + sName + " retrieved successfully.\n");
          } catch (IllegalArgumentException e) {
            this.addToOut(e.getLocalizedMessage());
          }
          break;
        default:
          this.view.appendToView("Enter a valid choice.\n");
      }
    }

  }

  private DataSources setDataSourceForModel() {
    while (true) {
      String d = view.getDataSource();
      switch (d.toLowerCase()) {
        case "f":
          return DataSources.FILE;

        case "a":
          return DataSources.ALPHA;
        default:
          this.view.appendToView("Data source should be F or A\n");
      }
    }
  }

  private void getValue(PortfolioManagement model, String portfolioName,
                        String date) {
    try {
      String temp = "" + model.getTotalValue(portfolioName, date) + "\n";
      this.addToOut(temp);
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    } catch (NoSuchElementException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }

  }

  private void getCostBasis(PortfolioManagement model, String portfolioName,
                            String date) {
    try {
      String temp = "" + model.getTotalCostBasis(portfolioName, date) + "\n";
      this.addToOut(temp);
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }
  }

  private void examinePortfolio(PortfolioManagement model, String portfolioName) {
    try {
      String temp = "";
      String[] names = model.getAllTickers(portfolioName);
      String[] shares = model.getNumberOfShares(portfolioName);
      String[] dates = model.getAllDatesofPurchase(portfolioName);
      for (int i = 0; i < names.length; i++) {
        temp = temp + "Ticker symbol: " + names[i] + ", ";
        temp = temp + "Number of shares: " + shares[i] + ", ";
        temp = temp + "Date of purchase: " + dates[i] + "\n";
      }
      this.addToOut(temp);
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }
  }

  private void buyStock(PortfolioManagement model, String portfolioName,
                        double amount, String date, String time, String tickerName) {
    try {
      model.buyStock(portfolioName, amount, date, time, tickerName);
      this.addToOut("Stock bought successfully\n");
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    } catch (NoSuchElementException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }
  }

  private void buyStockWithCommission(PortfolioManagement model, String portfolioName,
                                      double amount, double commission, String date, String time,
                                      String tickerName) {
    try {
      model.buyStockWithCommission(portfolioName, amount, commission, date, time, tickerName);
      this.addToOut("Stock bought successfully\n");
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    } catch (NoSuchElementException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }
  }

  private void createPortfolio(PortfolioManagement model, String portfolioName) {
    try {
      model.createPortfolio(portfolioName);
      this.addToOut("Portfolio " + portfolioName + " is created\n");
    } catch (IllegalArgumentException e) {
      this.addToOut(e.getLocalizedMessage() + "\n");
    }
  }

  private void addToOut(String st) {
    this.view.appendToView(st);
  }

}
