package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements Dao<Order,Integer>{
    private static final Logger logger = LoggerFactory.getLogger(OrderDao.class);
    private static final OrderDao INSTANCE = new OrderDao();
    private static final String SAVE_SQL = "INSERT INTO Orders(creating_date,status,transporting,transporting_date" +
            ",cost,apartment_num,user_id,linoleum_id) VALUES(?,?,?,?,?,?,?,?)";
    //private static final String SAVE_SQL_WITH_DELIVERY_ADDRESS = SAVE_SQL+"; INSERT INTO Orders_with_delivery_address(delivery_address_id) VALUES(?)";
    //private static final String SAVE_SQL_WITH_LAYOUT = "INSERT INTO Orders(layout_id) VALUES(?)";


    private static final String FIND_ALL_SQL = """
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
                              l.image_path
                           FROM Orders o
                                LEFT JOIN Users u
                                          ON o.user_id=u.id
                                LEFT JOIN Linoleums l
                                          ON o.linoleum_id=l.id
                                       
                   """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE o.id = ?";
    private static final String UPDATE_SQL = "UPDATE Orders SET status=?, transporting=?, transporting_date=?, linoleum_id=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM Orders WHERE id=?";

    private OrderDao(){}
    @Override
    public Order save(Order entity) {
        logger.debug("SAVE/order entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setObject(1, entity.getCreatingDate());
            preparedStatement.setObject(2,entity.getStatus().name());
            preparedStatement.setObject(3,entity.getTransporting().name());
            preparedStatement.setObject(4,entity.getTransportingDate());
            preparedStatement.setInt(5,entity.getCost());
            preparedStatement.setInt(6,entity.getApartmentNum());
            preparedStatement.setInt(7,entity.getUser().getId());
            preparedStatement.setInt(8,entity.getLinoleum().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                entity.setId(resultSet.getInt("id"));
            }
            System.out.println(entity.getId());
            return entity;

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }

    }

    @Override
    public Optional<Order> findById(Integer id) {
        logger.debug("FIND_BY_ID/order id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order= null;
            if(resultSet.next()){
              order = buildOrder(resultSet);
            }
         return Optional.ofNullable(order);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Order> findAll() {
        logger.debug("FIND_ALL/orders");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()){
                 orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Order entity) {
        logger.debug("UPDATE/order entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
            preparedStatement.setObject(1,entity.getStatus());
            preparedStatement.setObject(2,entity.getTransporting());
            preparedStatement.setObject(3,entity.getTransportingDate());
            preparedStatement.setInt(4,entity.getLinoleum().getId());
            preparedStatement.setInt(5,entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/order id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
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

        return new Order(resultSet.getObject("id",Integer.class)
        ,resultSet.getObject("creating_date",LocalDateTime.class)
        ,OrderStatus.valueOf(resultSet.getObject("status",String.class))
        ,OrderTransporting.valueOf(resultSet.getObject("transporting",String.class))
        ,resultSet.getObject("transporting_date",LocalDateTime.class)
        ,resultSet.getObject("cost",Integer.class)
        ,resultSet.getObject("apartment_num",Integer.class)
        ,user
        ,linoleum);

    }

    public static OrderDao getInstance(){
        return INSTANCE;
    }
}
