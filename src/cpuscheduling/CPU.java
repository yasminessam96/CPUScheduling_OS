package cpuscheduling;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Collections;


public class CPU extends javax.swing.JFrame {
   
    private int n;

    private int waitingTime[];
    private int TAT[];
    private int FT[];

    private float avWaitingTime;
    private float avTAT;

    ;
   
  
 private LinkedList<MyRectangle> rectangles = new LinkedList<MyRectangle>();
    private LinkedList<Process> RRList = new LinkedList<Process>();
    private LinkedList<Process> clone = new LinkedList<Process>();
    private LinkedList<Process> SJFList = new LinkedList<Process>();
    private LinkedList<Process> temp = new LinkedList<Process>();
    private LinkedList<Process> PrioNonPreList = new LinkedList<Process>();
    private LinkedList<Process> temp2 = new LinkedList<Process>();
    private LinkedList< Process> SRTFList = new LinkedList<Process>();
    private LinkedList< Process> PrioPremList = new LinkedList<Process>();

    int place = 0;
    int x = 50;
    int y = 500;

    LinkedList<Process> List;

    public CPU() {
        initComponents();
       
        n = 0;

        avWaitingTime = 0;
        avTAT = 0;

        List = new LinkedList<Process>();

    }

    public void calcTAT(LinkedList<Process> l) {
        FT[0] = l.get(0).getAT() + l.get(0).getBT();
        TAT[0] = FT[0] - l.get(0).getAT();
        for (int k = 1; k < n; k++) {
            FT[k] = l.get(k).getBT() + FT[k - 1];
            TAT[k] = FT[k] - l.get(k).getAT();

        }
    }

    public void calcWT(LinkedList<Process> l) {
        for (int j = 0; j < n; j++) {
            waitingTime[j] = TAT[j] - l.get(j).getBT();
        }
    }

    public void paint(Graphics g2) {
        Graphics g3 = getGraphics();
        g3.setColor(Color.black);
        super.paint(g2);
        int x = 0;
        int y = 0;
        int w = 0;
        int l = 0;
        String s = "";
        int xl = 0;
        int yl = 0;
        for (int z = 0; z < rectangles.size(); z++) {
            g2.setColor(rectangles.get(z).getCol());
            x = rectangles.get(z).getX();
            y = rectangles.get(z).getY();
            w = rectangles.get(z).getW();
            l = rectangles.get(z).getL();
            s = rectangles.get(z).getLabel();
            xl = rectangles.get(z).getXLabel();
            yl = rectangles.get(z).getYLabel();

            g2.fillRect(x, y, w, l);
            g3.drawString(s, xl, yl);
        }
    }

    public void draw(LinkedList<Process> l) {

        Graphics g = getGraphics();
        Graphics g1 = getGraphics();
        g1.setColor(Color.black);
        int x = 50;
        int y = 500;
        MyRectangle r;
        for (int m = 0; m < n; m++) {

            if (l.get(m).getName().equalsIgnoreCase("P1") || l.get(m).getName().equalsIgnoreCase("P7")
                    || l.get(m).getName().equalsIgnoreCase("P13") || l.get(m).getName().equalsIgnoreCase("P19")
                    || l.get(m).getName().equalsIgnoreCase("P25")) {
                g.setColor(Color.yellow);
            }
            if (l.get(m).getName().equalsIgnoreCase("P2") || l.get(m).getName().equalsIgnoreCase("P8")
                    || l.get(m).getName().equalsIgnoreCase("P14") || l.get(m).getName().equalsIgnoreCase("P20")
                    || l.get(m).getName().equalsIgnoreCase("P26")) {
                g.setColor(Color.red);
            }
            if (l.get(m).getName().equalsIgnoreCase("P3") || l.get(m).getName().equalsIgnoreCase("P9")
                    || l.get(m).getName().equalsIgnoreCase("P15") || l.get(m).getName().equalsIgnoreCase("P21")
                    || l.get(m).getName().equalsIgnoreCase("P27")) {
                g.setColor(Color.green);
            }
            if (l.get(m).getName().equalsIgnoreCase("P4") || l.get(m).getName().equalsIgnoreCase("P10")
                    || l.get(m).getName().equalsIgnoreCase("P16") || l.get(m).getName().equalsIgnoreCase("P22")
                    || l.get(m).getName().equalsIgnoreCase("P28")) {
                g.setColor(Color.pink);
            }
            if (l.get(m).getName().equalsIgnoreCase("P5") || l.get(m).getName().equalsIgnoreCase("P11")
                    || l.get(m).getName().equalsIgnoreCase("P17") || l.get(m).getName().equalsIgnoreCase("P23")
                    || l.get(m).getName().equalsIgnoreCase("P29")) {
                g.setColor(Color.blue);
            }
            if (l.get(m).getName().equalsIgnoreCase("P6") || l.get(m).getName().equalsIgnoreCase("P12")
                    || l.get(m).getName().equalsIgnoreCase("P18") || l.get(m).getName().equalsIgnoreCase("P24")
                    || l.get(m).getName().equalsIgnoreCase("P30")) {
                g.setColor(Color.orange);
            }
            if (m == 0) {
                g.fillRect(x, y, ((l.get(m).getBT()) * 30), 50);
                g1.drawString(l.get(m).getName(), (x + (l.get(m).getBT()) * 30) / 2, y + 25);
                r = new MyRectangle(x, y, ((l.get(m).getBT()) * 30), 50, g.getColor(), l.get(m).getName(), (x + (l.get(m).getBT()) * 30) / 2, y + 25);
                rectangles.add(r);

            } else {
                x = x + ((l.get(m - 1).getBT() * 30));
                g.fillRect(x, y, ((l.get(m).getBT()) * 30), 50);
                int f = (x + (l.get(m).getBT()) * 30) - l.get(m).getBT() * 30 / 2;
                g1.drawString(l.get(m).getName(), f, y + 25);
                r = new MyRectangle(x, y, ((l.get(m).getBT()) * 30), 50, g.getColor(), l.get(m).getName(), f, y + 25);
                rectangles.add(r);
            }
        }

    }

