import heapq # Создание бинарной кучи
my_heap = [3, 1, 4, 1, 5, 9, 2, 6]
heapq.heapify(my_heap)  # Преобразует список в бинарную кучу
print("Куча после heapify:", my_heap)  # Вывод: Куча после heapify: [1, 1, 2, 3, 5, 9, 4, 6]
