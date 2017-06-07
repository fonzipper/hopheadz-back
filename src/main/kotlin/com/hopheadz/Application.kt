package com.hopheadz

import com.hopheadz.repository.IngredientRepository
import com.hopheadz.util.Serializer
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by NS on 30/03/16
 */

@SpringBootApplication
open class Application {

//    @Autowired
//    lateinit var connection: ConnectionConfig

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

@Component
@ConfigurationProperties(prefix = "mongo.database")
open class ConnectionConfig {
    lateinit var host: String
    lateinit var port: Number
    lateinit var dbname: String
    lateinit var dbuser: String
    lateinit var password: CharArray
    lateinit var uri: String
}