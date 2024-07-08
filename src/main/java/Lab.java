

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        //write jdbc code here
        try {
            // Retrieve active connection to the database
            Connection connection = ConnectionUtil.getConnection();
    
            // SQL statement to insert a new song
            String sql = "INSERT INTO songs (title, artist) VALUES ('" + song.gettitle() + "', '" + song.getArtist() + "')";

            // Create Statement object
            Statement statement = connection.createStatement();
    
            // Execute the statement to insert the song into the database
            statement.executeUpdate(sql);
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        try {
            // Retrieve active connection to the database
            Connection connection = ConnectionUtil.getConnection();
    
            // SQL query to retrieve all songs
            String sql = "SELECT * FROM songs";
    
            // Create Statement object
            Statement statement = connection.createStatement();
    
            // Execute the query and retrieve result set
            ResultSet rs = statement.executeQuery(sql);
    
            // Iterate through the result set and populate the songs list
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
    
                Song song = new Song(id, title, artist);
                songs.add(song);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return songs;
    }
}
