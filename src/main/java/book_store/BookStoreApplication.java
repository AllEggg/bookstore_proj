package book_store;

import book_store.dao.entity.Product;
import book_store.dao.entity.Warehouse;
import book_store.dao.service.BookService;
import book_store.dao.service.ProductService;
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

	static void massUpdateProduct(ProductService service, Integer productId) {

		Product product = service.getProductById(productId);
		for (int i = 0; i < 5; i++) {
			product.setName("Humus");

			new Thread(() -> {
				try {
					service.updateProduct(product);
				} catch (ObjectOptimisticLockingFailureException e) {
					System.out.println("К сожалению, обновление невозможно");
				}
			}).start();
			log.info("Обновление продукта");
		}

	}

	static void massPurchase(WarehouseService warehouseService, BookService bookService, Integer bookId) throws InterruptedException {


		for (int i = 0; i < 5; i++) {
			log.info("Количество книг до покупки {} - {} ", bookService.getNameById(bookId), warehouseService.getBooksCount(bookId));
			Warehouse warehouse = warehouseService.getWarehouseById(bookId);
			warehouseService.purchaseBook(bookId, warehouse);

			Thread thread = new Thread(() -> {
				try {
					warehouseService.save(warehouse);
				} catch (ObjectOptimisticLockingFailureException e) {
					System.out.println("К сожалению, покупка неуспешна, попробуйте позднее.");
				}
			});
			thread.start();
			log.info("Количество книг после покупки {} - {} ", bookService.getNameById(3), warehouseService.getBooksCount(3));

		}
	}

	private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

	public static void main(String[] args) throws InterruptedException {

		ConfigurableApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);

		System.out.println("Hello");

		WarehouseService warService = context.getBean(WarehouseService.class);

		BookService bookService = context.getBean(BookService.class);

		ProductService productService = context.getBean(ProductService.class);

		int idForTest = 3;

		log.info("Количество книг перед покупками {} - {} ", bookService.getNameById(idForTest), warService.getBooksCount(idForTest));
		massPurchase(warService, bookService, idForTest);

		massUpdateProduct(productService, 3);


	}


}


