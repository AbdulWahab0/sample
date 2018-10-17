package viseco.sc.model;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import viseco.sc.model.repository.ApplicationRespository;
import viseco.sc.model.document.Application;

@EnableMongoRepositories(basePackageClasses = ApplicationRespository.class)

@Configuration
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(ApplicationRespository applicationRespository)
    {
        return strings->{

              // applicationRespository.save(new Application(1,"HeartBeating","stop","john"));



        };
    }
}



