import java.util.LinkedList;

/**
 * Hashing 哈希算法复习。
 *
 * 覆盖 Week 12：
 * 1. Open Addressing 开放地址法
 * 2. Linear Probing 线性探测
 * 3. Quadratic Probing 二次探测
 * 4. Double Hashing 双重哈希
 * 5. Separate Chaining 分离链表
 * 6. Load Factor 与 Rehashing
 */
public class HashingAlgorithms {

    public static void main(String[] args) {
        OpenAddressIntSet linear = new OpenAddressIntSet(11, ProbingStrategy.LINEAR);
        linear.add(10);
        linear.add(21);
        linear.add(32);
        System.out.println("Linear probing contains(21): " + linear.contains(21));

        OpenAddressIntSet quadratic = new OpenAddressIntSet(11, ProbingStrategy.QUADRATIC);
        quadratic.add(10);
        quadratic.add(21);
        quadratic.add(32);
        System.out.println("Quadratic probing contains(32): " + quadratic.contains(32));

        OpenAddressIntSet doubleHash = new OpenAddressIntSet(11, ProbingStrategy.DOUBLE_HASHING);
        doubleHash.add(10);
        doubleHash.add(21);
        doubleHash.add(32);
        System.out.println("Double hashing contains(10): " + doubleHash.contains(10));

        SeparateChainingIntSet chaining = new SeparateChainingIntSet(5);
        chaining.add(10);
        chaining.add(15);
        chaining.add(20);
        System.out.println("Separate chaining contains(15): " + chaining.contains(15));
    }

    public enum ProbingStrategy {
        LINEAR,
        QUADRATIC,
        DOUBLE_HASHING
    }

    /**
     * 开放地址法 HashSet，专门存 int，方便看懂探测过程。
     *
     * state:
     * 0 = 从未使用 empty
     * 1 = 正在使用 occupied
     * 2 = 删除过 deleted
     */
    public static class OpenAddressIntSet {
        private int[] table;
        private byte[] state;
        private int size;
        private final ProbingStrategy strategy;

        public OpenAddressIntSet(int capacity, ProbingStrategy strategy) {
            int actualCapacity = nextPrime(Math.max(3, capacity));
            this.table = new int[actualCapacity];
            this.state = new byte[actualCapacity];
            this.strategy = strategy;
        }

        /**
         * 插入元素。
         *
         * 如果负载因子超过 0.5，就扩容并 rehash。
         */
        public boolean add(int value) {
            if (contains(value)) {
                return false;
            }

            if ((size + 1.0) / table.length > 0.5) {
                rehash(table.length * 2);
            }

            int firstDeletedIndex = -1;

            for (int j = 0; j < table.length; j++) {
                int index = probeIndex(value, j);

                if (state[index] == 2 && firstDeletedIndex == -1) {
                    firstDeletedIndex = index;
                }

                if (state[index] == 0) {
                    int target = firstDeletedIndex == -1 ? index : firstDeletedIndex;
                    table[target] = value;
                    state[target] = 1;
                    size++;
                    return true;
                }
            }

            // 理论上负载因子控制得当不会走到这里；保险起见再扩容。
            rehash(table.length * 2);
            return add(value);
        }

        /** 查找元素。 */
        public boolean contains(int value) {
            for (int j = 0; j < table.length; j++) {
                int index = probeIndex(value, j);

                if (state[index] == 0) {
                    return false;
                }

                if (state[index] == 1 && table[index] == value) {
                    return true;
                }
            }

            return false;
        }

        /** 删除元素：开放地址法不能简单清空，要标记 deleted，否则会截断后续探测链。 */
        public boolean remove(int value) {
            for (int j = 0; j < table.length; j++) {
                int index = probeIndex(value, j);

                if (state[index] == 0) {
                    return false;
                }

                if (state[index] == 1 && table[index] == value) {
                    state[index] = 2;
                    size--;
                    return true;
                }
            }

            return false;
        }

        /**
         * 根据探测策略计算第 j 次探测的位置。
         */
        private int probeIndex(int value, int j) {
            int n = table.length;
            int start = Math.floorMod(value, n);

            switch (strategy) {
                case LINEAR:
                    return (start + j) % n;
                case QUADRATIC:
                    return (start + j * j) % n;
                case DOUBLE_HASHING:
                    int step = secondHash(value);
                    return (start + j * step) % n;
                default:
                    throw new IllegalStateException("unknown probing strategy");
            }
        }

        /**
         * 双重哈希的第二个 hash function。
         *
         * h2(value) = q - value % q
         * q 是小于表长的质数，保证 step 不为 0，并减少重复探测序列。
         */
        private int secondHash(int value) {
            int q = previousPrime(table.length);
            return q - Math.floorMod(value, q);
        }

        /** Rehash：新建更大的表，把旧表中正在使用的元素重新插入。 */
        private void rehash(int newCapacity) {
            int[] oldTable = table;
            byte[] oldState = state;

            table = new int[nextPrime(newCapacity)];
            state = new byte[table.length];
            size = 0;

            for (int i = 0; i < oldTable.length; i++) {
                if (oldState[i] == 1) {
                    add(oldTable[i]);
                }
            }
        }
    }

    /**
     * 分离链表 Separate Chaining。
     *
     * 每个 table index 是一个 bucket；
     * 同一个 index 的多个元素存在同一个 LinkedList 中。
     */
    public static class SeparateChainingIntSet {
        private LinkedList<Integer>[] buckets;
        private int size;

        @SuppressWarnings("unchecked")
        public SeparateChainingIntSet(int capacity) {
            buckets = new LinkedList[nextPrime(Math.max(3, capacity))];

            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        public boolean add(int value) {
            if (contains(value)) {
                return false;
            }

            if ((size + 1.0) / buckets.length > 0.9) {
                rehash(buckets.length * 2);
            }

            buckets[index(value)].add(value);
            size++;
            return true;
        }

        public boolean contains(int value) {
            return buckets[index(value)].contains(value);
        }

        public boolean remove(int value) {
            boolean removed = buckets[index(value)].remove((Integer) value);
            if (removed) {
                size--;
            }
            return removed;
        }

        private int index(int value) {
            return Math.floorMod(value, buckets.length);
        }

        @SuppressWarnings("unchecked")
        private void rehash(int newCapacity) {
            LinkedList<Integer>[] oldBuckets = buckets;

            buckets = new LinkedList[nextPrime(newCapacity)];
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }

            size = 0;
            for (LinkedList<Integer> bucket : oldBuckets) {
                for (int value : bucket) {
                    add(value);
                }
            }
        }
    }

    private static int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private static int previousPrime(int n) {
        for (int candidate = n - 1; candidate >= 2; candidate--) {
            if (isPrime(candidate)) {
                return candidate;
            }
        }
        return 2;
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= n; divisor++) {
            if (n % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}
