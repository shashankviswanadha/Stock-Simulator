The main package investment has four subpackages - controller, data and model.

Controller package:
- It has an interface InvestmentController and a class InvestmentControllerImpl.
- The InvestmentController interface has one method go - which takes in a model and a datasource as
arguments. The model and view interacts with the controller using this interface. The
InvestmentControllerImpl is a concrete implementation of this interface.

Data package:
- It has an interface Data. This interface has one method which takes in a ticker symbol and date
for a particular stock and returns the current price of the stock.
- The idea of having this Data interface is that it can have multiple classes implementing this and
each of this class can get data from a different source (like file, API, etc).

Model package:
- It has a main model interface PortfolioManagement which offers several methods. It consists of a
group of Portfolios and each portfolio in turn has a group of stocks. The collection of portfolios
is represented bt the Portfolio interface and PortfolioImpl is the implementation of this interface.
The PortfolioManagement Interface is implemented by PortfolioManagementImpl class.

Design Features:
- When the program begins it starts by creating a new InvestmentController object which takes in a
  new PortfolioManagementImpl object as a parameter. It also takes an input from the user as to
  which data source the program is supposed to use. The model uses the setDataSource method to
  create the corresponding Data object (eg. for data from file the model creates a DataFromFile
  object based on the input from the user.)

- This ensures that the logic of the model is independent from where the program gets its data from.
  It also ensures minimal changes to the code on adding a new Data source.


--------------------->>>> Updated version of Investment Manager 2.0 <<<-----------------------------

Included a new subpackage - view in the investment package.

View Package:
- It has an interface InvestmentView and its implementation InvestmentViewImpl.
- The purpose of the view is to get all the required data from the user like choice, portfolio name,
 amount, date, time, ticker name, data source, tickers, weights, frequency and
commission.
- The view is also responsible for displaying the output to the user using Appendable object.


Other Features added:

1. Included a feature to invest a fixed amount into the existing portfolio containing multiple
stocks, using a specified weight for each stock in the portfolio. For instance, the user can create
a custom portfolio consisting of any number of stocks, say FANG portfolio (Facebook, Apple, Netflix,
Google) and then specify to invest $2000 in the portfolio, such that 40% goes towards Facebook, 20%
towards Apple, 30% towards Netflix and 10% towards Google).

2. Added a feature to invest in the existing portfolio using equal weights for all stocks in the
portfolio. For example, “invest $2000 in this portfolio by weighing each stock equally (e.g. 25%
each in the FANG portfolio above)".

3. Included a Higher level investment strategy - dollar-cost averaging. For instance, the user may
specify to create a portfolio of 10 stocks, and invest $2000 in the portfolio every 30 days
starting on Jan 1 2012 until Dec 31 2015 using the same, fixed weights. This application supports a
different amount, different frequency and time range. Furthermore, The strategy may be ongoing, i.e.
the user may not specify an end date.

4. Commission fee for a transaction is included for purchasing a stock.

5. Integrated the Alpha Vantage API, with the possibility of using other APIs and data sources in
the future.

Other key features:

- For Periodic investment strategies, if the transaction is done on a holiday, the application
chooses the next available day to invest.
- All stocks are purchased using the end-of-day prices.


--------------------->>>> Updated version of Investment Manager 3.0 <<<-----------------------------

1. We have moved the strategies into the model. Previously our strategies were designed using
command design patten within our controller. In this version, we moved it to the model to handle
persistence.

2. Added the features to save and retrieve portfolios, save and retrieve investment strategies. We
have chosen to save the files in JSON format because they are less verbose and it is text based.

3. Designed a new Graphical User Interface view. We created a new Interface for GUI view so that it
supports the new GUI. In order for the console based view also to co-exist we created a new
interface and implementation for GUI based Interface.

