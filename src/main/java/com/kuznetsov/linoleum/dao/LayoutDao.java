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

public class LayoutDao implements Dao<Layout,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(LayoutDao.class);
    private static final LayoutDao INSTANCE = new LayoutDao();

    private static final String SAVE_SQL = "INSERT INTO Layouts(city,street,home_num,room_count,row_type,l_type,layout_name_id) VALUES(?,?,?,?,?,?,?)";
    private static final String FIND_ALL_SQL = """
                         SELECT l.id,
                                l.city,
                                l.street,
                                l.home_num,
                                l.room_count,
                                l.row_type,
                                l.l_type,
                                l.layout_name_id,
                                la.id,
                                la.ln_name
                                FROM Layouts l 
                                JOIN Layouts_names la 
                                ON l.layout_name_id = la.id
""";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE l.id = ?";
    private static final String UPDATE_SQL = "UPDATE Layouts SET city=? WHERE id=?";
    private static final String UPDATE_L_TYPE_SQL = "UPDATE Layouts SET l_type=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM Layouts WHERE id=?";
    private static final String FIND_ALL_TEMPLATE_SQL = FIND_ALL_SQL+" WHERE l.l_type=?";
    private static final String FIND_BY_MANY_FIELDS_SQL = FIND_ALL_SQL+ """
                                WHERE city = ? 
                                AND street = ? AND home_num = ?
                                AND room_count = ? 
                                AND row_type = ?
""";

    @Override
    public Layout save(Layout entity) {
        logger.debug("SAVE/layout entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setString(2, entity.getStreet());
            preparedStatement.setString(3, entity.getHomeNum());
            preparedStatement.setInt(4, entity.getRoomCount());
            preparedStatement.setString(5, entity.getLayoutRowType().name());
            preparedStatement.setString(6, entity.getlType().name());
            preparedStatement.setInt(7, entity.getLayoutName().getId());
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
    public Optional<Layout> findById(Integer id) {
        logger.debug("FINDBYID/layout id: {}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Layout layout = null;
            if(resultSet.next()){
                layout = buildLayout(resultSet);
            }
            return Optional.ofNullable(layout);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }


    public Optional<Layout> findByManyFields(String city,String street,String homeNum,Integer roomCount
    ,LayoutRowType layoutRowType) {
        logger.debug("FIND_BY_MANY_FIELDS/layout city: {},layout street: {},layout homeNum: {},layout roomCount: {}" +
                ",layout rowType: {}",city,street,homeNum,roomCount,layoutRowType);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MANY_FIELDS_SQL)) {
            preparedStatement.setString(1,city);
            preparedStatement.setString(2,street);
            preparedStatement.setString(3,homeNum);
            preparedStatement.setInt(4,roomCount);
            preparedStatement.setObject(5,layoutRowType.name());

            ResultSet resultSet = preparedStatement.executeQuery();
            Layout layout = null;
            if(resultSet.next()){
                layout = buildLayout(resultSet);
            }
            return Optional.ofNullable(layout);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Layout> findAll() {
        logger.debug("FIND_ALL/layouts");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Layout> layouts = new ArrayList<>();
            while (resultSet.next()){
                layouts.add(buildLayout(resultSet));
            }
            return layouts;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    public List<Layout> findAllTemplate() {
        logger.debug("FIND_ALL_TEMPLATE/layouts");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TEMPLATE_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Layout> layouts = new ArrayList<>();
            while (resultSet.next()){
                layouts.add(buildLayout(resultSet));
            }
            return layouts;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Layout entity) {
        logger.debug("UPDATE/layout entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
            preparedStatement.setObject(1,entity.getCity());
            preparedStatement.setInt(2,entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }


    public void updateLType(Integer layoutId,LayoutType layoutType) {
        logger.debug("UPDATE_L_TYPE/layoutId:{},layoutType:{}",layoutId,layoutType);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_L_TYPE_SQL)){
            preparedStatement.setObject(1,layoutType.name());
            preparedStatement.setInt(2,layoutId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/layout id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private Layout buildLayout(ResultSet resultSet) throws SQLException {
        LayoutName layoutName = new LayoutName(resultSet.getObject(8,Integer.class), //using column idx, because field "id" is repeating
                resultSet.getObject("ln_name",String.class));
        Layout layout = new Layout(resultSet.getObject("id",Integer.class)
                ,resultSet.getObject("city",String.class)
                ,resultSet.getObject("street",String.class)
                ,resultSet.getObject("home_num",String.class)
                ,resultSet.getObject("room_count",Integer.class)
                ,LayoutRowType.valueOf(resultSet.getObject("row_type",String.class))
                ,LayoutType.valueOf(resultSet.getObject("l_type",String.class))
                ,layoutName);
        return layout;
    }

    public static LayoutDao getInstance(){
        return INSTANCE;
    }


}
