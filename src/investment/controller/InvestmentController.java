package investment.controller;

/**
 * This interface represents an Investment controller. It takes in the model and data source in
 * order to create the portfolioManager and perform the operations on it.
 */
public interface InvestmentController {
  /**
   * Execute the  perform operations on it.
   *
   * @throws IllegalStateException if the dataSource or model is null or invalid
   */
  void execute() throws IllegalStateException;
}
