public class App {

    public static void main(String[] args) {

        Pessoa pessoa1 = new Pessoa();
        pessoa1.nome = "Jorge Augusto";
        pessoa1.documento = "88421317253";

        Pessoa pessoa2 = new Pessoa();
        pessoa2.nome = "Larissa Correa";
        pessoa2.documento = "12345678";

        Conta conta1 = new Conta();
        conta1.titular = pessoa1;
        conta1.agencia = 123;
        conta1.numero = 1234;
        conta1.saldo = 15000;

        Conta conta2 = new Conta();
        conta2.titular = pessoa2;
        conta2.agencia = 321;
        conta2.numero = 4321;
        conta2.saldo = 30000;

        System.out.println(conta1.titular.nome);
        System.out.println(conta1.titular.documento);
        System.out.println(conta1.saldo);
        System.out.println();
        System.out.println(conta2.titular.nome);
        System.out.println(conta2.titular.documento);
        System.out.println(conta2.saldo);
    }
}
