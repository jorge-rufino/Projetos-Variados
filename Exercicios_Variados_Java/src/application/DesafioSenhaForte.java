package application;

import javax.swing.JOptionPane;

public class DesafioSenhaForte {

	public static void main(String[] args) {
		
		boolean temDigito = false;
		boolean temLetraMinuscula = false;
		boolean temLetraMaiuscula = false;
		boolean temCaractereEspecial = false;
		String mensagensSistema = "";
		
		String senha = JOptionPane.showInputDialog("Digite uma senha que com os seguintes critérios:\n" +
	            "- Possua no mínimo 6 caracteres;"+ "\n" +
	            "- Contenha no mínimo 1 digito;"+ "\n" +
	            "- Contenha no mínimo 1 letra em minúsculo;" + "\n" +
	            "- Contenha no mínimo 1 letra em maiúsculo;" + "\n" +
	            "- Contenha no mínimo 1 caractere especial;");
		
		if (senha.length() < 6) {
			mostraMensagem("Senha precisa ter no mínimo 6 caracteres!");
		}
		else {			
			for(int i = 0; i < senha.length(); i++) {
				char c = senha.charAt(i);
				
				if (Character.isDigit(c)) {
					temDigito = true;
				} 
				if(Character.isLowerCase(c)) {
					temLetraMinuscula = true;
				}
				if(Character.isUpperCase(c)) {
					temLetraMaiuscula = true;
				}
				if(!Character.isLetterOrDigit(c)) {
					temCaractereEspecial = true;
				}
			}
			
			if(!temDigito) {
				mensagensSistema += "Senha precisa ter no mínimo 1 dígito!\n";
			}
			if(!temLetraMinuscula) {
				mensagensSistema += "Senha precisa ter no mínimo 1 letra minúscula!\n";
			}
			if(!temLetraMaiuscula) {
				mensagensSistema += "Senha precisa ter no mínimo 1 letra maiúscula!\n";
			}
			if(!temCaractereEspecial) {
				mensagensSistema += "Senha precisa ter no mínimo 1 caractere especial!\n";
			}
			
			if(temDigito && temLetraMinuscula && temLetraMaiuscula && temCaractereEspecial) {
				mostraMensagem("Senha cadastrada com sucesso!");
			} else {
				mostraMensagem(mensagensSistema);
			}
		}
				
		System.exit(0);
	}

	public static void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}
}
