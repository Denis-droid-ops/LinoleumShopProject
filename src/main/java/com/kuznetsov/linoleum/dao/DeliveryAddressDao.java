package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryAddressDao implements Dao<DeliveryAddress,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryAddressDao.class);
    private static final DeliveryAddressDao INSTANCE = new DeliveryAddressDao();

    private static final String SAVE_SQL = "INSERT INTO Delivery_addresses(d_city, d_street,d_home_num) VALUES(?,?,?)";
    private static final String FIND_BY_ID_SQL = "SELECT id, d_city, d_street,d_home_num FROM Delivery_addresses WHERE id=?";

    private static final String FIND_ALL  = "SELECT id,d_city,d_street,d_home_num FROM Delivery_addresses";
    private static final String UPDATE_SQL = "UPDATE Delivery_addresses SET d_city = ?,d_street = ?,d_home_num = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Delivery_addresses WHERE id = ?";

    @Override
    public DeliveryAddress save(DeliveryAddress entity) {
        logger.debug("SAVE/delivery address entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getdCity());
            preparedStatement.setString(2, entity.getdStreet());
            preparedStatement.setString(3, entity.getdHomeNum());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                entity.setId(resultSet.getInt("id"));
            }
            return entity;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<DeliveryAddress> findById(Integer id) {
        logger.debug("FINDBYID/delivery address id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            DeliveryAddress deliveryAddress = null;
            if(resultSet.next()){
                deliveryAddress = buildDeliveryAddress(resultSet);
            }
            return Optional.ofNullable(deliveryAddress);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<DeliveryAddress> findAll() {
        logger.debug("FIND_ALL");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DeliveryAddress> deliveryAddresses = new ArrayList<>();
            while (resultSet.next()){
                deliveryAddresses.add(buildDeliveryAddress(resultSet));
            }
            return deliveryAddresses;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(DeliveryAddress entity) {
        logger.debug("UPDATE/delivery entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1,entity.getdCity());
            preparedStatement.setString(2,entity.getdStreet());
            preparedStatement.setString(3,entity.getdHomeNum());
            preparedStatement.setLong(4,entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/delivery address id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private DeliveryAddress buildDeliveryAddress(ResultSet resultSet) throws SQLException{
        DeliveryAddress deliveryAddress = new DeliveryAddress(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("d_city",String.class)
                ,resultSet.getObject("d_street",String.class)
                , resultSet.getObject("d_home_num",String.class)
                );
        return deliveryAddress;
    }

    public static DeliveryAddressDao getInstance(){
        return INSTANCE;
    }
}
