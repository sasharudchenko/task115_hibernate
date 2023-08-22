package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Handler;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static SessionFactory sessionFactory;
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.setProperty(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydb");
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "root");
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.SHOW_SQL, "false");
            properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.setProperties(properties);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
           sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        }
        return sessionFactory;

    }
    // реализуйте настройку соеденения с БД
}
