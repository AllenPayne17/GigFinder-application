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

            
            
            JPanel[] jobPanel = new JPanel[job.length];
            JPanel[] campusjobPanel = new JPanel[on_campus_index.size()];
            JPanel[] tutorjobPanel = new JPanel[tutor_index.size()];
            JPanel[] foodjobPanel = new JPanel[food_service_index.size()];
            
            
            JLabel[] title = new JLabel[job.length];
            JLabel[] desc = new JLabel[job.length];
            JLabel[] hour_rate = new JLabel[job.length];
            JLabel[] workHour = new JLabel[job.length];
            JLabel[] pref_skill = new JLabel[job.length];
            JLabel[] work_categ = new JLabel[job.length];
            JLabel[] id = new JLabel[job.length];
            JLabel[] createdDate = new JLabel[job.length];
            
            JLabel[] campustitle = new JLabel[on_campus_index.size()];
            JLabel[] campusdesc = new JLabel[on_campus_index.size()];
            JLabel[] campushour_rate = new JLabel[on_campus_index.size()];
            JLabel[] campusworkHour = new JLabel[on_campus_index.size()];
            JLabel[] campuspref_skill = new JLabel[on_campus_index.size()];
            JLabel[] campuswork_categ = new JLabel[on_campus_index.size()];
            JLabel[] campusid = new JLabel[on_campus_index.size()];
            JLabel[] campuscreatedDate = new JLabel[on_campus_index.size()];
            
            JLabel[] foodtitle = new JLabel[food_service_index.size()];
            JLabel[] fooddesc = new JLabel[food_service_index.size()];
            JLabel[] foodhour_rate = new JLabel[food_service_index.size()];
            JLabel[] foodworkHour = new JLabel[food_service_index.size()];
            JLabel[] foodpref_skill = new JLabel[food_service_index.size()];
            JLabel[] foodwork_categ = new JLabel[food_service_index.size()];
            JLabel[] foodid = new JLabel[food_service_index.size()];
            JLabel[] foodcreatedDate = new JLabel[food_service_index.size()];
            
            JLabel[] tutortitle = new JLabel[tutor_index.size()];
            JLabel[] tutordesc = new JLabel[tutor_index.size()];
            JLabel[] tutorhour_rate = new JLabel[tutor_index.size()];
            JLabel[] tutorworkHour = new JLabel[tutor_index.size()];
            JLabel[] tutorpref_skill = new JLabel[tutor_index.size()];
            JLabel[] tutorwork_categ = new JLabel[tutor_index.size()];
            JLabel[] tutorid = new JLabel[tutor_index.size()];
            JLabel[] tutorcreatedDate = new JLabel[tutor_index.size()];

            initComponents();
            getContentPane().add(jScrollPane2);
            getContentPane().add(allJobs, BorderLayout.CENTER);
            allJobs.setLayout(new BoxLayout(allJobs, BoxLayout.Y_AXIS));
            jScrollPane2.setVisible(true);
            
 
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
                allJobs.add(Box.createRigidArea(new Dimension(0,25)));
                
            }
           
           
           
           
           getContentPane().add(campusScroll);
           getContentPane().add(campusJob, BorderLayout.CENTER);
           campusJob.setLayout(new BoxLayout(campusJob, BoxLayout.Y_AXIS));
           
           for(int i = 0; i < on_campus_index.size(); i++){
                campusjobPanel[i] = new JPanel();
                campustitle[i] = new JLabel(job[on_campus_index.get(i)].get_job_title());
                campusdesc[i] = new JLabel(job[on_campus_index.get(i)].get_job_description());
                campushour_rate[i] = new JLabel(String.valueOf(job[on_campus_index.get(i)].get_hourly_rate()));
                campusworkHour[i] = new JLabel(String.valueOf(job[on_campus_index.get(i)].get_workHours()));
                campuspref_skill[i] = new JLabel();
                
                String skills = "";
                    for(String skill : job[on_campus_index.get(i)].get_preferred_skills()){
                         skills += skill + " "; // MAGDAGDAG NG , somwhere
                    }
                
                campuspref_skill[i].setText(skills);
                campuswork_categ[i] = new JLabel(job[on_campus_index.get(i)].get_work_category()[0]);
                campusid[i] = new JLabel(job[on_campus_index.get(i)].get_id());
                campuscreatedDate[i] = new JLabel(String.valueOf(job[on_campus_index.get(i)].get_createdAt()));
                

                campusjobPanel[i].setBackground(Color.LIGHT_GRAY);
                campusjobPanel[i].add(campuscreatedDate[i]);
                campusjobPanel[i].add(campustitle[i]);
                campusjobPanel[i].add(campusdesc[i]);
                campusjobPanel[i].add(campushour_rate[i]);
                campusjobPanel[i].add(campusworkHour[i]);
                campusjobPanel[i].add(campuspref_skill[i]);
                campusjobPanel[i].add(campuswork_categ[i]);
                campusjobPanel[i].add(campusid[i]);
                
                campusjobPanel[i].setLayout(new BoxLayout(campusjobPanel[i], BoxLayout.Y_AXIS));
                campusJob.add(campusjobPanel[i]);
                campusJob.add(Box.createRigidArea(new Dimension(0,25)));
                
            }
           
         
           
           
           getContentPane().add(foodScroll);
           getContentPane().add(foodServiceJob, BorderLayout.CENTER);
           foodServiceJob.setLayout(new BoxLayout(foodServiceJob, BoxLayout.Y_AXIS));
           
           for(int i = 0; i < food_service_index.size(); i++){
                foodjobPanel[i] = new JPanel();
                foodtitle[i] = new JLabel(job[food_service_index.get(i)].get_job_title());
                fooddesc[i] = new JLabel(job[food_service_index.get(i)].get_job_description());
                foodhour_rate[i] = new JLabel(String.valueOf(job[food_service_index.get(i)].get_hourly_rate()));
                foodworkHour[i] = new JLabel(String.valueOf(job[food_service_index.get(i)].get_workHours()));
                foodpref_skill[i] = new JLabel();
                
                String skills = "";
                    for(String skill : job[food_service_index.get(i)].get_preferred_skills()){
                         skills += skill + " "; // MAGDAGDAG NG , somwhere
                    }
                
                foodpref_skill[i].setText(skills);
                foodwork_categ[i] = new JLabel(job[food_service_index.get(i)].get_work_category()[0]);
                foodid[i] = new JLabel(job[food_service_index.get(i)].get_id());
                foodcreatedDate[i] = new JLabel(String.valueOf(job[food_service_index.get(i)].get_createdAt()));
                

                foodjobPanel[i].setBackground(Color.LIGHT_GRAY);
                foodjobPanel[i].add(foodcreatedDate[i]);
                foodjobPanel[i].add(foodtitle[i]);
                foodjobPanel[i].add(fooddesc[i]);
                foodjobPanel[i].add(foodhour_rate[i]);
                foodjobPanel[i].add(foodworkHour[i]);
                foodjobPanel[i].add(foodpref_skill[i]);
                foodjobPanel[i].add(foodwork_categ[i]);
                foodjobPanel[i].add(foodid[i]);
                
                foodjobPanel[i].setLayout(new BoxLayout(foodjobPanel[i], BoxLayout.Y_AXIS));
                foodServiceJob.add(foodjobPanel[i]);
                foodServiceJob.add(Box.createRigidArea(new Dimension(0,25)));
                
            }
           
           getContentPane().add(tutorScroll);
           getContentPane().add(tutorJob, BorderLayout.CENTER);
           tutorJob.setLayout(new BoxLayout(tutorJob, BoxLayout.Y_AXIS));
           
           for(int i = 0; i < tutor_index.size(); i++){
                tutorjobPanel[i] = new JPanel();
                tutortitle[i] = new JLabel(job[tutor_index.get(i)].get_job_title());
                tutordesc[i] = new JLabel(job[tutor_index.get(i)].get_job_description());
                tutorhour_rate[i] = new JLabel(String.valueOf(job[tutor_index.get(i)].get_hourly_rate()));
                tutorworkHour[i] = new JLabel(String.valueOf(job[tutor_index.get(i)].get_workHours()));
                tutorpref_skill[i] = new JLabel();
                
                String skills = "";
                    for(String skill : job[tutor_index.get(i)].get_preferred_skills()){
                         skills += skill + " "; // MAGDAGDAG NG , somwhere
                    }
                
                tutorpref_skill[i].setText(skills);
                tutorwork_categ[i] = new JLabel(job[tutor_index.get(i)].get_work_category()[0]);
                tutorid[i] = new JLabel(job[tutor_index.get(i)].get_id());
                tutorcreatedDate[i] = new JLabel(String.valueOf(job[tutor_index.get(i)].get_createdAt()));
                

                tutorjobPanel[i].setBackground(Color.LIGHT_GRAY);
                tutorjobPanel[i].add(tutorcreatedDate[i]);
                tutorjobPanel[i].add(tutortitle[i]);
                tutorjobPanel[i].add(tutordesc[i]);
                tutorjobPanel[i].add(tutorhour_rate[i]);
                tutorjobPanel[i].add(tutorworkHour[i]);
                tutorjobPanel[i].add(tutorpref_skill[i]);
                tutorjobPanel[i].add(tutorwork_categ[i]);
                tutorjobPanel[i].add(tutorid[i]);
                
                tutorjobPanel[i].setLayout(new BoxLayout(tutorjobPanel[i], BoxLayout.Y_AXIS));
                tutorJob.add(tutorjobPanel[i]);
                tutorJob.add(Box.createRigidArea(new Dimension(0,25)));
                
            }
          
           jScrollPane2.getViewport().add(allJobs);
           campusScroll.getViewport().add(campusJob);
           foodScroll.getViewport().add(foodServiceJob);
           tutorScroll.getViewport().add(tutorJob);

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
        jPanel1 = new javax.swing.JPanel();
        allPostPanel = new javax.swing.JPanel();
        postPanel = new javax.swing.JPanel();
        bProfilePanel = new javax.swing.JPanel();
        BprofilePic = new javax.swing.JLabel();
        companyName = new javax.swing.JLabel();
        badress = new javax.swing.JLabel();
        bbio = new javax.swing.JLabel();
        bAboutPanel = new javax.swing.JPanel();
        LoginPage = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        login = new javax.swing.JButton();
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
        campusScroll = new javax.swing.JScrollPane();
        campusJob = new javax.swing.JPanel();
        tutorScroll = new javax.swing.JScrollPane();
        tutorJob = new javax.swing.JPanel();
        foodScroll = new javax.swing.JScrollPane();
        foodServiceJob = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        allJobs = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GigFinder");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

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
        BallPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BallPostActionPerformed(evt);
            }
        });

        Bpost.setBackground(new java.awt.Color(255, 255, 255));
        Bpost.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Bpost.setForeground(new java.awt.Color(0, 94, 129));
        Bpost.setText("Post");
        Bpost.setFocusPainted(false);
        Bpost.setBorder(null);
        Bpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BpostActionPerformed(evt);
            }
        });

        Bprofile.setBackground(new java.awt.Color(255, 255, 255));
        Bprofile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Bprofile.setForeground(new java.awt.Color(0, 94, 129));
        Bprofile.setText("Profile");
        Bprofile.setBorder(null);
        Bprofile.setFocusPainted(false);
        Bprofile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BprofileActionPerformed(evt);
            }
        });

        BaboutUs.setBackground(new java.awt.Color(255, 255, 255));
        BaboutUs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BaboutUs.setForeground(new java.awt.Color(0, 94, 129));
        BaboutUs.setText("About Us");
        BaboutUs.setBorder(null);
        BaboutUs.setFocusPainted(false);
        BaboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaboutUsActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Blogout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(0, 94, 129));

        allPostPanel.setBackground(new java.awt.Color(204, 255, 204));
        allPostPanel.setVisible(true);

        javax.swing.GroupLayout allPostPanelLayout = new javax.swing.GroupLayout(allPostPanel);
        allPostPanel.setLayout(allPostPanelLayout);
        allPostPanelLayout.setHorizontalGroup(
            allPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        allPostPanelLayout.setVerticalGroup(
            allPostPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        postPanel.setBackground(new java.awt.Color(51, 255, 204));
        postPanel.setVisible(false);

        javax.swing.GroupLayout postPanelLayout = new javax.swing.GroupLayout(postPanel);
        postPanel.setLayout(postPanelLayout);
        postPanelLayout.setHorizontalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        postPanelLayout.setVerticalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        bProfilePanel.setBackground(new java.awt.Color(255, 255, 153));
        bProfilePanel.setVisible(false);

        BprofilePic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        companyName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        companyName.setText("John Allen Salapayne");

        badress.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        badress.setText("adresss");

        bbio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bbio.setText("jLabel5");

        bProfilePanel.setSize(826, 620);

        javax.swing.GroupLayout bProfilePanelLayout = new javax.swing.GroupLayout(bProfilePanel);
        bProfilePanel.setLayout(bProfilePanelLayout);
        bProfilePanelLayout.setHorizontalGroup(
            bProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bProfilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(badress)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bProfilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bbio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bProfilePanelLayout.createSequentialGroup()
                .addContainerGap(366, Short.MAX_VALUE)
                .addComponent(companyName)
                .addContainerGap(364, Short.MAX_VALUE))
            .addGroup(bProfilePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BprofilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bProfilePanelLayout.setVerticalGroup(
            bProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bProfilePanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(BprofilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(companyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(badress)
                .addGap(47, 47, 47)
                .addComponent(bbio)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        bAboutPanel.setBackground(new java.awt.Color(255, 204, 204));
        bAboutPanel.setVisible(false);

        javax.swing.GroupLayout bAboutPanelLayout = new javax.swing.GroupLayout(bAboutPanel);
        bAboutPanel.setLayout(bAboutPanelLayout);
        bAboutPanelLayout.setHorizontalGroup(
            bAboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bAboutPanelLayout.setVerticalGroup(
            bAboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bAboutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bProfilePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(postPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(allPostPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bAboutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bProfilePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(postPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(allPostPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BhomepageLayout = new javax.swing.GroupLayout(Bhomepage);
        Bhomepage.setLayout(BhomepageLayout);
        BhomepageLayout.setHorizontalGroup(
            BhomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BhomepageLayout.createSequentialGroup()
                .addComponent(Bsidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BhomepageLayout.setVerticalGroup(
            BhomepageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bsidebar, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        LoginPage.setBackground(new java.awt.Color(0, 94, 129));

        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setText(" Enter Email");
        email.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        email.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        password.setForeground(new java.awt.Color(153, 153, 153));
        password.setText(" Enter Password");
        password.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFocusLost(evt);
            }
        });

        login.setText("Login");
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
                .addContainerGap(352, Short.MAX_VALUE)
                .addGroup(LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(367, Short.MAX_VALUE))
        );
        LoginPageLayout.setVerticalGroup(
            LoginPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPageLayout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
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
                .addGroup(aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        aboutPanelLayout.setVerticalGroup(
            aboutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutPanelLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel4)
                .addGap(113, 113, 113)
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
        JobsPanel.setPreferredSize(new java.awt.Dimension(790, 2000));

        campusScroll.setForeground(new java.awt.Color(0, 94, 129));
        campusScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        campusScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        campusScroll.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        campusScroll.setMinimumSize(new java.awt.Dimension(790, 485));
        campusScroll.setPreferredSize(new java.awt.Dimension(790, 2000));

        campusJob.setBackground(new java.awt.Color(255, 204, 204));
        campusJob.setForeground(new java.awt.Color(0, 94, 129));
        campusJob.setMinimumSize(new java.awt.Dimension(770, 400));
        campusJob.setPreferredSize(new java.awt.Dimension(770, 700));
        campusJob.setVisible(false);

        javax.swing.GroupLayout campusJobLayout = new javax.swing.GroupLayout(campusJob);
        campusJob.setLayout(campusJobLayout);
        campusJobLayout.setHorizontalGroup(
            campusJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        campusJobLayout.setVerticalGroup(
            campusJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        campusScroll.setViewportView(campusJob);

        tutorScroll.setForeground(new java.awt.Color(0, 94, 129));
        tutorScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tutorScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tutorScroll.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tutorScroll.setMinimumSize(new java.awt.Dimension(790, 485));
        tutorScroll.setPreferredSize(new java.awt.Dimension(790, 2000));

        tutorJob.setBackground(new java.awt.Color(255, 204, 0));
        tutorJob.setForeground(new java.awt.Color(0, 94, 129));
        tutorJob.setMinimumSize(new java.awt.Dimension(770, 400));
        tutorJob.setPreferredSize(new java.awt.Dimension(770, 700));

        javax.swing.GroupLayout tutorJobLayout = new javax.swing.GroupLayout(tutorJob);
        tutorJob.setLayout(tutorJobLayout);
        tutorJobLayout.setHorizontalGroup(
            tutorJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        tutorJobLayout.setVerticalGroup(
            tutorJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        tutorScroll.setViewportView(tutorJob);
        tutorJob.setVisible(false);

        foodScroll.setForeground(new java.awt.Color(0, 94, 129));
        foodScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        foodScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        foodScroll.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        foodScroll.setMinimumSize(new java.awt.Dimension(800, 485));
        foodScroll.setPreferredSize(new java.awt.Dimension(790, 2000));

        foodServiceJob.setBackground(new java.awt.Color(0, 204, 204));
        foodServiceJob.setForeground(new java.awt.Color(0, 94, 129));
        foodServiceJob.setMinimumSize(new java.awt.Dimension(770, 400));
        foodServiceJob.setName(""); // NOI18N
        foodServiceJob.setPreferredSize(new java.awt.Dimension(770, 700));

        javax.swing.GroupLayout foodServiceJobLayout = new javax.swing.GroupLayout(foodServiceJob);
        foodServiceJob.setLayout(foodServiceJobLayout);
        foodServiceJobLayout.setHorizontalGroup(
            foodServiceJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 778, Short.MAX_VALUE)
        );
        foodServiceJobLayout.setVerticalGroup(
            foodServiceJobLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        foodScroll.setViewportView(foodServiceJob);
        foodServiceJob.setVisible(false);

        jScrollPane2.setForeground(new java.awt.Color(0, 94, 129));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(790, 485));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(790, 2000));

        allJobs.setBackground(new java.awt.Color(0, 94, 129));
        allJobs.setForeground(new java.awt.Color(0, 94, 129));
        allJobs.setMinimumSize(new java.awt.Dimension(770, 400));
        allJobs.setPreferredSize(new java.awt.Dimension(770, 1900));

        javax.swing.GroupLayout allJobsLayout = new javax.swing.GroupLayout(allJobs);
        allJobs.setLayout(allJobsLayout);
        allJobsLayout.setHorizontalGroup(
            allJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );
        allJobsLayout.setVerticalGroup(
            allJobsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1900, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(allJobs);

        javax.swing.GroupLayout JobsPanelLayout = new javax.swing.GroupLayout(JobsPanel);
        JobsPanel.setLayout(JobsPanelLayout);
        JobsPanelLayout.setHorizontalGroup(
            JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JobsPanelLayout.createSequentialGroup()
                .addComponent(campusScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JobsPanelLayout.createSequentialGroup()
                    .addComponent(tutorScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 7, Short.MAX_VALUE)))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JobsPanelLayout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(foodScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(JobsPanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        JobsPanelLayout.setVerticalGroup(
            JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campusScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tutorScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(foodScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JobsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JobsPanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1994, Short.MAX_VALUE)
                    .addContainerGap()))
        );

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
                        .addGroup(findworkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JobsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelJoblist))))
                .addContainerGap(24, Short.MAX_VALUE))
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
                .addComponent(JobsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(findworkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1001, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Bhomepage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LoginPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Shomepage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Bhomepage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(LoginPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Shomepage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            String response = apiCLient.loginUser("johndoe@gmail.com", "mysecretpassword");
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
                
                try {
                    URL url = new URL(Owner.getProfile().getProfile_pic());
                    BufferedImage image = ImageIO.read(url);
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(profilePic.getWidth(), profilePic.getHeight(), Image.SCALE_SMOOTH));
                    BprofilePic.setIcon(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                companyName.setText(Owner.getProfile().getCompany_name());
                badress.setText(Owner.getProfile().getAddress());
                bbio.setText(Owner.getProfile().getAboutUs());
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
        campusScroll.setVisible(false);
        jScrollPane2.setVisible(true);
        tutorScroll.setVisible(false);
        foodScroll.setVisible(false);
        
        allJobs.setVisible(true);
        tutorJob.setVisible(false);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(false);
        
    }//GEN-LAST:event_allBtnActionPerformed

    private void foodserviceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodserviceBtnActionPerformed
        // TODO add your handling code here:
        campusScroll.setVisible(false);
        jScrollPane2.setVisible(false);
        tutorScroll.setVisible(false);
        foodScroll.setVisible(true);
        
        allJobs.setVisible(false);
        tutorJob.setVisible(false);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(true);
    }//GEN-LAST:event_foodserviceBtnActionPerformed

    private void tutorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorBtnActionPerformed
        // TODO add your handling code here:
        campusScroll.setVisible(false);
        jScrollPane2.setVisible(false);
        tutorScroll.setVisible(true);
        foodScroll.setVisible(false);
        
        allJobs.setVisible(false);
        tutorJob.setVisible(true);
        campusJob.setVisible(false);
        foodServiceJob.setVisible(false);
    }//GEN-LAST:event_tutorBtnActionPerformed

    private void campusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campusBtnActionPerformed
        // TODO add your handling code here:
        campusScroll.setVisible(true);
        jScrollPane2.setVisible(false);
        tutorScroll.setVisible(false);
        foodScroll.setVisible(false);
        
        allJobs.setVisible(false);
        tutorJob.setVisible(false);
        campusJob.setVisible(true);
        foodServiceJob.setVisible(false);
    }//GEN-LAST:event_campusBtnActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void BallPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BallPostActionPerformed
        // TODO add your handling code here:
        allPostPanel.setVisible(true);
        postPanel.setVisible(false);
        bProfilePanel.setVisible(false);
        bAboutPanel.setVisible(false);
    }//GEN-LAST:event_BallPostActionPerformed

    private void BpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BpostActionPerformed
        // TODO add your handling code here:
        allPostPanel.setVisible(false);
        postPanel.setVisible(true);
        bProfilePanel.setVisible(false);
        bAboutPanel.setVisible(false);
    }//GEN-LAST:event_BpostActionPerformed

    private void BprofileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BprofileActionPerformed
        // TODO add your handling code here:
        allPostPanel.setVisible(false);
        postPanel.setVisible(false);
        bProfilePanel.setVisible(true);
        bAboutPanel.setVisible(false);
    }//GEN-LAST:event_BprofileActionPerformed

    private void BaboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaboutUsActionPerformed
        // TODO add your handling code here:
        allPostPanel.setVisible(false);
        postPanel.setVisible(false);
        bProfilePanel.setVisible(false);
        bAboutPanel.setVisible(true);
    }//GEN-LAST:event_BaboutUsActionPerformed
 
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
    private javax.swing.JLabel BprofilePic;
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
    private javax.swing.JPanel allPostPanel;
    private javax.swing.JPanel bAboutPanel;
    private javax.swing.JPanel bProfilePanel;
    private javax.swing.JLabel badress;
    private javax.swing.JLabel bbio;
    private javax.swing.JLabel bio;
    private javax.swing.JLabel brandName;
    private javax.swing.JButton campusBtn;
    private javax.swing.JPanel campusJob;
    private javax.swing.JScrollPane campusScroll;
    private javax.swing.JLabel categoriesLabel;
    private javax.swing.JLabel company;
    private javax.swing.JLabel companyName;
    private javax.swing.JTextField email;
    private javax.swing.JButton findWork;
    private javax.swing.JPanel findworkPanel;
    private javax.swing.JScrollPane foodScroll;
    private javax.swing.JPanel foodServiceJob;
    private javax.swing.JButton foodserviceBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelJoblist;
    private javax.swing.JButton login;
    private javax.swing.JLabel logo;
    private javax.swing.JButton logout;
    private javax.swing.JLabel name;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField password;
    private javax.swing.JLabel position;
    private javax.swing.JPanel postPanel;
    private javax.swing.JButton profile;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JLabel profilePic;
    private javax.swing.JLabel skills;
    private javax.swing.JButton tutorBtn;
    private javax.swing.JPanel tutorJob;
    private javax.swing.JScrollPane tutorScroll;
    // End of variables declaration//GEN-END:variables
}
