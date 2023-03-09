package br.com.alurafood.pagamentos1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    //anotação @Bean é usada para indicar que um método de uma classe é responsável
    // por criar um objeto gerenciado pelo Spring Framework
    @Bean
    public ModelMapper obterModelMapper() {
        return new ModelMapper();
    }
}
