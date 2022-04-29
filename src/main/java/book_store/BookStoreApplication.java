package book_store;

import book_store.dao.entity.Author;
import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.Warehouse;
import book_store.dao.service.AuthorService;
import book_store.dao.service.BookService;

import book_store.dao.service.UserService;
import book_store.dao.service.WarehouseService;
import org.hibernate.StaleStateException;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootApplication
@EnableCaching
public class BookStoreApplication {


	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);

//		UserService userService = context.getBean(UserService.class);
//
//		BookStoreUser user = new BookStoreUser();
//		user.setId(2L);
//		user.setUsername("bookStoreUser1");
//		user.setPassword("12345");
//
//		userService.create(user);

		System.out.println("Hello");
	}


}


