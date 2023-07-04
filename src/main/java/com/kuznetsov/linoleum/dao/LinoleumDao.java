package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.Linoleum;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LinoleumDao implements Dao<Linoleum,Integer> {
    private static final Logger logger = LoggerFactory.getLogger(LinoleumDao.class);
    private static final LinoleumDao INSTANCE = new LinoleumDao();
    private static final String FIND_ALL_SQL = "SELECT id,l_name,protect,thickness,price,image_path FROM Linoleums";
    private static final String FIND_BY_ID = FIND_ALL_SQL+" WHERE id = ?";

    private LinoleumDao(){}
    @Override
    public Linoleum save(Linoleum entity) {
        return null;
    }

    @Override
    public Optional<Linoleum> findById(Integer id) {
        logger.debug("FIND_BY_ID/linoleum id is:{}",id);
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet =  preparedStatement.executeQuery();
            Linoleum linoleum = null;
            if (resultSet.next()){
               linoleum = buildLinoleum(resultSet);
            }
            return Optional.ofNullable(linoleum);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }

    }

    @Override
    public List<Linoleum> findAll() {
        logger.debug("FIND_ALL");
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet =  preparedStatement.executeQuery();
            List<Linoleum> linoleums = new ArrayList<>();
            while (resultSet.next()){
                linoleums.add(buildLinoleum(resultSet));
            }
            return linoleums;
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(e);
        }
    }

    private Linoleum buildLinoleum(ResultSet resultSet) throws SQLException {
        return new Linoleum(resultSet.getObject("id",Integer.class)
        ,resultSet.getObject("l_name",String.class)
        ,resultSet.getObject("protect",Float.class)
        ,resultSet.getObject("thickness",Float.class)
        ,resultSet.getObject("price",Integer.class)
        ,resultSet.getObject("image_path",String.class));
    }

    @Override
    public void update(Linoleum entity) {

    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public static LinoleumDao getInstance(){
        return INSTANCE;
    }
}
