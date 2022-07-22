package server.custom.forum;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.FileoutputUtil;

public class Forum_Section {

    private int Id;
    private String Name;
    private static ArrayList<Forum_Section> AllSection = new ArrayList<Forum_Section>();

    public Forum_Section() {

    }

    public Forum_Section(int id, String name) {
        Id = id;
        Name = name;
    }

    public static ArrayList<Forum_Section> getAllSection() {
        return AllSection;
    }

    public static void setAllSection(ArrayList<Forum_Section> allSection) {
        AllSection = allSection;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static ArrayList<Forum_Section> loadAllSection() {
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_section");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                AllSection.add(new Forum_Section(id, name));
            }

            rs.close();
            ps.close();
            Forum_Thread.loadAllThread();
            return AllSection;
        } catch (SQLException ex) {

        }

        return null;
    }

    public static boolean addSection(String name) {
        Connection con = DatabaseConnection.getConnection();
        try {

            if (getSectionByName(name) != null) {
                return false;
            }

            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO forum_section(name) VALUES (?)");

            PreparedStatement ps = con.prepareStatement(query.toString());

            ps.setString(1, name);

            ps.executeUpdate();
            ps.close();

            AllSection.add(getSectionByNameToSql(name));

            return true;
        } catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
        }

        return false;

    }

    public static boolean deleteSection(int id) {
        Connection con = DatabaseConnection.getConnection();
        try {

            boolean isExist = false;

            if (getSectionById(id) != null) {
                AllSection.remove(getSectionById(id));
                isExist = true;
            }

            if (!isExist) {
                return isExist;
            }

            Forum_Thread.deleteThread(id, 0, true);

            StringBuilder query2 = new StringBuilder();
            query2.append("DELETE FROM forum_section WHERE id = ?");

            PreparedStatement ps = con.prepareStatement(query2.toString());

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
        }

        return false;
    }

    public static Forum_Section getSectionById(int id) {
        ArrayList<Forum_Section> allSection = getAllSection();
        for (Forum_Section fs : allSection) {
            if (fs.getId() == id) {
                return fs;
            }
        }

        return null;
    }

    public static Forum_Section getSectionByIdToSql(int id) {
        String name = "";
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_section WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
            return new Forum_Section(id, name);
        } catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
        }

        return null;
    }

    public static Forum_Section getSectionByName(String name) {
        ArrayList<Forum_Section> allSection = getAllSection();
        for (Forum_Section fs : allSection) {
            if (fs.getName().equals(name)) {
                return fs;
            }
        }

        return null;
    }

    public static Forum_Section getSectionByNameToSql(String name) {
        int id = 0;
        Connection con = DatabaseConnection.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM forum_section WHERE name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            return new Forum_Section(id, name);
        } catch (SQLException ex) {
            FileoutputUtil.outputFileError("logs/鏁版嵁搴撳紓甯?txt", ex);
        }

        return null;
    }
}
