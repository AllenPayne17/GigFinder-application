package gigfinder;

import java.util.List;

public class BusinessOwnerProfile {
    
    
    public class UserProfile {
        private String accountType;
        private Profile profile;

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
    }   
    class Profile {
        private String _id;
        private String profile_pic;
        private String company_name;
        private String address;
        private String aboutUs;
        private List<JobPost> job_posts;
        private List<Account> account;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getProfile_pic() {
            return profile_pic;
        }

        public void setProfile_pic(String profile_pic) {
            this.profile_pic = profile_pic;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAboutUs() {
            return aboutUs;
        }

        public void setAboutUs(String aboutUs) {
            this.aboutUs = aboutUs;
        }

        public List<JobPost> getJob_posts() {
            return job_posts;
        }

        public void setJob_posts(List<JobPost> job_posts) {
            this.job_posts = job_posts;
        }

        public List<Account> getAccount() {
            return account;
        }

        public void setAccount(List<Account> account) {
            this.account = account;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
    
    class JobPost {
        private String job_title;
        private String job_description;
        private int hourly_rate;
        private int workHours;
        private List<String> preferred_skills;
        private List<String> work_category;
        private String _id;
        private String createdAt;
        private String updatedAt;

        public String getJob_title() {
            return job_title;
        }

        public void setJob_title(String job_title) {
            this.job_title = job_title;
        }

        public String getJob_description() {
            return job_description;
        }

        public void setJob_description(String job_description) {
            this.job_description = job_description;
        }

        public int getHourly_rate() {
            return hourly_rate;
        }

        public void setHourly_rate(int hourly_rate) {
            this.hourly_rate = hourly_rate;
        }

        public int getWorkHours() {
            return workHours;
        }

        public void setWorkHours(int workHours) {
            this.workHours = workHours;
        }

        public List<String> getPreferred_skills() {
            return preferred_skills;
        }

        public void setPreferred_skills(List<String> preferred_skills) {
            this.preferred_skills = preferred_skills;
        }

        public List<String> getWork_category() {
            return work_category;
        }

        public void setWork_category(List<String> work_category) {
            this.work_category = work_category;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
    
    class Account {
        private String email;
        private String password;
        private String _id;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }
    }
}
