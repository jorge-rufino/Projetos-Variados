package secao_20_Prog_Funcional_Lambda.appication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExemploStream {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(3,4,5,10,7);
		
		Stream<Integer> st = list.stream();		
		System.out.println(Arrays.toString(st.toArray()));
		
		Stream<String> st1 = Stream.of("Jorge", "Nickolas", "Larissa");
		System.out.println(Arrays.toString(st1.toArray()));
		
		//Iterate o primeiro argumento é o primeiro elemento do Stream e o segundo é a regra para criar os proximos
		//Repare que neste caso, ele vai criar uma sequencia infinita então teremos que limitar ele na chamada do Stream
		Stream<Integer> st2 = Stream.iterate(0, x -> x + 2);
		System.out.println(Arrays.toString(st2.limit(10).toArray()));	
		
		Stream<Long> fibonacci = Stream.iterate(new long[]{ 0L, 1L }, p->new long[]{ p[1], p[0]+p[1] }).map(p -> p[0]);
		System.out.println(Arrays.toString(fibonacci.limit(15).toArray()));
		
		list.sort((n2,n1) -> n1.compareTo(n2));
		System.out.println(list);
	}

}
