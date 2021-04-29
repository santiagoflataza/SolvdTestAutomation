package com.solvd.jaxB.services.comerce;

import com.solvd.jaxB.dao.jaxB.impl.comerce.SupplierDAO;
import com.solvd.jaxB.models.comerce.Supplier;
import com.solvd.jaxB.services.IAbstractServ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SupplierServ implements IAbstractServ<Supplier> {
    private final static Logger logger = LogManager.getLogger(SupplierServ.class);
    private final SupplierDAO supplierDAO = new SupplierDAO();

    public SupplierServ() {
    }

    @Override
    public void create(Supplier supplier) {
        supplierDAO.create(supplier);
        logger.info("Supplier created in DB: " + supplier.toString());
    }

    @Override
    public Supplier getByID(int id) {
        Supplier supplier = supplierDAO.getByID(id);
        logger.info("Getting supplier by " + id + ": "+ supplier.toString());
        return supplier;
    }

    @Override
    public void update(Supplier supplier) {
        supplierDAO.update(supplier);
        logger.info("Updating supplier: " + supplier.toString());
    }

    @Override
    public void delete(int id) {
        supplierDAO.delete(id);
        logger.info("Deleting supplier with id " + id);
    }
}