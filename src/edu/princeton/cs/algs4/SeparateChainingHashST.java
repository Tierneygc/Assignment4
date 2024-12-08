package edu.princeton.cs.algs4;
import java.io.*;
import java.util.*;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables
    private int hashVersion;
    public int counter1 = 0;
    public int counter2 = 0;

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }


    public SeparateChainingHashST(int m, int hashVersion) {
        this.m = m;
        this.hashVersion = hashVersion;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    public SeparateChainingHashST(int initCapacity) {
    }

    private int hashCode1(Key key) {

        int hash = 0;
        String str = (String) key;
        int skip = Math.max(1, str.length() / 8);
        for (int i = 0; i < str.length(); i += skip) {
            hash = (hash * 37) + str.charAt(i);
        }
        return hash;
    }

    private int hashCode2(Key key) {
        int hash = 0;
        String str = (String) key;
        for (int i = 0; i < str.length(); i++)
            hash = (hash * 31) + str.charAt(i);
        return hash;
    }



    // hash function for keys - returns value between 0 and m-1 (assumes m is a power of 2)
    // (from Java 7 implementation, protects against poor quality hashCode() implementations)
    private int hash(Key key) {
        if (hashVersion == 1){
            return (hashCode1(key) & 0x7fffffff) % m;

        }
        else{
            return (hashCode2(key) & 0x7fffffff) % m;

        }
    }


    public int size() {
        return n;
    }


    public boolean isEmpty() {
        return size() == 0;
    }


    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");

        return get(key) != null;
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");


        int i = hash(key);
        Value value = st[i].get(key);

        if(hashVersion == 1){
            counter1 += st[i].getCount();
        }
        else{
            counter2 += st[i].getCount();
        }
        return value;
    }


    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }



        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    }


    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);


    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }




}