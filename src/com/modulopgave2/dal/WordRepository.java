package com.modulopgave2.dal;

import com.modulopgave2.model.Letter;
import com.modulopgave2.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WordRepository extends RepositoryBase<Word>
{
    public static String DATABASENAME = "modulopgave2";


    @Override
    public Word create(Word entity) throws Exception {
        final String QUERY = "INSERT IGNORE INTO word (Value) VALUES (?)";

        try (
                Connection connection = ConnectionFactory.getConnection(DATABASENAME);
                PreparedStatement statement = connection.prepareStatement(QUERY,
                        Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, entity.getValue());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                statement.close();
                connection.close();

                throw new Exception("Creating word " + entity.getValue() +" failed, no rows affected. Word may be a duplicate.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                    generatedKeys.close();
                }
                else
                {
                    generatedKeys.close();
                    throw new Exception("Creating word " + entity.getValue() +" failed, no ID obtained.");
                }

                statement.close();
                connection.close();
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
                "SELECT * FROM word";

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
    public Collection<Word> find(Word entity) throws Exception {
        Collection<Word> result = new HashSet<>();

        // PREPARE QUERY
        String query = "SELECT word.Id, word.Value FROM word";

        if(entity == null || entity.getLetters().size() > 0) {
            // add inner joins
            for(int i=0; i < entity.getLetters().size(); i++) {
                query +=
                        " INNER JOIN word_letter AS word_letter"+ i +" ON word_letter"+ i +".Word_Id = word.Id" +
                        " INNER JOIN letter AS letter"+ i +" ON letter"+ i +".Id = word_letter"+ i +".Letter_Id";
            }

            query += " WHERE (";

            // add where clause
            int i = 0;
            Iterator<Letter> it = entity.getLetters().iterator();
            while (it.hasNext()) {
                Letter letter = it.next();
                if(i != 0)
                    query += " AND ";
                query += "(word_letter"+ i +".Offset = "+ letter.getOffset() +" AND letter"+ i +".Value = '"+ letter.getValue() +"')";
                i++;
            }

            query += ")";
        }

        query +=
                " ORDER BY word.Id;";

        Connection conn = ConnectionFactory.getConnection(DATABASENAME);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
        ResultSet res = stmt.executeQuery(query);

        while(res.next()) {
            Word word = new Word(res.getInt("Id"), res.getString("Value"));
            result.add(word);
        }

        return result;
    }
}
