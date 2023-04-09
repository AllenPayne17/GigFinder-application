package gigfinder;

import java.util.List;

public class StudentProfile {
   
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
    private String name;
    private String bio;
    private List<String> skills;
    private String address;
    private List<EducationalBackground> educational_background;
    private List<WorkExperience> work_experience;
    private List<Account> account;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EducationalBackground> getEducational_background() {
        return educational_background;
    }

    public void setEducational_background(List<EducationalBackground> educational_background) {
        this.educational_background = educational_background;
    }

    public List<WorkExperience> getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(List<WorkExperience> work_experience) {
        this.work_experience = work_experience;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }
}

class EducationalBackground {
    private String degree;
    private String institution;
    private String start_date;
    private String end_date;
    private String _id;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

class WorkExperience {
    private String position;
    private String company_name;
    private String Responsibilities;
    private String end_date;
    private String _id;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return company_name;
    }
    
        public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getResponsibilities() {
        return Responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        Responsibilities = responsibilities;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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