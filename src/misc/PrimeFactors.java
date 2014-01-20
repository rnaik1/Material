package misc;
import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
	public static List<Integer> primeFactors(int number) {
		int n = number; 
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		return factors;
	}
	public static List<Integer> primeFactorsEffective(int numbers) {
		int n = numbers; 
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}
		return factors;
	}

	public static void main(String[] args) {
		for (Integer integer : primeFactors(44)) {
			System.out.println(integer);
		}
		for (Integer integer : primeFactors(3)) {
			System.out.println(integer);
		}
		for (Integer integer : primeFactorsEffective(44)) {
			System.out.println(integer);
		}
		for (Integer integer : primeFactorsEffective(3)) {
			System.out.println(integer);
		}
	}
} 