package com.hopheadz

import com.auth0.spring.security.api.JwtWebSecurityConfigurer
import com.hopheadz.repository.IngredientRepository
import com.hopheadz.util.Serializer
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by NS on 30/03/16
 */

@SpringBootApplication
open class Application {

    @Bean open fun uri() = MongoClientURI(System.getenv("MONGODB_URI"))

    @Bean open fun db() = MongoClient(uri()).getDatabase(System.getenv("DBNAME"))
    @Bean open fun serializer() = Serializer()
    @Bean open fun iRepo() = IngredientRepository(db(), serializer())
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}


@Configuration
@EnableWebMvc
open class WebConfig: WebMvcConfigurerAdapter(){
    @Override
    override fun addCorsMappings(registry: CorsRegistry?) {
        registry?.addMapping("/**")
        super.addCorsMappings(registry)
    }
}

@Configuration
@EnableWebSecurity(debug = true)
@PropertySource(value = "auth0.yml")
open class AuthConfig: WebSecurityConfigurerAdapter() {
    @Value(value = "\${auth0.issuer}")
    lateinit var issuer: String

    @Value(value = "\${auth0.apiAudience}")
    lateinit var apiAudience: String

    @Override
    override fun configure(http: HttpSecurity?) {
        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.GET, "/malt").permitAll()
                .antMatchers(HttpMethod.GET, "/yeast").permitAll()
                .antMatchers(HttpMethod.GET, "/style").permitAll()
                .antMatchers(HttpMethod.GET, "/hop").permitAll()
                .antMatchers(HttpMethod.GET, "/mash-type").permitAll()
                .antMatchers(HttpMethod.GET, "/recipe").hasAuthority("read:recipe")
                .antMatchers(HttpMethod.POST, "/recipe").hasAuthority("create:recipe")
                .antMatchers(HttpMethod.GET, "/brew-setup").hasAuthority("create:brewsetup")
                .antMatchers(HttpMethod.POST, "/brew-setup").hasAuthority("create:brewsetup")
                .antMatchers(HttpMethod.POST, "/mash-step").hasAuthority("create:brewsetup")
                .antMatchers(HttpMethod.POST, "/mash-type").hasAuthority("create:brewsetup")
                .anyRequest().authenticated();
    }
}