package com.namit.awsmysql;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowActivity extends AppCompatActivity {
    TextView t1;


    public static final String DATABASE_NAME = "dexter_live";
    public static final String url = "jdbc:mysql://cargobee.cygd38p10i6h.us-east-1.rds.amazonaws.com:3306/" +
            DATABASE_NAME;
    public static final String username = "admin", password = "e$3B6WsGb21A#rz8NKx7D";

    public static final String TABLE_NAME = "master_customer_operation";
//    public final String name_COLUMN = "name";
//    public final String place_COLUMN = "place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        t1 = findViewById(R.id.showTextView1);

        try {
            utilFun();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void utilFun() throws SQLException {
        new Thread(() -> {
            //do your work

            StringBuilder records = new StringBuilder();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
                while (rs.next()) {
                   // records.append("Name: ").append(rs.getString(1)).append(", Place: ").append(rs.getString(3)).append("\n");
                    Log.d("Gokulnathan2-->","" +rs.getString(1)+rs.getString(2));
                    records.append(rs.getString(2));
                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // after the job is finished:

                    t1.setText(records.toString());
                }
            });
        }).start();


    }

    public static void addTemp(String name_str, String place_str) {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                // add to RDS DB:

                statement.execute("INSERT INTO " + TABLE_NAME + "(name, place) VALUES('" + name_str + "', '" + place_str + "')");

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
