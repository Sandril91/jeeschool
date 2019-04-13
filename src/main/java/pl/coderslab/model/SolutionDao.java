package pl.coderslab.model;

import pl.coderslab.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SolutionDao {
    public List<Solution>  getAll() {
        LinkedList<Solution> result = new LinkedList<>();

        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM solution");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("users_id"));
                result.add(solution);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
