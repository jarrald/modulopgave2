package com.modulopgave2.dal;

import com.modulopgave2.model.Word;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ImportedWordRepository extends RepositoryBase<Word>
{
    public static String DATABASENAME = "modulopgave2";


    @Override
    public Word create(Word entity) {
        return null;
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
                "SELECT * FROM importedword";

        conn = ConnectionFactory.getConnection(DATABASENAME);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//must be updateable
        res = stmt.executeQuery(query);

        while(res.next()) {
            Word word = new Word(res.getString("Value"));

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
