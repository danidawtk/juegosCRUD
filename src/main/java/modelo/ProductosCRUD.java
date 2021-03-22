/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author DAW2-PROFESOR
 */
public class ProductosCRUD {
     public static List<Productos> getProductos() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM juegos";
        Query q = manager.createNativeQuery(sql,Productos.class); //método para consultas en SQL
        List<Productos> productosBD =  q.getResultList();

        return productosBD;        
        }    
     
        public static int actualizaProductoTest() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Productos p SET p.genero = 'zumos' WHERE p.id = 10";
        Query q = manager.createQuery(sql,Productos.class);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;      
    }
        
       
       
       public static int actualizaProducto(Productos miProducto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE juegos p SET p.nombre = :nombre, p.genero = :genero, p.precio = :precio WHERE p.id = :id";
        Query q = manager.createQuery(sql,Productos.class);
        q.setParameter("id", miProducto.getId());
        q.setParameter("nombre", miProducto.getNombre());
        q.setParameter("genero", miProducto.getGenero());
        q.setParameter("precio", miProducto.getPrecio());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        manager.close();
        return filasAfectadas;      
    }
       
        public static void insertaProducto(Productos producto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
         manager.getTransaction().begin();
        manager.merge(producto);
        manager.getTransaction().commit();
        }
        
        public static int destroyProducto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from juegos p WHERE p.id = " + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;  
    }
        
      public static Productos getProducto(int id) {  //devuelve un objeto de clase Productos
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUDupdate_war_1PU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT p FROM juegos p WHERE p.id=" + id;
        Query q = manager.createQuery(sql,Productos.class); //método para consultas en SQL
        Productos miProducto =  ( Productos ) q.getSingleResult(); //para un único registro
        manager.close();
        return  miProducto;
        } 


}
