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
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()

                .antMatchers("/test").permitAll()

                .antMatchers("/","/index","/login","/registration","/validateRegistration").permitAll()

                .antMatchers("/settings", "/validatemodifyPassword", "/manageWorkout", "/validateManageWorkout",
                        "/addWorkoutItem", "/removeWorkoutItem", "/saveWorkoutItemToList" , "/modifyWorkoutName",
                        "/createExercise","/validateCreateExercise", "/myWorkouts", "/loadWorkout").hasRole("USER") //extract in the future (createExercise, validateCreateExercise)

                .antMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().denyAll() //Incidentally, all requests are denied

                .and().formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?errorLogin")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/index", true)
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/index?successLogout")
                    .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {return NoOpPasswordEncoder.getInstance(); }

}
