package application.DAO;

import application.database.Database;
import application.model.Word;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WordDAO implements WordInterface {
    public static final WordDAO getInstance() {
        return new WordDAO();
    }


    @Override
    public void insertWord(String word, Explanation explanation) {
        try {
            Connection connection = Database.getConnection();

            // create statement
            Statement statement = connection.createStatement();

            // execute statement
            String sql = "insert into av (wordtarget , wordmeaning , worddescribe) "
                    + "values ('" + word
                    + "' , '" + explanation.getWordExplanation()
                    + "' , '" + explanation.getWordExample() + "');";

            statement.executeUpdate(sql);

            // disconnection
            Database.Disconnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWord(String word) {
        try {
            Connection connection = Database.getConnection();

            Statement statement = connection.createStatement();

            String sql = "delete from av where wordtarget = '" + word + "'";
            statement.executeUpdate(sql);

            Database.Disconnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWord(String word, Explanation explanation) {
        try {
            Connection connection = Database.getConnection();

            Statement statement = connection.createStatement();

            String sql = "update av set wordtarget = '" + word + "',"
                    + "wordmeaning = '" + explanation.getWordExplanation() + "',"
                    + "worddescribe = '" + explanation.getWordExample() + "'"
                    + "where wordtarget = '" + word + "'";

            statement.executeUpdate(sql);

            Database.Disconnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Word> getWords() {
        List<Word> words = null;
        try {
            words = new ArrayList<>();

            Connection connection = Database.getConnection();

            // create statement
            Statement statement = connection.createStatement();

            // execute sql
            String sql = "Select * from av";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String wordTarget = resultSet.getString("wordtarget");
                String wordMeaning = resultSet.getString("wordmeaning");
                String wordDescribe = resultSet.getString("worddescribe");

                words.add(new Word(wordTarget, wordMeaning, wordDescribe));
            }

            Database.Disconnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }
}
