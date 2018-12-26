package mate.academy.myJdbc;

import mate.academy.myJdbc.dao.MyUserDao;
import mate.academy.myJdbc.dao.MyUserDaoImpl;
import mate.academy.myJdbc.service.MyUserService;
import mate.academy.myJdbc.service.MyUserServiceImpl;

public class Main {
    public static void main(String[] args) {
        MyUserDao myUserDao = new MyUserDaoImpl(MyConnectionUtil.getConnection());
        MyUserService myUserService = new MyUserServiceImpl(myUserDao);

        myUserService.printSumSalaryForProject("Astra");

        myUserService.printAllDevsOnProject("Astra");

        myUserService.printAllJavaDevs();

        myUserService.printAllMiddleDevs();

        myUserService.printAllProjects();
    }
}
