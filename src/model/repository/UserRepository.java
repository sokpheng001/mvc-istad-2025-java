package model.repository;

import model.entities.User;
import utils.DatabaseConnectionConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserRepository implements Repository<User,Integer> {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
    public User findUserById(Integer id){
        String sql = """
                SELECT * FROM users
                WHERE id = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUuid(resultSet.getString("uuid"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
            }
            return user;
        }catch (Exception exception){
            System.out.println("[+] Error during get user by uuid: " + exception.getMessage());
        }
        return null;
    }
    public User findUserByUuid(String uuid){
        String sql = """
                SELECT * FROM users
                WHERE uuid = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUuid(resultSet.getString("uuid"));
                user.setUserName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setCreatedDate(resultSet.getDate("created_date"));
            }
            return user;
        }catch (Exception exception){
            System.out.println("[+] Error during get user by uuid: " + exception.getMessage());
        }
        return null;
    }
}
