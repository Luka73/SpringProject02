package br.com.springproject02.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration //classe de configuração do Spring
@EnableJpaRepositories(basePackages = {"br.com.springproject02"})
@EnableTransactionManagement //Habilitar o uso de transações no banco de dados
public class JpaConfiguration {

    /*
     * Método para configurar a conexão com o banco de dados
     * mapeada no arquivo /META-INF/persistence.xml
     */
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("conexao_mysql");

        return factoryBean;
    }

    /*
     * Método para configurar o gerenciamento de transações do projeto no banco de dados
     * @EnableTransactionManagement -> habilita o controle de transações e exige a configuração abaixo
     */
    //O nome desse método TEM QUE ser transactionManager, afinal é um Bean.
    // Tem que bater com os nomes que o Spring espera.
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);

        return transactionManager;
    }

}

