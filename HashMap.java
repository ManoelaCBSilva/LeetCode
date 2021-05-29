import java.util.Arrays;
import java.util.Objects;

public class HashMap {
    private class HashNode<K, V> {
        K key;
        V value;

        HashNode<K, V> next;

        HashNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private int bucketsQty = 10;
    private int fullFilledBuckets = 0;

    private HashNode[] buckets = new HashNode[bucketsQty];

    public void put(K key, V value) {

        int index = getKeyIndex(key);
        HashNode<K, V> hashHead = this.buckets[index];

        if (Objects.isNull(hashHead)) {
            this.buckets[index] = new HashNode(key, value);
            this.fullFilledBuckets++;
            if (this.aboveLoadFactor()) {
                this.expandHashMap();
            }
            return;
        }

        HashNode<K, V> iterator = hashHead;
        while (Objects.nonNull(iterator) && !iterator.key.equals(key)) {
            iterator = iterator.next;
        }

        if (Objects.nonNull(iterator)) {
            iterator.value = value;
        } else {
            HashNode<K, V> newNode = new HashNode<>(key, value);
            newNode.next = hashHead;
            this.buckets[index] = newNode;
        }
    }

    public V get(K key) {

        int index = getKeyIndex(key);
        HashNode<K, V> hashHead = this.buckets[index];

        if (Objects.isNull(hashHead)) {
            return null;
        }

        while (!hashHead.key.equals(key) && Objects.nonNull(hashHead)) {
            hashHead = hashHead.next;
        }

        return Objects.isNull(hashHead) ? null : hashHead.value;
    }

    public void remove(K key) {

        int index = getKeyIndex(key);
        HashNode<K, V> hashHead = this.buckets[index];

        if (Objects.isNull(hashHead)) {
            return;
        }

        if (hashHead.key.equals(key)) {
            this.buckets[index] = hashHead.next;
            if (Objects.isNull(hashHead.next)) {
                this.fullFilledBuckets--;
            }
            return;
        }

        HashNode<K, V> previous = hashHead;
        while (Objects.nonNull(previous.next) && !previous.next.key.equals(key)) {
            previous = previous.next;
        }

        if (Objects.nonNull(previous.next)) {
            previous.next = previous.next.next;
        }
    }

    public int size() {
        return this.fullFilledBuckets;
    }

    public boolean isEmpty() {
        return this.fullFilledBuckets == 0;
    }

    public void print() {

        for (HashNode<K, V> bucket : this.buckets) {
            if (Objects.nonNull(bucket)) {
                HashNode entry = bucket;
                while (Objects.nonNull(entry)) {
                    System.out.print(String.format("{key: %s, value: %s}, ", entry.key, entry.value));
                    entry = entry.next;
                }
                System.out.println();
            }
        }
    }

    private boolean aboveLoadFactor() {
        return ((double) this.fullFilledBuckets / (double) this.bucketsQty) > 0.75;
    }

    private void expandHashMap() {
        this.bucketsQty *= 2;
        HashNode<K, V>[] oldBuckets = this.buckets;
        this.buckets = new HashNode[this.bucketsQty];
        this.fullFilledBuckets = 0;

        Arrays.stream(oldBuckets).forEach(
                bucket -> {
                    while (Objects.nonNull(bucket)) {
                        this.put(bucket.key, bucket.value);
                        bucket = bucket.next;
                    }
                }
        );
    }

    private int getKeyIndex(K key) {
        return Objects.hash(key) % this.bucketsQty;
    }
}
