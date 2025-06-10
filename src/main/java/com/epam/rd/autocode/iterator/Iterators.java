package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new RepeatingIntArrayIterator(array, 2);
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new RepeatingIntArrayIterator(array, 3);
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new RepeatingIntArrayIterator(array, 5);
    }

    public static Iterable<String> table(String[] columns, int[] rows){
        return new Table(columns, rows);
    }

    private static class RepeatingIntArrayIterator implements Iterator<Integer> {
        private final int[] array;
        private final int repeatCount;

        private int index = 0;
        private int currentRepeat = 0;

        public RepeatingIntArrayIterator(int[] array, int repeatCount) {
            this.array = array;
            this.repeatCount = repeatCount;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int value = array[index];
            if (++currentRepeat == repeatCount) {
                ++index;
                currentRepeat = 0;
            }
            return value;
        }
    }


    private static class Table implements Iterable<String> {
        final private String[] columns;
        final private int[] rows;

        public Table(String[] columns, int[] rows) {
            this.columns = columns;
            this.rows = rows;
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<>() {
                private int currentColumnsCount = 0;
                private int currentRowsCount = 0;

                @Override
                public boolean hasNext() {
                    return currentColumnsCount < columns.length;
                }

                @Override
                public String next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    String val = columns[currentColumnsCount] + rows[currentRowsCount++];
                    if (currentRowsCount == rows.length) {
                        ++currentColumnsCount;
                        currentRowsCount = 0;
                    }
                    return val;
                }
            };
        }
    }

}
