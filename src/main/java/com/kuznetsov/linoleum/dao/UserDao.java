package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.dto.UserFilter;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.util.InitDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao implements Dao<User,Integer>{

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = "INSERT INTO Users(name,email,password,phone_number,role) " +
                                           "VALUES(?,?,?,?,?)";
    private static final String FIND_BY_ID_SQL = "SELECT id,name,email,password,phone_number,role FROM Users WHERE id = ?";
    private static final String FIND_ALL  = "SELECT id,name,email,password,phone_number,role FROM Users";
    private static final String UPDATE_SQL = "UPDATE Users SET name = ?,email = ?,password = ?,phone_number = ?," +
                                             "role = ? WHERE id = ?";
    private static final String UPDATE_ROLE_SQL = "UPDATE Users SET role = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Users WHERE id = ?";
    private static final String FIND_BY_EMAIL_AND_PASSWORD = "SELECT id,name,email,password,phone_number,role FROM Users WHERE email = ? AND password = ?";

    private UserDao(){
       
    }


    @Override
    public User save(User entity) {
        logger.debug("SAVE/user entity:{}",entity);
        try (Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
          preparedStatement.setString(1,entity.getName());
          preparedStatement.setString(2,entity.getEmail());
          preparedStatement.setString(3,entity.getPassword());
          preparedStatement.setLong(4,entity.getPhoneNumber());
          preparedStatement.setObject(5,entity.getRole().name());
          preparedStatement.executeUpdate();
          ResultSet resultSet = preparedStatement.getGeneratedKeys();
          if(resultSet.next()) {
              entity.setId(resultSet.getInt(1));
          }

          return entity;

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        logger.debug("FINDBYID/user id is:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    public Optional<User> findByEmailAndPassword(String email,String password) {
        logger.debug("FINDBYEMAILANDPASSWORD/user email:{}",email);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> findAll() {
        logger.debug("FIND_ALL/Users");
        try(Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
             ResultSet resultSet = preparedStatement.executeQuery();
             List<User> users = new ArrayList<>();
             while (resultSet.next()){
                 users.add(buildUser(resultSet));
             }
             return users;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User entity) {
        logger.debug("UPDATE/user entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getEmail());
            preparedStatement.setString(3,entity.getPassword());
            preparedStatement.setLong(4,entity.getPhoneNumber());
            preparedStatement.setObject(5,entity.getRole().name());
            preparedStatement.setInt(6,entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    public void updateRole(Integer id, Role role) {
        logger.debug("UPDATEROLE/user id:{},user role:{}",id,role);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            preparedStatement.setObject(1,role.name());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/user id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getObject("id",Integer.class),
                        resultSet.getObject("name",String.class),
                        resultSet.getObject("email",String.class),
                        resultSet.getObject("password",String.class),
                        resultSet.getObject("phone_number",Long.class),
                        Role.valueOf(resultSet.getObject("role",String.class)));
    }

    public List<User> findAll(UserFilter userFilter){
        logger.debug("FIND_ALL/users");
        List<Object> params = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if(userFilter.getPhoneNumber()!=0){
            whereSql.add("phoneNumber = ?");
            params.add(userFilter.getPhoneNumber());
        }
        params.add(userFilter.getLimit());
        params.add(userFilter.getOffset());
        String sql=whereSql.stream().collect(Collectors.joining(" AND "," WHERE "," LIMIT ?, OFFSET ?"));
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL+sql)) {
            List<User> list = new ArrayList<>();
            for(int i = 0;i<params.size();i++){
                preparedStatement.setObject(i+1,params.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(buildUser(resultSet));
            }
            return list;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }
    public static UserDao getInstance(){
        return INSTANCE;
    }

}
