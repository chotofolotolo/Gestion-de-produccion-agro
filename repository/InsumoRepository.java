package com.myproyect.miproyect.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.myproyect.miproyect.model.InsumoModel;

@Repository
public class InsumoRepository extends STDRespository implements InsumoRepositoryInterface {

    public InsumoRepository() {
        super();
    }

    @Override
    public List<InsumoModel> listarInsumos() {
        List<InsumoModel> insumos = new ArrayList<>();
        String query = "SELECT * FROM insumo";
        InsumoModel insumo = null;

        try {
            pstmt = conn.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Date fechaDeCompraSQL = resultSet.getDate("fecha_compra");
                // PARSEO DE DATE(sql) A LOCALDATE(java)
                LocalDate fechaDeCompra = fechaDeCompraSQL.toLocalDate();

                insumo = new InsumoModel(resultSet.getInt("id_insumo"),
                        resultSet.getInt("id_lote"),
                        resultSet.getString("nombre_insumo"),
                        resultSet.getString("tipo_insumo"),
                        resultSet.getDouble("cantidad_insumos"),
                        resultSet.getBigDecimal("costo_unitario"),
                        fechaDeCompra);
                insumos.add(insumo);
                insumo = null;
            }

            return insumos;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public InsumoModel obtenerInsumo(int id) {
        String query = "SELECT * FROM insumo WHERE id_insumo = ?";
        InsumoModel insumo = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                Date fechaDeCompraSQL = resultSet.getDate("fecha_compra");
                // PARSEO DE DATE(sql) A LOCALDATE(java)
                LocalDate fechaDeCompra = fechaDeCompraSQL.toLocalDate();

                insumo = new InsumoModel(resultSet.getInt("id_insumo"),
                        resultSet.getInt("id_lote"),
                        resultSet.getString("nombre_insumo"),
                        resultSet.getString("tipo_insumo"),
                        resultSet.getDouble("cantidad_insumos"),
                        resultSet.getBigDecimal("costo_unitario"),
                        fechaDeCompra);
            }

            return insumo;
        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

    @Override
    public InsumoModel agregarInsumo(InsumoModel insumo) {
        String query = "INSERT INTO insumo(id_lote,nombre_insumo,tipo_insumo,cantidad_insumos,costo_unitario,fecha_compra)VALUES(?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);// RETURN_GENERATED_KEYS indica que

            pstmt.setInt(1, insumo.getIdLote());
            pstmt.setString(2, insumo.getNombreInsumo());
            pstmt.setString(3, insumo.getTipoInsumo());
            pstmt.setDouble(4, insumo.getCantidadInsumos());
            pstmt.setBigDecimal(5, insumo.getCostoUnitario());
            pstmt.setDate(6, Date.valueOf(insumo.getFechaCompra()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();// Recupera el id autogenerado
                if (generatedKeys.next()) {
                    int nuevoId = generatedKeys.getInt(1);// obtiene el id
                    insumo.setIdInsumo(nuevoId);
                }

                return insumo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }

    }

    @Override
    public boolean eliminarInsumo(int id) {
        String query = "DELETE FROM insumo WHERE id_insumo = ?";

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
    public InsumoModel actualizarInsumo(InsumoModel insumo) {
        String query = "UPDATE insumo SET nombre_insumo = ?, tipo_insumo = ?,cantidad_insumos = ?,costo_unitario = ? WHERE id_insumo = ?";
        try {
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, insumo.getNombreInsumo());
            pstmt.setString(2, insumo.getTipoInsumo());
            pstmt.setDouble(3, insumo.getCantidadInsumos());
            pstmt.setBigDecimal(4, insumo.getCostoUnitario());
            pstmt.setDate(5, Date.valueOf(insumo.getFechaCompra()));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return insumo;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("EXCEPTION->" + ex.getMessage());
            return null;
        }
    }

}

interface InsumoRepositoryInterface {
    abstract List<InsumoModel> listarInsumos();

    abstract InsumoModel obtenerInsumo(int id);

    abstract InsumoModel agregarInsumo(InsumoModel insumo);

    abstract boolean eliminarInsumo(int id);

    abstract InsumoModel actualizarInsumo(InsumoModel insumo);
}