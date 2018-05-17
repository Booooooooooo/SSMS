import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainWindow extends JFrame implements ActionListener{
    private List tableList;
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton searchButton;
    private Box listBox;
    private Box buttonBox;
    private JPanel panel;
    private Font headFont;
    private int index;
    private StuModel stuModel;
    private ScoModel scoModel;
    private CourModel courModel;

    public JTable table;
    private String[] colStudent = {"学号", "姓名", "性别", "院系" };
    private String[] colScore = {"课程号", "学生学号", "分数", "重修标记"};
    private String[] colCourse = {"课程号", "课程名", "学分", "教师"};
    private String[] col;

    private AddWin addWin;
    private AbstractTableModel m;
    private Statement stat = null;
    private PreparedStatement ps;
    private Connection ct = null;
    private ResultSet rs = null;
    //private JPanel listPanel;
    //private JPanel displayPanel;
    //private JPanel buttonPanel;
    //String id;

    public MainWindow()
    {
        super("主窗口");
        setSize(1000, 1000);
        setVisible(true);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        headFont = new Font("Dialog", 0, 20);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS)); //尚未修改 用于测试
        setContentPane(panel);

        listBox = Box.createVerticalBox();
        listBox.setSize(150, 1000);
        tableList = new List(3);
        tableList.setSize(150, 1000);
        tableList.setFont(headFont);
        tableList.add("学生表");
        tableList.add("课程表");
        tableList.add("成绩表");
        listBox.add(tableList);
        col = colStudent;

        String[][] rowData = {{}, {}, {}};
        table = new JTable();
        stuModel = new StuModel();
        scoModel = new ScoModel();
        courModel = new CourModel();
        tableList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = tableList.getSelectedIndex();
                switch(index){
                    case 0:
                        table.setModel(stuModel);break;
                    case 1:
                        table.setModel(courModel);col = colCourse;break;
                    case 2:
                        table.setModel(scoModel);break;
                }

                update(getGraphics());
            }
        });
        table.getTableHeader().setFont(headFont);
        table.setFont(new Font("dialog", 0, 16));
        table.setRowHeight(30);

        buttonBox = Box.createHorizontalBox();
        buttonBox.setSize(600, 100);
        addButton = new JButton("添加");
        addButton.setFont(headFont);
        addButton.setSize(100, 40);
        addButton.addActionListener(this);
        deleteButton = new JButton("删除");
        deleteButton.setFont(headFont);
        deleteButton.setSize(100, 40);
        deleteButton.addActionListener(this);
        modifyButton = new JButton("修改");
        modifyButton.setFont(headFont);
        modifyButton.setSize(100, 40);
        modifyButton.addActionListener(this);
        searchButton = new JButton("查找");
        searchButton.setFont(headFont);
        searchButton.setSize(100, 40);
        searchButton.addActionListener(this);
        buttonBox.add(addButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(deleteButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(modifyButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        buttonBox.add(searchButton);
        buttonBox.add(Box.createHorizontalStrut(30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setSize(getWidth() - 150, 50);
        buttonPanel.add(Box.createHorizontalStrut((getWidth() - 670) / 2));
        buttonPanel.add(buttonBox);
        //panel.add(buttonBox);

        JPanel panelr = new JPanel();
        panelr.setLayout(new BorderLayout());
        panelr.add(table.getTableHeader(), BorderLayout.NORTH);
        panelr.add(table, BorderLayout.CENTER);
        panelr.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(listBox);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(panelr);
        panel.add(Box.createHorizontalStrut(10));

    }

    public void actionPerformed(ActionEvent event){
        Object obj = event.getSource();
        addWin = new AddWin(index);
        if(obj == addButton){
            addWin.setVisible(true);
            //addWin = new AddWin(index);
            //addWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            //stuModel.fireTableDataChanged();
            //table.setModel(stuModel);
            StuModel mo= new StuModel();
            table.setModel(mo);//下一次点击按钮时才会更新表单
            //table.validate();
            //table.updateUI();
            //update(getGraphics());
        }else if(obj == deleteButton){
            String tableName = "";
            String id = "";
            int rowNum = table.getSelectedRow();
            //System.out.println(rowNum);
            if(rowNum == -1){
                JOptionPane.showMessageDialog(this, "请选中一行");
                return;
            }
            switch (index){
                case 0:
                    id = stuModel.getValueAt(rowNum, 0).toString();
                    //System.out.println("Id: " + id);
                    tableName = "student";break;
                case 1:id = courModel.getValueAt(rowNum, 0).toString();
                    tableName = "course";break;
                case 2:id = scoModel.getValueAt(rowNum, 0).toString();
                    tableName = "score";break;
            }
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/studentsystem";
                String user = "root";
                String passwd = "wyb980401";

                ct = DriverManager.getConnection(url, user, passwd);
                System.out.println("连接成功");
                ps = ct.prepareStatement("delete from `" + tableName + "` where id = " + id);
                ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(rs != null){
                        rs.close();
                        rs = null;
                    }
                    if(ps != null){
                        ps.close();
                        ps = null;
                    }
                    if(ct != null){
                        ct.close();
                        ct = null;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            switch (index){
                case 0:stuModel = new StuModel();table.setModel(stuModel);break;
                case 1:courModel = new CourModel();table.setModel(courModel);break;
                case 2:scoModel = new ScoModel();table.setModel(scoModel);break;
            }

        }else if(obj == modifyButton){
            //new modifyWin();
        }else{

        }
    }
    public static void main(String[] args){
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
