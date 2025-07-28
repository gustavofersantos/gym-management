package com.managementgroup.gymmanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managementgroup.gymmanagement.dto.LoginDto;
import com.managementgroup.gymmanagement.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/debug/{cpf}")
	public ResponseEntity<String> debugUser(@PathVariable String cpf) {
		try {
			var user = userService.findByCpf(cpf);
			return ResponseEntity.ok("Usuário encontrado: " + user.getName() + 
				" | CPF: [" + user.getCpf() + "] | Length: " + user.getCpf().length() +
				" | CPF buscado: [" + cpf + "] | Length: " + cpf.length());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado. CPF buscado: [" + cpf + "] | Erro: " + e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto loginDto) {
		System.out.println("=== LOGIN ENDPOINT CHAMADO ===");
		System.out.println("Tentativa de login com CPF: " + loginDto.getCpf());

		Map<String, Object> response = new HashMap<>();

		try {
			// Buscar o usuário diretamente
			var user = userService.findByCpf(loginDto.getCpf());
			
			// Verificar a senha
			if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
				System.out.println("Senha incorreta para usuário: " + loginDto.getCpf());
				response.put("success", false);
				response.put("message", "CPF ou senha incorretos");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
			
			// Criar autenticação manualmente
			Authentication authentication = new UsernamePasswordAuthenticationToken(
				user.getCpf(), 
				null, 
				user.getAuthorities()
			);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println("Login realizado com sucesso para: " + loginDto.getCpf());
			
			response.put("success", true);
			response.put("message", "Login realizado com sucesso!");
			response.put("user", Map.of(
				"cpf", user.getCpf(),
				"name", user.getName(),
				"email", user.getEmail()
			));
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println("Erro inesperado no login: " + e.getMessage());
			e.printStackTrace();
			
			response.put("success", false);
			response.put("message", "Erro interno no servidor: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PostMapping("/test-password")
	public ResponseEntity<String> testPassword(@RequestBody LoginDto loginDto) {
		System.out.println("=== TEST PASSWORD ENDPOINT CHAMADO ===");
		System.out.println("Testando senha para CPF: " + loginDto.getCpf());
		
		try {
			var user = userService.findByCpf(loginDto.getCpf());
			boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
			
			String result = "Senha está correta: " + matches + 
				" | Senha enviada: " + loginDto.getPassword() + 
				" | Hash no banco: " + user.getPassword() +
				" | Usuário: " + user.getName();
			
			System.out.println(result);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			System.out.println("Erro no test-password: " + e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}
}