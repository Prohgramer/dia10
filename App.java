import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class App {
   static void mostrar_top_clientes(Connection c) throws SQLException {
      String selectSql = "SELECT count(f.id),c.nombre,c.apellido,c.nro_cedula FROM cliente c join factura f on c.id = f.cliente_id GROUP BY c.nombre,c.apellido,c.nro_cedula ORDER BY count DESC";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
     while(rs.next()){
        //Display values
            System.out.print("Cantidad_factura: " + rs.getInt("count"));
            System.out.print(", Nombre: " + rs.getString("nombre"));
            System.out.print(", Apellido: " + rs.getString("apellido"));
            System.out.println(", nro_cedula " + rs.getString("nro_cedula"));
         }
   }
   static void mostrar_top_clien_mas_factura(Connection c) throws SQLException{
      String selectSql = "SELECT c.nombre, SUM(ROUND(p.precio*fd.cantidad)) AS suma " +
              "FROM producto  p " +
              "INNER JOIN factura_detalle fd " +
              "ON p.id = fd.producto_id " +
              "INNER JOIN factura f " +
              "ON f.id = fd.factura_id " +
              "INNER JOIN cliente c " +
              "ON f.cliente_id = c.id GROUP BY (c.id) ORDER BY suma DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print(" nombre: " + rs.getString("nombre"));
         System.out.println("suma: " + rs.getInt("suma"));
      }
   }
   static void mostrar_top_monedas(Connection c) throws SQLException {
      String selectSql = "SELECT count(m.nombre),m.nombre FROM moneda m join factura f on m.id = f.moneda_id GROUP BY m.nombre ORDER BY count DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Count " + rs.getInt("count"));
         System.out.println(", Nombre: " + rs.getString("nombre"));
      }
   }
   static void mostrar_top_proveedor(Connection c) throws SQLException {
      String selectSql = "SELECT count(p.ruc),p.nombre FROM proveedor p join producto pr on p.id=pr.proveedor_id GROUP BY p.nombre ORDER BY count DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Count " + rs.getInt("count"));
         System.out.println(", Nombre: " + rs.getString("nombre"));
      }
   }
   static void mostrar_producto_mas_vendido(Connection c) throws SQLException {
      String selectSql = "SELECT p.nombre, SUM(ROUND(fd.cantidad)) AS mas_vendido FROM producto p INNER JOIN factura_detalle fd ON p.id = fd.producto_id GROUP BY(p.id) ORDER BY mas_vendido DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Nombre: " + rs.getString("nombre"));
         System.out.println(", mas_vendido " + rs.getInt("mas_vendido"));

      }
   }
   static void mostrar_producto_menos_vendido(Connection c) throws SQLException {
      String selectSql = "SELECT p.nombre, SUM(ROUND(fd.cantidad)) AS menos_vendido FROM producto p INNER JOIN factura_detalle fd ON p.id = fd.producto_id GROUP BY(p.id) ORDER BY menos_vendido ASC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Nombre: " + rs.getString("nombre"));
         System.out.println(", menos_vendido " + rs.getInt("menos_vendido"));

      }
   }
   static void Consulta_variada(Connection c) throws SQLException {
      String selectSql = "SELECT f.fecha_emision, c.nombre, c.apellido, p.nombre AS nombre_producto, fd.cantidad, ft.nombre AS tipo_factura " +
              "FROM cliente c " +
              "INNER JOIN factura f " +
              "ON c.id = f.cliente_id " +
              "INNER JOIN factura_detalle fd " +
              "ON f.id = fd.factura_id " +
              "INNER JOIN producto  p " +
              "ON fd.producto_id = p.id " +
              "INNER JOIN factura_tipo ft " +
              "ON ft.id = f.factura_tipo_id;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Cantidad_factura: " + rs.getDate("fecha_emision"));
         System.out.print(", Nombre: " + rs.getString("nombre"));
         System.out.print(", Apellido: " + rs.getString("apellido"));
         System.out.print(", Nombre-producto: " + rs.getString("nombre_producto"));
         System.out.print(", Cantidad: " + rs.getDouble("cantidad"));
         System.out.println(", tipo-factura " + rs.getString("tipo_factura"));
      }
   }
   static void mostrar_monto_factura_ordenadas(Connection c) throws SQLException {
      String selectSql = "SELECT f.id, SUM(ROUND(p.precio*fd.cantidad)) AS monto " +
              "FROM producto p " +
              "INNER JOIN factura_detalle fd " +
              "ON p.id = fd.producto_id " +
              "INNER JOIN factura f " +
              "ON f.id = fd.factura_id GROUP BY (f.id) ORDER BY monto DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Id: " + rs.getString("id"));
         System.out.println(", Monto " + rs.getDouble("monto"));

      }
   }
   static void mostrar_iva(Connection c) throws SQLException {
      String selectSql = "SELECT f.id, SUM(ROUND(p.precio*fd.cantidad)) AS iva " +
              "FROM producto p " +
              "INNER JOIN factura_detalle fd " +
              "ON p.id = fd.producto_id " +
              "INNER JOIN factura f " +
              "ON f.id = fd.factura_id GROUP BY (f.id) ORDER BY iva DESC;";
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(selectSql);
      while(rs.next()){
         //Display values
         System.out.print("Id: " + rs.getString("id"));
         System.out.println(", Iva " + rs.getDouble("iva"));

      }
   }
   public static void main(String args[]) {
      Connection c = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/bootcamp_market",
            "postgres", "rootpro");
         mostrar_top_clientes(c);
         System.out.println("");
         mostrar_top_clien_mas_factura(c);
         System.out.println("");
         mostrar_top_monedas(c);
         System.out.println("");
         mostrar_top_proveedor(c);
         System.out.println("");
         mostrar_producto_mas_vendido(c);
         System.out.println("");
         mostrar_producto_menos_vendido(c);
         System.out.println("");
         Consulta_variada(c);
         System.out.println("");
         mostrar_monto_factura_ordenadas(c);
         System.out.println("");
         mostrar_iva(c);
         System.out.println("");
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      System.out.println("Opened database successfully");
   }

}