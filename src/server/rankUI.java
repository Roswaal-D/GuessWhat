package server;

import UI.startUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rankUI extends JFrame implements ActionListener {
    private Container contain = this.getContentPane();
    private JScrollPane jsp1,jsp2;
    private JTextArea jta1,jta2;
    private JPanel zong;
    private Font font = new Font("宋体", Font.PLAIN, 25);//创建1个字体实例
    private int mod=ranking.getkk();
    private String s1=(mod==0?"一站到底":"七步杀"),s2=s1;
    private ImageIcon logo=new ImageIcon("images/logo.png");
    private JButton jb=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>返回主页面</div></body></html>");

    public rankUI(){

        mod=ranking.getkk();

        init();

        jta1.append(s1);
        jta2.append(s2);

        //常规设置
        setIconImage(logo.getImage());
        setTitle("GuessWhat-猜一猜");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setSize(800,420);
        //this.setBounds(500, 200, 800, 560);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        setLocation(scrnsize.width / 2 - getWidth() / 2, scrnsize.height / 2 - getHeight() / 2);

        contain.setLayout(new GridLayout(1,1));
        contain.add(zong);
        setVisible(true);

    }
    private void init(){
        zong=new JPanel();
        zong.setLayout(null);

        s1+="——数字榜\n\n";
        s2+="——字母榜\n\n";

        ranking rnkA=new ranking(0);
        s1+=rnkA.getrank();

        ranking rnkB=new ranking(1);
        s2+=rnkB.getrank();

        //ranking ra=new ranking(0);

        jta1 = new JTextArea();
        jta1.setFont(font);
        jsp1=new JScrollPane(jta1);
        jta1.setEditable(false);
        jsp1.setBounds(15, 15, 360, 270);
        zong.setLayout(null);
        zong.add(jsp1);


        jta2 = new JTextArea();
        jta2.setFont(font);
        jsp2=new JScrollPane(jta2);
        jta2.setEditable(false);
        jsp2.setBounds(415, 15, 360, 270);
        zong.setLayout(null);
        zong.add(jsp2);

        jb.setBounds(300,322,200,40);
        zong.add(jb);
        jb.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startUI su =new startUI();
    }
}
