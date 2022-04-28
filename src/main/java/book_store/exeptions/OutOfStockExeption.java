package book_store.exeptions;

public class OutOfStockExeption extends RuntimeException{

    public OutOfStockExeption() {
        super("К сожалению, на складе недостаточно книг");
    }
}
