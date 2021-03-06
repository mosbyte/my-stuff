package mapsql.sql.test;

import mapsql.sql.condition.LessThan;
import mapsql.sql.condition.Like;
import mapsql.sql.core.Field;
import mapsql.sql.core.SQLException;
import mapsql.sql.core.SQLManager;
import mapsql.sql.core.SQLResult;
import mapsql.sql.core.SQLStatement;
import mapsql.sql.field.CHARACTER;
import mapsql.sql.field.INTEGER;
import mapsql.sql.statement.CreateTable;
import mapsql.sql.statement.Insert;
import mapsql.sql.statement.Select;

public class LikeTest {
static SQLManager manager = new SQLManager();
	
	public static void main(String[] args) 
	{
		createTableStatement();

		showTables();
		insertData();
		insertMyData();
		insertDataB();
		selectTable();
		selectRow();
	}
	
	private static void executeStatement(SQLStatement statement) {
		try {
			SQLResult result = manager.execute(statement);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createTableStatement() {
		executeStatement(
				new CreateTable(
						"contact", 
						new Field[] {
								new INTEGER("id", true, false, true), 
								new CHARACTER("name", 30, false, true), 
								new CHARACTER("email", 30, false, false)
						}
				)
		);
	}
	
	public static void showTables() {
		executeStatement(new Select("mapsql.tables", new String[] { "*" }));
	}
	
	public static void selectTable() {
		executeStatement(new Select("contact", new String[] { "*" }));
	}
	
	public static void insertData() 
	{
		executeStatement(
				new Insert(
						"contact", 
						new String[] {"name", "email"}, 
						new String[] {"Rem", "rem.collier@ucd.ie"}
				)
		);
	}
	public static void insertMyData() 
	{
		executeStatement(
				new Insert(
						"contact", 
						new String[] {"name", "email"}, 
						new String[] {"mossy", "mossy@ucd.ie"}
				)
		);
	}
	public static void insertDataB()
	{
		executeStatement(
				new Insert(
						"contact", 
						new String[] {"name", "email"}, 
						new String[] {"john", "john@ucd.ie"}
				)
		);
	}
	public static void selectRow()
	{
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "%john")));
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "mossy%")));
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "%Rem%")));
		executeStatement(new Select("contact", new String[] { "*" }, new Like("name", "Rem")));
		
	}

}
