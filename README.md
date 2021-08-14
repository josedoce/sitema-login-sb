## Usando Spring Security

### Funcionalidades


1. Login com HTTP Basic
	Crie um controller com rotas
2. Login com página HTML gerada pelo Spring Security
	com a configurações basicas > 
		http
			.authorizeRequests()
				.anyRequest()
				.authenticated();
	já é possivel se autenticar para ser autorizado.
3. Login com página customizada
	crie um pacote chamado config.
	crie uma classe de configuração para o spring-security
	apos configuar crie uma pagina personalizada para login.
	Crie paginas html e configure desta forma
			.and()
			.formLogin()
				.loginPage("/entrar")
				.permitAll();
	juntando ao bloco de cima.
4. Proteger páginas do sistema
		http
			.authorizeRequests()
			.antMatchers("/relatorio-equipe").hasAnyRole("uma_regra")
			.anyRequest()
	
5. Configurando permissão para usuários
	builder
			.inMemoryAuthentication()
			.withUser("carlos").password("123").roles("PG_PROJETOS","PG_REL_CUSTOS","PG_REL_EQUIPE")
			criando usuarios para o sistema.
6. Proteger itens do menu
	com as regras definidas e mais facil bloquear itens
7. Incluir o botão de sair
	.and()
		.logout()
		.logoutSuccessUrl("/entrar?logout")
		.permitAll()
8. Buscando usuário da base de dados
	//depois faço um resumo
9. Criando uma implementação de UserDetailsService
	//depois faço um resumo
10. Exibindo o nome do usuário na página html
	//depois faço o resumo
11. Configurando o "remember-me"
	//facilimo, depois faço o resumo.

##  Teste o projeto

```shell
$ ./mvnw clean package spring-boot:run
```



