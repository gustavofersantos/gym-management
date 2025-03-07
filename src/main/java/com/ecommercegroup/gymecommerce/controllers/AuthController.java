package com.ecommercegroup.gymecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercegroup.gymecommerce.dto.LoginDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		System.out.println("Tentativa de login com CPF: " + loginDto.getCpf());

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getCpf(), loginDto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			return ResponseEntity.ok("Login realizado com sucesso!");
		} catch (BadCredentialsException e) {
			System.out.println("Falha na autenticação: CPF ou senha incorretos");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos");
		} catch (Exception e) {
			System.out.println("Erro inesperado no login: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
		}
	}
}