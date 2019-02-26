package investment.model;

/**
 * This class implements the Dollar Cost Averaging Strategy. For this strategy, the name, start
 * date, end date, amount, commission, list of tickers and weights and frequency would be taken and
 * the strategy could be implemented on a required portfolio.
 */

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DollarCostAveraging implements Strategy {
  private String startDate;
  private String endDate;
  private double amount;
  private String[] tickers;
  private String currentDate;
  private int frequency;
  private String[] weights;
  private double commission;
  private String name;

  /**
   * A constructor for DollarCostAveraging operation which takes in the end date. It takes in the
   * start date, end date, amount, commission, frequency and list of tickers and weights from the
   * user.
   *
   * @param startDate  of the investment
   * @param endDate    of the investment
   * @param amount     which has to be invested
   * @param commission to be considered for the investment
   * @param frequency  of the investment
   * @param tickers    on which the investment has to be performed
   * @param weights    of the tickers
   */

  public DollarCostAveraging(String name, String startDate, String endDate,
                             double amount, double commission, int frequency,
                             String[] tickers, String[] weights) {

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Strategy name cannot be empty");
    }
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.amount = amount;
    Scanner inFile1 = null;
    try {
      inFile1 = new Scanner(new File("NYSE.txt")).useDelimiter(",\\s*");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    List<String> validTickers;
    validTickers = new ArrayList<String>();
    while (inFile1.hasNext()) {
      validTickers.add(inFile1.nextLine());
    }
    for (String ticker : tickers) {
      if (!validTickers.contains(ticker)) {
        throw new IllegalArgumentException("Ticker : " + ticker + "is invalid. "
                + "Enter a list of valid tickers.");
      }
    }
    this.tickers = tickers;
    this.currentDate = startDate;
    this.frequency = frequency;
    this.weights = weights;
    this.commission = commission;
  }

  /**
   * A constructor for DollarCostAveraging which does not have an end date. It takes in the start
   * date, amount, commission, frequency and list of tickers and weights from the user. The end date
   * is set as the current date.
   *
   * @param startDate  of the investment
   * @param amount     to be invested
   * @param commission of the investment
   * @param frequency  of duration
   * @param tickers    to be invested in the portfolio
   * @param weights    for the portfolio
   */
  public DollarCostAveraging(String name, String startDate, double amount,
                             double commission, int frequency,
                             String[] tickers, String[] weights) {
    this(name, startDate, "", amount, commission, frequency, tickers, weights);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, -1);
    this.endDate = sdf.format(cal.getTime());

  }

  private void incrementCurrentDate(int freq) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(sdf.parse(this.currentDate));
      c.add(Calendar.DATE, freq);  // number of days to add
      this.currentDate = sdf.format(c.getTime());  // dt is now the new date
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid Current Date format");
    }
  }

  private Calendar getDateFromString(String dt) {
    String[] lt = dt.split("\\-");
    if (lt.length != 3) {
      throw new IllegalArgumentException("Invalid Date");
    }
    try {
      int years = Integer.parseInt(lt[0]);
      int months = Integer.parseInt(lt[1]);
      int date = Integer.parseInt(lt[2]);
      LocalDate ld = LocalDate.of(years, months, date);
      Calendar cal = new GregorianCalendar(years, months - 1, date);
      return cal;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid Date");
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Date out of range");
    }

  }

  private boolean checkValidDuration() {
    Calendar sd = this.getDateFromString(this.startDate);
    Calendar ed = this.getDateFromString(this.endDate);
    return sd.compareTo(ed) < 0;
  }

  private void buyStocksInitially(PortfolioManagement m, String portfolioName)
          throws IllegalArgumentException,
          NoSuchElementException {
    double amtPerStock = this.amount / this.tickers.length;
    for (String ticker : this.tickers) {
      try {
        m.buyStockWithCommission(portfolioName, amtPerStock, this.commission, this.currentDate,
                "12:00:00", ticker);
      }
      catch (NoSuchElementException e){
        // do nothing
      }
    }
  }

  private void investOnCurrentDate(PortfolioManagement m, String portfolioName)
          throws IllegalArgumentException,
          NoSuchElementException {
    Strategy wi = new WeightedInvestment("dummy", this.currentDate,
            this.amount, this.commission, this.tickers, this.weights);
    wi.apply(m, portfolioName);
  }

  private boolean checkCurrentDate() {
    Calendar cd = this.getDateFromString(this.currentDate);
    Calendar ed = this.getDateFromString(this.endDate);
    return cd.compareTo(ed) > 0;
  }

  @Override
  public void apply(PortfolioManagement m, String portfolioName) throws IllegalArgumentException {
    try {
      m.createPortfolio(portfolioName);
    } catch (IllegalArgumentException e) {
      if (!e.getLocalizedMessage().equals("Portfolio already exists, use another name")) {
        throw e;
      }
    }
    if (!this.checkValidDuration()) {
      throw new IllegalArgumentException("Invalid Duration: End date comes before Start Date.");
    }
    int flag = 0;
    while (true) {
      try {
        this.buyStocksInitially(m, portfolioName);
        this.incrementCurrentDate(flag);
        flag = 0;
        this.incrementCurrentDate(this.frequency);
        break;
      } catch (IllegalArgumentException e) {
        if (e.getLocalizedMessage().equals("Cannot buy stock on a Holiday.")) {
          this.incrementCurrentDate(1);
          flag--;
        } else {
          throw e;
        }
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException(e.getLocalizedMessage());
      }
    }
    while (true) {
      if (this.checkCurrentDate()) {
        break;
      }
      try {
        this.investOnCurrentDate(m, portfolioName);
        this.incrementCurrentDate(flag);
        flag = 0;
        this.incrementCurrentDate(this.frequency);
      } catch (IllegalArgumentException e) {
        if (e.getLocalizedMessage().equals("Cannot buy stock on a Holiday.")) {
          flag--;
          this.incrementCurrentDate(1);
        } else {
          throw e;
        }
      } catch (NoSuchElementException e) {
        throw new IllegalArgumentException(e.getLocalizedMessage());
      }
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void saveToFile() {
    Gson gson = new Gson();
    try (FileWriter writer = new FileWriter("DC_" + this.name + ".json")) {
      gson.toJson(this, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
