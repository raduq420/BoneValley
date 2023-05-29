package GamePackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;

public class MonsterLoader {
    private static Connection c = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static void deleteRowById(int id) throws SQLException {
        Statement stat = c.createStatement();
        String deleteSql = "DELETE FROM dushman WHERE id = " + id;
        stat.executeUpdate(deleteSql);
    }
    public static void initDB() throws IOException {
        String copypath = "C:\\Users\\Rduku\\Desktop\\juegospao\\BV\\res\\copy_dushman.db";

        File originalDatabase = new File("C:\\Users\\Rduku\\Desktop\\juegospao\\BV\\res\\dushman.db");
        File copyDatabase = new File(copypath);

        Files.copy(originalDatabase.toPath(), copyDatabase.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    public static void LoadMonsters(int level, int room) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rduku\\Desktop\\juegospao\\BV\\res\\copy_dushman.db");

        statement = c.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM dushman");

        Fawn inst = Fawn.getInstance();
        int i = 0;
        inst.setMonsterCnt(0);
        while(resultSet.next())
        {
            int lvl = resultSet.getInt("Level");
            int rum = resultSet.getInt("Room");
            if(lvl == level && rum == room)
            {
                String type = resultSet.getString("Type");
                lvl = resultSet.getInt("X");
                rum = resultSet.getInt("Y");
                inst.buildMonster(type, lvl, rum);
                deleteRowById(resultSet.getInt("id"));
            }
        }
        inst.setFawnLoaded(true);
        resultSet.close();
        statement.close();
        c.close();
    }
    public static void SaveMonsters(int level, int room) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rduku\\Desktop\\juegospao\\BV\\res\\copy_dushman.db");

        statement = c.createStatement();
        Fawn inst = Fawn.getInstance();
        String selectSql = "SELECT id FROM dushman ORDER BY id DESC LIMIT 1";
        resultSet = statement.executeQuery(selectSql);
        int z = 0;

        if (resultSet.next())
            z = resultSet.getInt("id") + 1;

        for(int i = 0 ; i < inst.getMonsterCnt() ; i++)
        {
            String insertSql = "INSERT INTO dushman (Level, Room, Type, X, Y, id) VALUES ('"+ level +"','"
                    + room +"','"
                    + inst.getTypeI(i) +"','"
                    + inst.getXI(i) +"','"
                    + inst.getYI(i) +"','"
                    + z +"')";
            z++;
            statement.executeUpdate(insertSql);
        }
        inst.setMonsterCnt(0);

        resultSet.close();
        statement.close();
        c.close();
    }
}
