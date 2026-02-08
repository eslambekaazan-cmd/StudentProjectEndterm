package repository.jdbc;

import db.DatabaseConnection;
import exception.DatabaseOperationException;
import model.RoutineType;
import repository.interfaces.RoutineTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoutineTypeRepositoryJdbc implements RoutineTypeRepository {

    private final DatabaseConnection db;

    public RoutineTypeRepositoryJdbc(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public RoutineType create(RoutineType entity) {
        String sql = "INSERT INTO routine_types(name) VALUES (?) RETURNING id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getName());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) entity.setId(rs.getInt(1));
            return entity;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create RoutineType: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<RoutineType> findById(int id) {
        String sql = "SELECT id, name FROM routine_types WHERE id = ?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(new RoutineType(rs.getInt("id"), rs.getString("name")));

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find RoutineType: " + e.getMessage(), e);
        }
    }

    @Override
    public List<RoutineType> findAll() {
        String sql = "SELECT id, name FROM routine_types ORDER BY id";
        List<RoutineType> list = new ArrayList<>();
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoutineType(rs.getInt("id"), rs.getString("name")));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to list RoutineTypes: " + e.getMessage(), e);
        }
    }

    @Override
    public RoutineType update(RoutineType entity) {
        String sql = "UPDATE routine_types SET name=? WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
            ps.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update RoutineType: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM routine_types WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete RoutineType: " + e.getMessage(), e);
        }
    }
}
