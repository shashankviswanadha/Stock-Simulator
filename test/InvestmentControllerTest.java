import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import investment.controller.InvestmentController;
import investment.controller.InvestmentControllerImpl;
import investment.model.MockModel;
import investment.model.PortfolioManagement;
import investment.view.InvestmentView;
import investment.view.InvestmentViewImpl;

import static junit.framework.TestCase.assertEquals;

public class InvestmentControllerTest {


  // Create Portfolio.
  @Test
  public void testCreatePortfolio() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("A 1 MyPortfolio q");
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

  // Create Portfolio null input.
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioNullInput() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    StringBuffer out = new StringBuffer();
    InvestmentView view = new InvestmentViewImpl(null, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
  }

  // Create Portfolio null output.
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioNullOutput() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 MyPortfolio q");
    InvestmentView view = new InvestmentViewImpl(in, null);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
  }

  // Create Portfolio Model null.
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioNullModel() {
    Reader in = new StringReader("A 1 MyPortfolio q");
    StringBuffer out = new StringBuffer();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(null, view);
    controller.execute();
  }

  // Create Portfolio datasource invalid then valid.
  @Test
  public void testCreatePortfolioInvalidDatasource() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("c a 1 MyPortfolio q");
    StringBuffer out = new StringBuffer();
    InvestmentView view = new InvestmentViewImpl(in, out);
    InvestmentController controller = new InvestmentControllerImpl(portfolioManagement, view);
    controller.execute();
    assertEquals("Enter the data source: \n"
            + "A: API\n"
            + "F: Files\n"
            + "Data source should be F or A\n"
            + "Enter the data source: \n"
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

  // Create a Portfolio.
  @Test
  public void testCreatePortfolioOnlyQuit() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A q");
    StringBuffer out = new StringBuffer();
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
            + "Quitting the application", out.toString());
  }

  // Input 1 and Quit.
  @Test
  public void testCreateCommandOneQuit() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 q");
    StringBuffer out = new StringBuffer();
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

  // Create portfolio BuyStock.
  @Test
  public void testBuyAStock() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 45 2016-12-29 3:00:23 WFC q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "Quitting the application", out.toString());
  }

  // Create portfolio BuyStock with 1 parameter.
  @Test
  public void testBuyStock1() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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


  // Create portfolio BuyStock 2 Parameters.
  @Test
  public void testBuyAStock2Parameters() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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


  // Create portfolio BuyStock 3 parameters.
  @Test
  public void testBuyAStock3Parameters() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort 1290.08 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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


  // Create portfolio BuyStock 4 parameters.
  @Test
  public void testBuyAStock4Parameters() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort 1290.08 34 2016-12-29 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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


  // Create portfolio BuyStock 5 Parameters.
  @Test
  public void testBuyAStock5Parameters() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort 1290.08 34 2016-12-29 3:00:23 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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


  // Create portfolio BuyStock 6 Parameters.
  @Test
  public void testBuyAStockAllParameters() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 56 2016-12-29 3:20:23 WFC q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "Quitting the application", out.toString());
  }


  // Create portfolio BuyStock ExaminePortfolio no Params.
  @Test
  public void testExaminePortfolioNoParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 45 2016-12-29 3:00:23 WFC q 3 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "Quitting the application", out.toString());
  }

  // Create portfolio BuyStock ExaminePortfolio.
  @Test
  public void testExaminePortfolio() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 45 2016-12-29 3:00:23 WFC 3 "
            + "myPort q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis No params.
  @Test
  public void testCostBasisNoParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 34 2016-12-29 3:00:23 WFC 3 "
            + "myPort 4 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis one params.
  @Test
  public void testCostBasisOneParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 45 2016-12-29 3:00:23 WFC 3 myPort 4 "
            + "myPort q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis all params.
  @Test
  public void testCostBasisAllParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 445 2016-12-29 3:00:23 WFC 3 myPort"
            + " 4 "
            + "myPort 2018-11-12 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "34.0\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis GetValue.
  @Test
  public void testGetValueNoParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("A 1 myPort 2 myPort 1290.08 30 2016-12-29 3:00:23 "
            + "WFC 3 myPort 4 "
            + "myPort 2018-11-12 5 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "34.0\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis GetValue.
  @Test
  public void testGetValueTwoParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader(" a 1 myPort 2 myPort 1290.08 45 2016-12-29 3:00:23 WFC 3 myPort"
            + " 4 "
            + "myPort 2018-11-12 5 myPort q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "34.0\n"
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

  // Create portfolio BuyStock ExaminePortfolio CostBasis GetValue.
  @Test
  public void testGetValueAllParams() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort 1290.08 35 2016-12-29 3:00:23 WFC 3 myPort"
            + " 4 "
            + "myPort 2018-11-12 5 myPort 2018-11-12 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "34.0\n"
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
            + "67.0\n"
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


  // Create portfolio BuyStock ExaminePortfolio CostBasis GetValue PortNames.
  @Test
  public void testGetAllPortNames() {
    PortfolioManagement portfolioManagement = new MockModel("A", 34, 67);
    Reader in = new StringReader("a 1 myPort 2 myPort 1290.08 43 2016-12-29 3:00:23 WFC 3 myPort 4 "
            + "myPort 2018-11-12 5 myPort 2018-11-12 6 q");
    StringBuffer out = new StringBuffer();
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
            + "Portfolio myPort is created\n"
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
            + "34.0\n"
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
            + "67.0\n"
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
            + "[A]\n"
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
