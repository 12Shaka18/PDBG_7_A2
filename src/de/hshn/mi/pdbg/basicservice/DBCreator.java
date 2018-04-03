package de.hshn.mi.pdbg.basicservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBCreator {
    protected static final String[] SQL_DDL_STATEMENTS = {"SET WRITE_DELAY FALSE",
            "CREATE TABLE Department (" +
                    "	departmentID bigint PRIMARY KEY," +
                    "	name varchar(100) NOT NULL" +
                    ");",

            "CREATE TABLE Ward (" +
                    "	wardID bigint PRIMARY KEY," +
                    "	departmentID bigint NOT NULL," +
                    "	name varchar(100) NOT NULL," +
                    "	bedCount bigint NOT NULL," +
                    "	FOREIGN KEY (departmentID) REFERENCES Department(departmentID)" +
                    "		ON DELETE NO ACTION" +
                    "		ON UPDATE CASCADE" +
                    ");",

            "CREATE TABLE Patient (" +
                    "	patientID bigint PRIMARY KEY," +
                    "	insurance varchar(100)," +
                    "	insuranceNumber varchar(50)," +
                    "	firstName varchar(100) NOT NULL," +
                    "	lastName varchar(100) NOT NULL," +
                    "	birthday date" +
                    ");",

            "CREATE TABLE HospitalStay (" +
                    "	hsID bigint PRIMARY KEY," +
                    "	patientID bigint NOT NULL," +
                    "	wardID bigint NOT NULL," +
                    "	admission date NOT NULL," +
                    "	discharge date ," +
                    "	FOREIGN KEY (patientID) REFERENCES Patient(patientID)" +
                    "		ON DELETE NO ACTION" +
                    "		ON UPDATE CASCADE," +
                    "	FOREIGN KEY (wardID) REFERENCES Ward(wardID)" +
                    "		ON DELETE NO ACTION" +
                    "		ON UPDATE CASCADE" +
                    ");",
            "SHUTDOWN" // Specific to HsqlDB
    };

    public static void main(String[] args) throws Exception {
        String url = "jdbc:hsqldb: file :myDBSchemaName";
        new DBCreator().createDB(url, "sa", "");
    }

    public void createDB(String jdbcURL, String user, String password) throws ClassNotFoundException, SQLException {
        // get your connection here and execute your DDL statement


    }

    protected Connection createConnection(String jdbcURL, String user, String password) throws ClassNotFoundException, SQLException {
        // create a connection to your DB here
        String url;
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb",
                    "lschall", "hai123");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return con;

    }


}