    public void drawRT(LinkedList<Process> l, int index) {

        Graphics g = getGraphics();
        Graphics g1 = getGraphics();
        g1.setColor(Color.BLACK);
        MyRectangle rec;

        if (l.get(index).getName().equalsIgnoreCase("P1") || l.get(index).getName().equalsIgnoreCase("P7")
                || l.get(index).getName().equalsIgnoreCase("P13")) {
            g.setColor(Color.yellow);
        }
        if (l.get(index).getName().equalsIgnoreCase("P2") || l.get(index).getName().equalsIgnoreCase("P8")
                || l.get(index).getName().equalsIgnoreCase("P14")) {
            g.setColor(Color.red);
        }
        if (l.get(index).getName().equalsIgnoreCase("P3") || l.get(index).getName().equalsIgnoreCase("P9")
                || l.get(index).getName().equalsIgnoreCase("P15")) {
            g.setColor(Color.green);
        }
        if (l.get(index).getName().equalsIgnoreCase("P4") || l.get(index).getName().equalsIgnoreCase("P10")
                || l.get(index).getName().equalsIgnoreCase("P16")) {
            g.setColor(Color.pink);
        }
        if (l.get(index).getName().equalsIgnoreCase("P5") || l.get(index).getName().equalsIgnoreCase("P11")
                || l.get(index).getName().equalsIgnoreCase("P17")) {
            g.setColor(Color.blue);
        }
        if (l.get(index).getName().equalsIgnoreCase("P6") || l.get(index).getName().equalsIgnoreCase("P12")
                || l.get(index).getName().equalsIgnoreCase("P18")) {
            g.setColor(Color.orange);
        }

        if (place == 0) {
            g.fillRect(x, y, ((l.get(index).elapsedTime) * 30), 50);
            g1.drawString(l.get(index).getName(), (x + ((l.get(index).elapsedTime) * 30)) / 2, y + 25);
            place++;
            rec = new MyRectangle(x, y, ((l.get(index).elapsedTime) * 30), 50, g.getColor(), l.get(index).getName(), (x + ((l.get(index).elapsedTime) * 30)) / 2, y + 25);
            rectangles.add(rec);
        } else {
            x = x + rectangles.get(place - 1).getW();
            g.fillRect(x, y, ((l.get(index).elapsedTime) * 30), 50);
            place++;
            int f = (x + (l.get(index).elapsedTime) * 30) - l.get(index).elapsedTime * 30 / 2;
            g1.drawString(l.get(index).getName(), f, y + 25);
            rec = new MyRectangle(x, y, ((l.get(index).elapsedTime) * 30), 50, g.getColor(), l.get(index).getName(), f, y + 25);
            rectangles.add(rec);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Light", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Scheduling Algorithm");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS", "SJF", "SRTF", "Priority-Preemptive", "Priority-Non Preemptive", "RR", " " }));

        jButton1.setText("START");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Average Waiting Time = ");

        jLabel4.setText("Average TAT = ");

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Arrival Time");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Burst Time");

