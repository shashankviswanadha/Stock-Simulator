package investment.data;

/**
 * Types of data sources for the Investment Application. Currently it holds two type of data sources
 * - a file and an API from AlphaVantage. Additional data sources could be added in order to scale
 * the application.
 */
public enum DataSources {
  FILE, ALPHA;
}
