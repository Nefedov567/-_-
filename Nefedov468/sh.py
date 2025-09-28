def linear_probing(hash_table, key, value): # Объвление функции с параметрами hash_table(хэш таблица), key(ключ для вставки), value(значение связанное с ключом) 
    index = simple_hash(key) # Вычисление начального индекса
    while hash_table[index] is not None: # Цикл поиска свободной ячейки
        index = (index + 1) % len(hash_table) # Линейное пробирование
    hash_table[index] = (key, value) # вставка пары ключ-значения в найденную свободную ячейку
