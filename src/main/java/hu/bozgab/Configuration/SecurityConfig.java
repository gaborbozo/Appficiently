package hu.bozgab.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()

                .antMatchers("/icon/*").permitAll()

                .antMatchers("/","/index","/login","/registration","/validateRegistration").permitAll()

                .antMatchers("/settings","/createExercise","/validateCreateExercise").hasRole("USER") //extract in the future (createExercise, validateCreateExercise)

                .antMatchers("/admin/**", "/createExercise","/validateCreateExercise").hasRole("ADMIN")

                .anyRequest().denyAll() //Incidentally, all requests are denied

                .and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/index", true)
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/index?logout")
                    .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance(); }

}
