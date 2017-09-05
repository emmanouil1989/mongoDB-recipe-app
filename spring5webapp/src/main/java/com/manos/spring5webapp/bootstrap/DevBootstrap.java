package com.manos.spring5webapp.bootstrap;

import com.manos.spring5webapp.model.Author;
import com.manos.spring5webapp.model.Book;
import com.manos.spring5webapp.model.Publisher;
import com.manos.spring5webapp.repositories.AuthorRepository;
import com.manos.spring5webapp.repositories.BookRepository;
import com.manos.spring5webapp.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harperCollins = new Publisher("Harper Collins!!","address1");
        publisherRepository.save(harperCollins);

        Book  ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);


        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher wrox = new Publisher("Wrox","address2");
        publisherRepository.save(wrox);
        Book noEJB = new Book("J2EE Development without EJB", "23444", wrox);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
