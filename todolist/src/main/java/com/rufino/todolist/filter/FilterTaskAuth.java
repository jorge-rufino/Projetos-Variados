package com.rufino.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rufino.todolist.user.UserModelRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

	@Autowired
	private UserModelRepository repository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		Captura o caminho/endpoint da requisição
		var servletPath = request.getServletPath();
		
//		Queremos autenticar somente as requisições feitas no endpoint de "Task"
		if(servletPath.startsWith("/tasks")){
			
//		***Capturar os dados de autenticacao (usuario e senha)***
			
//		Exemplo de valor de um "authorization" em Base64: "Basic fhdaflsahfdasoifojfoaj"
			var authorization = request.getHeader("Authorization");
			
//		Remove a palavra "Basic" do authorization e os espaços em branco para ficar somente a senha em Base64
			var authEncoded = authorization.substring("Basic".length()).trim();
			
//		Decodifica a senha de Base64 para Bytes
			byte[] authDecode = Base64.getDecoder().decode(authEncoded);
			
//		Transforma a senha em bytes numa String. Ex: "username:password"
			var authString = new String(authDecode);
			
			String[] credentials = authString.split(":");
			String usernameCredentials = credentials[0];
			String passwordCredentials = credentials[1];
			
//		***Validar Usuario***
			
			var user = repository.findByUsername(usernameCredentials);
			
			if(user == null) {
				response.sendError(401, "Usuário não encontrado!");
			} else {
				
//			***Validar senha***
				
//			Compara as senhas
				var passwordVerify = BCrypt.verifyer().verify(passwordCredentials.toCharArray(), user.getPassword());
				
//			Se as senhas forem iguais, irá retornar um "true"
				if(passwordVerify.verified) {
					
//					Passa o "id" na requisição para ser capturado no "controller"
					request.setAttribute("idUser", user.getId());
					
					filterChain.doFilter(request, response);		
					
				} else {
					response.sendError(401, "Usuário não autorizado ou senha incorreta!"); 
				}
				
			}
		} else {
			filterChain.doFilter(request, response);
		}
		
		
	}

}

