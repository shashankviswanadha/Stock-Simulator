import org.junit.Test;

import investment.data.AlphaVantage;
import investment.data.Data;

import static junit.framework.TestCase.assertEquals;


public class AlphaVantageTest {

  @Test
  public void testOne() {
    Data d = new AlphaVantage();
    assertEquals(1038.63, d.getCurrentPrice("GOOG", "2018-11-12"));
    assertEquals(1055.81, d.getCurrentPrice("GOOG", "2018-11-06"));
  }

}