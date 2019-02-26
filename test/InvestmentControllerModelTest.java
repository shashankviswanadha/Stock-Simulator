import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import investment.controller.InvestmentController;
import investment.controller.InvestmentControllerImpl;
import investment.model.PortfolioManagementImpl;
import investment.view.InvestmentView;
import investment.view.InvestmentViewImpl;

import static junit.framework.TestCase.assertEquals;

public class InvestmentControllerModelTest {


  // Quit without any use.
  @Test
  public void quitWithoutAnyUse() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A q");
    InvestmentView view = new InvestmentViewImpl(in, out);
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Quitting the application", out.toString());
  }

  //   Readable fail (without q).
  @Test(expected = IllegalStateException.class)
  public void quitReadableFail() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 MyPortfolio");
    InvestmentView view = new InvestmentViewImpl(in, out);
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Quitting the application", out.toString());
  }

  // Null input.
  @Test(expected = IllegalArgumentException.class)
  public void TestNullInput() {
    StringBuffer out = new StringBuffer();
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(null, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
  }

  // Null output.
  @Test(expected = IllegalArgumentException.class)
  public void TestNullOutput() {
    Reader in = new StringReader("q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, null);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
  }

  // Null input and output.
  @Test(expected = IllegalArgumentException.class)
  public void TestNullInputOutput() {
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(null, null);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
  }

  // CreatePortfolio and Quit.
  @Test
  public void quitAfterCreate() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 MyPortfolio q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio MyPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quitWithCommand() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quit2WithCommand() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quit2WithCommand1() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("a 1 myPortfolio 2 myPortfolio q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quit2WithCommand2() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quit2WithCommand3() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("a 1 myPortfolio 2 myPortfolio 1290.08 20 2016-12-29 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio command Quit.
  @Test
  public void quit2WithCommand4() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 20 2016-12-29 3:00:23 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio BuyStock Examine Quit.
  @Test
  public void buyStock() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 20 2016-12-29 "
            + "13:00:23 WFC"
            + " 3 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio BuyStock Examine Quit 3.
  @Test
  public void quit3WithCommand1() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("F 1 myPortfolio 2 myPortfolio 1290.08 20 2016-12-29 13:00:23 "
            + "WFC 3 myPortfolio 4 q");

    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Ticker symbol: WFC, Number of shares: 23, Date of purchase: 2016-12-29\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Quitting the application", out.toString());
  }

  // CreatePortfolio BuyStock Examine Quit.
  @Test
  public void quit4WithCommand2() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 30 2016-12-29 "
            + "13:00:23 WFC"
            + " 3 myPortfolio 4 myPortfolio q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Ticker symbol: WFC, Number of shares: 23, Date of purchase: 2016-12-29\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "Quitting the application", out.toString());
  }


  @Test
  public void testOption4() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 30 2016-12-29"
            + " 13:00:23 WFC"
            + " 3 myPortfolio 4 myPortfolio 2018-11-13 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Ticker symbol: WFC, Number of shares: 23, Date of purchase: 2016-12-29\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "1291.32\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Quitting the application", out.toString());
  }


  @Test
  public void testOption5() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 30 2016-12-29 "
            + "13:00:23 WFC"
            + " 3 myPortfolio 4 myPortfolio 2018-11-13 5 myPortfolio 2018-11-13 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Ticker symbol: WFC, Number of shares: 23, Date of purchase: 2016-12-29\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "1291.32\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "1213.02\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Quitting the application", out.toString());
  }

  @Test
  public void testOption6() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 myPortfolio 2 myPortfolio 1290.08 30 2016-12-29 "
            + "13:00:23 WFC"
            + " 3 myPortfolio 4 myPortfolio 2018-11-13 5 myPortfolio 2018-11-13 6 q");
    PortfolioManagementImpl portfolioManagement = new PortfolioManagementImpl();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Portfolio myPortfolio is created\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the amount(in dollars)\n"
            + "Enter the commission amount\n"
            + "Enter the Date\n"
            + "Enter the Time\n"
            + "Enter the ticker name.\n"
            + "Stock bought successfully\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Ticker symbol: WFC, Number of shares: 23, Date of purchase: 2016-12-29\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "1291.32\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Enter the name of the Portfolio\n"
            + "Enter the Date\n"
            + "1213.02\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "[myPortfolio]\n"
            + "1.Create a new portfolio\n"
            + "2.Buy Stock\n"
            + "3.Examine Portfolio\n"
            + "4.Get Cost Basis of a Portfolio\n"
            + "5.Get current value of a portfolio\n"
            + "6.Get all portfolio names\n"
            + "7.Create a Strategy\n"
            + "8.Apply a strategy to a portfolio stocks\n"
            + "9.Save a Portfolio to file\n"
            + "10.Retrieve portfolio from file\n"
            + "11.Save Strategy to file\n"
            + "12.Retrieve Strategy from file\n"
            + "Q.Quit\n"
            + "Enter your choice.\n"
            + "Quitting the application", out.toString());
  }

}

