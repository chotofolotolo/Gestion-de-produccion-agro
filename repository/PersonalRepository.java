package com.myproyect.miproyect.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproyect.miproyect.model.PersonalModel;

@Repository
public class PersonalRepository extends STDRespository implements PersonalRepositoryInterface {

    public PersonalRepository() {
        super();
    }

    @Override
    public List<PersonalModel> listarPersonal() {
        List<PersonalModel> personal = new ArrayList<>();
        String query = "SELECT * FROM personal";
        PersonalModel p = null;

        try {
            pstmt = conn.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Date fechaSQL = resultSet.getDate("fecha_ingreso");
                LocalDate fecha = fechaSQL.toLocalDate();

                p = new PersonalModel(
                        resultSet.getInt("id_personal"),
                        resultSet.getString("nombre_personal"),
                        resultSet.getString("apellido_personal"),
                        resultSet.getString("dni_personal"),
                        resultSet.getString("cargo_personal"),
                        resultSet.getString("telefono"),
                        resultSet.getString("direccion"),
                        fecha);
                personal.add(p);
                p = null;
            }

            return personal;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public PersonalModel obtenerPersonal(int id) {
        String query = "SELECT * FROM personal WHERE id_personal = ?";
        PersonalModel personal = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Date fechaSQL = resultSet.getDate("fecha_ingreso");
                LocalDate fecha = fechaSQL.toLocalDate();
                
                personal = new PersonalModel(
                        resultSet.getInt("id_personal"),
                        resultSet.getString("nombre_personal"),
                        resultSet.getString("apellido_personal"),
                        resultSet.getString("dni_personal"),
                        resultSet.getString("cargo_personal"),
                        resultSet.getString("telefono"),
                        resultSet.getString("direccion"),
                        fecha);
            }

            return personal;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public PersonalModel agregarPersonal(PersonalModel personal) {
        String query = "INSERT INTO personal(nombre_personal,apellido_personal,dni_personal,cargo_personal,telefono,direccion,fecha_ingreso) VALUES (?,?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, personal.getNombrePersonal());
            pstmt.setString(2, personal.getApellidoPersonal());
            pstmt.setString(3, personal.getDniPersonal());
            pstmt.setString(4, personal.getCargoPersonal());
            pstmt.setString(5, personal.getTelefonoPersonal());
            pstmt.setString(6, personal.getDireccionPersonal());
            pstmt.setDate(7, (Date.valueOf(personal.getFechaIngreso())));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int nuevoId = generatedKeys.getInt(1);
                    personal.setIdPersonal(nuevoId);
                }
                return personal;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean eliminarPersonal(int id) {
        String query = "DELETE FROM personal WHERE id_personal = ?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return false;
        }
    }

    @Override
    public PersonalModel actualizarPersonal(PersonalModel personal) {
        String query = "UPDATE personal SET nombre_personal = ?, apellido_personal = ?, dni_personal = ?, cargo_personal = ?, telefono = ?, direccion = ?, fecha_ingreso = ? WHERE id_personal = ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, personal.getNombrePersonal());
            pstmt.setString(2, personal.getApellidoPersonal());
            pstmt.setString(3, personal.getDniPersonal());
            pstmt.setString(4, personal.getCargoPersonal());
            pstmt.setString(5, personal.getTelefonoPersonal());
            pstmt.setString(6, personal.getDireccionPersonal());
            pstmt.setDate(7, (Date.valueOf(personal.getFechaIngreso())));
            pstmt.setInt(8, personal.getIdPersonal());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return personal;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }
}

interface PersonalRepositoryInterface {
    abstract List<PersonalModel> listarPersonal();

    abstract PersonalModel obtenerPersonal(int id);

    abstract PersonalModel agregarPersonal(PersonalModel p);

    abstract boolean eliminarPersonal(int id);

    abstract PersonalModel actualizarPersonal(PersonalModel p);
}
