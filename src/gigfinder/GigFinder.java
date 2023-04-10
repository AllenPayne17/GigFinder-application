package gigfinder;

import com.google.gson.Gson;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GigFinder extends javax.swing.JFrame {
    
    private JPanel[] job;
    private JLabel[] listing;
    
    public GigFinder() {
        
        try {
            URL url = new URL("https://gigfinder.onrender.com/api/job-posts");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer data = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                data.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            
            Job[] job = gson.fromJson(data.toString(), Job[].class);
            
            int[] date_index = new int[job.length];
            Date[] date_created = new Date[job.length]; 
            ArrayList<Integer> on_campus_index = new ArrayList<>();
            ArrayList<Integer> food_service_index = new ArrayList<>();
            ArrayList<Integer> tutor_index = new ArrayList<>();
           
            for(int i = 0; i < job.length ; i++){
               date_index[i] = i;
               date_created[i] =  job[i].get_createdAt();
               switch (job[i].get_work_category()[0]) {
                    case "on-campus part-time job":
                        on_campus_index.add(i);
                        break;
                    case "food-service part-time job":
                        food_service_index.add(i);
                        break;
                    case "one-on-one tutor part-time job":
                        tutor_index.add(i);
                        break;
                    default:
                        break;
                }
            }

            quickSort(date_created, 0, job.length -1, date_index);

            
            initComponents();
            JPanel[] jobPanel = new JPanel[job.length];
            JLabel[] title = new JLabel[job.length];
            //JTextArea[] desc = new JTextArea[job.length];
            JLabel[] desc = new JLabel[job.length];
            JLabel[] hour_rate = new JLabel[job.length];
            JLabel[] workHour = new JLabel[job.length];
            JLabel[] pref_skill = new JLabel[job.length];
            JLabel[] work_categ = new JLabel[job.length];
            JLabel[] id = new JLabel[job.length];
            JLabel[] createdDate = new JLabel[job.length];
            
            allJobs.setLayout(new BoxLayout(allJobs, BoxLayout.Y_AXIS));
            getContentPane().add(allJobs, BorderLayout.CENTER);

            for(int i = 0; i < job.length; i++){
                jobPanel[i] = new JPanel();
                title[i] = new JLabel(job[date_index[i]].get_job_title());
                desc[i] = new JLabel(job[date_index[i]].get_job_description());
                hour_rate[i] = new JLabel(String.valueOf(job[date_index[i]].get_hourly_rate()));
                workHour[i] = new JLabel(String.valueOf(job[date_index[i]].get_workHours()));
                pref_skill[i] = new JLabel();
                
                String skills = "";
                    for(String skill : job[date_index[i]].get_preferred_skills()){
                         skills += skill + " "; // MAGDAGDAG NG , somwhere
                    }
                
                pref_skill[i].setText(skills);
                work_categ[i] = new JLabel(job[date_index[i]].get_work_category()[0]);
                id[i] = new JLabel(job[date_index[i]].get_id());
                createdDate[i] = new JLabel(String.valueOf(job[date_index[i]].get_createdAt()));
                

                jobPanel[i].setBackground(Color.LIGHT_GRAY);
                jobPanel[i].add(createdDate[i]);
                jobPanel[i].add(title[i]);
                jobPanel[i].add(desc[i]);
                jobPanel[i].add(hour_rate[i]);
                jobPanel[i].add(workHour[i]);
                jobPanel[i].add(pref_skill[i]);
                jobPanel[i].add(work_categ[i]);
                jobPanel[i].add(id[i]);
                
                jobPanel[i].setLayout(new BoxLayout(jobPanel[i], BoxLayout.Y_AXIS));
                allJobs.add(jobPanel[i]);
                allJobs.add(Box.createRigidArea(new Dimension(0,5)));
            }
           
        } catch (Exception e){ 
            e.printStackTrace();
        }
  
    }
    
    static int partition(Date[] date_created, int low, int high, int[] date_index) {
    Date pivot = date_created[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
        if(date_created[j].compareTo(pivot) > 0){
        i++;

        Date temp = date_created[i];
        date_created[i] = date_created[j];
        date_created[j] = temp;
        
        int temp_index = date_index[i];
        date_index[i] = date_index[j];
        date_index[j] = temp_index;
        }
       }
    Date temp = date_created[i + 1];
    date_created[i + 1] = date_created[high];
    date_created[high] = temp;
    
    int temp_index = date_index[i + 1];
    date_index[i+1] = date_index[high];
    date_index[high] = temp_index;
    return (i + 1);
    }
    
    private static void quickSort(Date[] date_created, int low, int high, int[] date_index){
        if(low < high){
        int pi = partition(date_created, low, high, date_index);
         quickSort(date_created, low, pi-1, date_index); 
         quickSort(date_created, pi + 1, high, date_index);
        }
    }  
    
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bhomepage = new javax.swing.JPanel();
        Bsidebar = new javax.swing.JPanel();
        Blogo = new javax.swing.JLabel();
        BbrandName = new javax.swing.JLabel();
        BallPost = new javax.swing.JButton();
        Bpost = new javax.swing.JButton();
        Bprofile = new javax.swing.JButton();
        BaboutUs = new javax.swing.JButton();
        Blogout = new javax.swing.JButton();
        Shomepage = new javax.swing.JPanel();
        Ssidebar = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        brandName = new javax.swing.JLabel();
        profile = new javax.swing.JButton();
        findWork = new javax.swing.JButton();
        aboutUs = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        aboutPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        profilePanel = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        address = new javax.swing.JLabel();
        bio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        skills = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        position = new javax.swing.JLabel();
        company = new javax.swing.JLabel();
        Responsibilities = new javax.swing.JLabel();
        profilePic = new javax.swing.JLabel();
        findworkPanel = new javax.swing.JPanel();
        categoriesLabel = new javax.swing.JLabel();
        allBtn = new javax.swing.JButton();
        foodserviceBtn = new javax.swing.JButton();
        tutorBtn = new javax.swing.JButton();
        campusBtn = new javax.swing.JButton();
        labelJoblist = new javax.swing.JLabel();
        JobsPanel = new javax.swing.JPanel();
        campusJob = new javax.swing.JPanel();
        tutorJob = new javax.swing.JPanel();
        foodServiceJob = new javax.swing.JPanel();
        allJobs = new javax.swing.JPanel();
        LoginPage = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        login = new javax.swing.JButton();

        Bhomepage.setBackground(new java.awt.Color(0, 94, 129));
        Bhomepage.setVisible(false);

        Bsidebar.setBackground(new java.awt.Color(255, 255, 255));
        Bsidebar.setPreferredSize(new java.awt.Dimension(168, 424));

        Blogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gigfinder 100px.png"))); // NOI18N

        BbrandName.setFont(new java.awt.Font("Georgia", 0, 20)); // NOI18N
        BbrandName.setForeground(new java.awt.Color(0, 94, 129));
        BbrandName.setText("GigFinder");

        BallPost.setBackground(new java.awt.Color(255, 255, 255));
        BallPost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BallPost.setForeground(new java.awt.Color(0, 94, 129));
        BallPost.setText("All post");
        BallPost.setFocusPainted(false);
        BallPost.setBorder(null);

        Bpost.setBackground(new java.awt.Color(255, 255, 255));
        Bpost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Bpost.setForeground(new java.awt.Color(0, 94, 129));
        Bpost.setText("Post");
        Bpost.setFocusPainted(false);
        Bpost.setBorder(null);

        Bprofile.setBackground(new java.awt.Color(255, 255, 255));
        Bprofile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Bprofile.setForeground(new java.awt.Color(0, 94, 129));
        Bprofile.setText("Profile");
        Bprofile.setBorder(null);
        Bprofile.setFocusPainted(false);

        BaboutUs.setBackground(new java.awt.Color(255, 255, 255));
        BaboutUs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BaboutUs.setForeground(new java.awt.Color(0, 94, 129));
        BaboutUs.setText("About Us");
        BaboutUs.setBorder(null);
        BaboutUs.setFocusPainted(false);

        Blogout.setBackground(new java.awt.Color(255, 255, 255));
        Blogout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Blogout.setForeground(new java.awt.Color(0, 94, 129));
        Blogout.setText("Logout");
        Blogout.setBorder(null);
        Blogout.setFocusPainted(false);

        javax.swing.GroupLayout BsidebarLayout = new javax.swing.GroupLayout(Bsidebar);
        Bsidebar.setLayout(BsidebarLayout);
        BsidebarLayout.setHorizontalGroup(
            BsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bpost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BsidebarLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(Blogo)
                .addGap(34, 34, 34))
            .addGroup(BsidebarLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(BbrandName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(BaboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BallPost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Blogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Bprofile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BsidebarLayout.setVerticalGroup(
            BsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BsidebarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Blogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BbrandName)
                .addGap(109, 109, 109)
                .addComponent(BallPost, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bpost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(Bprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(BaboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(Blogout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout BhomepageLayout = new javax.swing.GroupLayout(Bhomepage);
        Bhomepage.setLayout(BhomepageLayout);
        BhomepageLayout.setHorizontalGroup(
            BhomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BhomepageLayout.createSequentialGroup()
                .addComponent(Bsidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(808, Short.MAX_VALUE))
        );
        BhomepageLayout.setVerticalGroup(
            BhomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bsidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GigFinder");
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1000, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        Shomepage.setBackground(new java.awt.Color(0, 94, 129));
        Shomepage.setVisible(false);

        Ssidebar.setBackground(new java.awt.Color(255, 255, 255));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gigfinder 100px.png"))); // NOI18N

        brandName.setFont(new java.awt.Font("Georgia", 0, 20)); // NOI18N
        brandName.setForeground(new java.awt.Color(0, 94, 129));
        brandName.setText("GigFinder");

        profile.setBackground(new java.awt.Color(255, 255, 255));
        profile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        profile.setForeground(new java.awt.Color(0, 94, 129));
        profile.setText("Profile");
        profile.setFocusPainted(false);
        profile.setBorder(null);
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });

        findWork.setBackground(new java.awt.Color(255, 255, 255));
        findWork.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        findWork.setForeground(new java.awt.Color(0, 94, 129));
        findWork.setText("Find Work");
        findWork.setFocusPainted(false);
        findWork.setBorder(null);
        findWork.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findWorkActionPerformed(evt);
            }
        });

        aboutUs.setBackground(new java.awt.Color(255, 255, 255));
        aboutUs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        aboutUs.setForeground(new java.awt.Color(0, 94, 129));
        aboutUs.setText("About Us");
        aboutUs.setFocusPainted(false);
        aboutUs.setBorder(null);
        aboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutUsActionPerformed(evt);
            }
        });

        logout.setBackground(new java.awt.Color(255, 255, 255));
        logout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        logout.setForeground(new java.awt.Color(0, 94, 129));
        logout.setText("Logout");
        logout.setFocusPainted(false);
        logout.setBorder(null);

        javax.swing.GroupLayout SsidebarLayout = new javax.swing.GroupLayout(Ssidebar);
        Ssidebar.setLayout(SsidebarLayout);
        SsidebarLayout.setHorizontalGroup(
            SsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SsidebarLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(logo)
                .addGap(34, 34, 34))
            .addGroup(SsidebarLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(brandName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(aboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(findWork, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SsidebarLayout.setVerticalGroup(
            SsidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SsidebarLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandName)
                .addGap(109, 109, 109)
                .addComponent(findWork, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutUs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        aboutPanel.setBackground(new java.awt.Color(0, 94, 129));
        aboutPanel.setForeground(new java.awt.Color(51, 0, 51));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>GigFinder is a desktop application aimed at providing college students or young adults with a <br> side hustle while studying and providing small businesses with dedicated part-timers to reduce their expenses. <br> The application provides an interface for small business owners to post part-time jobs they<br> need help with and college students or young adults to find and apply to these jobs. <br> The application makes it easy for businesses to find reliable and affordable part-time workers, <br>and for students to earn extra income while studying.</html>");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ABOUT");

        javax.swing.GroupLayout aboutPanelLayout = new javax.swing.GroupLayout(aboutPanel);
        aboutPanel.setLayout(aboutPanelLayout);
        aboutPanelLayout.setHorizontalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel4)
                .addGap(79, 79, 79)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );

        profilePanel.setBackground(new java.awt.Color(0, 94, 129));

        name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setText("Lynwood Magnatta");
        name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        address.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        address.setForeground(new java.awt.Color(255, 255, 255));
        address.setText("1234 Taft Avenue Ermita, Manila 1000");

        bio.setForeground(new java.awt.Color(255, 255, 255));
        bio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bio.setText("<html>As a student with a passion for helping others, I am excited to explore opportunities in tutoring and mentoring. <br> I have experience working with children of all ages and can adapt to different learning styles. <br> In my free time, I enjoy painting and playing music.</html>");
        bio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Skills");

        skills.setForeground(new java.awt.Color(255, 255, 255));
        skills.setText("Communication Teamwork");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Work Experience");

        position.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        position.setForeground(new java.awt.Color(255, 255, 255));
        position.setText("Library Associate");

        company.setForeground(new java.awt.Color(255, 255, 255));
        company.setText("ABC Library");

        Responsibilities.setForeground(new java.awt.Color(255, 255, 255));
        Responsibilities.setText("<html>Assisted library patrons with finding books and materials, shelved and organized books, provided customer <br> service, and performed other duties as assigned.</html>");

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bio)
                    .addComponent(skills, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(profilePanelLayout.createSequentialGroup()
                        .addComponent(position)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(company))
                    .addComponent(Responsibilities))
                .addGap(0, 127, Short.MAX_VALUE))
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(profilePic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name)
                .addGap(31, 31, 31)
                .addComponent(address)
                .addGap(29, 29, 29)
                .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skills)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(position)
                    .addComponent(company))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Responsibilities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        findworkPanel.setBackground(new java.awt.Color(0, 94, 129));

        categoriesLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        categoriesLabel.setForeground(new java.awt.Color(255, 255, 255));
        categoriesLabel.setText("Categories");

        allBtn.setBackground(new java.awt.Color(61, 214, 196));
        allBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        allBtn.setText("All");
        allBtn.setFocusPainted(false);
        allBtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        allBtn.setBorderPainted(false);
        allBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBtnActionPerformed(evt);
            }
        });

        foodserviceBtn.setBackground(new java.awt.Color(61, 214, 196));
        foodserviceBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        foodserviceBtn.setText("Food-service");
        foodserviceBtn.setFocusPainted(false);
        foodserviceBtn.setBorder(null);
        foodserviceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodserviceBtnActionPerformed(evt);
            }
        });

        tutorBtn.setBackground(new java.awt.Color(61, 214, 196));
        tutorBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tutorBtn.setText("One-on-one tutor");
        tutorBtn.setFocusPainted(false);
        tutorBtn.setBorder(null);
        tutorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorBtnActionPerformed(evt);
            }
        });

        campusBtn.setBackground(new java.awt.Color(61, 214, 196));
        campusBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campusBtn.setText("On-campus");
        campusBtn.setBorder(null);
        campusBtn.setFocusPainted(false);
        campusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campusBtnActionPerformed(evt);
            }
        });

        labelJoblist.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        labelJoblist.setForeground(new java.awt.Color(255, 255, 255));
        labelJoblist.setText("Available Job for you!");

        JobsPanel.setBackground(new java.awt.Color(0, 94, 129));
        JobsPanel.setPreferredSize(new java.awt.Dimension(800, 485));

        campusJob.setBackground(new java.awt.Color(255, 204, 204));
        campusJob.setVisible(false);

        javax.swing.GroupLayout campusJobLayout = new javax.swing.GroupLayout(campusJob);
        campusJob.setLayout(campusJobLayout);
        campusJobLayout.setHorizontalGroup(
            campusJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        campusJobLayout.setVerticalGroup(
            campusJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        tutorJob.setBackground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout tutorJobLayout = new javax.swing.GroupLayout(tutorJob);
        tutorJob.setLayout(tutorJobLayout);
        tutorJobLayout.setHorizontalGroup(
            tutorJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        tutorJobLayout.setVerticalGroup(
            tutorJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        foodServiceJob.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout foodServiceJobLayout = new javax.swing.GroupLayout(foodServiceJob);
        foodServiceJob.setLayout(foodServiceJobLayout);
        foodServiceJobLayout.setHorizontalGroup(
            foodServiceJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        foodServiceJobLayout.setVerticalGroup(
            foodServiceJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        allJobs.setBackground(new java.awt.Color(0, 94, 129));
        allJobs.setPreferredSize(new java.awt.Dimension(800, 485));

        javax.swing.GroupLayout allJobsLayout = new javax.swing.GroupLayout(allJobs);
        allJobs.setLayout(allJobsLayout);
        allJobsLayout.setHorizontalGroup(
            allJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        allJobsLayout.setVerticalGroup(
            allJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JobsPanelLayout = new javax.swing.GroupLayout(JobsPanel);
        JobsPanel.setLayout(JobsPanelLayout);
        JobsPanelLayout.setHorizontalGroup(
            JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JobsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(allJobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(foodServiceJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tutorJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(campusJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JobsPanelLayout.setVerticalGroup(
            JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(allJobs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(foodServiceJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tutorJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(campusJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tutorJob.setVisible(false);
        foodServiceJob.setVisible(false);

        javax.swing.GroupLayout findworkPanelLayout = new javax.swing.GroupLayout(findworkPanel);
        findworkPanel.setLayout(findworkPanelLayout);
        findworkPanelLayout.setHorizontalGroup(
            findworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findworkPanelLayout.createSequentialGroup()
                .addGroup(findworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(findworkPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(categoriesLabel))
                    .addGroup(findworkPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(foodserviceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(tutorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(campusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(findworkPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelJoblist))
                    .addGroup(findworkPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JobsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        findworkPanelLayout.setVerticalGroup(
            findworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findworkPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(categoriesLabel)
                .addGap(16, 16, 16)
                .addGroup(findworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(foodserviceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tutorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campusBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(labelJoblist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JobsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(findworkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(profilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(aboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(findworkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(profilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(aboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ShomepageLayout = new javax.swing.GroupLayout(Shomepage);
        Shomepage.setLayout(ShomepageLayout);
        ShomepageLayout.setHorizontalGroup(
            ShomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShomepageLayout.createSequentialGroup()
                .addComponent(Ssidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ShomepageLayout.setVerticalGroup(
            ShomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Ssidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LoginPage.setBackground(new java.awt.Color(0, 94, 129));

        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setText(" Enter Email");
        email.setBorder(null);
        email.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });

        password.setForeground(new java.awt.Color(153, 153, 153));
        password.setText(" Enter Password");
        password.setBorder(null);
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });

        login.setText("login");
        login.setBorder(null);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LoginPageLayout = new javax.swing.GroupLayout(LoginPage);
        LoginPage.setLayout(LoginPageLayout);
        LoginPageLayout.setHorizontalGroup(
            LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPageLayout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(363, Short.MAX_VALUE))
        );
        LoginPageLayout.setVerticalGroup(
            LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPageLayout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1004, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Shomepage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LoginPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Shomepage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LoginPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1014, 657));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
       
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        ApiClient apiCLient = new ApiClient();
        
        var em = email.getText();
        var pass = password.getText();
        try {
            //user
            String response = apiCLient.loginUser("johndoe@gmail.com", "mypassword");
            System.out.println(response);
            
   
            Gson gson = new Gson();
            
            UserType.UserProfile user = gson.fromJson(response,UserType.UserProfile.class);
            
            System.out.println(user.getAccountType());
            if("Student".equals(user.getAccountType())) {
                StudentProfile.UserProfile student = gson.fromJson(response, StudentProfile.UserProfile.class);
                
                LoginPage.setVisible(false);
                Shomepage.setVisible(true);
                
                try {
                    URL url = new URL(student.getProfile().getProfile_pic());
                    BufferedImage image = ImageIO.read(url);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(profilePic.getWidth(), profilePic.getHeight(), Image.SCALE_SMOOTH));
                    profilePic.setIcon(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                name.setText(student.getProfile().getName());
                address.setText(student.getProfile().getAddress());
                bio.setText(student.getProfile().getBio());
                
                String stringSkills = "";
                for (String str : student.getProfile().getSkills()) {
                    stringSkills = stringSkills.concat(" " + str);
                }
                skills.setText(stringSkills);
                
                
                List<StudentProfile.WorkExperience> workExperienceList = student.getProfile().getWork_experience();
                for(StudentProfile.WorkExperience workExperience : workExperienceList) {
                    
                    position.setText(workExperience.getPosition());
                    company.setText(workExperience.getCompanyName());
                    Responsibilities.setText(workExperience.getResponsibilities());
                    System.out.println("Position: " + workExperience.getPosition());
                    System.out.println("Company Name: " + workExperience.getCompanyName());
                    System.out.println("Responsibilities: " + workExperience.getResponsibilities());
                }

            } else if ("BusinessOwner".equals(user.getAccountType())) {
                BusinessOwnerProfile.UserProfile Owner = gson.fromJson(response, BusinessOwnerProfile.UserProfile.class);
                // Process businessOwnerProfile data here
                LoginPage.setVisible(false);
                Bhomepage.setVisible(true);
                
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "incorrect email or password", "wrong input", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        // TODO add your handling code here:
        if(email.getText().equals(" Enter Email")){
            email.setText("");
            email.setForeground(new Color(153,153,153));
        }else{
            email.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        // TODO add your handling code here:
        if(email.getText().equals("")){
            email.setText(" Enter Email");
            email.setForeground(new Color(153,153,153));
        }else{
            email.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_emailFocusLost

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
        if(password.getText().equals(" Enter Password")){
            password.setText("");
            password.setForeground(new Color(153,153,153));
        }else{
            password.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_passwordFocusGained

    private void passwordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusLost
        // TODO add your handling code here:
        if(password.getText().equals("")){
            password.setText(" Enter Password");
            password.setForeground(new Color(153,153,153));
        }else{
            password.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_passwordFocusLost

    private void findWorkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findWorkActionPerformed
        // TODO add your handling code here:
        findworkPanel.setVisible(true);
        profilePanel.setVisible(false);
        aboutPanel.setVisible(false);
    }//GEN-LAST:event_findWorkActionPerformed

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileActionPerformed
        // TODO add your handling code here:
        findworkPanel.setVisible(false);
        profilePanel.setVisible(true);
        aboutPanel.setVisible(false);
        
        
        
    }//GEN-LAST:event_profileActionPerformed

    private void aboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutUsActionPerformed
        // TODO add your handling code here:
        findworkPanel.setVisible(false);
        profilePanel.setVisible(false);
        aboutPanel.setVisible(true);
    }//GEN-LAST:event_aboutUsActionPerformed

    private void allBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBtnActionPerformed
        // TODO add your handling code here:
        allJobs.setVisible(true);
        tutorJob.setVisible(false);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(false);
    }//GEN-LAST:event_allBtnActionPerformed

    private void foodserviceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodserviceBtnActionPerformed
        // TODO add your handling code here:
        allJobs.setVisible(false);
        tutorJob.setVisible(false);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(true);
    }//GEN-LAST:event_foodserviceBtnActionPerformed

    private void tutorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorBtnActionPerformed
        // TODO add your handling code here:
        allJobs.setVisible(false);
        tutorJob.setVisible(true);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(false);
    }//GEN-LAST:event_tutorBtnActionPerformed

    private void campusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusBtnActionPerformed
        // TODO add your handling code here:
        allJobs.setVisible(false);
        tutorJob.setVisible(false);
        campusJob.setVisible(true);
        foodServiceJob.setVisible(false);
    }//GEN-LAST:event_campusBtnActionPerformed
 
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GigFinder().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BaboutUs;
    private javax.swing.JButton BallPost;
    private javax.swing.JLabel BbrandName;
    private javax.swing.JPanel Bhomepage;
    private javax.swing.JLabel Blogo;
    private javax.swing.JButton Blogout;
    private javax.swing.JButton Bpost;
    private javax.swing.JButton Bprofile;
    private javax.swing.JPanel Bsidebar;
    private javax.swing.JPanel JobsPanel;
    private javax.swing.JPanel LoginPage;
    private javax.swing.JLabel Responsibilities;
    private javax.swing.JPanel Shomepage;
    private javax.swing.JPanel Ssidebar;
    private javax.swing.JPanel aboutPanel;
    private javax.swing.JButton aboutUs;
    private javax.swing.JLabel address;
    private javax.swing.JButton allBtn;
    private javax.swing.JPanel allJobs;
    private javax.swing.JLabel bio;
    private javax.swing.JLabel brandName;
    private javax.swing.JButton campusBtn;
    private javax.swing.JPanel campusJob;
    private javax.swing.JLabel categoriesLabel;
    private javax.swing.JLabel company;
    private javax.swing.JTextField email;
    private javax.swing.JButton findWork;
    private javax.swing.JPanel findworkPanel;
    private javax.swing.JPanel foodServiceJob;
    private javax.swing.JButton foodserviceBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel labelJoblist;
    private javax.swing.JButton login;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logout;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField password;
    private javax.swing.JLabel position;
    private javax.swing.JButton profile;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePic;
    private javax.swing.JLabel skills;
    private javax.swing.JButton tutorBtn;
    private javax.swing.JPanel tutorJob;
    // End of variables declaration//GEN-END:variables
}
