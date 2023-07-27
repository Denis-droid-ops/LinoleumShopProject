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
    private static final String     SAVE_SQL = "INSERT INTO Orders(creating_date,status,transporting,transporting_date" +
            ",cost,apartment_num,user_id,linoleum_id) VALUES(?,?,?,?,?,?,?,?)";
    //private static final String SAVE_SQL_WITH_DELIVERY_ADDRESS = SAVE_SQL+"; INSERT INTO Orders_with_delivery_address(delivery_address_id) VALUES(?)";
    //private static final String SAVE_SQL_WITH_LAYOUT = "INSERT INTO Orders(layout_id) VALUES(?)";


    private static final String FIND_ALL_SQL = """
                       WITH layouts_layoutsNames AS (SELECT la.id,la.city,la.street,la.home_num,la.room_count,la.row_type,la.l_type,layn.id AS layn_id,layn.ln_name FROM Layouts la
                                              			LEFT JOIN Layouts_names layn ON la.layout_name_id = layn.id),
                                              		    orders_with_layout_layoutNames AS 
                                              		                       (SELECT  owl.id,
                                              		                                owl.layout_id,
                                              		                                ll.id AS ll_id,
                                              		                                ll.city,
                                              		                                ll.street,
                                              		                                ll.home_num,
                                              		                                ll.room_count,
                                              		                                ll.row_type,
                                              		                                ll.l_type,
                                              		                                ll.layn_id,
                                              		                                ll.ln_name
                                              		                            FROM Orders_with_layout owl
                                              		                            LEFT JOIN layouts_layoutsNames ll
                                              		                            ON owl.layout_id=ll.id),
                       								orders_with_delivery_address AS 
                       								                          (SELECT owd.id,
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
                                                     owl.id,
                                                     owl.layout_id,
                                                     owl.ll_id,
                                                     owl.city,
                                                     owl.street,
                                                     owl.home_num,
                                                     owl.room_count,
                                                     owl.row_type,
                                                     owl.l_type,
                                                     owl.layn_id,
                                                     owl.ln_name,
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
                                                       LEFT JOIN orders_with_layout_layoutNames owl
                                                                 ON o.id = owl.id
                       								   LEFT JOIN orders_with_delivery_address owd
                                                                 ON o.id = owd.id         
                                       
                   """;
    private static final String FIND_ALL_UNREFERENCED_SQL =
            """
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
                              owl.id,
                              owd.id
                           FROM Orders o
                                LEFT JOIN Users u
                                          ON o.user_id=u.id
                                LEFT JOIN Linoleums l
                                          ON o.linoleum_id=l.id
                                LEFT JOIN Orders_with_layout owl
                                		  ON o.id = owl.id
                                LEFT JOIN Orders_with_delivery_address owd
                                ON o.id = owd.id
                                WHERE owl.id IS NULL
                                AND owd.id IS NULL          
                                      
                   """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE o.id = ?";
    private static final String UPDATE_SQL = "UPDATE Orders SET status=?, transporting=?, transporting_date=?, linoleum_id=? WHERE id=?";
    private static final String UPDATE_STATUS_SQL = "UPDATE Orders SET status=? WHERE id=?";
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

    public void updateStatus(Integer orderId,OrderStatus orderStatus) {
        logger.debug("UPDATE_STATUS/order status:{}",orderStatus);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_SQL)){
            preparedStatement.setObject(1,orderStatus.name());
            preparedStatement.setInt(2,orderId);
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

        Order order = new Order(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("creating_date",LocalDateTime.class)
                ,OrderStatus.valueOf(resultSet.getObject("status",String.class))
                ,OrderTransporting.valueOf(resultSet.getObject("transporting",String.class))
                ,resultSet.getObject("transporting_date",LocalDateTime.class)
                ,resultSet.getObject("cost",Integer.class)
                ,resultSet.getObject("apartment_num",Integer.class)
                ,user
                ,linoleum);
        if(resultSet.getObject("layout_id",Integer.class)!=null){
            LayoutName layoutName = new LayoutName(resultSet.getObject("layn_id",Integer.class),
                    resultSet.getObject("ln_name",String.class));
            Layout layout = new Layout(resultSet.getObject("id",Integer.class)
                    ,resultSet.getObject("city",String.class)
                    ,resultSet.getObject("street",String.class)
                    ,resultSet.getObject("home_num",String.class)
                    ,resultSet.getObject("room_count",Integer.class)
                    ,LayoutRowType.valueOf(resultSet.getObject("row_type",String.class))
                    ,LayoutType.valueOf(resultSet.getObject("l_type",String.class))
                    ,layoutName);
            return new OrderWithLayout(resultSet.getObject("id",Integer.class)
                    ,resultSet.getObject("creating_date", LocalDateTime.class)
                    ,OrderStatus.valueOf(resultSet.getObject("status",String.class))
                    ,OrderTransporting.valueOf(resultSet.getObject("transporting",String.class))
                    ,resultSet.getObject("transporting_date",LocalDateTime.class)
                    ,resultSet.getObject("cost",Integer.class)
                    ,resultSet.getObject("apartment_num",Integer.class)
                    ,user
                    ,linoleum
                    ,layout);
        }
        if(resultSet.getObject("delivery_address_id",Integer.class)!=null){
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

        return order;

    }

    public static OrderDao getInstance(){
        return INSTANCE;
    }
}
