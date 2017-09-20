package bancodedados;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

      
    
    public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Users/029161023/Documents/NetBeansProjects/BancoDeDados/Contatos.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
        } 
    
    public void criaTabela (Connection conn) {
        
        String sql = "CREATE TABLE IF NOT EXISTS pessoa(";
                sql += "        id integer PRIMARY KEY,";
                sql += "        nome text NOT NULL";
                sql += ");";
        
        try {
                Statement stmt = conn.createStatement();
                
            // cria uma tabela
            stmt.execute(sql);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insereDados (Connection conn, int id, String nome) {
        String sql = "INSERT INTO pessoa VALUES(?,?)";
        
        try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                
                pstmt.setInt(1, id);
                pstmt.setString(2, nome);
                
                pstmt.executeUpdate();
                
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void selecionaDados (Connection conn) {
        
        String sql = "SELECT id, nome "
                     + "FROM pessoa;";
        
        try {
            
            Statement comandoSql = conn.createStatement();
            
            ResultSet rs = comandoSql.executeQuery(sql);
            
            //loop no resultado
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" + 
                                   rs.getString("nome"));
                }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        }    
    
    public static void main(String[] args) {

        BancoDeDados bd = new BancoDeDados();
        
       Connection conn = bd.connect();
        
        bd.criaTabela(conn);
        
        bd.insereDados(conn, 0, "Fulano de Tal");
        
        bd.insereDados(conn, 1, "Beltrano");
        
        bd.insereDados(conn, 2, "Sicrano");
        
        bd.selecionaDados (conn);
    }
}


    

    
