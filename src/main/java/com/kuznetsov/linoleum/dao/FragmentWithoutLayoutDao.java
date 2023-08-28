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

public class FragmentWithoutLayoutDao implements Dao<FragmentWithoutLayout,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(FragmentWithoutLayoutDao.class);
    private static final FragmentWithoutLayoutDao INSTANCE = new FragmentWithoutLayoutDao();
    private static final String SAVE_SQL = "INSERT INTO Fragments_without_layout(f_width,f_length,order_id) VALUES(?,?,?)";
    private static final String FIND_ALL_SQL = """
                         SELECT id,
                                f_width,
                                f_length,
                                order_id
                                FROM Fragments_without_layout
                                
""";
    private static final String FIND_ALL_BY_ORDER_ID_SQL = FIND_ALL_SQL+" WHERE order_id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT id,f_width,f_length,order_id FROM Fragments_without_layout WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Fragments_without_layout WHERE id = ?";
    private FragmentWithoutLayoutDao(){}

    @Override
    public FragmentWithoutLayout save(FragmentWithoutLayout entity) {
        logger.debug("SAVE/fragment without layout entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setFloat(1, entity.getfWidth());
                preparedStatement.setFloat(2, entity.getfLength());
                preparedStatement.setInt(3, entity.getOrder().getId());
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
    public Optional<FragmentWithoutLayout> findById(Integer id) {
        logger.debug("FINDBYID/fragment without layout id is:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                FragmentWithoutLayout fragmentWithoutLayout = null;
                if (resultSet.next()){
                    fragmentWithoutLayout = buildFragmentWithoutLayout(resultSet);
                }
                connection.commit();
                return Optional.ofNullable(fragmentWithoutLayout);

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

    public List<FragmentWithoutLayout> findAllByOrderId(Integer orderId){
        logger.debug("FIND_ALL_BY_ORDER_ID/fragment without layout id is:{}",orderId);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_ORDER_ID_SQL)){
                preparedStatement.setInt(1,orderId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<FragmentWithoutLayout> fragmentsWithoutLayout = new ArrayList<>();
                while (resultSet.next()){
                    fragmentsWithoutLayout.add(buildFragmentWithoutLayout(resultSet));
                }
                connection.commit();
                return fragmentsWithoutLayout;

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
    public List<FragmentWithoutLayout> findAll() {
        logger.debug("FIND_ALL");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                List<FragmentWithoutLayout> fragmentsWithoutLayout = new ArrayList<>();
                while (resultSet.next()){
                    fragmentsWithoutLayout.add(buildFragmentWithoutLayout(resultSet));
                }
                connection.commit();
                return fragmentsWithoutLayout;
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
    public void update(FragmentWithoutLayout entity) {

    }

    @Override
    public boolean delete(Integer id) {
        logger.debug("DELETE/fragment without layout id:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
                preparedStatement.setInt(1,id);
                int res = preparedStatement.executeUpdate();
                connection.commit();
                return res>0;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(),e);
                throw new DAOException(e);
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    private FragmentWithoutLayout buildFragmentWithoutLayout(ResultSet resultSet) throws SQLException{
        Order order = new Order();
        order.setId(resultSet.getObject("order_id",Integer.class));
        //order only with id, other fields are null(lazy loading)
        FragmentWithoutLayout fragmentWithoutLayout = new FragmentWithoutLayout(resultSet.getObject("id",Integer.class)
        ,resultSet.getObject("f_width",Float.class)
        ,resultSet.getObject("f_length",Float.class)
        ,order);
        return fragmentWithoutLayout;
    }

    public static FragmentWithoutLayoutDao getInstance(){
        return INSTANCE;
    }
}
