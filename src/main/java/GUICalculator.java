import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUICalculator extends JFrame implements ActionListener {
    public static JTextArea tfHistory;
    public static JTextField tfAnswer;
    public static JPanel fOperator;
    public static JPanel panelTop;
    private int flag = 0;
    private JButton[] btn_operand = new JButton[10];
    private JButton clr, back, lbra, rbra, div, mul, sub, add, point, eql;
    class KeyMonitor extends KeyAdapter{
        public void KeyPressed(KeyEvent e){
            int key = e.getKeyChar();
            if(key==KeyEvent.VK_BACK_SPACE)
            {
                tfAnswer.setText("123");
            }
        }
    }
    private void initButton(JButton btn, String title, Color color)       //初始化按钮
    {
        btn = new JButton(title);
        fOperator.add(btn);
        btn.setFont(new Font("", Font.BOLD, 24));
        btn.setForeground(color);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        //btn.addKeyListener(new KeyMonitor());

    }

    private void initOperator()         //运算数与运算符框初始化
    {
        fOperator = new JPanel();
        //fOperator.addKeyListener(new KeyMonitor());
        fOperator.setLayout(new GridLayout(5, 4, 0, 0));

        initButton(back, "<-", new Color(114, 196, 250));
        initButton(lbra, "(", new Color(114, 196, 250));
        initButton(rbra, ")", new Color(114, 196, 250));
        initButton(div, "/", new Color(114, 196, 250));
        initButton(btn_operand[7], "7", new Color(83, 83, 83));
        initButton(btn_operand[8], "8", new Color(83, 83, 83));
        initButton(btn_operand[9], "9", new Color(83, 83, 83));
        initButton(mul, "*", new Color(114, 196, 250));
        initButton(btn_operand[4], "4", new Color(83, 83, 83));
        initButton(btn_operand[5], "5", new Color(83, 83, 83));
        initButton(btn_operand[6], "6", new Color(83, 83, 83));
        initButton(sub, "-", new Color(114, 196, 250));
        initButton(btn_operand[1], "1", new Color(83, 83, 83));
        initButton(btn_operand[2], "2", new Color(83, 83, 83));
        initButton(btn_operand[3], "3", new Color(83, 83, 83));
        initButton(add, "+", new Color(114, 196, 250));
        initButton(clr, "AC", new Color(114, 196, 250));
        initButton(btn_operand[0], "0", new Color(83, 83, 83));
        initButton(point, ".", new Color(83, 83, 83));
        initButton(eql, "=", new Color(114, 196, 250));
        this.add(fOperator);
    }

    private void initMainWindow()           //主窗口初始化
    {
        this.setBackground(Color.lightGray);
        setSize(600, 800);
        setLocation(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        //setLayout(null);
        setTitle("Calculator");
        //addKeyListener(new KeyMonitor());

    }

    private void initPanleTop(){        //编辑框与历史记录框
        panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(2, 1, 0, 0));
        //panelTop.addKeyListener(new KeyMonitor());
        this.add(panelTop);

        //JScrollPane scroll = new JScrollPane(tfHistory);
        tfHistory = new JTextArea();
        tfHistory.setEditable(false);
        tfHistory.setFont(new Font("", Font.PLAIN, 20));
        //tfHistory.addKeyListener(new KeyMonitor());
        JScrollPane scroll = new JScrollPane(tfHistory);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelTop.add(scroll);
        //panelTop.add(tfHistory);

        tfAnswer = new JTextField();
        tfAnswer.setHorizontalAlignment(JTextField.RIGHT);
        tfAnswer.setText("0");
        tfAnswer.setFont(new Font("", Font.PLAIN, 30));
        tfAnswer.setEditable(false);
        //tfAnswer.addKeyListener(new KeyMonitor());
        panelTop.add(tfAnswer);
    }

    GUICalculator() {
        initMainWindow();
        initPanleTop();
        initOperator();
    }


    public void actionPerformed(ActionEvent e)      //按钮事件监听器
    {
        JButton j = (JButton) e.getSource();
        String s = j.getText();

        if (s.equals("AC")) {
            tfAnswer.setText("0");
            tfHistory.setText("");
        }

        if (s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") ||
                s.equals("5") || s.equals("6") || s.equals("7") || s.equals("8") || s.equals("9")) {
            if (flag == 1) {
                tfAnswer.setText(s);
                flag = 0;
            } else {
                if (tfAnswer.getText().equals("0"))
                    tfAnswer.setText(s);
                else {
                    String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
                    if (!last.equals(")"))
                        tfAnswer.setText(tfAnswer.getText() + s);
                }
            }
        }

        if (s.equals("<-"))
            if (tfAnswer.getText().length() == 1)
                tfAnswer.setText("0");
            else
                tfAnswer.setText(tfAnswer.getText().substring(0, tfAnswer.getText().length() - 1));

        if (s.equals("/")) {
            flag = 0;
            if (!tfAnswer.getText().equals("0")) {
                String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
                if (!last.equals("/") && !last.equals("(") && !last.equals(".")) {
                    if (last.equals("-") || last.equals("*") || last.equals("+")) {
                        tfAnswer.setText(tfAnswer.getText().substring(0, tfAnswer.getText().length() - 1));
                        tfAnswer.setText(tfAnswer.getText() + s);
                    } else {
                        tfAnswer.setText(tfAnswer.getText() + s);
                    }
                }
            }
        }

        if (s.equals("+")) {
            flag = 0;
            String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
            if (!last.equals("+") && !last.equals("(") && !last.equals(".")) {
                if (last.equals("-") || last.equals("*") || last.equals("/")) {
                    tfAnswer.setText(tfAnswer.getText().substring(0, tfAnswer.getText().length() - 1));
                    tfAnswer.setText(tfAnswer.getText() + s);
                } else {
                    tfAnswer.setText(tfAnswer.getText() + s);
                }
            }
        }

        if (s.equals("-")) {
            flag = 0;
            String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
            if (!last.equals("-") && !last.equals(".")) {
                if (last.equals("+") || last.equals("*") || last.equals("/")) {
                    tfAnswer.setText(tfAnswer.getText().substring(0, tfAnswer.getText().length() - 1));
                    tfAnswer.setText(tfAnswer.getText() + s);
                } else {
                    tfAnswer.setText(tfAnswer.getText() + s);
                }
            }
        }

        if (s.equals("*")) {
            flag = 0;
            String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
            if (!last.equals("*") && !last.equals(".") && !last.equals("(")) {
                if (last.equals("+") || last.equals("-") || last.equals("/")) {
                    tfAnswer.setText(tfAnswer.getText().substring(0, tfAnswer.getText().length() - 1));
                    tfAnswer.setText(tfAnswer.getText() + s);
                } else {
                    tfAnswer.setText(tfAnswer.getText() + s);
                }
            }
        }

        if (s.equals("(")) {
            String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
            if (last.equals("0") && tfAnswer.getText().length() == 1) {
                tfAnswer.setText("(");
            } else {
                if (last.equals("+") || last.equals("-") || last.equals("/") || last.equals("*") || last.equals("("))
                    tfAnswer.setText(tfAnswer.getText() + s);
            }
        }

        if (s.equals(")")) {
            String temp = tfAnswer.getText();
            int left = temp.length() - temp.replace("(", "").length();
            int right = temp.length() - temp.replace(")", "").length();
            int sum = left - right;  //剩余可添加的右括号数，防止右括号数量大于左括号
            if (sum > 0) {
                String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
                if (!last.equals("(") && !last.equals("+") && !last.equals("-") && !last.equals("*") && !last.equals("/") && !last.equals(".")) {
                    tfAnswer.setText(tfAnswer.getText() + s);
                }
            }
        }

        if (s.equals(".")) {
            if (flag == 1) {
                tfAnswer.setText("0.");
                flag = 0;
            }
            String s1 = tfAnswer.getText();
            int temp[] = new int[7];
            temp[1] = s1.lastIndexOf("+");
            temp[2] = s1.lastIndexOf("-");
            temp[3] = s1.lastIndexOf("*");
            temp[4] = s1.lastIndexOf("/");
            temp[5] = s1.lastIndexOf("(");
            temp[6] = s1.lastIndexOf(")");
            temp[0] = temp[1];
            for (int i = 2; i < temp.length; i++) {    //求最后一个操作符的位置
                if (temp[i] > temp[0])
                    temp[0] = temp[i];
            }

            if (temp[0] == -1) {       //没有操作符的情况，判断串内没有小数点即可直接添加小数点
                if (!s1.contains("."))
                    tfAnswer.setText(tfAnswer.getText() + s);
            } else {
                String sub = s1.substring(temp[0]);     //截取最后一个操作符后的子串
                if (sub.length() == 1) {                   //如果最后一个操作符后没有操作数，且最后一个操作符不是")"，点击“."后添加"0."
                    String last = tfAnswer.getText().substring(tfAnswer.getText().length() - 1);
                    if (!last.equals(")"))
                        tfAnswer.setText(tfAnswer.getText() + "0" + s);
                } else {
                    if (!sub.contains(".")) {            //如果最后一个操作符后有操作数，判断串内没有小数点才可添加"."
                        tfAnswer.setText(tfAnswer.getText() + s);
                    }
                }
            }
        }

        if (s.equals("=")) {
            String temp = tfAnswer.getText();
            int left = temp.length() - temp.replace("(", "").length();
            int right = temp.length() - temp.replace(")", "").length();
            if (left != right) {
                tfHistory.setText(tfHistory.getText() + "中缀表达式不正确，需匹配完全左右括号\n");
            } else {
                flag = 1;
                Expression ex = new Expression((tfAnswer.getText() + "="));
                double answer = ex.EvaluateExpression();
                temp = tfAnswer.getText() + "=";
                tfAnswer.setText(Double.toString(answer));
                tfHistory.setText(tfHistory.getText() + temp + Double.toString(answer) + "\n");
            }
        }

    }

    public static void prt(String s) {
        tfHistory.setText(tfHistory.getText() + "\n" + s);
    }

//    class KeyMonitor extends KeyAdapter{
//        public void KeyPressed(KeyEvent e){
//            int key = e.getKeyChar();
//            if(key==KeyEvent.VK_BACK_SPACE)
//            {
//                tfAnswer.setText("123");
//            }
//        }
//    }

    public static void main(String[] args) {
        GUICalculator frame = new GUICalculator();
        frame.setVisible(true);
    }


}
