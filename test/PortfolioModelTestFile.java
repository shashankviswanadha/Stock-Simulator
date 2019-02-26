import org.junit.Test;

import investment.data.DataSources;
import investment.model.PortfolioManagement;
import investment.model.PortfolioManagementImpl;

import static junit.framework.TestCase.assertEquals;

public class PortfolioModelTestFile {

  // Tests on Create Portfolio

  // Null portfolio name.
  @Test(expected = IllegalArgumentException.class)
  public void testNullPortfolioName() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.createPortfolio(null);
  }

  // Empty portfolio name.
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyPortfolioName() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.createPortfolio("");
  }

  // Create Duplicate PortfolioName.
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicatePortfolioName() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.createPortfolio("MyPortfolio");
    myPortfolio.createPortfolio("MyPortfolio");

  }

  // Crete valid portfolios.
  @Test
  public void validPortfolios() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.createPortfolio("Education");
    myPortfolio.createPortfolio("Health");
    myPortfolio.createPortfolio("Retirement");
    assertEquals("[Education, Health, Retirement]",
            myPortfolio.getAllPortfolioNames().toString());
    myPortfolio.buyStockWithCommission("Education", 200, 20,
            "2018-11-13", "12:00:00", "MSFT");
    assertEquals(106.94, myPortfolio.getTotalValue("Education",
            "2018-11-13"));
    assertEquals(126.94, myPortfolio.getTotalCostBasis("Education",
            "2018-11-13"));
  }

  @Test
  public void testCostBasis() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.createPortfolio("myPort");
    myPortfolio.buyStockWithCommission("myPort", 2000.50, 35.45,
            "2018-11-13", "13:00:45", "MSFT");
    assertEquals(1960.37, myPortfolio.getTotalCostBasis("myPort",
            "2018-11-13"));
    assertEquals(0.0, myPortfolio.getTotalCostBasis("myPort",
            "2018-11-10"));
    assertEquals(1960.37, myPortfolio.getTotalCostBasis("myPort",
            "2018-11-23"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAmount() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.createPortfolio("myPort");
    myPortfolio.buyStockWithCommission("myPort", -2000.50, 23.34,
            "2018-11-13", "13:00:45",
            "MSFT");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCommissionFee() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.createPortfolio("myPort");
    myPortfolio.buyStockWithCommission("myPort", 2000.50, -23.34,
            "2018-11-13", "13:00:45", "MSFT");

    myPortfolio.createPortfolio("myPort");
    assertEquals("[myPort]", myPortfolio.getAllPortfolioNames().toString());
    assertEquals(1726.94, myPortfolio.getTotalCostBasis("Retirement",
            "2013-01-29"));
    assertEquals(2679.74, myPortfolio.getTotalCostBasis("Retirement",
            "2017-01-25"));
    assertEquals(3034.75, myPortfolio.getTotalValue("Retirement",
            "2018-11-13"));
    myPortfolio.buyStockWithCommission("HEalth", -2000.50, 23.34,
            "2018-11-13", "13:00:45",
            "MSFT");

  }

  @Test
  public void testTradingWCommission() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);

    // Create a portfolio.
    myPortfolio.createPortfolio("Retirement");
    assertEquals(0.0, myPortfolio.getTotalValue("Retirement",
            "2018-11-14"));
    assertEquals("[Retirement]", myPortfolio.getAllPortfolioNames().toString());
    assertEquals(0.0, myPortfolio.getTotalCostBasis("Retirement",
            "2018-11-14"));
    assertEquals(0.0, myPortfolio.getTotalValue("Retirement",
            "2018-11-14"));

    // Buy a MSFT stock.
    myPortfolio.buyStockWithCommission("Retirement", 1000, 40,
            "2017-01-24", "9:00:00", "MSFT");
    assertEquals(992.8, myPortfolio.getTotalCostBasis("Retirement",
            "2018-11-23"));
    assertEquals("[Retirement]", myPortfolio.getAllPortfolioNames().toString());
    assertEquals(992.8, myPortfolio.getTotalCostBasis("Retirement",
            "2017-01-25"));
    assertEquals(1604.1, myPortfolio.getTotalValue("Retirement",
            "2018-11-13"));

    // Buy a BEN stock.
    myPortfolio.buyStockWithCommission("Retirement", 1000, 70,
            "2013-01-28", "11:00:00", "BEN");
    assertEquals("[Retirement]", myPortfolio.getAllPortfolioNames().toString());
    assertEquals(1022.07, myPortfolio.getTotalCostBasis("Retirement",
            "2013-01-29"));
    assertEquals(2014.87, myPortfolio.getTotalCostBasis("Retirement",
            "2017-01-25"));
    assertEquals(1821.73, myPortfolio.getTotalValue("Retirement",
            "2018-11-13"));

    // Buy a WFC stock.
    myPortfolio.buyStockWithCommission("Retirement", 800, 40,
            "2010-04-20", "13:03:24", "WFC");
    assertEquals("[Retirement]", myPortfolio.getAllPortfolioNames().toString());
    assertEquals(1836.94, myPortfolio.getTotalCostBasis("Retirement",
            "2013-01-29"));
    assertEquals(2829.74, myPortfolio.getTotalCostBasis("Retirement",
            "2017-01-25"));
    assertEquals(3034.75, myPortfolio.getTotalValue("Retirement",
            "2018-11-13"));

    // Create another Portfolio Health.
    myPortfolio.createPortfolio("Home");
    myPortfolio.buyStockWithCommission("Home", 5450, 67,
            "2017-08-14", "11:45:09", "BAC");
    assertEquals("[Retirement, Home]", myPortfolio.getAllPortfolioNames().toString());
    // Check for Retirement.
    assertEquals(2614.08, myPortfolio.getTotalValue("Retirement",
            "2017-08-15"));
    // Check for Home
    assertEquals(5512.66, myPortfolio.getTotalCostBasis("Home",
            "2017-08-15"));
    assertEquals(6190.48, myPortfolio.getTotalValue("Home",
            "2018-11-13"));
  }

  @Test
  public void testSavePortfolio() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.createPortfolio("port");
    myPortfolio.buyStockWithCommission("port", 3000, 40,
            "2013-01-28", "11:00:00", "BEN");
    assertEquals("[port]", myPortfolio.getAllPortfolioNames().toString());
    myPortfolio.savePortfolio("port");
  }

  @Test
  public void testRetrievePortfolio() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.retrievePortfolio("port.json");
    assertEquals("[port]", myPortfolio.getAllPortfolioNames().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRetrievePortfolioFail() {
    PortfolioManagement myPortfolio = new PortfolioManagementImpl();
    myPortfolio.setDataSource(DataSources.FILE);
    myPortfolio.retrievePortfolio("myport.json");
  }
}
