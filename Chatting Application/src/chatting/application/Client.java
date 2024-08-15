package chatting.application;
import java.awt.Color;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener{
    
    JTextField text;
    static JPanel a1;
    static Box vertical =Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f=new JFrame(); 
    
    Client()
    {   f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(30,30,30));
        p1.setBounds(0 , 0 , 450,70);
        f.add(p1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 =i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        JLabel back =new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
            back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
            }) ;
            
            
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/srk.png"));
        Image i5 =i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 =new ImageIcon(i5);
        JLabel profile =new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
        
        
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/output-onlinepngtools.png"));
        Image i8 =i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 =new ImageIcon(i8);
        JLabel video =new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        p1.add(video);
        
        
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 =i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 =new ImageIcon(i11);
        JLabel phone =new JLabel(i12);
        phone.setBounds(360, 20, 35, 30);
        p1.add(phone);
        
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 =i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 =new ImageIcon(i14);
        JLabel morevert =new JLabel(i15);
        morevert.setBounds(420, 20, 10, 25);
        p1.add(morevert);
        
        
        JLabel name = new JLabel("SRK");
        name.setBounds(110, 15, 100, 18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110, 35, 100, 18);
        status.setForeground(new Color(159,159,159));
        status.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        p1.add(status);
        
        
        a1=new JPanel();
        a1.setBounds(5,75,440,570);
        a1.setBackground(new Color(40,40,41));
        f.add(a1);
        
        text  = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font ("SAN_SERIF",Font.PLAIN,16));
        text.setBackground(new Color(59,59,60));
        text.setForeground(Color.WHITE);
        f.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(320, 655, 123, 40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font ("SAN_SERIF",Font.PLAIN,16));
        send.addActionListener(this);
        send.setOpaque(true);// Ensure the button is opaque
        send.setBorderPainted(false);
        f.add(send);
        
        
        f.setSize(450,700);
        f.setLocation(900,100);
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(40,40,41));
        
        f.setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
        String out = text.getText();
        
        JPanel p2 =  formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2 , BorderLayout.LINE_END);
        right.setBackground(new Color(40, 40, 41));
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical,BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        
        text.setText("");
        
        
        f.repaint();
        f.invalidate();
        f.validate();}
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel =new JPanel();
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));
        panel.setBackground(new Color(40, 40, 41));
        
        
        JLabel output =new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal =Calendar.getInstance();
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm");
        
        JLabel time =new JLabel ();
        time.setText(sdf.format(cal.getTime()));
        time.setForeground(Color.WHITE);
        time.setBackground(new Color(40, 40, 41));
        time.setOpaque(true);
        
        
        panel.add(time);
        
        
        return panel;
    }
    
    public static void main(String[] args)
    {
        new Client();
        
        try {
            Socket s = new Socket ("127.0.0.1",6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout =new DataOutputStream(s.getOutputStream());
            
            while(true)
                {
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel =formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    left.setBackground(new Color(40,40,41));
                    vertical.add(left);
                    
                    
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical,BorderLayout.PAGE_START);
                    
                    
                    f.validate();
                }
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
