package investment.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * This class retrieves the data of a stock from the file.
 */
public class DataFromFile implements Data {
  @Override
  public double getCurrentPrice(String tickerSymbol, String date) throws IllegalArgumentException
          , NoSuchElementException {
    String fileName;
    fileName = tickerSymbol + ".txt";
    double out = 0;
    try {
      String line;
      String[] lineData;
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      while ((line = bufferedReader.readLine()) != null) {
        lineData = line.split("\\,");
        if (lineData[0].equals(date)) {
          out = Double.parseDouble(lineData[4]);
          break;
        }
      }
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException("Unable to open file " + fileName);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    if (out == 0) {
      throw new NoSuchElementException("There is no such entry in file");
    }
    return out;
  }
}