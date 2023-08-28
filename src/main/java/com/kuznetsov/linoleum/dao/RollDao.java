package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.Linoleum;
import com.kuznetsov.linoleum.entity.Roll;
import com.kuznetsov.linoleum.exception.ConnectionException;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RollDao implements Dao<Roll,Integer>{
    private static final Logger logger = LoggerFactory.getLogger(RollDao.class);
    private static final RollDao INSTANCE = new RollDao();

    private static final String SAVE_SQL = "INSERT INTO Rolls(part_num,r_width,r_length,is_remain,linoleum_id) " +
            "VALUES(?,?,?,?,?)";
    private static final String FIND_ALL  = """
                         SELECT  r.id
                                ,r.part_num
                                ,r.r_width
                                ,r.r_length
                                ,r.is_remain
                                ,r.linoleum_id 
                                ,l.l_name
                                ,l.protect
                                ,l.thickness
                                ,l.price
                                ,l.image_path
                                FROM Rolls r
                                LEFT JOIN Linoleums l
                                ON r.linoleum_id = l.id
    """;
    private static final String FIND_BY_ID_SQL = FIND_ALL+" WHERE r.id = ?";
    private static final String UPDATE_SQL = "UPDATE Rolls SET r_width = ?, r_length = ?, is_remain = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Rolls WHERE id = ?";

    private RollDao(){}

    @Override
    public Roll save(Roll entity) {
        logger.debug("SAVE/roll entity:{}",entity);
        try (Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1,entity.getPartNum());
                preparedStatement.setFloat(2,entity.getWidth());
                preparedStatement.setFloat(3,entity.getLength());
                preparedStatement.setBoolean(4,entity.isRemain());
                preparedStatement.setInt(5,entity.getLinoleum().getId());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()) {
                    entity.setId(resultSet.getInt(1));
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
    public Optional<Roll> findById(Integer id) {
        logger.debug("FIND_BY_ID/roll id is:{}",id);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                Roll roll = null;
                if (resultSet.next()){
                    roll = buildRoll(resultSet);
                }
                connection.commit();
                return Optional.ofNullable(roll);

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
    public List<Roll> findAll() {
        logger.debug("FIND_ALL/Rolls");
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Roll> rolls = new ArrayList<>();
                while (resultSet.next()){
                    rolls.add(buildRoll(resultSet));
                }
                connection.commit();
                return rolls;
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
    public void update(Roll entity) {
        logger.debug("UPDATE/roll entity:{}",entity);
        try(Connection connection = ConnectionManager.getConnection()){
            connection.setAutoCommit(false);
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
                preparedStatement.setFloat(1,entity.getWidth());
                preparedStatement.setFloat(2,entity.getLength());
                preparedStatement.setBoolean(3,entity.isRemain());
                preparedStatement.setInt(4,entity.getId());
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
        logger.debug("DELETE/roll id:{}",id);
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
                throw new ConnectionException(e);
            }
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    private Roll buildRoll(ResultSet resultSet) throws SQLException {
        Linoleum linoleum = new Linoleum(resultSet.getObject("linoleum_id",Integer.class)
                ,resultSet.getObject("l_name",String.class)
                ,resultSet.getObject("protect",Float.class)
                ,resultSet.getObject("thickness",Float.class)
                ,resultSet.getObject("price",Integer.class)
                , resultSet.getObject("image_path",String.class));
        return new Roll(resultSet.getObject("id",Integer.class),
                resultSet.getObject("part_num",Integer.class),
                resultSet.getObject("r_width",Float.class),
                resultSet.getObject("r_length",Float.class),
                resultSet.getObject("is_remain",Boolean.class),
                linoleum);
    }

    public static RollDao getInstance(){
        return INSTANCE;
    }
}
