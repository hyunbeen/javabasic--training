package db;

import java.sql.Connection;

public class OrderDB {
	DBCon db;
	Connection con;
	public OrderDB(){
		con = db.getInstance();
	}
}
