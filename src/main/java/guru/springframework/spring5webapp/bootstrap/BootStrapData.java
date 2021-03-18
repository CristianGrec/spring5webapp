package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData (AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Publisher humanitas = new Publisher("Humanitas", "Publisher Street", "Bucharest", "Romania", "463758");
        publisherRepository.save(humanitas);

        Author fyodor = new Author("Fyodor","Dostoievsky");
        Book tbk = new Book("The Brothers Karamazov","123456");

        fyodor.getBooks().add(tbk);
        tbk.getAuthors().add(fyodor);

        humanitas.getBooks().add(tbk);
        tbk.setPublisher(humanitas);
        authorRepository.save(fyodor);
        bookRepository.save(tbk);
        publisherRepository.save(humanitas);

        Author friedrich = new Author("Friedrich", "Nietzsche");
        Book tbot = new Book("The Birth Of Tragedy", "567890");

        friedrich.getBooks().add(tbot);
        tbot.getAuthors().add(friedrich);

        humanitas.getBooks().add(tbot);
        tbot.setPublisher(humanitas);

        authorRepository.save(friedrich);
        bookRepository.save(tbot);
        publisherRepository.save(humanitas);

        System.out.println("started in BootStrapData");
        System.out.println("Number of books: " + bookRepository.count() );
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books published by humanitas: " + humanitas.getBooks().size());
    }
}
