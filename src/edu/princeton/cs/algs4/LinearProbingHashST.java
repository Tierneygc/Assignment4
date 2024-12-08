package edu.princeton.cs.algs4;

public class LinearProbingHashST<Key, Value> {

    // must be a power of 2
    private static final int INIT_CAPACITY = 4;

    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private Key[] keys;      // the keys
    private Value[] vals;
    private int hashVersion;// the values
    public int count1 = 0;
    public int count2 = 0;




    public LinearProbingHashST(int capacity, int hashVersion) {
        this.m = capacity;
        this.n = 0;
        this.hashVersion = hashVersion;
        keys = (Key[])   new Object[m];
        vals = (Value[]) new Object[m];

    }



    private int hashCode1(Key key){
        int hash = 0;
        String str = (String) key;
        int skip = Math.max(1, str.length() / 8);
        for (int i = 0; i < str.length(); i += skip){
            hash = (hash * 37) + str.charAt(i);

        }
        return hash;
    }

    private int hashCode2(Key key){
        int hash = 0;
        String str = (String) key;
        for (int i = 0; i < str.length(); i++){
            hash = (hash * 31) + str.charAt(i);
        }
        return hash;
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




    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }



        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }


    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (hashVersion == 1) {
                count1++;
            } else {
                count2++;
            }
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        if (keys[hash(key)] == null) {
            if (hashVersion == 1) {
                count1++;  // Increment for the final empty slot check
            } else {
                count2++;  // Increment for the final empty slot check
            }
        }
        return null;
    }


    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] and vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;


        assert check();
    }


    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    // integrity check - don't check after each put() because
    // integrity not maintained during a call to delete()
    private boolean check() {

        // check that hash table is at most 50% full
        if (m < 2*n) {
            System.err.println("Hash table size m = " + m + "; array size n = " + n);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < m; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }




}
