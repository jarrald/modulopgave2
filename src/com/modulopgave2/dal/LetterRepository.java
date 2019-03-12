package com.modulopgave2.dal;

import com.modulopgave2.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LetterRepository implements Repository<Word>
{
    public static String DATABASENAME = "modulopgave2";


    @Override
    public Word create(Word entity) throws Exception {
        final String QUERY = "INSERT IGNORE INTO letter (Value) VALUES (?)";

        try (
                Connection connection = ConnectionFactory.getConnection(DATABASENAME);
                PreparedStatement statement = connection.prepareStatement(QUERY,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, entity.getValue());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("Creating word " + entity.getValue() +" failed, no rows affected. Word may be a duplicate.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new Exception("Creating word " + entity.getValue() +" failed, no ID obtained.");
                }
            }
        }

        return entity;
    }

    @Override
    public Word read(Word entity) {
        return null;
    }

    @Override
    public void update(Word entity) {

    }

    @Override
    public void delete(Word entity) {

    }

    @Override
    public Collection<Word> list() throws Exception {
        Set<Word> result = new HashSet<>();
        Connection conn;
        Statement stmt;
        ResultSet res;
        String query =
                "SELECT * FROM letter";

        conn = ConnectionFactory.getConnection(DATABASENAME);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
        res = stmt.executeQuery(query);

        while(res.next()) {
            Word word = new Word(res.getInt("Id"), res.getString("Value"));

            result.add(word);
        }

        //luk JDBC-objekter
        if (res!=null) res.close();
        if (stmt!=null) stmt.close();
        if (conn!=null) conn.close();


        return result;
    }

    @Override
    public Collection<Word> find(Word entity) {
        return null;
    }
}
