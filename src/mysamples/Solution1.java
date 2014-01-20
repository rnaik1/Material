package mysamples;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Solution1 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static public Map<String,Integer> sortByComparator(Map<String,Integer> inputMap) {

		LinkedList linkedList = new LinkedList(inputMap.entrySet());
		List list = linkedList;
		Collections.sort(list, new Comparator() 
		{
			public int compare(Object ele1, Object ele2)
			{
				return ((Comparable) ((Map.Entry) (ele2)).getValue()).compareTo(((Map.Entry) (ele1)).getValue());
			}
		}
				);
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry)it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	static void printKFreqWords(String[] arr, int k){
		HashMap<String, Integer> hM = new HashMap<String, Integer>();
		for(int i=0;i<arr.length; i++){
			if(hM.get(arr[i])!=null)
				hM.put(arr[i], hM.get(arr[i])+1);
			else
				hM.put(arr[i], 1);
		}

		hM = (HashMap<String, Integer>) sortByComparator(hM);
		int count = 0; int prevCount = 0;
		for(String key: hM.keySet()){
			if(count<k){
				System.out.println(key);
				if(hM.get(key)!=prevCount){
					count++;
					prevCount = hM.get(key);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String countArr[] = new String[N];
		for(int i=0;i<N;i++)
			countArr[i] = br.readLine();
		int k = Integer.parseInt(br.readLine());
		printKFreqWords(countArr,k);
	}

}
