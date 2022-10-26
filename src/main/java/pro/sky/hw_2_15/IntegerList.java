package pro.sky.hw_2_15;

import org.springframework.stereotype.Service;
import pro.sky.hw_2_15.Exception.IncorrectIndexException;
import pro.sky.hw_2_15.Exception.IncorrectObjectException;

import java.util.Arrays;
import java.util.Objects;

@Service
public class IntegerList {

    private Integer[] integerList;
    private int size;

    public IntegerList() {
        integerList = new Integer[10];
    }

    //перестановка элементов
    private static void swapElements(Integer[] arr, Integer indexA, Integer indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    //сортировка
    public static void quickSort(Integer[] arr, Integer begin, Integer end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, Integer begin, Integer end) {
        Integer pivot = arr[end];
        int i = (begin - 1);

        for (Integer j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    // Проверка на существование элемента. Бинарный поиск.
    // Вернуть true/false;
    public boolean contains(Integer item) {
        Integer[] temp = Arrays.copyOf(integerList, size);
        quickSort(temp, 0, size-1);
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(temp[mid])) {
                return true;
            }
            if (item < temp[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    //увеличение хранилища
    private void grow() {
        integerList = Arrays.copyOf(integerList, (int) (integerList.length * 1.5));
    }


    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    public Integer add(Integer item) {
        if (size == integerList.length) {
            grow();
        }
        return integerList[size++] = item;
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public Integer add(int index, Integer item) {
        if (index > size) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        }
        if (size == integerList.length) {
            grow();
        }
        size++;
        if (size + 1 - index >= 0)
            System.arraycopy(integerList, index, integerList, index + 1, size + 1 - index);
        return integerList[index] = item;
    }

    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение, если индекс больше фактического количества элементов или выходит за пределы массива.
    public Integer set(int index, Integer item) {
        if (index >= size) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        }
        return integerList[index] = item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public Integer remove(Integer item) {
        if (!contains(item)) {
            throw new IncorrectObjectException("Указанного элемента нет в хранилище");
        } else {
            for (int i = 0; i < size; i++) {
                if (integerList[i].equals(item)) {
                    if (size - 1 - i >= 0)
                        System.arraycopy(integerList, i + 1, integerList, i, size - 1 - i);
                }
            }
        }
        size--;
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public Integer remove(int index) {
        Integer forReturn;
        if (index >= size) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        } else {
            forReturn = integerList[index];
            if (integerList.length - 1 - index >= 0)
                System.arraycopy(integerList, index + 1, integerList, index, integerList.length - 1 - index);
        }
        size--;
        return forReturn;
    }


    // Поиск элемента.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int indexOf(Integer item) {
        for (int i = 0; i < integerList.length; i++) {
            if (item.equals(integerList[i])) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента или -1 в случае отсутствия.
    public int lastIndexOf(Integer item) {
        for (int i = integerList.length - 1; i >= 0; i--) {
            if (item.equals(integerList[i])) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение, если выходит за рамки фактического количества элементов.
    public Integer get(int index) {
        if (index > size - 1) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        }
        return integerList[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    // Вернуть фактическое количество элементов.
    public int size() {
        return size;
    }

    // Вернуть true, если элементов в списке нет, иначе false.
    public boolean isEmpty() {
        return size == 0;
    }

    // Удалить все элементы из списка.
    public void clear() {
        integerList = new Integer[10];
        size = 0;
    }

    // Создать новый массив из строк в списке и вернуть его.
    public Integer[] toArray() {
        return Arrays.copyOf(integerList, size);
    }
}

