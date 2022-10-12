import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class server extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;

    static ServerSocket skt;
    static Socket s;

    static DataInputStream din;
    static DataOutputStream dout;

    server() {

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 136, 204));
        p1.setBounds(0, 0, 400, 70);
        add(p1);

        // for back button
        ImageIcon i1 = new ImageIcon("3.png");
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);

        // mouse action for back button
        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        // for gaitonde DP
        ImageIcon i4 = new ImageIcon("1.png");
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 6, 60, 60);
        p1.add(l2);

        // call icon
        ImageIcon i7 = new ImageIcon("phone.png");
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(328, 20, 30, 30);
        p1.add(l5);

        // video call icon
        ImageIcon i11 = new ImageIcon("video.png");
        Image i12 = i11.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(285, 20, 30, 30);
        p1.add(l6);

        // 3 dots
        ImageIcon i14 = new ImageIcon("3icon.png");
        Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(365, 20, 15, 30);
        p1.add(l7);

        // Gaitonde name
        JLabel l3 = new JLabel("Gaitonde");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 20);
        p1.add(l3);

        // active status
        JLabel l4 = new JLabel("Active Now");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        l4.setForeground(Color.WHITE);
        l4.setBounds(115, 38, 100, 20);
        p1.add(l4);

        // messages to be recorded on chat
        a1 = new JTextArea();
        a1.setBounds(5, 75, 390, 540);
        // a1.setBackground(Color.GRAY);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        // adding typing field
        t1 = new JTextField();
        t1.setBounds(3, 620, 310, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        // adding send button
        b1 = new JButton("Send");
        b1.setBounds(320, 620, 78, 28);
        b1.setBackground(new Color(0, 136, 204));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(b1);
        b1.addActionListener(this);

        // getContentPane().setBackground(Color.YELLOW);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 650);
        setLocation(175, 30);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String out = t1.getText();
            a1.setText(a1.getText() + "\n\t\t\t\t" + out);
            t1.setText("");
            dout.writeUTF(out);
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new server().setVisible(true);

        // here comes the socket programming
        String msgInput = "";
        try {
            skt = new ServerSocket(6001);
            s = skt.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            msgInput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msgInput);

            // closing the connections we opened
            skt.close();
            s.close();

        } catch (Exception e) {
        }
    }
}