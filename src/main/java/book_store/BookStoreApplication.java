package book_store;



import book_store.dao.entity.BookOrder;
import book_store.dao.entity.BookStoreUser;
import book_store.dao.entity.OrderDetails;
import book_store.dao.service.DetailsService;
import book_store.dao.service.OrderService;
import book_store.dao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication
@EnableCaching
public class BookStoreApplication {


	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
		UserService userService = context.getBean(UserService.class);
		OrderService orderService = context.getBean(OrderService.class);
		DetailsService detailsService = context.getBean(DetailsService.class);


//		BookStoreUser user = new BookStoreUser();
//		user.setUsername("bookStoreUser1");
//		user.setPassword("12345");
//
//
//		userService.create(user);
//		BookOrder order = new BookOrder();
//		OrderDetails orderDetails = new OrderDetails();
//
//
//		orderService.createOrder(order);
//		order.setOrderDetails(List.of(orderDetails));
//
//		detailsService.create(orderDetails);


		System.out.println("Hello");
	}


}


