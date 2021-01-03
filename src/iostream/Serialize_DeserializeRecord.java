package iostream;

import bean.Record;
import java.io.*;
import java.util.ArrayList;

public class Serialize_DeserializeRecord {

    private static final String FILE_PATH = "files_examples/users_examples.ser";    // Пример
    // Запись
    public static void writeRecord(ArrayList<Record> obj) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            output.writeObject(obj);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    // Чтение
    public static void readRecord() {
        ArrayList<Record> record = new ArrayList<Record>();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            record=((ArrayList<Record>)input.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for(Record r : record) {
            System.out.printf("Адрес %s \t\tЛогин %s \t\tПароль %s \t\tВремя %s \n", r.getAddress(), r.getLogin(), r.getPassword(), r.getDate());
        }
    }
    */
}
