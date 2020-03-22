package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher ross = new Publisher("Ross Publisher", "Tverskaya 2b 64", "Moscow","Moscow", "127206");




        publisherRepository.save(ross);

        System.out.println("Publisher Count: " + publisherRepository.count());

        Author glennonDoyle = new Author("Doyle", "Glennon");
        Book untamed = new Book("Untamed", "2233-44");
        glennonDoyle.getBooks().add(untamed);
        untamed.getAuthors().add(glennonDoyle);
        untamed.setPublisher(ross);
        ross.getBooks().add(untamed);
        authorRepository.save(glennonDoyle);
        bookRepository.save(untamed);
        publisherRepository.save(ross);

        Author jamesDean = new Author("James", "Dean");
        Book peteTheCat = new Book("Pete the Cat: Big Easter Adventure" , "000-333");
        jamesDean.getBooks().add(peteTheCat);
        peteTheCat.getAuthors().add(jamesDean);

        peteTheCat.setPublisher(ross);
        ross.getBooks().add(peteTheCat);

        authorRepository.save(jamesDean);
        bookRepository.save(peteTheCat);
        publisherRepository.save(ross);


        System.out.println("Spring BootStrap");
        System.out.println("Books: "+ bookRepository.count());
        System.out.println("Publisher: "+ publisherRepository.count());


    }
}