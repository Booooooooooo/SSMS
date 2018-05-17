import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import static javax.swing.UIManager.get;

public class StuModel extends AbstractTableModel {

    Vector rowData, columnNames;

    Statement stat = null;
    Connection ct = null;
    ResultSet rs = null;

    public void init(String sql){
        if(sql.equals("")){
            sql = "select * from student";
        }

        columnNames = new Vector();
        columnNames.add("ID");
        columnNames.add("学号");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("院系");

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
                Vector hang = new Vector();
                hang.add(rs.getString(5));
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
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

    public StuModel(String sql){
        this.init(sql);
    }

    public StuModel(){
        this.init("");
    }

    public int getRowCount(){
        return this.rowData.size();
    }

    public int getColumnCount(){
        return this.columnNames.size();
    }

    public Object getValueAt(int row, int column){
        //System.out.println(((Vector)this.rowData.get(row)).get(0));
        return ((Vector)(this.rowData.get(row))).get(column);
    }

    public String getColumnName(int column){
        return (String)this.columnNames.get(column);
    }
}

