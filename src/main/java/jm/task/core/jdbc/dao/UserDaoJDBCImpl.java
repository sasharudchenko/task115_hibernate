package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE if not exists `mydb`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.execute(" drop  table if exists user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
       try( PreparedStatement preparedStatement =  Util.getConnection()
               .prepareStatement("insert into user(name, lastName, age) values (?, ?, ?)");) {

           preparedStatement.setString(1, name);
           preparedStatement.setString(2, lastName);
           preparedStatement.setByte(3, age);
           preparedStatement.execute();
           System.out.println("User с именем \"" + name + "\" добавлен в базу");


       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = Util.getConnection()
                    .prepareStatement("delete from user where id = ?");
            preparedStatement.setInt(1, (int) id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement statement = Util.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from mydb.user");
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getLong(1));
                u.setName(rs.getString(2));
                u.setLastName(rs.getString(3));
                u.setAge(rs.getByte(4));
                list.add(u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            statement.executeUpdate("delete from user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
