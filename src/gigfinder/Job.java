package gigfinder;

import java.util.Date;

public class Job{
    private final String job_title;
    private final String job_description;
    private final int hourly_rate;
    private final int workHours;
    private final String[] preferred_skills;
    private final String[] work_category;
    private final String _id;
    private final Date createdAt;


    public Job(String job_title, String job_description, int hourly_rate, int workHours, String[] preferred_skills, String[] work_category, String _id, Date createdAt) {
        this.job_title = job_title;
        this.job_description = job_description;
        this.hourly_rate = hourly_rate;
        this.workHours = workHours;
        this.preferred_skills = preferred_skills;
        this.work_category = work_category;
        this._id = _id;
        this.createdAt = createdAt;
    }

    public String get_job_title() {
        return job_title;
    }

    public String get_job_description() {
        return job_description;
    }

    public int get_hourly_rate() {
        return hourly_rate;
    }
    
    public int get_workHours() {
        return workHours;
    }
    
    public String[] get_preferred_skills() {
        return preferred_skills;
    }

    public String[] get_work_category() {
        return work_category;
    }

    public String get_id() {
        return _id;
    }

    public Date get_createdAt() {
        return createdAt;
    }
}