package module.one;

import java.util.Objects;

public class MyHashMap<K, V> {

    private static final int DEFFAULT_CAPASITY = 16;
    private static final float LOAD_CAPASITY = 0.75f;
    private Node<K, V>[] table;
    private int size;

    public MyHashMap(){
        table = new Node[DEFFAULT_CAPASITY];
    }

    public static class Node<K, V>{
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }



    public void put(K key, V value){
        if(size > table.length * LOAD_CAPASITY){
            resize();
        }

        int h = hash(key);
        int index = indexFor(key, table.length);

        for(Node<K, V> n = table[index]; n != null; n = n.next){
            if(Objects.equals(n.key, key)){
                n.value = value;
                return;
            }
        }

        table[index] = new Node<>(h, key, value, table[index]);
        size++;
    }

    public V get(K key){
        int index = indexFor(key, table.length);

        for(Node<K, V> n = table[index]; n != null; n = n.next){
            if(Objects.equals(n.key, key)){
                return n.value;
            }
        }

        return null;
    }

    public V remove(K key){
        int index = indexFor(key, table.length);
        Node<K, V> prev = null;
        Node<K, V> cur = table[index];

        while (cur != null){
            if(Objects.equals(cur.key, key)){
                if(prev == null){
                    table[index] = cur.next;
                } else {
                    prev.next = cur.next;
                }

                size--;
                return cur.value;
            }

            prev = cur;
            cur = cur.next;
        }

        return null;
    }

    private void resize(){
        Node<K, V>[] oldTable = table;
        table = new Node[table.length * 2];
        size = 0;

        for(int i = 0; i < oldTable.length; i++){
            Node<K, V> cur = oldTable[i];

            while (cur != null){
                put(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    private int indexFor(K key, int capacity){
        return hash(key) % capacity;
    }

    private static int hash(Object key){
        return (key == null) ? 0 : key.hashCode();
    }

    public int getSize(){
        return size;
    }
}
