package com.epam.rd.autocode.decorator;

import java.util.*;

public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new EvenIndexElementsList(sourceList);
    }
    private static class EvenIndexElementsList implements List<String> {
        private final List<String> sourceList;

        public EvenIndexElementsList(List<String> sourceList) {
            this.sourceList = sourceList;
        }

        @Override
        public String get(int index) {
            int actualIndex = index * 2;
            if (actualIndex >= sourceList.size()) {
                throw new IndexOutOfBoundsException();
            }
            return sourceList.get(actualIndex);
        }

        @Override
        public String set(int i, String string) {
            unsupported();
            return null;
        }

        @Override
        public void add(int i, String string) {
            unsupported();
        }

        @Override
        public String remove(int i) {
            unsupported();
            return null;
        }

        @Override
        public int indexOf(Object o) {
            unsupported();
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            unsupported();
            return 0;
        }

        @Override
        public ListIterator<String> listIterator() {
            return new EvenIndexListIterator();
        }


        @Override
        public ListIterator<String> listIterator(int i) {
            unsupported();
            return null;
        }

        @Override
        public List<String> subList(int i, int i1) {
            unsupported();
            return null;
        }

        @Override
        public int size() {
            return (sourceList.size() + 1) / 2;
        }

        @Override
        public boolean isEmpty() {
            unsupported();
            return false;
        }

        @Override
        public boolean contains(Object o) {
            unsupported();
            return false;
        }

        @Override
        public Iterator<String> iterator() {
            return new EvenIndexListIterator();
        }

        @Override
        public Object[] toArray() {
            unsupported();
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] ts) {
            unsupported();
            return null;
        }

        @Override
        public boolean add(String string) {
            unsupported();
            return false;
        }

        @Override
        public boolean remove(Object o) {
            unsupported();
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            unsupported();
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends String> collection) {
            unsupported();
            return false;
        }

        @Override
        public boolean addAll(int i, Collection<? extends String> collection) {
            unsupported();
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            unsupported();
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            unsupported();
            return false;
        }

        @Override
        public void clear() {
            unsupported();
        }

        private void unsupported() {
            throw new UnsupportedOperationException("Only get(), size(), and iterator() are supported.");
        }

        private class EvenIndexListIterator implements ListIterator<String> {
            private int virtualIndex = 0;
            @Override
            public boolean hasNext() {
                return virtualIndex * 2 < sourceList.size();
            }

            @Override
            public String next() {
                if (!hasNext()) throw new NoSuchElementException();
                return sourceList.get(virtualIndex++ * 2);
            }

            @Override
            public boolean hasPrevious() {
                return virtualIndex > 0;
            }

            @Override
            public String previous() {
                return sourceList.get(--virtualIndex * 2);
            }

            @Override public int nextIndex() { return virtualIndex; }

            @Override public int previousIndex() { return virtualIndex - 1; }

            @Override public void remove() { throw new UnsupportedOperationException(); }

            @Override public void set(String e) { throw new UnsupportedOperationException(); }

            @Override public void add(String e) { throw new UnsupportedOperationException(); }
        }

    }

}
