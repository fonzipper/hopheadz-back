package com.hopheadz

import com.hopheadz.repository.IngredientRepository
import com.hopheadz.util.Serializer
import com.mongodb.MongoClient
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by NS on 30/03/16
 */

@SpringBootApplication
open class Application {

    @Autowired
    lateinit var connection: ConnectionConfig

    val addr = ServerAddress(connection.host, connection.port)
    val credentials = MongoCredential.createCredential (connection.dbuser,
                                                        connection.dbname,
                                                        connection.password)
    @Bean open fun db() = MongoClient(addr, listOf(credentials)).getDatabase("hopheadz")
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

@ConfigurationProperties(prefix = "mongo.database")
data class ConnectionConfig(val host: String,
                            val port: Int,
                            val dbname: String,
                            val dbuser: String,
                            val password: CharArray){

}