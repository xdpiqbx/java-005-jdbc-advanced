# java-005-jdbc-advanced

## [Database](https://github.com/xdpiqbx/goit-java-dev-mod4-hw-jdbc/blob/main/app/src/main/java/com/xdpiqbx/db/Database.java)
- how to create database instance
## [`.sql` examples](https://github.com/xdpiqbx/goit-java-dev-mod4-hw-jdbc/tree/main/app/src/main/java/com/xdpiqbx/db/sql)
## [execute `init_db.sql`](https://github.com/xdpiqbx/goit-java-dev-mod4-hw-jdbc/blob/main/app/src/main/java/com/xdpiqbx/db/services/DatabaseInitService.java)
## [execute `populate_db.sql`](https://github.com/xdpiqbx/goit-java-dev-mod4-hw-jdbc/blob/main/app/src/main/java/com/xdpiqbx/db/services/DatabasePopulateService.java)
## [execute `SELECT` pack](https://github.com/xdpiqbx/goit-java-dev-mod4-hw-jdbc/blob/main/app/src/main/java/com/xdpiqbx/db/services/DatabaseQueryService.java)
## [prepareStatement examples](https://github.com/xdpiqbx/goit-java-dev-mod5-hw-jdbc-adv/tree/main/app/src/main/java/com/xdpiqbx/db/services)

---
## Batch update для Statement
```java
//Add queries to batch
statement.addBatch("INSERT INTO people VALUES('John')");
statement.addBatch("INSERT INTO people VALUES('Max')");

//Execute batch
statement.executeBatch();
```
---
## Batch update для PreparedStatement
```java
//Prepare statement
PreparedStatement statement = connection.prepareStatement("INSERT INTO people VALUES (?)");

//Add first query to batch
statement.setString(1, "John");
statement.addBatch();

//Add second query to batch
statement.setString(1, "Max");
statement.addBatch();

//Execute batch
statement.executeBatch();
```
---
## Batch update - цикли
```java
PreparedStatement statement = connection.prepareStatement("INSERT INTO people VALUES (?)");

String[] names = {"John", "Max", "Boris"};

//Add queries to batch
for(String name: names) {
    statement.setString(1, name);
    statement.addBatch();
}

//Execute batch
statement.executeBatch();
```
---

## Транзакції

### Технічно це реалізується серією методів у Connection:

- `connection.setAutoCommit(false)` - вимикаємо механізм автоматичного комміту кожного запиту
- `statement.executeUpdate()` - додаємо запити як зазвичай
- `connection.rollback()` - якщо щось пішло не так, то викликаємо цей метод щоб відкотити всі запити, що раніше виконались
- `connection.commit()` - викликаємо цей метод, коли всі запити виконались успішно. Виклик цього методу записує результат виконня всіх запитів в базу даних.
- `connection.setAutoCommit(true)` - **не забуваємо !!!** ввімкнути механізм автоматичного комміту кожного запиту

```java
connection.setAutoCommit(false);

try {
    String deleteMoneySql = "<SOME SQL TO DELETE MONEY>";
    statement.executeUpdate(deleteMoneySql);

    String addMoneySql = "<SOME SQL TO ADD MONEY>";
    statement.executeUpdate(addMoneySql);

    connection.commit();
} catch(Exception ex) {
    connection.rollback();
} finally {
    connection.setAutoCommit(true);
}
```
---

## Хранимые процедуры

[6.3 Using JDBC CallableStatements to Execute Stored Procedures](https://docs.oracle.com/cd/E17952_01/connector-j-5.1-en/connector-j-usagenotes-statements-callable.html)

---

## Міграції. Фреймворк Flyway

[Flyway Core (maven)](https://mvnrepository.com/artifact/org.flywaydb/flyway-core)

[Flyway Documentation](https://flywaydb.org/documentation/)

```java
public class DatabaseInitService {
    public void initDb(Storage storage){
        String connectionUrl = new Prefs().getString(Prefs.DB_URL);
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        // Start the migration
        flyway.migrate();
    }
}
```