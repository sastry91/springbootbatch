package com.sastry.spring.bootbatch.writer;


import com.sastry.spring.bootbatch.model.Person;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersonWriter {
    @Autowired
    public DataSource dataSource;

    @Bean("personItemWriter")
    JdbcBatchItemWriter<Person> personWriterBean() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setDataSource(dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        writer.setSql("INSERT INTO person (first_name, last_name,father_name) VALUES (:firstName, :lastName,:fatherName)");
        System.out.println("In writer");
        return writer;
    }
}