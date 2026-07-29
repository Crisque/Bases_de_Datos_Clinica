/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bases_de_datos_hospital;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public boolean registrar(Paciente paciente) {
        String sql = "INSERT INTO paciente (nombre, dni, edad, diagnostico) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getDni());
            ps.setInt(3, paciente.getEdad());
            ps.setString(4, paciente.getDiagnostico());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nombre, dni, edad, diagnostico FROM paciente ORDER BY id";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setDni(rs.getString("dni"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setDiagnostico(rs.getString("diagnostico"));
                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
        }

        return pacientes;
    }
    
    public boolean eliminar(int id) {
    String sql = "DELETE FROM paciente WHERE id = ?";

    try (Connection conexion = ConexionBD.obtenerConexion();
         PreparedStatement ps = conexion.prepareStatement(sql)) {

        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        System.err.println("Error al eliminar: " + e.getMessage());
        return false;
    }
}
   
}
