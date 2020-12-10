package lk.usj.OPD_Management.java.dao;

import lk.usj.OPD_Management.java.dao.custom.Impl.AdminDAOImpl;

public class DAOFactory {
    private static DAOFactory dAOFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();
        }

        return dAOFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOFactory.DAOTypes daoType) {
        switch (daoType) {
            case ADMIN:
                return (T) new AdminDAOImpl();
            default:
                return null;
        }
    }

    public static enum DAOTypes {
        ADMIN;

        private DAOTypes() {
        }
    }
}