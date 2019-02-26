1. The portfolios and strategies are stored in JSON file format in the application.

2. In our application, the portfolios and strategies are saved as separate files where each file
corresponds to a strategy or a portfolio.

3. Portfolio object description:
   a. The data in the file is stored in key-value pair format.
   b. The keys in the portfolio are - stocks and the name of the portfolio.
   c. Each stock in turn has - tickerSymbol, costBasisOfShare, currentPriceOfShare, numberOfShares,
   dateOfPurchase and commission.
   d. DateOfPurchase has the following - year, month, dayOfMonth, hourOfDay, minute and second.
Example of a file for Portfolio in JSON format:
    {
      "stocks": [
        {
          "tickerSymbol": "AAPL",
          "costBasisOfShare": 192.23,
          "currentPriceOfShare": 192.23,
          "numberOfShares": 271,
          "dateOfPurchase": {
            "year": 2018,
            "month": 10,
            "dayOfMonth": 13,
            "hourOfDay": 0,
            "minute": 0,
            "second": 0
          },
          "commission": 2.0
        }
      ],
      "name": "Test"
    }

4. Strategy object description:
   a. The data in the file is stored in key-value pair format.
   b. The keys in the portfolio are - date, time amount, commission and the name of the
   strategy.

Example of a file for Strategy in JSON format:
{
  "date": "2018-11-13",
  "time": "12:00:00",
  "amount": 122344.0,
  "commission": 45.0,
  "name": "TestSt"
}

5. In order to create a portfolio file manually, give the name of the file and enter the details as
shown in the above example.
    "tickerSymbol": String,
              "costBasisOfShare": Double value with two decimal places,
              "currentPriceOfShare": Double value with two decimal places,
              "numberOfShares": Integer,
              "dateOfPurchase": {
                "year": Integer for the year,
                "month": Integer between 1 to 12,
                "dayOfMonth": Integer depending on the month,
                "hourOfDay": 0 to 23,
                "minute": 0 to 60,
                "second": 0 t0 60
              },
              "commission": Double value with two decimal places

6. In order to create a strategy file manually, give the name of the file and enter the details as
shown in the above example.
    "date": in String format,
      "time": String,
      "amount": Double value,
      "commission": Double value,
      "name": String