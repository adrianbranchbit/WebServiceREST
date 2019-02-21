package com.branchbit.bd;

import java.sql.*;
import javax.swing.*;

public class ConexionOracle {
    String user;
    String pass;
    String url;
    Connection conex;
    Statement stm;
    
    public ConexionOracle(String usr,String ps,String surl) {
        super();
        this.user=usr;
        this.pass=ps;
        this.url=surl;
        this.conex=null;
        this.stm=null;
    }
    
    public void conectar(){
        try {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            conex=DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion establecida");    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexio no realizada");
        }
    }
    
    public void crearTabla() throws SQLException{
        int r=0;
        String cadSql="";
        stm=conex.createStatement();
        try{
            cadSql="create table PERSONAS (ID VARCHAR2(20) PRIMARY KEY,NOMBRE VARCHAR2(20),EDAD NUMBER(2))";
            r=stm.executeUpdate(cadSql);
            JOptionPane.showMessageDialog(null, (r+1)+" tabla realizada");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Tabla no creada");
        }
    }
    
    public String LeerDatosBD() throws SQLException{
        stm=conex.createStatement();
        String cadSql="";
        try{
            cadSql="select * from PERSONAS";
            ResultSet rs=stm.executeQuery(cadSql);
            String datos="";
            while(rs.next()){
                datos+=(rs.getString("ID")+" "+ rs.getString(2)+
                        " "+rs.getInt(3))+"\n";
            }
            return datos;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "La tabla no existe");
            return null; 
        }
    }
        
    public void ingresarDatosBD(String id, String nombre, int edad) 
            throws SQLException{
        
        int r=0;
        String cadSql;
        stm= conex.createStatement();
        try{
            cadSql="insert into PERSONAS values('"+id+"','"+nombre+"','"+edad+"')";
            r=stm.executeUpdate(cadSql);
            JOptionPane.showMessageDialog(null, "Registro agregado");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Registro no agrgado");
        }
    }
    
    public String BuscarRegistroBD(String id) throws SQLException{
        stm=conex.createStatement();
        String cadSql;
        try{
            String nombre="";
            cadSql="select * from PERSONAS where ID='" + id + "'";
            ResultSet rs=stm.executeQuery(cadSql);
            while (rs.next()){
                nombre=rs.getString(1)+" "+rs.getString(2)+
                       " "+rs.getInt(3);
            }
            return nombre;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "El registro no existe");
            return null;
        }
    }
    
    public void eliminarDatos() throws SQLException{
        int r;
        String cadSql;
        stm=conex.createStatement();
        try{
            cadSql="drop table PERSONAS ";
            r=stm.executeUpdate(cadSql);
            JOptionPane.showMessageDialog(null, (r+1)+" Tabla eliminada");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, " No se encontro la tabla");
        }
    }
}