package com.modulopgave2.dal;

import com.modulopgave2.model.Letter;
import com.modulopgave2.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class WordRepositoryV2 extends WordRepository
{
    public static String DATABASENAME = "modulopgave2";


    @Override
    public Word create(Word entity) throws Exception {
        char[] characters = entity.getValue().toCharArray();

        super.create(entity);


        for (int i = 0; i < characters.length; i++) {
            try {
                String QUERY = "INSERT INTO word_letter(Offset, Word_Id, Letter_Id)" +
                        " SELECT ?, word.Id, Letter.Id" +
                        " FROM word" +
                        " INNER JOIN letter AS Letter" +
                        " ON Letter.Value = ?" +
                        " WHERE word.Value = ?;";

                Connection connection = ConnectionFactory.getConnection(DATABASENAME);
                PreparedStatement statement = connection.prepareStatement(QUERY,
                        Statement.RETURN_GENERATED_KEYS);

                statement.setInt(1, i);
                statement.setString(2, characters[i] + "");
                statement.setString(3, entity.getValue());
                int affectedRows = statement.executeUpdate();

                statement.close();
                connection.close();

                if (affectedRows == 0) {
                    throw new Exception("Creating word_letter connection " + entity.getValue() +" failed, no rows affected.");
                }
            }
            catch (Exception e) {
                    // if word_letter rows weren't inserted, delete word.
                    this.delete(entity);
                    throw e;
            }
        }


        return entity;
    }

    @Override
    public Collection<Word> list() throws Exception {
        Set<Word> result = new HashSet<>();
        Connection conn;
        Statement stmt;
        ResultSet res;

        Statement stmt2;
        ResultSet res2;

        String query =
                "SELECT * FROM word";
        String query2 = "";

        conn = ConnectionFactory.getConnection(DATABASENAME);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
        res = stmt.executeQuery(query);

        while(res.next()) {
            Word word = new Word(res.getInt("Id"), res.getString("Value"));

            query2 = "SELECT wl.Id AS Id, wl.Offset AS Offset, l.Value AS Value FROM word_letter AS wl INNER JOIN letter AS l ON l.Id = wl.Letter_Id WHERE wl.Word_Id = " + word.getId() + " ORDER BY wl.Offset";

            stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            res2 = stmt2.executeQuery(query2);

            List<Letter> letters = new ArrayList<>();

            while(res2.next()) {
                letters.add(new Letter(res2.getInt("Id"), res2.getString("Value").charAt(0), res2.getInt("Offset")));
            }
            word.setLetters(letters);

            result.add(word);

            //luk JDBC-objekter
            if (res2!=null) res2.close();
            if (stmt2!=null) stmt2.close();
        }

        //luk JDBC-objekter
        if (res!=null) res.close();
        if (stmt!=null) stmt.close();
        if (conn!=null) conn.close();


        return result;
    }

    @Override
    public Collection<Word> find(Word entity) throws Exception {
        return super.find(entity);
    }
}
