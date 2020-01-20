package db;

import java.sql.Connection;
import java.awt.*;
import javax.swing.*;
public class GraphDB {
	DBCon db;
	Connection con;
	public GraphDB(){
		con = db.getInstance();
	}
}
