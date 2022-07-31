package secao_19_GenericsSetMap.application;

import secao_19_GenericsSetMap.entities.Client_HashCode_Equals;

public class TestaClient {

	public static void main(String[] args) {
		Client_HashCode_Equals c1 = new Client_HashCode_Equals("Maria", "maria@gmail.com");
		Client_HashCode_Equals c2 = new Client_HashCode_Equals("Alex", "alex@gmail.com");
		Client_HashCode_Equals c3 = new Client_HashCode_Equals("Maria", "maria@gmail.com");
		Client_HashCode_Equals c4 = c3;

		String s1 = "Test";
		String s2 = "Test";
		String s3 = new String("Test");
		String s4 = new String("Test");
		
		
		System.out.println(c1.hashCode());
		System.out.println(c2.hashCode());
		System.out.println(c1.equals(c2));
		System.out.println(c1.equals(c3) + " => Os conteudos sao iguais");
		System.out.println((c1 == c3) + " => As referencias sao diferentes");
		System.out.println((c4 == c3) + " => A referencia ao objeto é mesma ");
		System.out.println((s1 == s2) + " => Compilador dá um tratamento especial, por isso retorna verdadeiro");
		System.out.println((s3 == s4) + " => Dessa forma não tem tratamento especial, logo retorna falso");
	}

}

