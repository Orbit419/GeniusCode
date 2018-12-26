package mate.academy.myJdbc.service;

public interface MyUserService {
    void printSumSalaryForProject(String project);

    void printAllDevsOnProject(String project);

    void printAllJavaDevs();

    void printAllMiddleDevs();

    void printAllProjects();
}
