package book_store.views;


import book_store.dao.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseView {

    private Integer id;
    private Integer bookQuantity;

    public WarehouseView mapToView(Warehouse warehouse) {
        WarehouseView warehouseView = new WarehouseView();
        warehouseView.setId(warehouse.getId());
        warehouseView.setBookQuantity(warehouse.getBookQuantity());
        return warehouseView;
    }

    public Warehouse mapFromView(WarehouseView warehouseView) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseView.getId());
        warehouse.setBookQuantity(warehouseView.getBookQuantity());
        return warehouse;
    }
}
