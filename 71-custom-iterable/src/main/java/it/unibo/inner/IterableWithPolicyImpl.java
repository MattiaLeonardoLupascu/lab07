package it.unibo.inner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;


public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private List<T> array = new ArrayList<>();
    private int start;
    private int stop;
    private Predicate<T> filter;
    private List<T> accepted = new ArrayList<>();
    public IterableWithPolicyImpl(T[] array,int start, int stop){
        this(array, start, stop, new PredicateTrue<>());
    }
    public IterableWithPolicyImpl(T[] array,int start, int stop, Predicate<T> filter){
        this.array.addAll(Arrays.asList(array));
        this.start = start;
        this.stop = stop;
        this.filter = filter;
        this.setIterationPolicy(filter);
    }
    private static class PredicateTrue<T> implements Predicate<T>{
        public boolean test(T elem){
            return true;
        }
    }
    public class IteratorWithPolicyImpl implements Iterator<T>{
        int current = IterableWithPolicyImpl.this.start;
        public boolean hasNext(){
            return this.current < IterableWithPolicyImpl.this.accepted.size();
        }
        public T next(){
            return IterableWithPolicyImpl.this.accepted.get(this.current++);
        }
    }
    public void setIterationPolicy(Predicate<T> filter){
    this.accepted = new ArrayList<>();
    for (T elem : this.array) {
        if (filter.test(elem)) {
            this.accepted.add(elem);
        }
    }
}
    public Iterator<T> iterator(){
        return new IteratorWithPolicyImpl();
    }
}
