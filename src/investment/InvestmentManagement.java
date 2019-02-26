package investment;

import java.io.InputStreamReader;

import investment.controller.GUIController;
import investment.controller.InvestmentController;
import investment.controller.InvestmentControllerImpl;
import investment.model.PortfolioManagement;
import investment.model.PortfolioManagementImpl;
import investment.view.GUIView;
import investment.view.IGView;
import investment.view.InvestmentView;
import investment.view.InvestmentViewImpl;

/**
 * This is the class which has the main method.
 */
public class InvestmentManagement {
  /**
   * This is the main method of the project.
   * @param args -view gui/console
   */
  public static void main(String[] args) {
    if (args[0].equals("-view")) {
      switch (args[1]) {
        case "gui":
          IGView view = new GUIView("Investment for Dummies!");
          PortfolioManagement model = new PortfolioManagementImpl();
          try {
            GUIController controller = new GUIController(model,view);
            controller.execute();
          }
          catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
            return;
          }
          break;
        case "console":
          model = new PortfolioManagementImpl();
          InvestmentView view2 = new InvestmentViewImpl(new InputStreamReader(System.in),
                  System.out);
          InvestmentController controller2 = new InvestmentControllerImpl(model, view2);
          controller2.execute();
          break;
        default:
          System.out.println("Invalid input.");
          return;
      }
    }


  }
}
