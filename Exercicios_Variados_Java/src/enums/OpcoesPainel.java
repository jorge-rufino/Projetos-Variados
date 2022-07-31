package enums;

public enum OpcoesPainel {
	ERRO(-1),
    SAIR(0),
    CRIAR_CONTA(1),
    LISTAR_CONTAS(2),
    LISTAR_CLIENTES(3);
    
    private final int code;

    private OpcoesPainel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OpcoesPainel getOption(int code) {			//Recebe o codigo da opçao
        for (OpcoesPainel option : OpcoesPainel.values()) {	//Percorre todos as opçoes
            if (code == option.getCode()) {					//Se encontrar o código retorna a opçao correspondente
                return option;
            }
        }

        return ERRO;										//Se for passado um codigo que não existe, retorna ERRO
    }
	
}
