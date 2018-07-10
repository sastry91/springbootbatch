package com.sastry.spring.bootbatch.config;

import com.sastry.spring.bootbatch.listener.PersonStepListener;
import com.sastry.spring.bootbatch.model.Person;
import com.sastry.spring.bootbatch.processor.PersonProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("personItemReader")
    private ItemReader<Person> personItemReader;
    @Autowired
    @Qualifier("personItemWriter")
    private ItemWriter<Person> personItemWriter;

    @Autowired
    @Qualifier("personStepListener")
    private PersonStepListener personStepListener;

    @Autowired
    @Qualifier("personItemProcessor")
    private ItemProcessor<Person, Person> personItemProcessor;


    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    Job PersonJob() {
        return jobBuilderFactory.get("PersonJob").incrementer(new RunIdIncrementer()).start(PersonStep()).build();
    }

    @Bean
    Step PersonStep() {
        return stepBuilderFactory.get("PersonStep").<Person, Person>chunk(10).
                reader(personItemReader).writer(personItemWriter).processor(personItemProcessor).listener(personStepListener).build();

    }

    @Bean("personItemProcessor")
    public ItemProcessor<Person, Person> personItemProcessor() {
        return new PersonProcessor();
    }

    @Bean("personStepListener")
    public PersonStepListener personStepListener() {
        return new PersonStepListener();
    }


}
