package com.modulopgave2.dal;

import com.modulopgave2.model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    public Collection<Word> find(Word entity) throws Exception {
        return super.find(entity);
    }
}
