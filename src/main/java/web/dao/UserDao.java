package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
List<User> getAllUsers();
User getUserById(int id);
void addUser(User user);
void deleteUser(int id);
void upDateUser(User user);
}
