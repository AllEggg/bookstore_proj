package book_store;



import book_store.dao.entity.BookOrder;
import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.OrderDetails;
import book_store.dao.entity.Warehouse;
import book_store.dao.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.List;


@SpringBootApplication
@EnableCaching
public class BookStoreApplication {


	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);

//		BookStoreUser user = new BookStoreUser();
//		user.setUsername("bookStoreUser1");
//		user.setPassword("12345");

		System.out.println("Hello");


	}


}


