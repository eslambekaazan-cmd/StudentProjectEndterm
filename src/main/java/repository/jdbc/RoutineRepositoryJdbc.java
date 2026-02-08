package repository.jdbc;

import db.DatabaseConnection;
import exception.DatabaseOperationException;
import model.Routine;
import model.RoutineType;
import repository.interfaces.RoutineRepository;
import repository.interfaces.RoutineTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoutineRepositoryJdbc implements RoutineRepository {

    private final DatabaseConnection db;
    private final RoutineTypeRepository routineTypeRepo;

    public RoutineRepositoryJdbc(DatabaseConnection db, RoutineTypeRepository routineTypeRepo) {
        this.db = db;
        this.routineTypeRepo = routineTypeRepo;
    }

    @Override
    public Routine create(Routine entity) {
        String sql = "INSERT INTO routines(name, routine_type_id) VALUES (?, ?) RETURNING id";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getType().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) entity.setId(rs.getInt(1));
            return entity;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create Routine: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Routine> findById(int id) {
        String sql = "SELECT id, name, routine_type_id FROM routines WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();

            int typeId = rs.getInt("routine_type_id");
            RoutineType type = routineTypeRepo.findById(typeId).orElse(new RoutineType(typeId, "Unknown"));
            return Optional.of(new Routine(rs.getInt("id"), rs.getString("name"), type));

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to find Routine: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Routine> findAll() {
        String sql = "SELECT id, name, routine_type_id FROM routines ORDER BY id";
        List<Routine> list = new ArrayList<>();
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int typeId = rs.getInt("routine_type_id");
                RoutineType type = routineTypeRepo.findById(typeId).orElse(new RoutineType(typeId, "Unknown"));
                list.add(new Routine(rs.getInt("id"), rs.getString("name"), type));
            }
            return list;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to list Routines: " + e.getMessage(), e);
        }
    }

    @Override
    public Routine update(Routine entity) {
        String sql = "UPDATE routines SET name=?, routine_type_id=? WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getType().getId());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update Routine: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM routines WHERE id=?";
        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to delete Routine: " + e.getMessage(), e);
        }
    }
}
