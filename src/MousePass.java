
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class MousePass extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int numQuestions = 20;
    static Random rand = new Random();
    static int numAsk = 5;
    static long time = 0;
    static int count = -1;
    static boolean exit = false;
    static boolean done = true;
    static boolean clicked = false;
    static int[] dummyVal = new int[]{0, 0};
    static ArrayList<int[]> mouseRecord = new ArrayList<int[]>();
    static boolean firstRun = false;
    static boolean buttonClicked = false;
    static boolean missionComplete = false;

    public static void main(String[] args) throws InterruptedException, Exception {
        // Create interface
        JFrame f = new JFrame();//creating instance of JFrame
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add starting text
        JLabel labelDesc = new JLabel("             We at Charles Schwab have implemented some safety measures to help "
                + "protect your account.", JLabel.CENTER);
        labelDesc.setFont(new Font("Arial", Font.PLAIN, 20));
        labelDesc.setSize(1100, 200);
        labelDesc.setLocation(50, 0);
        f.add(labelDesc);

        JLabel labelDesc2 = new JLabel("               Please answer the following short security questions "
                + "to gain access to your account.", JLabel.CENTER);
        labelDesc2.setFont(new Font("Arial", Font.PLAIN, 20));
        labelDesc2.setSize(1000, 200);
        labelDesc2.setLocation(50, 50);
        f.add(labelDesc2);

        JLabel labelDesc3 = new JLabel("Thank you for your patience.", JLabel.CENTER);
        labelDesc3.setFont(new Font("Arial", Font.PLAIN, 20));
        labelDesc3.setSize(700, 200);
        labelDesc3.setLocation(0, 100);
        f.add(labelDesc3);

        JLabel labelBegin = new JLabel("Click the button above to begin this process", JLabel.CENTER);
        labelBegin.setFont(new Font("Arial", Font.ITALIC, 30));
        labelBegin.setSize(1500, 1100);
        labelBegin.setLocation(5, 5);
        f.add(labelBegin);

        f.setSize(1500,750);//width and height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        JButton start = new JButton("Start");
        start.setBounds(650,400,200, 80);//x axis, y axis, width, height
        start.setFont(new Font("Arial", Font.BOLD, 40));
        f.add(start);//adding button in JFrame

        JButton no = new JButton("No");//creating instance of JButton
        no.setBounds(50,50,200, 80);//x axis, y axis, width, height
        no.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton yes = new JButton("Yes");//creating instance of JButton
        yes.setBounds(1225,50,200, 80);//x axis, y axis, width, height
        yes.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton na = new JButton("N/A");//creating instance of JButton
        na.setBounds(650,50,200, 80);//x axis, y axis, width, height
        na.setFont(new Font("Arial", Font.PLAIN, 20));

        f.add(no);//adding button in JFrame
        f.add(yes);//adding button in JFrame
        f.add(na);//adding button in JFrame

        no.setVisible(false);
        yes.setVisible(false);
        na.setVisible(false);

        // Read in questions
        String[] questions = readFile("questions.txt");
        boolean[] asked = new boolean[numQuestions];

        // Add question text
        JLabel labelQuestion = new JLabel("[Question]", JLabel.CENTER);
        labelQuestion.setFont(new Font("Arial", Font.PLAIN, 30));
        labelQuestion.setSize(1500, 900);
        labelQuestion.setLocation(5, 5);
        f.add(labelQuestion);
        labelQuestion.setVisible(false);

        JLabel labelInstr = new JLabel("Answer the following questions to access your account", JLabel.CENTER);
        // Add instruction text
        labelInstr.setFont(new Font("Arial", Font.PLAIN, 20));
        labelInstr.setSize(1500, 1250);
        labelInstr.setLocation(5, 5);
        f.add(labelInstr);
        labelInstr.setVisible(false);

        // Button action listeners
        start.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                    time = System.currentTimeMillis();
                    firstRun = true;
                start.setVisible(false);
                no.setVisible(true);
                yes.setVisible(true);
                na.setVisible(true);
                labelInstr.setVisible(true);

                //Generate question
                int toAsk = rand.nextInt(numQuestions);
                while(asked[toAsk]) toAsk = rand.nextInt(numQuestions);
                asked[toAsk] = true;
                labelQuestion.setText(questions[toAsk]);
                labelQuestion.setVisible(true);

                labelBegin.setText("");
                labelDesc.setText("");
                labelDesc2.setText("");
                labelDesc3.setText("");

                count++;
                }

              }

          );


        no.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                //Reset mouse pos
                try {
                    Robot robot = new Robot();
                    robot.mouseMove(650, 400);
                } catch (AWTException ex) {
                    Logger.getLogger(MousePass.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Generate new question
                int toAsk = rand.nextInt(numQuestions);
                while(asked[toAsk]) toAsk = rand.nextInt(numQuestions);
                asked[toAsk] = true;
                labelQuestion.setText(questions[toAsk]);
                labelQuestion.setVisible(true);

                // Timing stuff
                time = System.currentTimeMillis();
                buttonClicked = true;
                count++;
                missionComplete = false;
              }
            }
          );

        yes.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      //Reset mouse pos
                      try {
                          Robot robot = new Robot();
                          robot.mouseMove(650, 400);
                      } catch (AWTException ex) {
                          Logger.getLogger(MousePass.class.getName()).log(Level.SEVERE, null, ex);
                      }

                      //Generate new question
                      int toAsk = rand.nextInt(numQuestions);
                      while(asked[toAsk]) toAsk = rand.nextInt(numQuestions);
                      asked[toAsk] = true;
                      labelQuestion.setText(questions[toAsk]);
                      labelQuestion.setVisible(true);

                      // Timing stuff
                      time = System.currentTimeMillis();
                      buttonClicked = true;
                      count++;
                      missionComplete = false;
                    }
                  }
          );

        na.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                      //Reset mouse pos
                      try {
                          Robot robot = new Robot();
                          robot.mouseMove(650, 400);
                      } catch (AWTException ex) {
                          Logger.getLogger(MousePass.class.getName()).log(Level.SEVERE, null, ex);
                      }

                      //Generate new question
                      int toAsk = rand.nextInt(numQuestions);
                      while(asked[toAsk]) toAsk = rand.nextInt(numQuestions);
                      asked[toAsk] = true;
                      labelQuestion.setText(questions[toAsk]);
                      labelQuestion.setVisible(true);

                      // Timing stuff
                      time = System.currentTimeMillis();
                      buttonClicked = true;
                      count++;
                      missionComplete = false;
                    }
                  }
          );



        // Ask questions
        ArrayList<int[]> mouseRecordMaster = new ArrayList<int[]>();
        while(!exit) {
             Thread.sleep(1);
             //To change number of trials, modify the "count-1" term to be less
             if(count-1 > numAsk) exit = true;
                //log time
            if(buttonClicked == true && System.currentTimeMillis() - time < 5000) {
                // filter results
                int lenArr = mouseRecord.size();
                System.out.println(lenArr);
                if(lenArr > 400) {
                    // Remove end entries until len == 400
                    while(mouseRecord.size() > 400) mouseRecord.remove(mouseRecord.size()-1);
                } else if (lenArr < 400) {
                    while(mouseRecord.size() < 400) mouseRecord.add(new int[] {0, 0});
                }

                for(int[] elem : mouseRecord) {
                    mouseRecordMaster.add(elem);
                }

                mouseRecord.clear();

                buttonClicked = false;
            }
            if(firstRun == true ) {
                if(System.currentTimeMillis() - time < 5000)  {
                    //System.out.println(System.currentTimeMillis() - time);
                    try {
                        mouseRecord.add(mousePos());
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MousePass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

              if(buttonClicked == true && System.currentTimeMillis() - time > 5000) {
                    if(missionComplete == false) {
                        // filter results
                        int lenArr = mouseRecord.size();
                        System.out.println(lenArr);
                        if(lenArr > 400) {
                            // Remove end entries until len == 400
                            while(mouseRecord.size() > 400) mouseRecord.remove(mouseRecord.size()-1);
                        } else if (lenArr < 400) {
                            while(mouseRecord.size() < 400) mouseRecord.add(new int[] {0, 0});
                        }

                        for(int[] elem : mouseRecord) {
                            mouseRecordMaster.add(elem);
                        }
                        mouseRecord.clear();
                        missionComplete = true;
                    }
                }
            }

            //This keeps the program running

            //System.out.println("Count: " + count);

        }

        String filename = "inputdata.csv";
                        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(filename), "utf-8")))
                        {
                            for (int[] points : mouseRecordMaster)
                            {
                                    //System.out.println(points[0]+ ", " + points[1] + "\n");
                                    writer.write(points[0]+ ", " + points[1] + "\n");
                                }
                            writer.close();
                        } catch (UnsupportedEncodingException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                } catch (FileNotFoundException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

        no.setVisible(false);
        yes.setVisible(false);
        na.setVisible(false);
        File file = new File("printfile.txt");
        if(file.exists()) { //check to see if there is a lingering file
        	file.delete();
        }
        //uses venv version of python - path will change based on user (update this with flexible path)
        Process p = Runtime.getRuntime().exec("venv/bin/python2.7 mouspass_loaded_weights.py"); 
        /*
         * testing stuff: (remove later)
         * BufferedReader br = new BufferedReader(new FileReader("printfile.txt"));
         * Process p = Runtime.getRuntime().exec("python testing.py");
        */
        labelQuestion.setText("Processing...");
        labelInstr.setText("");
        
        while(file.exists() == false) { //check again until found new file
        }
        BufferedReader br = new BufferedReader(new FileReader("printfile.txt"));
        
        boolean read_from_printfile = Boolean.parseBoolean(br.readLine());
        System.out.println("read_from_printfile: " + read_from_printfile);

        JLabel labelAcc = new JLabel("Michael Sprintson's Account", JLabel.CENTER);
        labelAcc.setFont(new Font("Arial", Font.BOLD, 20));
        labelAcc.setSize(300, 200);
        labelAcc.setLocation(55, 0);
        f.add(labelAcc); 

        JLabel labelAcc2 = new JLabel("Routing Number: 031176110", JLabel.CENTER);
        labelAcc2.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc2.setSize(300, 200);
        labelAcc2.setLocation(51, 50);
        f.add(labelAcc2);

        JLabel labelAcc3 = new JLabel("Account Number: 2732522652226868", JLabel.CENTER);
        labelAcc3.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc3.setSize(500, 200);
        labelAcc3.setLocation(-5, 100);
        f.add(labelAcc3);

        JLabel labelAcc4 = new JLabel("Balance: enough to live comfortably for 3-4 days", JLabel.CENTER);
        labelAcc4.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc4.setSize(500, 200);
        labelAcc4.setLocation(40, 150);
        f.add(labelAcc4);

        JLabel labelAcc5 = new JLabel("Recent transactions: TAMUhack Registration: FREE!!!", JLabel.CENTER);
        labelAcc5.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc5.setSize(500, 200);
        labelAcc5.setLocation(65, 200);
        f.add(labelAcc5);

        JLabel labelAcc6 = new JLabel("Instant Ramen 48-pack: $13", JLabel.CENTER);
        labelAcc6.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc6.setSize(300, 200);
        labelAcc6.setLocation(240, 250);
        f.add(labelAcc6);

        JLabel labelAcc7 = new JLabel("Starbucks: $148 for a large coffee", JLabel.CENTER);
        labelAcc7.setFont(new Font("Arial", Font.PLAIN, 20));
        labelAcc7.setSize(500, 200);
        labelAcc7.setLocation(165, 300);
        f.add(labelAcc7);

        JLabel labelSucc = new JLabel("Authentication succeeded...", JLabel.CENTER);
        labelSucc.setFont(new Font("Arial", Font.PLAIN, 20));
        labelSucc.setSize(500, 200);
        labelSucc.setLocation(-50, 400);
        f.add(labelSucc);

        labelAcc.setVisible(true);
        labelAcc2.setVisible(true);
        labelAcc3.setVisible(true);
        labelAcc4.setVisible(true);
        labelAcc5.setVisible(true);
        labelAcc6.setVisible(true);
        labelAcc7.setVisible(true);
        labelSucc.setVisible(true);

        if(!read_from_printfile) {

            JLabel labelFail = new JLabel("Authentication failed...", JLabel.CENTER);
            labelFail.setFont(new Font("Arial", Font.BOLD, 20));
            labelFail.setSize(300, 200);
            labelFail.setLocation(55, 0);
            f.add(labelFail);

            JLabel labelFail2 = new JLabel("Are you sure you are logging into the correct account?", JLabel.CENTER);
            labelFail2.setFont(new Font("Arial", Font.PLAIN, 20));
            labelFail2.setSize(900, 200);
            labelFail2.setLocation(-115, 50);
            f.add(labelFail2);

            labelAcc.setVisible(false);
            labelAcc2.setVisible(false);
            labelAcc3.setVisible(false);
            labelAcc4.setVisible(false);
            labelAcc5.setVisible(false);
            labelAcc6.setVisible(false);
            labelAcc7.setVisible(false);
            labelSucc.setVisible(false);
        }

        labelQuestion.setText("");
        labelInstr.setText("");

    }

    public void actionPerformed(ActionEvent e) {}

    static String[] readFile(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String[] contents = new String[numQuestions];
        for(int i=0; i<numQuestions; i++) contents[i] = br.readLine();
        return contents;
    }

    static int[] mousePos() {
        // Returns array containing mouse position
        Point p = MouseInfo.getPointerInfo().getLocation();
        return new int[]{p.x, p.y};
    }

}
