import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class CourModel extends AbstractTableModel {
    Vector rowData, columnNames;

    Statement stat = null;
    Connection ct = null;
    ResultSet rs = null;
    //private int maxId;

    //public int getMaxId(){
    //    return maxId;
    //}

    public void init(String sql){
        if(sql.equals("")){
            sql = "select * from course order by cno";
        }

        columnNames = new Vector();
        //columnNames.add("");
        columnNames.add("课程号");
        columnNames.add("课程名");
        columnNames.add("学分");
        columnNames.add("授课老师");

        rowData = new Vector();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("加载成功");
            String url = "jdbc:mysql://localhost/studentsystem";
            String user = "root";
            String passwd = "wyb980401";

            ct = DriverManager.getConnection(url, user, passwd);
            stat = ct.createStatement();
            rs = stat.executeQuery(sql);

            while(rs.next()){
                //maxId = Math.max(maxId, rs.getInt(5));
                Vector hang = new Vector();
                //hang.add(rs.getInt(5));
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getDouble(3));
                hang.add(rs.getString(4));
                rowData.add(hang);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(rs != null){
                    rs.close();
                    rs = null;
                }
                if(stat!= null){
                    stat.close();
                    stat = null;
                }
                if(ct != null){
                    ct.close();
                    ct = null;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addStu(String sql){

    }

    public CourModel(String sql){
        this.init(sql);
    }

    public CourModel(){
        this.init("");
    }

    public int getRowCount(){
        return this.rowData.size();
    }

    public int getColumnCount(){
        return this.columnNames.size();
    }

    public Object getValueAt(int row, int column){
        return ((Vector)(this.rowData.get(row))).get(column);
    }

    public String getColumnName(int column){
        return (String)this.columnNames.get(column);
    }
}
