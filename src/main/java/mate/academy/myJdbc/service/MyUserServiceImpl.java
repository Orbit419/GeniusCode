package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.MyUserDao;

public class MyUserServiceImpl implements MyUserService {
    private final MyUserDao myUserDao;

    public MyUserServiceImpl(MyUserDao myUserDao) {
        this.myUserDao = myUserDao;
    }

    public void printSumSalaryForProject(String project) {
        myUserDao.printSumSalaryForProject(project);
    }

    public void printAllDevsOnProject(String project) {
        myUserDao.printAllDevsOnProject(project);
    }

    public void printAllJavaDevs() {
        myUserDao.printAllJavaDevs();
    }

    public void printAllMiddleDevs() {
        myUserDao.printAllMiddleDevs();
    }

    public void printAllProjects() {
        myUserDao.printAllProjects();
    }
}
