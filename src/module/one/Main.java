package module.one;

public class Main {
    static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();

        map.put(0, 1);
        map.put(1, 2);
        map.put(16, 3);

        int gotten = map.get(16);
        int removed = map.remove(16);

        System.out.println(gotten);
        System.out.println(removed);

        for(int i = 0; i <= 64; i++){
            map.put(i, i);
        }

        System.out.println(map.getSize());
    }
}
