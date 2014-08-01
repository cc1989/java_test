package consistenthash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {

	private final HashFunction hashFunction;
	private final int numberOfReplicas;
	private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas,
			Collection<T> nodes) {
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;

		for (T node : nodes) {
			add(node);
		}
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			System.out.println(hashFunction.hash(node.toString() + i) + " : " + node);
			circle.put(hashFunction.hash(node.toString() + i), node);
		}
	}

	public void remove(T node) {
		for (int i = 0; i < numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString() + i));
		}
	}

	public T get(Object key) {
		if (circle.isEmpty()) {
			return null;
		}
		int hash = hashFunction.hash(key);
		if (!circle.containsKey(hash)) {
			SortedMap<Integer, T> tailMap = circle.tailMap(hash);
			hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
		}
		System.out.println(key + " : " + hash + " : " + circle.get(hash));
		return circle.get(hash);
	}

	public static void main(String[] args){
		ArrayList<String> node = new ArrayList<String>();
		node.add("node0");
		node.add("node1");
		node.add("node2");
		node.add("node3");
		String[] name = {"huangchen", "lixi", "cc", "xx", "dd", "ll"};
		ConsistentHash<String> hash = new ConsistentHash<String>(new HashFunction(),
				3, node);
		for (String str : name){
			hash.get(str);
		}
	}
}