import javax.swing.*;
import java.awt.*;

public class SearchRes extends JDialog {

    private JTable table;
    private StuModel stuModel;
    private ScoModel scoModel;
    private CourModel courModel;
    private int index;
    private Font font = new Font("Dialog", Font.PLAIN, 15);

    public SearchRes(Dialog owner, boolean modal, int index, String strsql){
        super(owner, "查询结果", modal);
        this.index = index;
        table = new JTable();
        table.setFont(font);
        add(table);
        setSize(500, 500);
        Dimension screenSize = getToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        try{
            switch (index){
                case 0:
                    stuModel = new StuModel(strsql);
                    table.setModel(stuModel);
                    break;
                case 1:
                    courModel = new CourModel(strsql);
                    table.setModel(courModel);
                    break;
                case 2:
                    scoModel = new ScoModel(strsql);
                    table.setModel(scoModel);
                    break;
            }
            setVisible(true);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, "查找不到信息");
            this.dispose();
        }

    }
}
