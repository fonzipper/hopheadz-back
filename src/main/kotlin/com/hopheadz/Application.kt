package com.hopheadz

import com.hopheadz.repository.IngredientRepository
import com.hopheadz.util.Serializer
import com.mongodb.MongoClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by NS on 30/03/16.
 */

@SpringBootApplication
open class Application {

    @Bean open fun db() = MongoClient("localhost", 27017).getDatabase("hopheadz")
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