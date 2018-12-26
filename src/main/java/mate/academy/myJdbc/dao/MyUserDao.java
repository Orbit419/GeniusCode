package mate.academy.myJdbc.dao;

public interface MyUserDao {
    void printSumSalaryForProject(String project);

    void printAllDevsOnProject(String project);

    void printAllJavaDevs();

    void printAllMiddleDevs();

    void printAllProjects();
}
