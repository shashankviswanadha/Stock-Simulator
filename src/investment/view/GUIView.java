package investment.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;


/**
 * This class implements the GUIView interface. It uses the Java Swing features to create a
 * user-friendly and intuitive graphical user interface. The user is given various options to choose
 * from in the form of buttons. The user can click the appropriate button in order to perform an
 * action.
 */
public class GUIView extends JFrame implements IGView {

  private JButton createPortfolio;
  private JButton buyStock;
  private JButton viewPortfolio;
  private JButton costBasis;
  private JButton currentValue;
  private JButton strategies;
  private JButton apply;
  private JButton savePortfolio;
  private JButton retrievePortfolio;
  private JButton saveStrategy;
  private JButton retrieveStrategy;
  private JButton plot;
  private String dataSource;

  /**
   * A constructor which sets the size, and location of the window for the GUIview. The button
   * objects are created in this constructor, the Action commands are set and they are added to the
   * layout.
   *
   * @param caption to be displayed on the window
   */
  public GUIView(String caption) {
    super(caption);

    setSize(600, 600);
    setLocation(500, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());
    this.setDataSource();
    this.createPortfolio = new JButton("Create Portfolio");
    this.createPortfolio.setActionCommand("CreatePortfolio");
    this.add(this.createPortfolio);

    this.buyStock = new JButton("Buy A Stock");
    this.buyStock.setActionCommand("BuyStock");
    this.add(this.buyStock);

    this.viewPortfolio = new JButton("View Portfolio");
    this.viewPortfolio.setActionCommand("ViewPortfolio");
    this.add(this.viewPortfolio);

    this.costBasis = new JButton("Get Cost Basis of a Portfolio");
    this.costBasis.setActionCommand("GetCostBasis");
    this.add(this.costBasis);

    this.currentValue = new JButton("Get current value of a Portfolio");
    this.currentValue.setActionCommand("GetCurrentValue");
    this.add(this.currentValue);

    this.strategies = new JButton("Create a new Strategy");
    this.strategies.setActionCommand("Strategies");
    this.add(this.strategies);

    this.apply = new JButton("Apply a strategy on a portfolio");
    this.apply.setActionCommand("Apply");
    this.add(this.apply);

    this.savePortfolio = new JButton("Save a portfolio to file");
    this.savePortfolio.setActionCommand("SavePortfolio");
    this.add(this.savePortfolio);

    this.retrievePortfolio = new JButton("Retrieve a portfolio from file");
    this.retrievePortfolio.setActionCommand("RetrievePortfolio");
    this.add(this.retrievePortfolio);

    this.saveStrategy = new JButton("Save a strategy to file");
    this.saveStrategy.setActionCommand("SaveStrategy");
    this.add(this.saveStrategy);

    this.retrieveStrategy = new JButton("Retrieve strategy from file");
    this.retrieveStrategy.setActionCommand("RetrieveStrategy");
    this.add(this.retrieveStrategy);

    this.plot = new JButton("Plot the performance of a portfolio.");
    this.plot.setActionCommand("Plot");
    this.add(this.plot);
    this.display();

  }

