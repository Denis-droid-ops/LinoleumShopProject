package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.exception.ConnectionException;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LayoutsNamesDao implements Dao<LayoutName,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(LayoutsNamesDao.class);
    private static final LayoutsNamesDao INSTANCE = new LayoutsNamesDao();

    private static final String SAVE_SQL = "INSERT INTO Layouts_names(ln_name) VALUES(?)";
    private static final String FIND_BY_ID_SQL = "SELECT id,ln_name FROM Layouts_names WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id,ln_name FROM Layouts_names";
    private static final String UPDATE_SQL = "UPDATE Layouts_names SET ln_name=? WHERE id=?";
    private static final String DELETE_SQL = "DELETE FROM Layouts_names WHERE id=?";

    @Override
    public LayoutName save(LayoutName entity) {
        logger.debug("SAVE/layoutName entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, entity.getLnName());
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

    @Override
    public Optional<LayoutName> findById(Integer id) {
        logger.debug("FINDBYID/layoutName id: {}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                LayoutName layoutName = null;
                if(resultSet.next()){
                    layoutName = buildLayoutName(resultSet);
                }
                connection.commit();
                return Optional.ofNullable(layoutName);
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
    public List<LayoutName> findAll() {
        logger.debug("FIND_ALL/layouts_names");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)){
                ResultSet resultSet = preparedStatement.executeQuery();
                List<LayoutName> layoutNames = new ArrayList<>();
                while (resultSet.next()){
                    layoutNames.add(buildLayoutName(resultSet));
                }
                connection.commit();
                return layoutNames;
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
    public void update(LayoutName entity) {
        logger.debug("UPDATE/layoutName entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
                preparedStatement.setObject(1,entity.getLnName());
                preparedStatement.setInt(2,entity.getId());
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
        logger.debug("DELETE/layoutName id:{}",id);
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

    private LayoutName buildLayoutName(ResultSet resultSet) throws SQLException {
        return new LayoutName(resultSet.getObject("id", Integer.class),
                resultSet.getObject("ln_name", String.class));
    }

    public static LayoutsNamesDao getInstance(){
        return INSTANCE;
    }
}
