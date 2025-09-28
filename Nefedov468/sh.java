import java.util.Hashtable;

public class HashtableExample {
    public static void main(String[] args) {
        // Создать хэш-таблицу
        Hashtable<String, Integer> ht = new Hashtable<>();
        
        // Добавить элементы
        ht.put("Apple", 10);
        ht.put("Banana", 20);
        ht.put("Orange", 30);
        
        // Отобразить элементы
        System.out.println("Hashtable: " + ht);
        
        // Получить значение по ключу
        System.out.println("Value for Apple: " + ht.get("Apple"));
    }
}
