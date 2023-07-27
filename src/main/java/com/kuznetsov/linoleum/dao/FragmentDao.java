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

public class FragmentDao implements Dao<Fragment,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(FragmentDao.class);
    private static final FragmentDao INSTANCE = new FragmentDao();

    private static final String SAVE_SQL = "INSERT INTO Fragments(width, length,f_type,layout_name_id) VALUES(?,?,?,?)";
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
    private static final String FIND_ALL_BY_LAYOUT_NAME_SQL = FIND_ALL_SQL +" WHERE f.layout_name_id=?";
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL+" WHERE f.id = ?";
    private static final String DELETE_SQL = "DELETE FROM Fragments WHERE id=?";



    @Override
    public Fragment save(Fragment entity) {
        logger.debug("SAVE/fragment entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, entity.getWidth());
            preparedStatement.setFloat(2, entity.getLength());
            preparedStatement.setString(3, entity.getfType().name());
            preparedStatement.setInt(4, entity.getLayoutName().getId());
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
    public Optional<Fragment> findById(Integer id) {
        logger.debug("FINDBYID/fragment id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Fragment fragment = null;
            if(resultSet.next()){
                fragment = buildFragment(resultSet);
            }
            return Optional.ofNullable(fragment);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public List<Fragment> findAll() {
        logger.debug("FIND_ALL/fragments");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Fragment> fragments = new ArrayList<>();
            while (resultSet.next()){
                fragments.add(buildFragment(resultSet));
            }
            return fragments;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    public List<Fragment> findAllByLayoutNameId(Integer layoutNameid) {
        logger.debug("FIND_ALL_BY_LAYOUT_NAME_ID/fragments_layout_name_id is:{}",layoutNameid);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_LAYOUT_NAME_SQL)) {
            preparedStatement.setInt(1,layoutNameid);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Fragment> fragments = new ArrayList<>();
            while (resultSet.next()){
                fragments.add(buildFragment(resultSet));
            }
            return fragments;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Fragment entity) {

    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/fragment id:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)){
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private Fragment buildFragment(ResultSet resultSet) throws SQLException{
        LayoutName layoutName = new LayoutName(resultSet.getObject(5,Integer.class), //using column idx, because field "id" is repeating
                resultSet.getObject("ln_name",String.class));
        return new Fragment(resultSet.getObject("id",Integer.class)
        ,resultSet.getObject("width",Float.class)
        ,resultSet.getObject("length",Float.class)
        ,FragmentType.valueOf(resultSet.getObject("f_type",String.class))
        ,layoutName);

    }

    public static FragmentDao getInstance(){
        return INSTANCE;
    }
}
