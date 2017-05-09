package model;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;


public class ReportModel {
	public static void prepareReports(){
		Connection connection = Model.instance.getConnection();

		JasperReportBuilder report1 = DynamicReports.report();//a new report
		report1
		  .columns(
		  	Columns.column("Profit", "totprice", DataTypes.stringType()),
		    Columns.column("Quantity", "totquantity", DataTypes.integerType())
		  		.setHorizontalAlignment(HorizontalAlignment.LEFT)	
		  )
		  .title(//title of the report
				  Components.text("BookStore Report\n\nThe total sales for books in the previous month\n\n")
		  			.setHorizontalAlignment(HorizontalAlignment.CENTER)
		  )
		  .pageFooter(Components.pageXofY())//show page number on the page footer
		  .setDataSource("SELECT SUM(bp.Quantity*b.price) as totprice, SUM(bp.Quantity) AS totquantity "
		  		+ "FROM purchases as p "
		  		+ "JOIN book_has_purchases as bp ON purchases_id=p.id "
		  		+ "JOIN book as b ON b.isbn=bp.isbn "
		  		+ "WHERE  p.Created >= date_sub(CURRENT_DATE, INTERVAL 1 MONTH) ;", connection);

		
		
		JasperReportBuilder report2 = DynamicReports.report();//a new report
		report2
		  .columns(
		  	Columns.column("Username", "username", DataTypes.stringType())
		  		.setHorizontalAlignment(HorizontalAlignment.LEFT),
		  	Columns.column("FirstName", "firstname", DataTypes.stringType()),
		  	Columns.column("LastName", "lastname", DataTypes.stringType()),
		    Columns.column("Total Paid", "totpaid", DataTypes.integerType())
	  		.setHorizontalAlignment(HorizontalAlignment.LEFT)	,
		    Columns.column("Quantity", "totquantity", DataTypes.integerType())
		  		.setHorizontalAlignment(HorizontalAlignment.LEFT)	
		  )
		  .title(//title of the report
				  Components.text("BookStore Report\n\nThe top 5 customers who purchase the most purchase amount in descending order for the last three months\n\n")
		  			.setHorizontalAlignment(HorizontalAlignment.CENTER)
		  )
		  .pageFooter(Components.pageXofY())//show page number on the page footer
		  .setDataSource("SELECT u.username, u.firstname, u.lastname, SUM(bp.Quantity*b.price) AS totpaid, SUM(bp.Quantity) AS totquantity  "
		  		+ "FROM purchases as p  "
		  		+ "JOIN book_has_purchases as bp ON purchases_id=p.id "
		  		+ " JOIN book as b ON b.isbn=bp.isbn  "
		  		+ "JOIN user as u ON u.username=p.username "
		  		+ "WHERE p.Created >= date_sub(current_date, INTERVAL 3 MONTH) "
		  		+ " GROUP BY u.username ORDER BY SUM(bp.quantity*b.price) DESC LIMIT 5;", connection);

		
		
		
		JasperReportBuilder report3 = DynamicReports.report();//a new report
		report3
		  .columns(
		  	Columns.column("ISBN", "isbn", DataTypes.stringType())
		  		.setHorizontalAlignment(HorizontalAlignment.LEFT),
		  	Columns.column("Title", "title", DataTypes.stringType()),
		    Columns.column("Quantity", "totquantity", DataTypes.integerType())
		  		.setHorizontalAlignment(HorizontalAlignment.LEFT)	
		  )
		  .title(//title of the report
				  Components.text("BookStore Report\n\nThe top 10 selling books for the last three months\n\n")
		  			.setHorizontalAlignment(HorizontalAlignment.CENTER)
		  )
		  .pageFooter(Components.pageXofY())//show page number on the page footer
		  .setDataSource("SELECT b.isbn, b.title, SUM(bp.Quantity) AS totquantity "
		  		+ " FROM purchases as p "
		  		+ " JOIN book_has_purchases as bp ON purchases_id=p.id  "
		  		+ "JOIN book as b ON b.isbn=bp.isbn"
		  		+ " WHERE p.Created >= date_sub(current_date, INTERVAL 3 MONTH)"
		  		+ "GROUP BY b.isbn "
		  		+ "ORDER BY SUM(bp.quantity) DESC LIMIT 10", connection);

		
		showReport(report1, "report1");
		showReport(report2, "report2");
		showReport(report3, "report3");
	}
	private static void showReport(JasperReportBuilder report, String reportName){
		try {
			report.show(false);//show the report
			report.toPdf(new FileOutputStream(reportName+".pdf"));//export the report to a pdf file
		} catch (DRException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ReportModel s = new ReportModel();
		s.prepareReports();
	}

}
