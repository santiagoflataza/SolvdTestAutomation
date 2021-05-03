package com.solvd.jaxB.services.commerce;

import com.solvd.jaxB.dao.jaxB.impl.commerce.ProductCartDAO;
import com.solvd.jaxB.models.commerce.ProductCart;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductCartServ implements IAbstractServ<ProductCart> {
    private final static Logger logger = LogManager.getLogger(ProductCartServ.class);
    private final ProductCartDAO productDAO = new ProductCartDAO();

    public ProductCartServ() {
    }

    @Override
    public void create(ProductCart productCart) {
        productDAO.create(productCart);
        logger.info("ProductCart created in DB: " + productCart.toString());
    }

    @Override
    public ProductCart getByID(int id) {
        ProductCart productCart = productDAO.getByID(id);
        logger.info("Getting productCart by " + id + ": "+ productCart.toString());
        return productCart;
    }

    @Override
    public void update(ProductCart productCart) {
        productDAO.update(productCart);
        logger.info("Updating productCart: " + productCart.toString());
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
        logger.info("Deleting productCart with id " + id);
    }
}