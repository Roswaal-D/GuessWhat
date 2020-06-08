package server;

import java.io.*;

public class ranking implements rankport{

    private String id[]=new String[5];
    private int score[]=new int[5];
    private File file;
    public ranking(int k){
        if(k==0){
            file=new File("d:/TEM","rankA.txt");
        }else {
            file = new File("d:/TEM", "rankB.txt");
        }
    }
    public int getint(String aim){
        int num=0;
        int length=aim.length();
        for (int i=0;i<length;i++){
            num=num*10+(aim.charAt(i)-'0');
        }
        return num;
    }

    @Override
    public void readTxt() {
        BufferedReader reader=null;
        try {
            reader=new BufferedReader(new FileReader(file));
            String str=null,sc=null;

            for(int i=0;i<5;i++){
                str=reader.readLine();
                id[i]=str;
                sc=reader.readLine();
                score[i]=getint(sc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i=0;i<5;i++){
            System.out.println(id[i]+" "+score[i]);
        }
    }

    @Override
    public String getrank() {
        this.readTxt();


        return null;
    }

    @Override
    public String getWinnerid() {
        this.readTxt();
        return id[0];
    }

    @Override
    public int getWinnersc() {
        this.readTxt();
        return score[0];
    }

    @Override
    public boolean insertIt(String str, int sc) {
        this.readTxt();
        int k=-1;
        for(int i=0;i<5;i++){
            if(score[i]<sc||id[i].equals("无")){
                k=i;
                break;
            }
        }
        if(k==-1){
            return false;
        }
        for(int i=4;i>k;i--){
            id[i]=id[i-1];
            score[i]=score[i-1];
        }
        id[k]=str;
        score[k]=sc;
        String fin="";
        for(int i=0;i<5;i++){
            fin+=(id[i]+"\n");
            fin+=(""+score[i]+"\n");
        }
        try {
            FileWriter fw=new FileWriter(file);
            fw.write(fin);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