        jLabel8.setText("Priority");

        jButton3.setText("Add Process");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel9.setText("Quantum");

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Desktop\\Others\\ruler.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(579, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(307, 307, 307)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//,(int)jTable1.getValueAt(1, 4)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        waitingTime = new int[n];
        TAT = new int[n];
        FT = new int[n];

        Collections.sort(List, new ArrivalTimeComp());

        if ((String) jComboBox1.getSelectedItem() == "FCFS") {

            calcTAT(List);
            calcWT(List);

            for (int j = 0; j < n; j++) {

                avWaitingTime = avWaitingTime + waitingTime[j];
                avTAT = avTAT + TAT[j];

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

            draw(List);

        }

        if ((String) jComboBox1.getSelectedItem() == "SJF") {
            int time = 0;
            int m = 0;

            while (m < List.size()) {
                for (Process p : List) {
                    if (p.getAT() <= time && List.indexOf(p) >= m) {
                        temp.add(p);
                        m++;
                    }
                }
                
                Collections.sort(temp, new SJFComp());
             //  for (int o = 0; o < temp.size(); o++) {
             //      time += temp.get(o).getBT();
             //  }
               time += temp.get(0).BT;
                SJFList.add(temp.get(0));
                temp.remove(0);

            }
            
           Collections.sort(temp, new SJFComp());
           SJFList.addAll(temp);
           

            calcTAT(SJFList);
            calcWT(SJFList);

            for (int j = 0; j < n; j++) {

                avWaitingTime = avWaitingTime + waitingTime[j];
                avTAT = avTAT + TAT[j];

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

            draw(SJFList);

        }

        if ((String) jComboBox1.getSelectedItem() == "Priority-Non Preemptive") {
            int time = 0;
            int m = 0;

            while (m < List.size()) {
                for (Process p : List) {
                    if (p.getAT() <= time && List.indexOf(p) >= m) {
                        temp2.add(p);
                        m++;
                    }
                }
                for (int o = 0; o < temp2.size(); o++) {
                    time += temp2.get(o).getBT();
                }
                Collections.sort(temp2, new PriorityComp());
                PrioNonPreList.add(temp2.get(0));
                temp2.remove(0);

            }
            Collections.sort(temp2, new PriorityComp());
            PrioNonPreList.addAll(temp2);

            calcTAT(PrioNonPreList);
            calcWT(PrioNonPreList);

            for (int j = 0; j < n; j++) {

                avWaitingTime = avWaitingTime + waitingTime[j];
                avTAT = avTAT + TAT[j];

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

            draw(PrioNonPreList);

        }

        if ((String) jComboBox1.getSelectedItem() == "SRTF") {

            int time = 0;
            int flag = 0;

            boolean remaining = true;

            while (remaining) {
                for (Process p2 : List) {

                    if (p2.getAT() == time && List.indexOf(p2) >= flag) {

                        SRTFList.add(p2);

                    }
                }

                time++;
                Collections.sort(SRTFList, new RemainingTimeComp());
                SRTFList.get(0).RT = SRTFList.get(0).RT - 1;
                SRTFList.get(0).elapsedTime = 1;
                drawRT(SRTFList, 0);
                if (SRTFList.get(0).RT == 0) {
                    Process p = SRTFList.getFirst();
                    p.FT = time;
                    p.setTAT(time - p.getAT());
                    p.setWT(p.getTAT() - p.getBT());
                    SRTFList.remove(0);
                }
                if (SRTFList.isEmpty()) {
                    remaining = false;
                }

            }
            for (int i = 0; i < List.size(); i++) {
                avWaitingTime = avWaitingTime + List.get(i).getWT();
                avTAT = avTAT + List.get(i).getTAT();

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

        }

        if ((String) jComboBox1.getSelectedItem() == "Priority-Preemptive") {

            int time = 0;
            int flag = 0;

            boolean remaining = true;

            while (remaining) {
                for (Process p2 : List) {

                    if (p2.getAT() == time && List.indexOf(p2) >= flag) {

                        PrioPremList.add(p2);

                    }
                }

                time++;
                Collections.sort(PrioPremList, new PriorityComp());
                PrioPremList.get(0).RT = PrioPremList.get(0).RT - 1;
                PrioPremList.get(0).elapsedTime = 1;
                drawRT(PrioPremList, 0);
                if (PrioPremList.get(0).RT == 0) {
                    Process p = PrioPremList.getFirst();
                    p.FT = time;
                    p.setTAT(time - p.getAT());
                    p.setWT(p.getTAT() - p.getBT());
                    PrioPremList.remove(0);
                }
                if (PrioPremList.isEmpty()) {
                    remaining = false;
                }

            }
            for (int i = 0; i < List.size(); i++) {
                avWaitingTime = avWaitingTime + List.get(i).getWT();
                avTAT = avTAT + List.get(i).getTAT();

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

        }

        if ((String) jComboBox1.getSelectedItem() == "RR") {

            clone.addAll(List);

            int q = Integer.parseInt(jTextField4.getText());
            int time = 0;
            boolean remaining = true;

            int c = 0;
            int flag = 0;

            while (remaining) {
                for (Process p2 : clone)
              
               {
                   // if(p2.getAT() > time)

                    if (p2.getAT() <= time && List.indexOf(p2) >= flag) {
                        
                        RRList.add(p2);
                        flag++;

                    }
                }
                 if(RRList.size() > c+1){
                c++;
                }
                else{
                    c=0;
                }

                if (RRList.get(c).RT >= q) {
                    time += q;
                    RRList.get(c).RT = RRList.get(c).RT - q;
                    RRList.get(c).elapsedTime = q;
                    drawRT(RRList, c);
                } else if (RRList.get(c).RT < q) {
                    time += RRList.get(c).RT;
                    RRList.get(c).elapsedTime = RRList.get(c).RT;

                    drawRT(RRList, c);
                    RRList.get(c).RT = 0;

                }

                if (RRList.get(c).RT == 0) {
                    Process pr = RRList.get(c);
                    pr.FT = time;
                    pr.setTAT(time - pr.getAT());
                    pr.setWT(pr.getTAT() - pr.getBT());
                    RRList.remove(c);
                    clone.remove(c);
                    c--;

                }
                if (clone.isEmpty()) {
                    remaining = false;
                }
               
                if (c == clone.size()) {
                    c = 0;
                }

            }
            for (int i = 0; i < List.size(); i++) {
                avWaitingTime = avWaitingTime + List.get(i).getWT();
                avTAT = avTAT + List.get(i).getTAT();

            }

            avWaitingTime = avWaitingTime / n;
            avTAT = avTAT / n;
            jLabel3.setText(avWaitingTime + "");
            jLabel5.setText(avTAT + "");

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        n++;
        Process pro;

        if ((String) jComboBox1.getSelectedItem() == "Priority-Preemptive"
                || (String) jComboBox1.getSelectedItem() == "Priority-Non Preemptive") {
            jTextField3.setEnabled(true);
            pro = new Process("P" + n, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()), Integer.parseInt(jTextField3.getText()));
            List.add(pro);
            jTextArea1.append(pro.getName() + " with AT = " + pro.getAT() + ", BT = " + pro.BT + " and priority = " + pro.getPrio() + " has been added.\n");

        } else {
            pro = new Process("P" + n, Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));
            List.add(pro);
            jTextArea1.append(pro.getName() + " with AT = " + pro.getAT() + " and BT = " + pro.BT + " has been added.\n");

        }
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List.clear();
        rectangles.clear();

        repaint();
        n = 0;
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextArea1.setText("");

        jComboBox1.setSelectedIndex(0);
        if (RRList.isEmpty() == false) {
            RRList.clear();
        }
        if (clone.isEmpty() == false) {
            clone.clear();
        }
        if (SJFList.isEmpty() == false) {
            SJFList.clear();
        }
        if (temp.isEmpty() == false) {
            temp.clear();
        }
        if (PrioNonPreList.isEmpty() == false) {
            PrioNonPreList.clear();
        }
        if (temp2.isEmpty() == false) {
            temp2.clear();
        }
        if (SRTFList.isEmpty() == false) {
            SRTFList.clear();
        }
        if (PrioPremList.isEmpty() == false) {
            PrioPremList.clear();
        }
        avWaitingTime = 0;
        avTAT = 0;
        jLabel3.setText("");
        jLabel5.setText("");
        place = 0;
        x = 50;
        y = 500;


    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CPU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CPU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
