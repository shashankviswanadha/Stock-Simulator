import org.junit.Test;

import investment.data.Data;
import investment.data.DataFromFile;

import static junit.framework.TestCase.assertEquals;

public class DataFromFileTest {

  @Test
  public void testOne() {
    Data d = new DataFromFile();
    assertEquals(106.8700, d.getCurrentPrice("MSFT", "2018-11-12"));
  }

}