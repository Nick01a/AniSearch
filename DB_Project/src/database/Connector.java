package database;

import classes.Anime;
import classes.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Connector {
    private static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/anime_database";
            String username = "root";
            String password = "initial";
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
            return connection;
        } catch (Exception e) {
            System.out.println("Not connected \n" + e);
        }
        return null;
    }

    public static Anime getRandomAnime() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection != null ? connection.createStatement() : null;
        Random random = new Random();
        int select = random.nextInt(1000);
        ResultSet set = statement.executeQuery(String.format("Select * from anime where anime_id = %d", select));
        set.next();
        ArrayList<String> genres = new ArrayList<>(Arrays.asList(set.getString("genre").split(",")));
        Anime anime = new Anime(set.getInt("anime_id"), set.getString("title"), set.getString("image_url"),
                set.getInt("episodes"), set.getDouble("score"), set.getInt("scored_by"), genres);
        connection.close();
        return anime.JSONify();
    }
    public static Anime getCorrectAnime(User user) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection != null ? connection.createStatement() : null;
        ResultSet set = statement.executeQuery("SELECT * from marks;");
        set.next();
        HashMap<Integer,Double> user_marks = new HashMap<>();

        String username = set.getString("username");
        while (set.next()) {
            while (set.getString("username").equals(username)) {
                user_marks.put(set.getInt("anime_id"),set.getDouble("score"));
                username = set.getString("username");
            }

        }
        return null;
    }
    public static void addUser(User user) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection != null ? connection.createStatement() : null;
        statement.execute(String.format("INSERT INTO anime_database.users (nick, pass, gender, birthday, favourite_genres, watchlist, finished_list) VALUES ('%s', '%s', %d, %d, '%s', '%s', '%s');",
                user.getNickname(), user.getPass(), user.getGender(), user.getAge_group(), Arrays.toString(user.getFav_genres()), user.getWatchlist_as_String(), user.getFinished_list_as_String()));
        connection.close();
    }

    public static void changeUserInformation(User olduser, User user) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection != null ? connection.createStatement() : null;
        ResultSet set = statement.executeQuery(String.format("SELECT * from anime_database.users where nick = '%s' and pass='%s'", olduser.getNickname(), olduser.getPass()));
        if (set.next()) {
            statement.execute(String.format("DELETE FROM anime_database.users where nick = '%s'", olduser.getNickname()));
            statement.execute(String.format("INSERT INTO anime_database.users (nick, pass, gender, birthday, favourite_genres, watchlist, finished_list) VALUES ('%s', '%s', %d, %d, '%s', '%s', '%s');",
                    user.getNickname(), user.getPass(), user.getGender(), user.getAge_group(), Arrays.toString(user.getFav_genres()), user.getWatchlist_as_String(), user.getFinished_list_as_String()));

        }
        connection.close();
    }

    public static boolean validateUser(User u) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection != null ? connection.createStatement() : null;
        ResultSet set = statement.executeQuery(String.format("SELECT * from anime_database.users where nick = '%s' and pass='%s'", u.getNickname(), u.getPass()));
        if (set.next())
            return true;
        return false;
    }
}
