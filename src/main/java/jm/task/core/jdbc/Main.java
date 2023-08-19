package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Sasha", "Rudchenko", (byte) 24);
        usi.saveUser("Masha", "Gurchenko", (byte) 30);
        usi.saveUser("Natasha", "Yakovleva", (byte) 40);
        usi.saveUser("Theo", "Walcott", (byte) 34);
        System.out.println(usi.getAllUsers());
        usi.removeUserById(2);
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
