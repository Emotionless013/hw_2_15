package pro.sky.hw_2_15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hw_2_15.Exception.IncorrectIndexException;
import pro.sky.hw_2_15.Exception.IncorrectObjectException;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListTest {
    public final IntegerList integerList = new IntegerList();

    @BeforeEach
    public void addDefaultValues() {
        integerList.clear();
        integerList.add(1);
        integerList.add(1);
    }

    @Test
    public void returnsArrayFromStringList() {
        Integer[] test = {1, 1};
        assertArrayEquals(test, integerList.toArray());
    }

    @Test
    public void returnsLengthEqualZeroWhenClear() {
        integerList.clear();
        assertEquals(0, integerList.size());
    }

    @Test
    public void returnFalseWhenStringListIsNotEmpty() {
        assertFalse(integerList.isEmpty());
    }

    @Test
    public void returnTrueWhenStringListIsEmpty() {
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    public void returnsSizeCorrectly() {
        assertEquals(2, integerList.size());
    }

    @Test
    public void returnsTrueForEqualStringList() {
        IntegerList test = new IntegerList();
        test.add(1);
        test.add(1);
        assertTrue(integerList.equals(test));
    }

    @Test
    public void returnsFalseForNotEqualStringList() {
        IntegerList test = new IntegerList();
        test.add(1);
        assertFalse(integerList.equals(test));
    }

    @Test
    public void throwsIncorrectIndexExceptionTryingToGetElementByWrongIndex() {
        assertThrows(IncorrectIndexException.class, () -> integerList.get(2));
    }

    @Test
    public void returnsCorrectStringByIndex() {
        assertEquals(1, integerList.get(0));
    }

    @Test
    public void indexOfReturnsIndexZeroAsIndexOfDigitOne() {
        assertEquals(0, integerList.indexOf(1));
    }

    @Test
    public void lastIndexOfReturnsIndexOneAsIndexOfDigitOne() {
        assertEquals(1, integerList.lastIndexOf(1));
    }

    @Test
    public void indexOfReturnsIndexMinusOneIfTheWordDoesntExist() {
        assertEquals(-1, integerList.indexOf(2));
    }

    @Test
    public void lastIndexOfReturnsMinusOneIfTheWordDoesntExist() {
        assertEquals(-1, integerList.lastIndexOf(2));
    }

    @Test
    public void containsReturnsTrueIfStringListContainsElement() {
        assertTrue(integerList.contains(1));
    }

    @Test
    public void containsReturnsFalseIfStringListDoesntContainElement() {
        assertFalse(integerList.contains(2));
    }


    @Test
    public void returnsZeroAfterAdd() {
        assertEquals(0, integerList.add(0));
    }

    @Test
    public void zeroIsAddedToLastIndex() {
        integerList.add(0);
        assertEquals(2, integerList.lastIndexOf(0));
    }

    @Test
    public void returnsZeroAfterAddByIndex() {
        assertEquals(0, integerList.add(1,0));
    }

    @Test
    public void zeroIsAddedToCorrectIndex() {
        integerList.add(1,0);
        assertEquals(1, integerList.lastIndexOf(0));
    }

    @Test
    public void throwsIncorrectIndexExceptionTryingToAddElementByWrongIndex() {
        assertThrows(IncorrectIndexException.class, () -> integerList.add(3, 0));
    }

    @Test
    public void returnsZeroAfterSetByIndex() {
        assertEquals(0, integerList.set(1,0));
    }

    @Test
    public void zeroIsSetAtCorrectIndex() {
        integerList.set(1,0);
        assertEquals(1, integerList.lastIndexOf(0));
    }

    @Test
    public void throwsIncorrectIndexExceptionTryingToSetElementByWrongIndex() {
        assertThrows(IncorrectIndexException.class, () -> integerList.set(2, 0));
    }

    @Test
    public void returnsOneAfterRemoval() {
        Integer q = 1;
        assertEquals(1, integerList.remove(q));
    }

    @Test
    public void sizeWasContractedAfterRemoval() {
        integerList.remove(1);
        assertEquals(1, integerList.size());
    }

    @Test
    public void throwsIncorrectObjectExceptionTryingToRemoveElementThatDoesntExist() {
        Integer q = 3;
        assertThrows(IncorrectObjectException.class, () -> integerList.remove(q));
    }

    @Test
    public void returnsOneAfterRemovalByIndex() {
        assertEquals(1, integerList.remove(1));
    }

    @Test
    public void sizeWasContractedAfterRemovalByIndex() {
        integerList.remove(0);
        assertEquals(1, integerList.size());
    }

    @Test
    public void throwsIncorrectIndexExceptionTryingToRemoveElementByIndexThatDoesntExist() {
        assertThrows(IncorrectIndexException.class, () -> integerList.remove(5));
    }
}