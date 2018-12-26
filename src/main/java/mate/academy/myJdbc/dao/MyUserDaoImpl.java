package mate.academy.myJdbc.dao;

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
}
