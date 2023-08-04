package virtualShelf.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Virtual Shelf API")
                        .description("A Virtual Shelf API where you can register books and your own shelves")
                        .summary("Here you can create an user, make login, register a book and your shelves. " +
                                "Choose already registered books to put on the shelves that you created. Each user," +
                                "book, shelf and book on shelf has your own id." )
                        .contact(new Contact()
                                .name("Aryadne Fernanda Ronqui")
                                .email("aryadne.fernanda@gmail.com")));
    }
}
