package com.solvd.dataBaseOnlineShop.services.comerce;

import com.solvd.dataBaseOnlineShop.dao.jdbc.impl.comerce.ProductOrderDAO;
import com.solvd.dataBaseOnlineShop.models.comerce.ProductOrder;
import com.solvd.dataBaseOnlineShop.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductOrderServ implements IAbstractServ<ProductOrder> {
    private final static Logger logger = LogManager.getLogger(ProductOrderServ.class);
    private final ProductOrderDAO productOrderDAO = new ProductOrderDAO();

    public ProductOrderServ() {
    }

    @Override
    public void create(ProductOrder productOrder) {
        productOrderDAO.create(productOrder);
        logger.info("ProductOrder created in DB: " + productOrder.toString());
    }

    @Override
    public ProductOrder getByID(int id) {
        ProductOrder productOrder = productOrderDAO.getByID(id);
        logger.info("Getting productOrder by " + id + ": "+ productOrder.toString());
        return productOrder;
    }

    @Override
    public void update(ProductOrder productOrder) {
        productOrderDAO.update(productOrder);
        logger.info("Updating productOrder: " + productOrder.toString());
    }

    @Override
    public void delete(int id) {
        productOrderDAO.delete(id);
        logger.info("Deleting productOrder with id " + id);
    }
}
