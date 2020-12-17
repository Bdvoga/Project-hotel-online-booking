package service;

import model.Session;
import model.UserType;

public class GeneralService {

    public void verificationAdmin() throws Exception {
        if (Session.getUser() == null || Session.getUser().getUserType() != UserType.ADMIN) {
            throw new Exception("Недостаточно прав для выполнения данной операции");
        }
    }

    public void verification() throws Exception {
        if (Session.getUser() == null) {
            throw new Exception("Для выполнения данной операции необходимо войти в систему");
        }
    }
}