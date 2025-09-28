#include <iostream> // Подключение стандартной библиотеки ввода-вывода для использования std::cout и std::endl
#include <unordered_map> // Подключение библиотеки для работы с хеш-таблицами (ассоциативными массивами)

int main() { // Главная функция - точка входа в программу
    std::unordered_map<std::string, int> myMap; // Создание хеш-таблицы
    myMap["apple"] = 1; // Добавление пары ключ-значение
    myMap["banana"] = 2;
    myMap["orange"] = 3;

    // Поиск значения по ключу
    std::cout << "Значение для apple: " << myMap["apple"] << std::endl; // Вывод: 1
    std::cout << "Значение для banana: " << myMap.at("banana") << std::endl; // Вывод: 2

    return 0;
}
