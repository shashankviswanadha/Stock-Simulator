//package investment.controller;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//import javax.swing.JFrame;
//
///**
// * This class is used to plot a line chart.
// */
//
//public class LineChart extends JFrame {
//
//  /**
//   * This is the constructor of the class.
//   */
//  public LineChart( String applicationTitle , String chartTitle,double []data, String []dates) {
//    super(applicationTitle);
//    JFreeChart lineChart = ChartFactory.createLineChart(
//            chartTitle,
//            "Days","Value of Portfolio",
//            createDataset(data, dates),
//            PlotOrientation.VERTICAL,
//            true,true,false);
//
//    ChartPanel chartPanel = new ChartPanel( lineChart );
//    chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
//    setContentPane( chartPanel );
//  }
//
//  private DefaultCategoryDataset createDataset(double []data, String []dates) {
//    DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
//    for (int i = 0; i < data.length; i ++) {
//
//      dataset.addValue(data[i],"days",dates[i]);
//    }
//    return dataset;
//  }
//
//}
