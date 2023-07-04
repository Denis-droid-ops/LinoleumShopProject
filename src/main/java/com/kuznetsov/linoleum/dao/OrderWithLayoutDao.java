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

public class OrderWithLayoutDao implements Dao<OrderWithLayout,Integer> {
    private final Logger logger = LoggerFactory.getLogger(OrderWithLayoutDao.class);
    private static final OrderWithLayoutDao INSTANCE = new OrderWithLayoutDao();
    private final OrderDao orderDao = OrderDao.getInstance();

    private static final String SAVE_SQL = "INSERT INTO Orders_with_layout(id,layout_id) VALUES(?,?)";
    private static final String FIND_ALL_SQL = """
                       WITH layouts_layoutsNames AS (SELECT la.id,la.city,la.street,la.home_num,la.room_count,la.row_type,la.l_type,layn.id AS layn_id,layn.ln_name FROM Layouts la
                       			LEFT JOIN Layouts_names layn ON la.layout_name_id = layn.id),
                       		    orders_with_layout_layoutNames AS (SELECT 
                       		                                owl.id,
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
                       		                            ON owl.layout_id=ll.id)
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
                              owl.ln_name
                              
                       FROM Orders o
                                LEFT JOIN Users u
                                          ON o.user_id=u.id
                                LEFT JOIN Linoleums l
                                          ON o.linoleum_id=l.id
                                LEFT JOIN orders_with_layout_layoutNames owl
                                          ON o.id = owl.id
                   """;

    @Override
    public OrderWithLayout save(OrderWithLayout entity) {
        logger.debug("SAVE/order with layout entity:{}",entity);
        OrderWithLayout intermediateOrder = (OrderWithLayout) orderDao.save(entity);
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL)){
            preparedStatement.setInt(1,intermediateOrder.getId());
            preparedStatement.setInt(2,entity.getLayout().getId());
            preparedStatement.executeUpdate();
            return intermediateOrder;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<OrderWithLayout> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<OrderWithLayout> findAll() {
        logger.debug("FIND_ALL/orders with layout");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<OrderWithLayout> orders = new ArrayList<>();
            while (resultSet.next()){
                orders.add(buildOrderWithLayout(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(OrderWithLayout entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    private OrderWithLayout buildOrderWithLayout(ResultSet resultSet) throws SQLException {
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

        LayoutName layoutName = new LayoutName(resultSet.getObject("id",Integer.class),
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

    public static OrderWithLayoutDao getInstance(){
        return INSTANCE;
    }
}
