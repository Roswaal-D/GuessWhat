package Game;
//一站到底系列
import server.rankUI;
import server.ranking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class fatherGame extends JFrame implements ActionListener {

    protected JPanel hist,sbm,zong;
    protected JTextArea jta,inp;
    protected JScrollPane jsp;
    protected Container contain = this.getContentPane();
    protected ImageIcon logo=new ImageIcon("images/logo.png");
    protected Font font = new Font("宋体", Font.PLAIN, 25);//创建1个字体实例
    protected JLabel jl=new JLabel("在此输入你的猜测");
    protected JButton jb=new JButton("<html><body><div style='font-size:17px;font-family:黑体;'>提 交</div></body></html>");
    protected JLabel jll1,jll2;


    protected String ans;//真正谜底
    protected int as[]=new int[4];//数字化谜底
    protected int temas[]=new int[4];
    protected int mod=0;
    protected String id;
    protected int lj,sc=31;
    protected final int max_lj=30;



    //根据模式位mod确定基本参数
    public fatherGame(int mo){
        mod=mo;
        randans();

        tran(as);
        System.out.println("aim:"+ans);
        lj=0;

        init();
        tinit();

        id=JOptionPane.showInputDialog("请输入ID","ID不超过10字符");
        System.out.println(id);

        //常规设置
        setIconImage(logo.getImage());
        setTitle("GuessWhat-猜一猜");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.setSize(800,560);
        //this.setBounds(500, 200, 800, 560);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        setLocation(scrnsize.width / 2 - getWidth() / 2, scrnsize.height / 2 - getHeight() / 2);

        contain.add(zong);
        setVisible(true);
        /*System.out.println(as[0]+" "+as[1]+" "+as[2]+" "+as[3]+" ");
        System.out.println(ans);*/
    }

    protected void tinit(){
        jll1=new JLabel(mod<2?"一站到底":"七步杀");
        Font font1 = new Font("方正兰亭黑_GBK", Font.PLAIN, 45);//创建1个字体实例
        jll1.setFont(font1);
        jll1.setBounds(590-(mod<2?90:68),80,300,60);
        zong.add(jll1);

        jll2=new JLabel(mod%2==0?"-数字-":"-字母-");
        Font font2=new Font("方正兰亭黑_GBK", Font.PLAIN, 22);
        jll2.setFont(font2);
        jll2.setBounds(557,145,100,37);
        zong.add(jll2);
    }

    //UI初始化
    protected void init(){
        zong=new JPanel();
        hist=new JPanel();
        jta = new JTextArea();
        jta.setFont(font);
        jsp=new JScrollPane(jta);
        jta.setEditable(false);
        jsp.setBounds(10, 15, 380, 500);
        zong.setLayout(null);
        zong.add(jsp);

        jl.setFont(font);
        jl.setBounds(490,230,300,40);
        zong.add(jl);

        inp=new JTextArea();
        inp.setFont(font);
        inp.setBounds(440,305,300,32);
        zong.add(inp);

        jb.setBounds(530,370,120,40);
        zong.add(jb);
        jb.addActionListener(this);

    }


    //随机数生成
    protected void randans(){
        Random r=new Random();
        as[0]=r.nextInt(10);

        for(int i=1;i<4;i++){
            boolean flag=true;
            int tem=0;
            while(flag){
                flag=false;
                tem=r.nextInt(10);
                for(int j=0;j<i;j++){
                    if(tem==as[j]){
                        flag=true;
                    }
                }
            }
            as[i]=tem;
        }
    }
    //翻译函数
    protected String tran(int tt[]){
        String tem="";
        if(mod%2==0){
            for(int i=0;i<4;i++){
                tem+=tt[i];
            }
        }else{
            for(int i=0;i<4;i++){
                tem+=(char)('a'+tt[i]);
            }
        }
        ans=tem;
        return tem;
    }
    protected void deco(String s){
        char zero=mod%2==0? '0':'a';
        for(int i=0;i<4;i++) {
            temas[i] = s.charAt(i) - zero;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int a=0,b=0;

        boolean yz=false;
        String te=inp.getText();
        if(te.length()<4){
            JOptionPane.showMessageDialog(null,"输入长度应为四个字符！","警告",JOptionPane.PLAIN_MESSAGE);
            return;
        }
        String guess=te.substring(0,4);
        inp.setText("");
        deco(guess);

        System.out.println("get "+temas[0]+" "+temas[1]+" "+temas[2]+" "+temas[3]+" ");


        for(int i=0;i<4;i++){
            if(temas[i]==as[i]) b++;
            for(int j=0;j<4;j++){
                if(temas[i]==as[j]) a++;
            }
        }
        b=a-b;
        a=a-b;

        if(a==4){
            yz=true;
        }

        JOptionPane.showMessageDialog(null,("本轮结果："+a+"A"+b+"B"),"本轮结果",JOptionPane.PLAIN_MESSAGE);
        jta.append(guess+"  ->  "+a+"A"+b+"B\n");

        System.out.println(guess);
        lj++;
        sc--;
        if(lj>=max_lj||yz){
            String jl="游戏结束，您的得分为：";
            jl+=(""+sc);
            ranking rk=new ranking(mod%2);
            if(rk.insertIt(id,sc)){
                jl+="，恭喜成功打榜！";
            }else{
                jl+="，未能成功打榜，请再接再厉~";
            }

            JOptionPane.showMessageDialog(null,jl,"游戏结算",JOptionPane.PLAIN_MESSAGE);
            rankUI ru=new rankUI();
            dispose();
        }
    }
}
