package book_store.views;


import book_store.dao.entity.Warehouse;
import book_store.dao.service.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseView {

    private Integer id;
    private Integer bookQuantity;
    private String bookName;

    public WarehouseView mapToView(String bookName, WarehouseService warehouseService) {
        WarehouseView warehouseView = new WarehouseView();
        warehouseView.setId(warehouseService.getBookIdByName(bookName));
        warehouseView.setBookQuantity(warehouseService.getBooksCount(warehouseView.getId()));
        return warehouseView;
    }

    public Warehouse mapFromView(WarehouseView warehouseView) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(warehouseView.getId());
        warehouse.setBookQuantity(warehouseView.getBookQuantity());
        return warehouse;
    }

    public List<WarehouseView> mapToViewList(List<Warehouse> warehouses, WarehouseService warehouseService) {
        List<WarehouseView> warehouseViews = new ArrayList<>();
        for(Warehouse warehouse: warehouses) {
            WarehouseView warehouseView = new WarehouseView();
            warehouseView.setId(warehouse.getId());
            warehouseView.setBookQuantity(warehouse.getBookQuantity());
            warehouseView.setBookName(warehouseService.getBookNameByIdWarehouse(warehouse.getId()));
            warehouseViews.add(warehouseView);
        } return warehouseViews;
    }
}
