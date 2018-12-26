package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyUserDaoImpl extends ConnectionDao implements MyUserDao {
    public MyUserDaoImpl(Connection connection) {
        super(connection);
    }

    public void printSumSalaryForProject(String project) {
        String sql = "SELECT projects.project_id, projects.project_name, sum(developers.developer_salary) AS SumSalary\n" +
                "FROM projects\n" +
                "JOIN projects_developers ON (projects.project_id = projects_developers.project_id)\n" +
                "JOIN developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                "GROUP BY projects.project_id\n" +
                "HAVING projects.project_name = \"" + project + "\"";
        int result = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) result = rs.getInt("SumSalary");

            System.out.println("Sum of developers salary for project \"" + project + "\" is: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllDevsOnProject(String project) {
        String sql = "SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, projects.project_name\n" +
                "FROM developers\n" +
                "JOIN projects_developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                "JOIN projects ON (projects.project_id = projects_developers.project_id)\n" +
                "WHERE projects.project_name = (SELECT project_name FROM projects WHERE project_name = '" + project + "');";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("List of " + project + "'s developers:");
            while (rs.next()) {
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
                System.out.println(dev);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong!");
            e.printStackTrace();
        }
    }

    public void printAllJavaDevs() {
        String sql = "SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, skills.skill_branch\n" +
                "FROM developers\n" +
                "JOIN developers_skills ON (developers.developer_id = developers_skills.developer_id)\n" +
                "JOIN skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                "WHERE skills.skill_branch = 'Java';";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("All Java developers:");
            while (rs.next()) {
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
                System.out.println(dev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllMiddleDevs() {
        String sql = "SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, skills.skill_level\n" +
                "FROM developers\n" +
                "JOIN developers_skills ON (developers.developer_id = developers_skills.developer_id)\n" +
                "JOIN skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                "WHERE skills.skill_level = 'Middle';";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("All middle developers:");
            while (rs.next()) {
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
                System.out.println(dev);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllProjects() {
        String sql = "SELECT projects.project_birthday, projects.project_name, COUNT(developers.developer_id) AS Devs\n" +
                "FROM projects\n" +
                "JOIN projects_developers ON (projects.project_id = projects_developers.project_id)\n" +
                "JOIN developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                "GROUP BY projects.project_name;";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("All projects:");
            while (rs.next()) {
                Project project = new Project(
                        rs.getDate("project_birthday"),
                        rs.getString("project_name"),
                        rs.getInt("Devs")
                );
                System.out.println(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
