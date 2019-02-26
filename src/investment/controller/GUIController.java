package investment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import investment.data.DataSources;
import investment.model.DollarCostAveraging;
import investment.model.EquallyWeightedInvestment;
import investment.model.PortfolioManagement;
import investment.model.Strategy;
import investment.model.WeightedInvestment;
import investment.view.IGView;

/**
 * This class implements the InvestmentController interface. It takes in a model upon which all the
 * operations are performed.
 */
public class GUIController implements InvestmentController {
  private PortfolioManagement model;
  private IGView view;

  /**
   * A constructor for implementing the GUIController class. It takes in the model and view as the
   * arguments.
   *
   * @param m model for the implementation
   * @param v view for the implementation
   */
  public GUIController(PortfolioManagement m, IGView v) {
    model = m;
    view = v;
    String temp = view.getDataSource();
    if (temp == null) {
      throw new IllegalArgumentException("Enter a valid data source.");
    }
    if (temp.equals("API")) {
      model.setDataSource(DataSources.ALPHA);
    } else if (temp.equals("Files")) {
      model.setDataSource(DataSources.FILE);
    }
  }

  @Override
  public void execute() {
    configureButtonListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("CreatePortfolio", () -> {
      createPortfolio();
    });
    buttonClickedMap.put("BuyStock", () -> {
      this.buy();
    });
    buttonClickedMap.put("ViewPortfolio", () -> {
      buyStock();
    });
    buttonClickedMap.put("GetCostBasis", () -> {
      getCostBasis();
    });
    buttonClickedMap.put("GetCurrentValue", () -> {
      currentValue();
    });
    buttonClickedMap.put("Strategies", () -> {
      strategies();
    });
    buttonClickedMap.put("Apply", () -> {
      applyStrategies();
    });
    buttonClickedMap.put("SavePortfolio", () -> {
      savePortfolio();
    });
    buttonClickedMap.put("RetrievePortfolio", () -> {
      retrievePortfolio();
    });
    buttonClickedMap.put("SaveStrategy", () -> {
      saveStrategy();
    });
    buttonClickedMap.put("RetrieveStrategy", () -> {
      retrieveStrategy();
    });
    buttonClickedMap.put("Plot", () -> {
      this.plot();
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.setListener(buttonListener);
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

  private String incrementCurrentDate(String currentDate, int freq) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    try {
      c.setTime(sdf.parse(currentDate));
      c.add(Calendar.DATE, freq);  // number of days to add
      currentDate = sdf.format(c.getTime());  // dt is now the new date
      return currentDate;
    } catch (ParseException e) {
      throw new IllegalArgumentException("Invalid Current Date format");
    }
  }

  private void plot() {
    String[] dates = this.view.getDates(this.model.getAllPortfolioNames().toArray(new String[0]));
    if (dates == null) {
      return;
    }
    List<String> date = new ArrayList<>();
    List<Double> data = new ArrayList<>();
    int count = 0;
    double t;
    while (!dates[1].equals(dates[2])) {
      try {
        t = this.model.getTotalValue(dates[0], dates[1]);
        data.add(t);
        date.add(dates[1]);
      } catch (IllegalArgumentException e) {
        // do nothing
      }

      dates[1] = this.incrementCurrentDate(dates[1], 1);

    }
    double[] target = new double[data.size()];
    for (int i = 0; i < target.length; i++) {
      target[i] = data.get(i);
    }
    //    LineChart chart = new LineChart(
    //            "Value vs Dates",
    //            "Value of a Portfolio vs Dates", target, date.toArray(new String[0]));
    //    chart.pack();
    //    RefineryUtilities.centerFrameOnScreen(chart);
    //    chart.setVisible(true);

  }


  private void createPortfolio() {
    String name = view.getPortfolioName();
    if (name == null) {
      return;
    }
    try {
      model.createPortfolio(name);
      this.view.displayStatus("Portfolio " + name + " created successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void applyStrategies() {
    String[] app = this.view.applyStrategyArgs(this.model.getAllPortfolioNames().
            toArray(new String[0]), this.model.getAllStrategyNames().toArray(new String[0]));
    if (app == null) {
      return;
    }
    try {
      this.model.applyStrategy(app[1], app[0]);
      this.view.displayStatus("Strategy " + app[1] + " applied to " + app[0]);
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }

  }

  private void savePortfolio() {
    String pfName = this.view.selectPortfolio(this.model.getAllPortfolioNames().
            toArray(new String[0]));
    if (pfName == null) {
      return;
    }
    try {
      this.model.savePortfolio(pfName);
      this.view.displayStatus("File saved successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void retrievePortfolio() {
    String pfName = this.view.getFileName();
    try {
      this.model.retrievePortfolio(pfName);
      this.view.displayStatus("File retrieved successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void retrieveStrategy() {
    String sName = this.view.getFileName();
    try {
      this.model.retrieveStrategy(sName);
      this.view.displayStatus("File retrieved successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void saveStrategy() {
    String sName = this.view.selectStrategy(this.model.getAllStrategyNames().
            toArray(new String[0]));
    if (sName == null) {
      return;
    }
    try {
      this.model.saveStrategy(sName);
      this.view.displayStatus("Strategy saved successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void strategies() {
    String st = view.getSelectedStrategy();
    if (st == null) {
      return;
    }
    if (st.equals("Weighted Investment")) {
      this.weighted();
    } else if (st.equals("Equally Weighted Investment")) {
      this.equallyWeighted();
    } else if (st.equals("Dollar Cost Averaging")) {
      this.dollarCost();
    }
  }

  private void currentValue() {
    String[] args1 = view.getCostBasisArguments(this.model.getAllPortfolioNames().
            toArray(new String[0]));
    if (args1 == null) {
      return;
    }

    try {
      Double db = model.getTotalValue(args1[0], args1[1]);
      this.view.displayStatus(db.toString());

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void getCostBasis() {
    String[] args1 = view.getCostBasisArguments(this.model.getAllPortfolioNames().
            toArray(new String[0]));
    if (args1 == null) {
      return;
    }

    try {
      Double db = model.getTotalCostBasis(args1[0], args1[1]);
      this.view.displayStatus(db.toString());

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void buy() {
    String[] args = view.getBuyStockArguments(this.model.getAllPortfolioNames().
            toArray(new String[0]));
    if (args == null) {
      return;
    }

    try {
      this.model.buyStockWithCommission(args[0], Double.parseDouble(args[1]),
              Double.parseDouble(args[2]), args[3], args[4], args[5]);
      this.view.displayStatus("Stock bought successfully.");
    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void buyStock() {
    String pf = view.selectPortfolio(this.model.getAllPortfolioNames().toArray(new String[0]));
    if (pf == null) {
      return;
    }
    try {
      String[] names = model.getAllTickers(pf);
      String[] shares = model.getNumberOfShares(pf);
      String[] dates = model.getAllDatesofPurchase(pf);
      String temp = "";
      for (int i = 0; i < names.length; i++) {
        temp = temp + "Ticker symbol: " + names[i] + ", ";
        temp = temp + "Number of shares: " + shares[i] + ", ";
        temp = temp + "Date of purchase: " + dates[i] + "\n";
      }
      this.view.examine(temp);

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void weighted() {

    String[] args1 = view.getWeightedInvestmentArguments();
    if (args1 == null) {
      return;
    }
    String[][] tckrsAndWeihts = this.view.getTickersAndWeights();
    if (tckrsAndWeihts == null) {
      return;
    }
    double amt = 0.0;
    double comm = 0.0;
    Strategy cmd;
    try {
      amt = Double.parseDouble(args1[2]);
      comm = Double.parseDouble(args1[3]);
    } catch (NumberFormatException e) {
      this.view.displayStatus("Enter a Valid number for amount/commission.");
    }

    try {
      cmd = new WeightedInvestment(args1[0], args1[1], amt, comm, tckrsAndWeihts[0],
              tckrsAndWeihts[1]);
      model.addStrategy(cmd);
      this.view.displayStatus("Created strategy successfully");

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void equallyWeighted() {
    String[] args2 = view.getEquallyWeightedArgs();
    if (args2 == null) {
      return;
    }
    double amt = 0.0;
    double comm = 0.0;
    Strategy cmd;
    try {
      amt = Double.parseDouble(args2[0]);
      comm = Double.parseDouble(args2[1]);
    } catch (NumberFormatException e) {
      this.view.displayStatus("Enter a Valid number for amount/commission.");
    }
    try {
      cmd = new EquallyWeightedInvestment(args2[3], args2[2], amt, comm);
      model.addStrategy(cmd);
      this.view.displayStatus("Strategy " + args2[3] + " created successfully.");

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }
  }

  private void dollarCost() {
    String[] args1 = view.gerDollarCostAvg();
    if (args1 == null) {
      return;
    }
    String[][] tckrsAndWeihts = this.view.getTickersAndWeights();
    if (tckrsAndWeihts == null) {
      return;
    }
    List<String> wt = new ArrayList<>();
    double amt = 0.0;
    double comm = 0.0;
    int freq = 0;
    Strategy cmd;
    try {
      amt = Double.parseDouble(args1[3]);
      comm = Double.parseDouble(args1[4]);
      freq = Integer.parseInt(args1[5]);
    } catch (NumberFormatException e) {
      this.view.displayStatus("Enter a Valid number for amount/commission/frequency.");
    }
    for (int i = 5; i < args1.length; i++) {
      wt.add(args1[i]);
    }

    String[] weights = wt.toArray(new String[0]);
    try {
      if (args1[2].equals("n")) {
        cmd = new DollarCostAveraging(args1[0], args1[1], amt, comm, freq, tckrsAndWeihts[0],
                tckrsAndWeihts[1]);
      } else {
        cmd = new DollarCostAveraging(args1[0], args1[1], args1[2], amt, comm, freq,
                tckrsAndWeihts[0], tckrsAndWeihts[1]);
      }
      model.addStrategy(cmd);
      this.view.displayStatus("Dollar Cost Strategy " + args1[0] + " successfully.");

    } catch (IllegalArgumentException e) {
      this.view.displayStatus(e.getLocalizedMessage());
    }


  }

}
