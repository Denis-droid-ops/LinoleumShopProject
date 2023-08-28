package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.exception.ConnectionException;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderWithDeliveryAddressDao implements Dao<OrderWithDeliveryAddress,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(OrderWithDeliveryAddressDao.class);
    private static final OrderWithDeliveryAddressDao INSTANCE = new OrderWithDeliveryAddressDao();
    private final OrderDao orderDao = OrderDao.getInstance();

    private static final String SAVE_SQL = "INSERT INTO Orders_with_delivery_address(id,delivery_address_id) VALUES(?,?)";
    private static final String FIND_ALL_SQL = """
                       WITH orders_with_delivery_address AS (SELECT 
                                                               owd.id,
                                                               owd.delivery_address_id,
                                                               da.id AS da_id,
                                                               da.d_city,
                                                               da.d_street,
                                                               da.d_home_num 
                                                               FROM Orders_with_delivery_address owd
                       			LEFT JOIN Delivery_addresses da ON owd.delivery_address_id = da.id)
                       		    
                       SELECT o.id,
                              o.creating_date,
                              o.status,
                              o.transporting,
                              o.transporting_date,
                              o.cost,
                              o.apartment_num,
                              o.user_id,
                              o.linoleum_id,
                              u.id,
                              u.name,
                              u.email,
                              u.password,
                              u.phone_number,
                              u.role,
                              l.id,
                              l.l_name,
                              l.protect,
                              l.thickness,
                              l.price,
                              l.image_path,
                              owd.id,
                              owd.delivery_address_id,
                              owd.da_id,
                              owd.d_city,
                              owd.d_street,
                              owd.d_home_num
                              
                       FROM Orders o
                                LEFT JOIN Users u
                                          ON o.user_id=u.id
                                LEFT JOIN Linoleums l
                                          ON o.linoleum_id=l.id
                                JOIN orders_with_delivery_address owd
                                          ON o.id = owd.id
                   """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE o.id = ?";

    @Override
    public OrderWithDeliveryAddress save(OrderWithDeliveryAddress entity) {
        logger.debug("SAVE/order with delivery address entity:{}",entity);
        OrderWithDeliveryAddress intermediateOrder = (OrderWithDeliveryAddress) orderDao.save(entity);
        try (Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL)){
                preparedStatement.setInt(1,intermediateOrder.getId());
                preparedStatement.setInt(2,entity.getDeliveryAddress().getId());
                preparedStatement.executeUpdate();
                connection.commit();
                return intermediateOrder;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
                throw new ConnectionException(e);
            }
        }catch (SQLException e){
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }

    }

    @Override
    public Optional<OrderWithDeliveryAddress> findById(Integer id) {
        logger.debug("FIND_BY_ID/order with delivery address id:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                OrderWithDeliveryAddress orderWithDeliveryAddress= null;
                if(resultSet.next()){
                    orderWithDeliveryAddress = buildOrderWithDeliveryAddress(resultSet);
                }
                connection.commit();
                return Optional.ofNullable(orderWithDeliveryAddress);
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(),e);
                throw new ConnectionException(e);
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    @Override
    public List<OrderWithDeliveryAddress> findAll() {
        logger.debug("FIND_ALL/orders with delivery address");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<OrderWithDeliveryAddress> orders = new ArrayList<>();
                while (resultSet.next()){
                    orders.add(buildOrderWithDeliveryAddress(resultSet));
                }
                connection.commit();
                return orders;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(),e);
                throw new ConnectionException(e);
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    @Override
    public void update(OrderWithDeliveryAddress entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    private OrderWithDeliveryAddress buildOrderWithDeliveryAddress(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("name",String.class)
                ,resultSet.getObject("email",String.class)
                ,resultSet.getObject("password",String.class)
                ,resultSet.getObject("phone_number",Long.class)
                , Role.valueOf(resultSet.getObject("role",String.class)));

        Linoleum linoleum = new Linoleum(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("l_name",String.class)
                ,resultSet.getObject("protect",Float.class)
                ,resultSet.getObject("thickness",Float.class)
                ,resultSet.getObject("price",Integer.class)
                , resultSet.getObject("image_path",String.class));

        DeliveryAddress deliveryAddress = new DeliveryAddress(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("d_city",String.class)
                ,resultSet.getObject("d_street",String.class)
                ,resultSet.getObject("d_home_num",String.class));

        return new OrderWithDeliveryAddress(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("creating_date", LocalDateTime.class)
                ,OrderStatus.valueOf(resultSet.getObject("status",String.class))
                ,OrderTransporting.valueOf(resultSet.getObject("transporting",String.class))
                ,resultSet.getObject("transporting_date",LocalDateTime.class)
                ,resultSet.getObject("cost",Integer.class)
                ,resultSet.getObject("apartment_num",Integer.class)
                ,user
                ,linoleum
                ,deliveryAddress);
    }

    public static OrderWithDeliveryAddressDao getInstance(){
        return INSTANCE;
    }
}
