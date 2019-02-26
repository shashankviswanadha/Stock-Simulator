package investment.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import investment.data.Data;

/**
 * This class represents a single stock. Each stock has a tickerSymbol, costBasis - the value of the
 * stock at the time of purchase, current price of the share which represents the price of stock on
 * a particular date, the number of shares for the given stock and the date of purchase of this
 * stock.
 */
class Stock {
  private final String tickerSymbol;
  private final double costBasisOfShare;
  private double currentPriceOfShare;
  private final int numberOfShares;
  private final Calendar dateOfPurchase;
  private double commission;

  public String getTickerSymbol() {
    return tickerSymbol;
  }


  private boolean isHoliday(Calendar cal) {

    if (cal.get(Calendar.MONTH) == Calendar.JULY
            && cal.get(Calendar.DAY_OF_MONTH) == 4) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
            && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
            && cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.MAY
            && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
            && cal.get(Calendar.DAY_OF_MONTH) > (31 - 7)) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
            && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
            && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY
            && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
            && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
            && cal.get(Calendar.DAY_OF_MONTH) == 11) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.JANUARY
            && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
            && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
      return true;
    }

    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
            cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
      return true;
    }

    if (cal.get(Calendar.MONTH) == Calendar.JANUARY
            && cal.get(Calendar.DAY_OF_MONTH) == 1) {
      return true;
    }

    return (cal.get(Calendar.MONTH) == Calendar.DECEMBER
            && cal.get(Calendar.DAY_OF_MONTH) == 25);

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

  private void setCurrentPriceOfShare(String dateOfPurchase, Data
          datasource) throws IllegalArgumentException {
    this.currentPriceOfShare =
            datasource.getCurrentPrice(tickerSymbol, dateOfPurchase);
  }

  private boolean isValidTime(String time) {
    String[] lt = time.split("\\:");
    if (lt.length != 3) {
      throw new IllegalArgumentException("Invalid Time");
    }
    try {
      int hours = Integer.parseInt(lt[0]);
      int minutes = Integer.parseInt(lt[1]);
      int seconds = Integer.parseInt(lt[2]);
      LocalTime tme = LocalTime.of(hours, minutes, seconds);
      return (hours >= 9 && hours <= 15);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid Time");
    } catch (DateTimeException e) {
      throw new IllegalArgumentException("Time out of range");
    }

  }

  /**
   * A constructor for stock class which takes in the fields tickerSymbol, amount, dateOfPurchase,
   * time and datasource. The dateofPurchase, tickerSymbol, time or datasource cannot be null. The
   * stock cannot be bought on a holiday or when the market is closed. If the amount is too low to
   * purchase the stock an exception would be thrown. Moreover, the cost of the share must not be
   * zero or negative
   *
   * @param tickerSymbol   of the stock
   * @param amount         for which the stock is to be purchased
   * @param dateOfPurchase on which stock is bought
   * @param time           at which the stock s purchased
   * @param datasource     from which the stock data is to be retrieved
   * @throws IllegalArgumentException if any of the input is null, empty or invalid, if the stock to
   *                                  be bought is ona holiday or when the market is closed and if
   *                                  the amount is too low to buy a stock
   * @throws NoSuchElementException   if there is no data for the stock in the datasource
   */
  Stock(String tickerSymbol, double amount, String
          dateOfPurchase, String time, Data datasource) throws
          IllegalArgumentException, NoSuchElementException {
    if (dateOfPurchase == null || tickerSymbol == null || time == null
            || datasource == null) {
      throw new IllegalArgumentException("Invalid input: Cannot be null");
    }
    Calendar temp = this.getDateFromString(dateOfPurchase);
    if (this.isHoliday(temp)) {
      throw new IllegalArgumentException("Cannot buy stock on a Holiday.");
    }
    if (!this.isValidTime(time)) {
      throw new IllegalArgumentException("Cannot buy stock when market is closed");
    }
    this.tickerSymbol = tickerSymbol;
    this.setCurrentPriceOfShare(dateOfPurchase, datasource);
    double costBasis = this.currentPriceOfShare;
    int numOfShares = (int) Math.floor(amount / costBasis);

    if (costBasis <= 0) {
      throw new IllegalArgumentException("Cost of share cannot be 0 floor negative.");
    }
    if (numOfShares <= 0) {
      throw new IllegalArgumentException("Amount too low to buy a share.");
    }

    this.costBasisOfShare = costBasis;
    this.numberOfShares = numOfShares;
    this.dateOfPurchase = temp;
    this.commission = 0;
  }

  Stock(String tickerSymbol, double amount, double commission, String
          dateOfPurchase, String time, Data datasource) throws
          IllegalArgumentException, NoSuchElementException {
    this(tickerSymbol, amount, dateOfPurchase, time, datasource);
    if (commission < 0) {
      throw new IllegalArgumentException("Commission fee cannot be negative");
    }
    this.commission = commission;
  }

  /**
   * Get the total cost basis of the stock for a particular date.
   *
   * @param date on which cost basis is to be calculated.
   * @return the costBasis of the stock for the given date as a double
   */

  public double getTotalCostBasis(String date) {
    if (date == null) {
      throw new IllegalArgumentException("Invalid date. Cannot be null");
    }
    Calendar dt = this.getDateFromString(date);
    if (this.dateOfPurchase.compareTo(dt) <= 0) {
      return Math.round(((costBasisOfShare * numberOfShares) + this.commission) * 100.0) / 100.0;
    }
    return 0.00;
  }

  /**
   * Get the current holdings data of the stock by taking in a date and the datasource from which
   * the stock data is to be calculated.
   *
   * @param date       on which holdings is to be calculated
   * @param datasource from which stock data is to be retrieved
   * @return the holdings on the given date as a double
   * @throws IllegalArgumentException if the input is null or date is not a business day
   * @throws NoSuchElementException   if there is no data for this stock in the datasource.
   */

  public double getHoldings(String date, Data datasource) throws
          IllegalArgumentException, NoSuchElementException {
    if (date == null || datasource == null) {
      throw new IllegalArgumentException("Invalid input: Cannot be null");
    }
    Calendar dt = this.getDateFromString(date);
    if (this.dateOfPurchase.compareTo(dt) <= 0) {
      if (this.isHoliday(dt)) {
        throw new IllegalArgumentException("You entered a holiday. Enter a business day.");
      }
      this.setCurrentPriceOfShare(date, datasource);
      return Math.round(this.currentPriceOfShare * this.numberOfShares * 100.0) / 100.0;
    } else {
      return 0.00;
    }
  }

  /**
   * Get the number of shares in this stock.
   *
   * @return the number of shares as a String
   */
  public String getNumOfShares() {
    return Integer.toString(this.numberOfShares);
  }

  /**
   * Get the date of purchase of the stock.
   *
   * @return the date in string format
   */

  public String getDateOfPurchase() {

    return this.dateOfPurchase.get(Calendar.YEAR) + "-"
            + String.format("%02d", this.dateOfPurchase.get(Calendar.MONTH) + 1)
            + "-" + String.format("%02d", this.dateOfPurchase.get(Calendar.DAY_OF_MONTH));

  }
}
