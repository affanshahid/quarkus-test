package com.affanshahid.quarkustest;

import java.sql.DriverManager;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public GreetingService() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("============================================");
        System.out.println(System.getenv("DB_URL"));
        var url = System.getenv("DB_URL");
        var connect = DriverManager.getConnection(url);
        var statement = connect.createStatement();
        var resultSet = statement.executeQuery("select 1 as AFFAN;");

        while(resultSet.next()) {
            System.out.println("============================================");
            System.out.println(resultSet.getInt("AFFAN"));
        }

        resultSet.close();
        statement.close();
        connect.close();
    }

    public String getGreeting(String name) {
        return "hello " + name;
    }
}
