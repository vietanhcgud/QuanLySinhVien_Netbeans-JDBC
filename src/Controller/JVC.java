package Controller;
import model.SINHVIEN;
import java.sql.*;
import java.util.ArrayList;

public class JVC {
    private Connection conn;
    
    public JVC() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=DoAn_QLSV_PhungVietAnh;user=VA2711;password=271104;encrypt=true;trustServerCertificate=true");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<SINHVIEN> getlistSINHVIEN() {
        ArrayList<SINHVIEN> list = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SINHVIEN s = new  SINHVIEN();
                s.setMASV(rs.getString("MASV"));
                s.setHOSV(rs.getString("HOSV"));
                s.setTENSV(rs.getString("TENSV"));
                s.setGT(rs.getString("GT"));
                s.setNS(rs.getString("NS"));
                s.setLOP(rs.getString("LOP"));
                s.setMAKHOA(rs.getString("MAKHOA"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;       
    }
    
    public boolean addSV(SINHVIEN s){
        String sql = "INSERT INTO SINHVIEN(MASV,HOSV,TENSV,GT,NS,LOP,MAKHOA)" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMASV());
            ps.setString(2, s.getHOSV());
            ps.setString(3, s.getTENSV());
            ps.setString(4, s.getGT());
            ps.setString(5, s.getNS());
            ps.setString(6, s.getLOP());
            ps.setString(7, s.getMAKHOA());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean editSV(SINHVIEN s) {
        String sql = "UPDATE SINHVIEN set HOSV=?,TENSV=?,GT=?,NS=?,LOP=?,MAKHOA=? WHERE MASV=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);           
            ps.setString(1, s.getHOSV());
            ps.setString(2, s.getTENSV());
            ps.setString(3, s.getGT());
            ps.setString(4, s.getNS());
            ps.setString(5, s.getLOP());
            ps.setString(6, s.getMAKHOA());
            ps.setString(7, s.getMASV());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    public static void main(String[] args) {
        new JVC();
    }
    
}
