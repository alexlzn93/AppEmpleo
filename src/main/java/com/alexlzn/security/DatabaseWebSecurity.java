package com.alexlzn.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select username, password, estatus from usuarios where username=?")
		.authoritiesByUsernameQuery("select u.username, p.perfil from usuarioperfil up " + 
			"inner join usuarios u on u.id = up.idusuario " + 
			"inner join perfiles p on p.id = up.idperfil " + 
			"where u.username = ?");
		/*select username y perfil de la tabla usuariosperfil inner join usuarios y perfiles donde id es =idusuario
		 y donde id es= idperfil*/
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() 
            	
    	// Los recursos estÃ¡ticos no requieren autenticaciÃ³n
        .antMatchers(
                "/bootstrap/**",                        
                "/images/**",
                "/tinymce/**",
                "/logotipos/**").permitAll()
        
        // URL permitidas 
        .antMatchers("/", 
        			 "/login",
        			 "/singup",
        			 "/vacantes/buscarVacante",
        			 "/bycrypt/**",
        			 "/vacantes/verDetalle/**").permitAll()
        
      // Asignar permisos a URLs por ROLES

        .antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
        .antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
        .antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
        
        // Todas las demas url requieren autenticacion
        .anyRequest().authenticated()
        // El formulario de Login no requiere autenticacion
        .and().formLogin().loginPage("/login").permitAll();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
