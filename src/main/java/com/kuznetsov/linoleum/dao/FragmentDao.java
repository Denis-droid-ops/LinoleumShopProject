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

public class FragmentDao implements Dao<Fragment,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(FragmentDao.class);
    private static final FragmentDao INSTANCE = new FragmentDao();

    private static final String SAVE_SQL = "INSERT INTO Fragments(width, length,f_type,layout_name_id) VALUES(?,?,?,?)";
    private static final String SAVE_FRAGMENT_ORDER_SQL = "INSERT INTO Fragments_orders(fragment_id,order_id) VALUES(?,?)";
    private static final String FIND_ALL_SQL = """
                         SELECT f.id,
                                f.width,
                                f.length,
                                f.f_type,
                                f.layout_name_id,
                                la.id,
                                la.ln_name
                                FROM Fragments f 
                                JOIN Layouts_names la 
                                ON f.layout_name_id = la.id
""";

    private static final String FIND_ALL_FRAGMENTS_ORDERS_SQL = """
                                    WITH layouts_layoutsNames AS (SELECT la.id,
                                                                         la.city,
                                                                         la.street,
                                                                         la.home_num,
                                                                         la.room_count,
                                                                         la.row_type,
                                                                         la.l_type,
                                                                         layn.id AS layn_id,
                                                                         layn.ln_name 
                                                                  FROM Layouts la
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
                                    LEFT JOIN Delivery_addresses da 
                                    ON owd.delivery_address_id = da.id),
                                    ordd AS 
                                    (SELECT o.id,
                                            o.creating_date,
                                            o.status,
                                            o.transporting,
                                            o.transporting_date,
                                            o.cost,
                                            o.apartment_num,
                                            o.user_id,
                                            o.linoleum_id,
                                            u.id AS u_id,
                                            u.name,
                                            u.email,
                                            u.password,
                                            u.phone_number,
                                            u.role,
                                            l.id AS l_id,
                                            l.l_name,
                                            l.protect,
                                            l.thickness,
                                            l.price,
                                            l.image_path,
                                            owl.id AS owl_id,
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
                                            owd.id AS owd_id,
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
                                    ON o.id = owd.id),
                                    
                                    fol	AS	
                                (SELECT
                                    fo.id,
                                    fo.fragment_id,
                                    fo.order_id,
                                    ord.id,
                                    ord.creating_date,
                                    ord.status,
                                    ord.transporting,
                                    ord.transporting_date,
                                    ord.cost,
                                    ord.apartment_num,
                                    ord.user_id,
                                    ord.linoleum_id,
                                    ord.u_id,
                                    ord.name,
                                    ord.email,
                                    ord.password,
                                    ord.phone_number,
                                    ord.role,
                                    ord.l_id,
                                    ord.l_name,
                                    ord.protect,
                                    ord.thickness,
                                    ord.price,
                                    ord.image_path,
                                    ord.owl_id,
                                    ord.layout_id,
                                    ord.ll_id,
                                    ord.city,
                                    ord.street,
                                    ord.home_num,
                                    ord.room_count,
                                    ord.row_type,
                                    ord.l_type,
                                    ord.layn_id,
                                    ord.ln_name,
                                    ord.owd_id,
                                    ord.delivery_address_id,
                                    ord.da_id,
                                    ord.d_city,
                                    ord.d_street,
                                    ord.d_home_num
                                    FROM Fragments_orders fo
                                    JOIN ordd ord
                                    ON fo.order_id = ord.id)
                                    
                                    SELECT f.id,
                                           f.width,
                                           f.length,
                                           f.f_type,
                                           f.layout_name_id,
                                           ff.fragment_id,
                                           ff.order_id,
                                           ff.creating_date,
                                           ff.status,
                                           ff.transporting,
                                           ff.transporting_date,
                                           ff.cost,
                                           ff.apartment_num,
                                           ff.user_id,
                                           ff.linoleum_id,
                                           ff.u_id,
                                           ff.name,
                                           ff.email,
                                           ff.password,
                                           ff.phone_number,
                                           ff.role,
                                           ff.l_id,
                                           ff.l_name,
                                           ff.protect,
                                           ff.thickness,
                                           ff.price,
                                           ff.image_path,
                                           ff.owl_id,
                                           ff.layout_id,
                                           ff.ll_id,
                                           ff.city,
                                           ff.street,
                                           ff.home_num,
                                           ff.room_count,
                                           ff.row_type,
                                           ff.l_type,
                                           ff.layn_id,
                                           ff.ln_name,
                                           ff.owd_id,
                                           ff.delivery_address_id,
                                           ff.da_id,
                                           ff.d_city,
                                           ff.d_street,
                                           ff.d_home_num
                                    
                                    FROM Fragments f
                                    JOIN fol ff
                                    ON f.id = ff.fragment_id
                                    
            """;
    private static final String FIND_ALL_FRAGMENTS_ORDERS_BY_ORDER_ID_SQL = FIND_ALL_FRAGMENTS_ORDERS_SQL+" WHERE ff.order_id = ?";
    private static final String FIND_ALL_BY_LAYOUT_NAME_SQL = FIND_ALL_SQL +" WHERE f.layout_name_id=?";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE f.id = ?";
    private static final String UPDATE_SQL = "UPDATE Fragments SET width=?,length=?,f_type=?,layout_name_id=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM Fragments WHERE id=?";



    @Override
    public Fragment save(Fragment entity) {
        logger.debug("SAVE/fragment entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setFloat(1, entity.getWidth());
                preparedStatement.setFloat(2, entity.getLength());
                preparedStatement.setString(3, entity.getfType().name());
                preparedStatement.setInt(4, entity.getLayoutName().getId());
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    entity.setId(resultSet.getInt("id"));
                }
                connection.commit();
                return entity;
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

    public void saveWithOrder(Integer fragmentId,Integer orderId) {
        logger.debug("SAVE/fragmentWithOrder fragmentId:{}, orderId: {}",fragmentId,orderId);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FRAGMENT_ORDER_SQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1,fragmentId);
                preparedStatement.setInt(2,orderId);
                preparedStatement.execute();
                connection.commit();
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
    public Optional<Fragment> findById(Integer id) {
        logger.debug("FINDBYID/fragment id:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                Fragment fragment = null;
                if(resultSet.next()){
                    fragment = buildFragmentLazy(resultSet);
                }
                connection.commit();
                return Optional.ofNullable(fragment);
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
    public List<Fragment> findAll() {
        logger.debug("FIND_ALL/fragments");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Fragment> fragments = new ArrayList<>();
                while (resultSet.next()){
                    fragments.add(buildFragmentLazy(resultSet));
                }
                connection.commit();
                return fragments;
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


    public List<Fragment> findAllWithOrders() {
        logger.debug("FIND_ALL_FRAGMENTS_ORDERS/fragments");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FRAGMENTS_ORDERS_SQL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Fragment> fragments = new ArrayList<>();
                while (resultSet.next()){
                    Fragment fragment = buildFragment(resultSet);
                    if(fragments.contains(fragment)){
                        fragments.stream().filter(f->f.equals(fragment)).forEach(f->f.getOrders().add(fragment.getOrders().get(0)));
                    }else {
                        fragments.add(fragment);
                    }
                }
                connection.commit();
                return fragments;
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

    public List<Fragment> findAllWithOrdersByOrderId(Integer orderId) {
        logger.debug("FIND_ALL_FRAGMENTS_ORDERS_BY_ORDER_ID/orderId is: {}",orderId);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FRAGMENTS_ORDERS_BY_ORDER_ID_SQL)){
                preparedStatement.setInt(1,orderId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Fragment> fragments = new ArrayList<>();
                while (resultSet.next()){
                    Fragment fragment = buildFragment(resultSet);
                    if(fragments.contains(fragment)){
                        fragments.stream().filter(f->f.equals(fragment)).forEach(f->f.getOrders().add(fragment.getOrders().get(0)));
                    }else {
                        fragments.add(fragment);
                    }
                }
                connection.commit();
                return fragments;
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

    public List<Fragment> findAllByLayoutNameId(Integer layoutNameid) {
        logger.debug("FIND_ALL_BY_LAYOUT_NAME_ID/fragments_layout_name_id is:{}",layoutNameid);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_LAYOUT_NAME_SQL)) {
                preparedStatement.setInt(1,layoutNameid);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Fragment> fragments = new ArrayList<>();
                while (resultSet.next()){
                    fragments.add(buildFragmentLazy(resultSet));
                }
                connection.commit();
                return fragments;
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
    public void update(Fragment entity) {
        logger.debug("UPDATE/fragment is:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
                preparedStatement.setFloat(1,entity.getWidth());
                preparedStatement.setFloat(2,entity.getLength());
                preparedStatement.setObject(3,entity.getfType().name());
                preparedStatement.setInt(4,entity.getLayoutName().getId());
                preparedStatement.setInt(5,entity.getId());
                preparedStatement.executeUpdate();
                connection.commit();
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
    public boolean delete(Integer id) {
        logger.debug("DELETE/fragment id:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
                preparedStatement.setInt(1,id);
                int res = preparedStatement.executeUpdate();
                connection.commit();
                return res>0;
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

    private Fragment buildFragment(ResultSet resultSet) throws SQLException{
        LayoutName layoutName = new LayoutName(resultSet.getObject("layout_name_id",Integer.class), //using column idx, because field "id" is repeating
                resultSet.getObject("ln_name",String.class));
           User user = new User(resultSet.getObject("u_id",Integer.class)
                    ,resultSet.getObject("name",String.class)
                    ,resultSet.getObject("email",String.class)
                    ,resultSet.getObject("password",String.class)
                    ,resultSet.getObject("phone_number",Long.class)
                    , Role.valueOf(resultSet.getObject("role",String.class)));

            Linoleum linoleum = new Linoleum(resultSet.getObject("l_id",Integer.class)
                    ,resultSet.getObject("l_name",String.class)
                    ,resultSet.getObject("protect",Float.class)
                    ,resultSet.getObject("thickness",Float.class)
                    ,resultSet.getObject("price",Integer.class)
                    , resultSet.getObject("image_path",String.class));

            Layout layout = new Layout(resultSet.getObject("ll_id",Integer.class)
                    ,resultSet.getObject("city",String.class)
                    ,resultSet.getObject("street",String.class)
                    ,resultSet.getObject("home_num",String.class)
                    ,resultSet.getObject("room_count",Integer.class)
                    ,LayoutRowType.valueOf(resultSet.getObject("row_type",String.class))
                    ,LayoutType.valueOf(resultSet.getObject("l_type",String.class))
                    ,layoutName);
            //for fragment entity(not fragments without layout) create OrderWithLayout
            OrderWithLayout orderWithLayout = new OrderWithLayout(resultSet.getObject("owl_id",Integer.class)
                    ,resultSet.getObject("creating_date", LocalDateTime.class)
                    ,OrderStatus.valueOf(resultSet.getObject("status",String.class))
                    ,OrderTransporting.valueOf(resultSet.getObject("transporting",String.class))
                    ,resultSet.getObject("transporting_date",LocalDateTime.class)
                    ,resultSet.getObject("cost",Integer.class)
                    ,resultSet.getObject("apartment_num",Integer.class)
                    ,user
                    ,linoleum
                    ,layout);
            return new Fragment(resultSet.getObject("id", Integer.class)
                    , resultSet.getObject("width", Float.class)
                    , resultSet.getObject("length", Float.class)
                    , FragmentType.valueOf(resultSet.getObject("f_type", String.class))
                    , layoutName,List.of(orderWithLayout));

    }

    private Fragment buildFragmentLazy(ResultSet resultSet) throws SQLException{
        LayoutName layoutName = new LayoutName(resultSet.getObject("layout_name_id",Integer.class),
                resultSet.getObject("ln_name",String.class));
        return new Fragment(resultSet.getObject("id", Integer.class)
                , resultSet.getObject("width", Float.class)
                , resultSet.getObject("length", Float.class)
                , FragmentType.valueOf(resultSet.getObject("f_type", String.class))
                , layoutName);

        }



    public static FragmentDao getInstance(){
        return INSTANCE;
    }
}
