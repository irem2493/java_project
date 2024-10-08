package c1007;

public class Object_infinityArr<T> {
	private int current_length, length;
	private Object[] dArr;
	public Object_infinityArr() {
		length = 5;
		dArr = new Object[length];
	}
	
	public int size() {return current_length;}
	
	public void add(T d) {
		if(current_length >= length) {
			length += 5;
			Object[] tmp = new Object[length];
			for(int i = 0; i < dArr.length; i++) {
				tmp[i] = dArr[i];
			}
			dArr = tmp;
		}
		dArr[current_length++] = d;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int idx) {
		return (T) dArr[--idx];
	}
	
	public void update(T d, int idx) {
		dArr[--idx] = d;
	}
	
	public void remove(int idx) {
		idx -= 1;
		int j = idx;
		for(int i = idx; i < current_length; i++) {
			dArr[j] = dArr[++j];
		}
		current_length--;
	}
}
