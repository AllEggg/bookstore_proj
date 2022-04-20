package book_store;

import book_store.dao.entity.Book;
import book_store.dao.repository.AuthorRepository;
import book_store.dao.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import javax.naming.Context;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class BookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);

		System.out.println("Hello");

		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);

		log.info("Автор: {} книги: {}",authorRepository.getAuthorName(2), authorRepository.findAllBooksByAuthor(2));

		log.info("Автор и книги {}", authorRepository.getAuthorAndBooks(1));
	}

}
