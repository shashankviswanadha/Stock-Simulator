package investment.model;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * This interface represents a strategy for an investment portfolio. It has methods to apply the
 * strategy, get the name of the strategy, save the strategy to a file and retrieve the strategy
 * from a file.
 */
public interface Strategy {

  /**
   * Apply the strategy to a given portfolio name and model.
   *
   * @param m             model in which the portfolio would be present
   * @param portfolioName on which the strategy has to be applied
   * @throws IllegalArgumentException if the portfolio the given model is invalid
   */
  void apply(PortfolioManagement m, String portfolioName) throws IllegalArgumentException;

  /**
   * Retrieve the name of the strategy.
   *
   * @return the name of the strategy as a string
   */
  String getName();

  /**
   * Save the strategy to a file in JSON format.
   */
  void saveToFile();

  /**
   * Retrieve the strategy from a file by taking in the name of the file. The file has to be in JSON
   * format.
   *
   * @param name of the file from which strategy has to be retrieved
   * @return the Strategy
   */
  static Strategy retrieveFromFile(String name) {
    Gson gson = new Gson();
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    Strategy s = null;
    try (Reader reader = new FileReader(name)) {
      // Convert JSON to Java Object
      if (name.contains("DC")) {
        s = gson.fromJson(reader, DollarCostAveraging.class);
      } else if (name.contains("WI")) {
        s = gson.fromJson(reader, WeightedInvestment.class);
      } else if (name.contains("EI")) {
        s = gson.fromJson(reader, EquallyWeightedInvestment.class);
      } else {
        throw new IllegalArgumentException("Invalid file name.");
      }
    } catch (IOException e) {
      e.printStackTrace();

    }
    return s;
  }
}
