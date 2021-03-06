import java.sql.*;
import java.util.*;

public class UseCase3Ctrl extends DBConn {

    private PreparedStatement replyStatement;
    private PreparedStatement postStatement;

    public void startReply() {
        try {
            replyStatement = conn.prepareStatement("INSERT INTO reply VALUES ((?),(?),(?),(?),(?))");
            postStatement = conn.prepareStatement("INSERT INTO post VALUES ((?),(?),(?),(?))");
        } catch (Exception e) {
            System.out.println("Error during prepare of insert values into reply");

        }
    }

    public void makeReply(int postID, int anonymous, String content, int userID, int threadID) {
        try {
            replyStatement.setInt(1, postID);
            replyStatement.setInt(2, anonymous);
            replyStatement.setString(3, content);
            replyStatement.setInt(4, userID);
            replyStatement.setInt(5, threadID);
            replyStatement.execute();

            postStatement.setInt(1, postID);
            postStatement.setString(2, content);
            postStatement.setInt(3, anonymous);
            postStatement.setInt(4, userID);
            postStatement.execute();
            System.out.println("\nSvaret ble lagt inn suksessfullt!");
        } catch (Exception e) {
            System.out.println("error inserting values into reply");
            e.printStackTrace();
        }
    }

}
