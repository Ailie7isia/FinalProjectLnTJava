package application;

import java.util.ArrayList;
import java.util.List;

public class DataControl {
    private List<DataStorage> menuList = new ArrayList<>();

    public void addData(String code, String name, Integer price, Integer stock) {
    	DataStorage data = new DataStorage(code, name, price, stock);
        menuList.add(data);
    }

    public List<DataStorage> getDataList() {
        return menuList;
    }
}