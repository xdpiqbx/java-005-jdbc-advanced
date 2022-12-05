package cw.jdbcadv.feature.human;

import cw.jdbcadv.feature.storage.Storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HumanServiceV2 {
    private PreparedStatement insertSt;
    private PreparedStatement selectByIdSt;
    private PreparedStatement selectAllSt;
    public HumanServiceV2(Storage storage) throws SQLException {
        Connection conn = storage.getConnection();

        insertSt = conn.prepareStatement(
                "INSERT INTO human (name, birthday) VALUES (?, ?)"
        );
        selectByIdSt = conn.prepareStatement(
                "SELECT name, birthday FROM human WHERE id = ?"
        );
        selectAllSt = conn.prepareStatement(
                "SELECT id FROM human"
        );
    }
    public boolean printHumanInfo(long id){
        try {
            selectByIdSt.setLong(1, id);
        } catch (SQLException e) {
            return false;
        }

        try (ResultSet rs = selectByIdSt.executeQuery()){
            if(!rs.next()){
//                System.out.println("Human with id: ["+id+"] not found");
                return false;
            }
            String name = rs.getString("name");
            String birthday = rs.getString("birthday");
//            System.out.println("name: "+ name + ", birthday: " + birthday);
            return true;
        } catch (SQLException e) {
            return true;
        }
    }

    public void createNewHumans(String[] names, LocalDate[] birthdays){

    }

    public boolean createNawHuman(String name, LocalDate birthday) {
        try {
            insertSt.setString(1, name);
            insertSt.setString(2, birthday.toString());
            return insertSt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getIds(){
        List<Long> result = new ArrayList<>();
        try(ResultSet rs = selectAllSt.executeQuery()) {
            while (rs.next()){
                result.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