  private void setDataSource() {
    JComboBox<String> dS = new JComboBox<>(new String[]{"API", "Files"});
    Object[] message = {"Select the data source", dS};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      dataSource = (String) dS.getSelectedItem();
    } else {
      dataSource = null;
    }
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public String getPortfolioName() {
    String temp = "";
    JTextField name = new JTextField();
    Object[] message = {"Enter the name of the Portfolio to be created.", name};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp = name.getText();
      return temp;
    } else {
      return null;
    }
  }

  @Override
  public void displayStatus(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

  @Override
  public void examine(String message) {
    JTextArea textArea = new JTextArea(message);
    JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
    JOptionPane.showMessageDialog(null, scrollPane, "Status",
            JOptionPane.YES_NO_OPTION);
  }
  @Override
  public String[] getBuyStockArguments(String[] portfolios) {
    String[] temp = new String[6];
    JTextField amount = new JTextField();
    JComboBox<String> pfName = new JComboBox<>(portfolios);
    JTextField commission = new JTextField();
    JTextField date = new JTextField();
    JTextField time = new JTextField();
    JTextField ticker = new JTextField();
    Object[] message = {"Select the Portfolio", pfName, "Enter the amount", amount,
      "Enter the commission fee.", commission, "Enter the ticker name", ticker,
      "Enter the Date(yyyy-mm-dd)", date, "Enter the time(hh:mm:ss)", time};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp[0] = (String) pfName.getSelectedItem();
      temp[1] = amount.getText();
      temp[2] = commission.getText();
      temp[3] = date.getText();
      temp[4] = time.getText();
      temp[5] = ticker.getText();
      return temp;
    }
    return null;

  }

  @Override
  public String selectPortfolio(String[] portfolios) {
    String temp = "";
    JComboBox<String> pfName = new JComboBox<>(portfolios);
    Object[] message = {"Select the Portfolio", pfName};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp = (String) pfName.getSelectedItem();
      return temp;
    }
    return null;

  }

  @Override
  public String[] getCostBasisArguments(String[] portfolios) {
    String[] temp = new String[2];
    JComboBox<String> pfName = new JComboBox<>(portfolios);
    JTextField date = new JTextField();
    Object[] message = {"Select the Portfolio", pfName, "Enter the Date(yyyy-mm-dd)", date};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp[0] = (String) pfName.getSelectedItem();
      temp[1] = date.getText();
      return temp;
    }
    return null;
  }

  @Override
  public String[] getWeightedInvestmentArguments() {
    List<String> temp = new ArrayList<>();
    JTextField name = new JTextField();
    JTextField date = new JTextField();
    JTextField amount = new JTextField();
    JTextField commission = new JTextField();
    List<Object> message = new ArrayList<>();
    Object[] message2;
    message.add("Enter the name of the Weighted Strategy");
    message.add(name);
    message.add("Enter the amount");
    message.add(amount);
    message.add("Enter the commission fee.");
    message.add(commission);
    message.add("Enter the Date(yyyy-mm-dd)");
    message.add(date);
    message2 = message.toArray(new Object[0]);
    int option = JOptionPane.showConfirmDialog(null, message2,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp.add(name.getText());
      temp.add(date.getText());
      temp.add(amount.getText());
      temp.add(commission.getText());
      return temp.toArray(new String[0]);
    }
    return null;

  }

  @Override
  public String[] getEquallyWeightedArgs() {
    String[] temp = new String[4];
    JTextField date = new JTextField();
    JTextField amount = new JTextField();
    JTextField commission = new JTextField();
    JTextField name = new JTextField();
    Object[] message = {"Enter the name of the Equally weighted strategy", name, "Enter the Date(yyyy-mm-dd)",
      date, "Enter the amount", amount, "Enter the commission", commission};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp[0] = amount.getText();
      temp[1] = commission.getText();
      temp[2] = date.getText();
      temp[3] = name.getText();
      return temp;
    }
    return null;
  }

  @Override
  public String[] gerDollarCostAvg() {
    List<String> temp = new ArrayList<>();
    JTextField name = new JTextField();
    JTextField amount = new JTextField();
    JTextField commission = new JTextField();
    JTextField sdate = new JTextField();
    JTextField edate = new JTextField();
    JTextField freq = new JTextField();
    List<Object> message = new ArrayList<>();
    Object[] message2;
    message.add("Enter the name of the Weighted Strategy");
    message.add(name);
    message.add("Enter the amount");
    message.add(amount);
    message.add("Enter the commission fee.");
    message.add(commission);
    message.add("Enter the Start Date(yyyy-mm-dd)");
    message.add(sdate);
    message.add("Enter the End Date(yyyy-mm-dd)");
    message.add(edate);
    message.add("Enter the Frequency(in days)");
    message.add(freq);
    message2 = message.toArray(new Object[0]);
    int option = JOptionPane.showConfirmDialog(null, message2,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp.add(name.getText());
      temp.add(sdate.getText());
      temp.add(edate.getText());
      temp.add(amount.getText());
      temp.add(commission.getText());
      temp.add(freq.getText());
      return temp.toArray(new String[0]);
    }
    return null;
  }

  @Override
  public String getSelectedStrategy() {
    String temp = "";
    JComboBox<String> strategies = new JComboBox<>(new String[]{"Weighted Investment",
        "Equally Weighted Investment", "Dollar Cost Averaging"});
    Object[] message = {"Select the type of strategy", strategies};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp = (String) strategies.getSelectedItem();
      return temp;
    }
    return null;
  }

  @Override
  public String[][] getTickersAndWeights() {

    JTextField num = new JTextField();
    int n = 0;
    Object[] message = {"Enter the number of companies you want to add in the strategy", num};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      n = Integer.parseInt(num.getText());
    }
    if (n == 0) {
      return null;
    }
    String[][] temp = new String[2][n];
    JTextField[] arr = new JTextField[n];
    JTextField[] arr1 = new JTextField[n];
    List<Object> message2 = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int t = i + 1;
      message2.add("Enter ticker for Stock " + t);
      arr[i] = new JTextField();
      message2.add(arr[i]);
      message2.add("Enter weight for Stock " + t);
      arr1[i] = new JTextField();
      message2.add(arr1[i]);
    }
    Object[] mess2 = message2.toArray(new Object[0]);
    option = JOptionPane.showConfirmDialog(null, mess2,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      for (int i = 0; i < n; i++) {
        temp[0][i] = arr[i].getText();
        temp[1][i] = arr1[i].getText();
      }
      return temp;
    }
    return null;
  }

  @Override
  public String[] applyStrategyArgs(String[] portfolios, String[] strategies) {
    String[] temp = new String[2];
    JComboBox<String> portfoliosBox = new JComboBox<>(portfolios);
    JComboBox<String> strategiesBox = new JComboBox<>(strategies);
    Object[] message = {"Select the portfolio to apply the strategy on", portfoliosBox,
      "Select the strategy to apply on the portfolio", strategiesBox};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp[0] = (String) portfoliosBox.getSelectedItem();
      temp[1] = (String) strategiesBox.getSelectedItem();
      return temp;
    }
    return null;
  }

  @Override
  public String getFileName() {
    JFileChooser saveas = new JFileChooser();
    saveas.showOpenDialog(new JFrame());
    File file = saveas.getSelectedFile();
    return file.getName();
  }

  @Override
  public String selectStrategy(String[] strategies) {
    String temp = "";
    JComboBox<String> pfName = new JComboBox<>(strategies);
    Object[] message = {"Select the Strategy", pfName};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp = (String) pfName.getSelectedItem();
      return temp;
    }
    return null;
  }

  @Override
  public String getDataSource() {
    return this.dataSource;
  }

  @Override
  public String[] getDates(String[] portfolios) {
    String[] temp = new String[3];
    JComboBox<String> pfName = new JComboBox<>(portfolios);
    JTextField sDate = new JTextField();
    JTextField eDate = new JTextField();
    Object[] message = {"Select the Portfolio", pfName, "Enter the Start date(yyyy-mm-dd)", sDate,
      "Enter the end date(hh:mm:ss)", eDate};
    int option = JOptionPane.showConfirmDialog(null, message,
            "Input", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
      temp[0] = (String) pfName.getSelectedItem();
      temp[1] = sDate.getText();
      temp[2] = eDate.getText();
      return temp;
    }
    return null;
  }

  @Override
  public void setListener(ActionListener listener) {
    createPortfolio.addActionListener(listener);
    buyStock.addActionListener(listener);
    viewPortfolio.addActionListener(listener);
    costBasis.addActionListener(listener);
    currentValue.addActionListener(listener);
    strategies.addActionListener(listener);
    apply.addActionListener(listener);
    savePortfolio.addActionListener(listener);
    retrievePortfolio.addActionListener(listener);
    saveStrategy.addActionListener(listener);
    retrieveStrategy.addActionListener(listener);
    plot.addActionListener(listener);
  }

}
