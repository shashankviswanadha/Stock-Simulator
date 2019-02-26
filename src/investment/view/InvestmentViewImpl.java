package investment.view;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InvestmentViewImpl implements InvestmentView {
  private Appendable output;
  private Scanner s;

  /**
   * A constructor for InvestmentViewImpl class.
   *
   * @param r  the readable object
   * @param ap the appendable object
   */
  public InvestmentViewImpl(Readable r, Appendable ap) {
    Readable input;
    if (r == null || ap == null) {
      throw new IllegalArgumentException("Readable or Appendable cannot be null");
    }
    this.output = ap;
    input = r;
    s = new Scanner(input);
  }

  @Override
  public void appendToView(String st) {
    try {
      this.output.append(st);
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed.");
    }
  }

  @Override
  public String[] getTickers() {
    this.appendToView("Enter the list of tickers:\n");
    return s.next().split("\\s*,\\s*");
  }

  @Override
  public String[] getWeights() {
    this.appendToView("Enter the list of weights:\n");
    return s.next().split("\\s*,\\s*");
  }

  @Override
  public String getFrequency() {
    this.appendToView("Enter the frequency(in days) to buy the stocks.\n");
    return this.readData();
  }

  private String readData() {
    try {
      String temp = this.s.next();
      return temp;
    } catch (NoSuchElementException e) {
      throw new IllegalStateException("Readable failed.");
    }
  }

  @Override
  public String getPortfolioName() {
    this.appendToView("Enter the name of the Portfolio\n");
    return this.readData();
  }

  @Override
  public String getAmount() {
    this.appendToView("Enter the amount(in dollars)\n");
    return this.readData();
  }

  @Override
  public String getDate() {
    this.appendToView("Enter the Date\n");
    return this.readData();
  }

  @Override
  public String getTime() {
    this.appendToView("Enter the Time\n");
    return this.readData();
  }

  @Override
  public String getChoice() {
    this.appendToView("1.Create a new portfolio\n2.Buy Stock\n3.Examine Portfolio\n4.Get Cost "
            + "Basis of a Portfolio\n5.Get current value of a portfolio\n6.Get all portfolio "
            + "names\n7.Create a Strategy\n8.Apply a strategy to a portfolio"
            + " stocks\n9.Save a Portfolio to file\n10.Retrieve portfolio from file\n11.Save "
            + "Strategy to file\n12.Retrieve Strategy from file\nQ.Quit\nEnter your choice.\n");
    return this.readData();
  }

  @Override
  public String getTickerName() {
    this.appendToView("Enter the ticker name.\n");
    return this.readData();
  }

  @Override
  public String getDataSource() {
    this.appendToView("Enter the data source: \nA: API\nF: Files\n");
    return this.readData();
  }

  @Override
  public String getEndDate() {
    this.appendToView("Enter the end date. Enter n if the investment is currently running: \n");
    return this.readData();
  }

  @Override
  public String getCommission() {
    this.appendToView("Enter the commission amount\n");
    return this.readData();
  }

  @Override
  public String selectStrategy() {
    this.appendToView("1.Weighted Investment\n2.Equally Weighted Investment\n3.Dollar "
            + "Cost Averaging\nEnter your choice.");
    return this.readData();
  }

  @Override
  public String getStrategyName() {
    this.appendToView("Enter the name of the Strategy to be created.\n");
    return this.readData();
  }
}




