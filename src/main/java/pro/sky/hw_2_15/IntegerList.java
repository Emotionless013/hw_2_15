package pro.sky.hw_2_15;

import org.springframework.stereotype.Service;
import pro.sky.hw_2_15.Exception.IncorrectIndexException;
import pro.sky.hw_2_15.Exception.IncorrectObjectException;

import java.util.Objects;

@Service
public class IntegerList {

    private Integer[] integerList = new Integer[0];


    public Integer[] getStringList() {
        return integerList;
    }

    //перестановка элементов
    private static void swapElements(Integer[] arr, Integer indexA, Integer indexB) {
        Integer tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    //сортировка
    public void sort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    // Проверка на существование элемента. Бинарный поиск.
    // Вернуть true/false;
    public boolean contains(Integer item) {
        Integer[] temp = integerList;
        sort(temp);
        int min = 0;
        int max = temp.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == temp[mid]) {
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
    public void extend() {
        Integer[] newList = new Integer[integerList.length + 1];
        for (int i = 0; i < integerList.length; i++) {
            newList[i] = integerList[i];
        }
        integerList = newList;
    }

    //уменьшение хранилища
    public void contract() {
        Integer[] newList = new Integer[integerList.length - 1];
        for (int i = 0; i < integerList.length - 1; i++) {
            newList[i] = integerList[i];
        }
        integerList = newList;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    public Integer add(Integer item) {
        extend();
        return integerList[integerList.length - 1] = item;
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива, выбросить исключение.
    // Вернуть добавленный элемент в качестве результата выполнения.
    public Integer add(int index, Integer item) {
        if (index > integerList.length - 1) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        }
        extend();
        for (int i = (integerList.length - 2); i >= index; i--)
            integerList[i + 1] = integerList[i];
        return integerList[index] = item;
    }

    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение, если индекс больше фактического количества элементов или выходит за пределы массива.
    public Integer set(int index, Integer item) {
        if (index > integerList.length - 1) {
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
            for (int i = 0; i < integerList.length; i++) {
                if (integerList[i].equals(item)) {
                    for (int j = i; j < integerList.length - 1; j++) {
                        integerList[j] = integerList[j + 1];
                    }
                }
            }
        }
        contract();
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент или исключение, если подобный элемент отсутствует в списке.
    public Integer remove(int index) {
        Integer forReturn;
        if (index >= integerList.length) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        } else {
            forReturn = integerList[index];
            for (int i = index; i < integerList.length - 1; i++) {
                integerList[i] = integerList[i + 1];
            }
        }
        contract();
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
        if (index > integerList.length - 1) {
            throw new IncorrectIndexException("индекс превышает размер хранилища");
        }
        return integerList[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение, если передан null.
    public boolean equals(IntegerList otherList) {
        if (otherList.size() != integerList.length) {
            return false;
        }
        for (int i = 0; i < integerList.length; i++) {
            if (!Objects.equals(integerList[i], otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Вернуть фактическое количество элементов.
    public int size() {
        return integerList.length;
    }

    // Вернуть true, если элементов в списке нет, иначе false.
    public boolean isEmpty() {
        return integerList.length <= 0;
    }

    // Удалить все элементы из списка.
    public void clear() {
        integerList = new Integer[0];
    }

    // Создать новый массив из строк в списке и вернуть его.
    public Integer[] toArray() {
        Integer[] newList = new Integer[integerList.length];
        return newList = integerList;
    }
}

