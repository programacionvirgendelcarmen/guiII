package modelo.modelo.dao;



import modelo.conexion.ConexionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements UsuarioDAO{
    private static final Connection conexion;

    static {
        try {
            conexion = ConexionSingleton.getConexionSingleton().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insertarUsuario(Usuario usuario) throws SQLException {
        String sql = " INSERT INTO usuario (nombre , dni ) VALUES ( ?, ?);";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setString(1, usuario.getNombre());
        sentencia.setString(2, usuario.getDni());
        int exito = sentencia.executeUpdate();
        if (sentencia != null)
            sentencia.close();
        return exito == 1;
    }

    @Override
    public boolean eliminarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = " DELETE FROM usuario WHERE id = ?;";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idUsuario);
        int exito = sentencia.executeUpdate();
        if (sentencia != null)
            sentencia.close();
        return exito == 1;
    }

    @Override
    public boolean actualizarUsuarioPorId(Usuario newUsuario, int id) throws SQLException {
       String sql = "UPDATE usuario SET nombre = ?, dni = ? WHERE id = ?;";
       PreparedStatement sentencia = conexion.prepareStatement(sql);
       sentencia.setString(1, newUsuario.getNombre());
       sentencia.setString(2, newUsuario.getDni());
       sentencia.setInt(3, id);
       int exito = sentencia.executeUpdate();
       if (sentencia != null)
           sentencia.close();
       return exito == 1;
    }

    @Override
    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        Usuario usuario = null;
        String sql = "select * from usuario  where id = ?;";
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        sentencia.setInt(1, idUsuario);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()){
            int id        = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String dni    = resultado.getString("dni");
            usuario = new Usuario(id, nombre, dni);
        }
        if (resultado != null)
            resultado.close();
        if (sentencia != null)
            sentencia.close();
        return usuario;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuario() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select * from usuario;";
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sql);
        while (resultado.next()){
            int id        = resultado.getInt("id");
            String nombre = resultado.getString("nombre");
            String dni    = resultado.getString("dni");
            Usuario usuario = new Usuario(id, nombre, dni);
            usuarios.add(usuario);
        }
        if (resultado != null)
            resultado.close();
        if (sentencia != null)
            sentencia.close();
        return usuarios;
    }

    @Override
    public List<String[]> obtenerTodosLosUsuariosParaLaTabla() throws SQLException {
        List<String[]> data = new ArrayList<>();
        List<Usuario> usuarios = obtenerTodosLosUsuario();
        for(Usuario usuario : usuarios){
            data.add(usuario.toString().split(","));
        }
        return data;
    }
}
