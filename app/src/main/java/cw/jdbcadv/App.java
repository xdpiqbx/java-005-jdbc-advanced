package cw.jdbcadv;

import cw.jdbcadv.feature.human.HumanGenerator;
import cw.jdbcadv.feature.human.HumanServiceV1;
import cw.jdbcadv.feature.human.HumanServiceV2;
import cw.jdbcadv.feature.storage.DatabaseInitService;
import cw.jdbcadv.feature.storage.Storage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        Storage storage = Storage.getInstance();

        HumanServiceV2 humanServiceV2 = new HumanServiceV2(storage);

        int count = 100000;
        HumanGenerator generator = new HumanGenerator();

        String[] names = generator.generateNames(count);
        LocalDate[] dates = generator.generateDates(count);

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            humanServiceV2.createNawHuman(names[i], dates[i]);
        }
        long duration = System.currentTimeMillis() - start;
        System.out.println("duration = " + duration);

//        new DatabaseInitService().initDb(storage);

//        HumanServiceV1 humanServiceV1 = new HumanServiceV1(storage);
//        HumanServiceV2 humanServiceV2 = new HumanServiceV2(storage);

//        for (int i = 0; i < 100000; i++) {
//            humanServiceV2.printHumanInfo(1);
//            humanServiceV1.printHumanInfo(1);
//        }

//        boolean res = humanServiceV2.createNawHuman("Bogun", LocalDate.now().minusYears(40));
//        System.out.println("res = " + res);

//        List<Long> ids = humanServiceV2.getIds();
//        ids.forEach(it -> humanServiceV2.printHumanInfo(it));

//        HumanServiceV1 humanService = new HumanServiceV1(storage);
//        humanService.createNewHuman("Valerii", LocalDate.now());
//        humanService.createNewHuman("Volodymir", LocalDate.now());
//        humanService.createNewHuman("Ares", LocalDate.now());
//        humanService.printHumanInfo(3);
//        humanService.printHumanIds();



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
