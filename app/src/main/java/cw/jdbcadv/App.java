package cw.jdbcadv;

import cw.jdbcadv.feature.human.HumanServiceV1;
import cw.jdbcadv.feature.storage.Storage;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Storage storage = Storage.getInstance();
//        new DatabaseInitService().initDb(storage);
        HumanServiceV1 humanService = new HumanServiceV1(storage);
//        humanService.createNewHuman("Valerii", LocalDate.now());
//        humanService.createNewHuman("Volodymir", LocalDate.now());
//        humanService.createNewHuman("Ares", LocalDate.now());
//        humanService.printHumanInfo(3);
        humanService.printHumanIds();



//        String insertSQL = String.format(
//                "INSERT INTO human (name, birthday) VALUES ('%s', '%s')",
//                "Musk", LocalDate.now()
//        );
//        storage.executeUpdate(insertSQL);
//
//        String selectSQL = "SELECT id, name, birthday FROM human WHERE id = 1";
//        Statement st = storage.getConnection().createStatement();
//        ResultSet rs = st.executeQuery(selectSQL);
//        if(rs.next()){
//            long id = rs.getLong("id");
//            String name = rs.getString("name");
//            LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
//            System.out.println("id = " + id);
//            System.out.println("name = " + name);
//            System.out.println("birthday = " + birthday);
//        }else{
//            System.out.println("Human id not found");
//        }
//        rs.close();
//        st.close();
    }
}
