package org.example;

import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.ConnectException;
import java.net.URL;
import java.time.LocalDate;

@XmlRootElement
public class Company {
    private String fullName;
    private String shortName;


    private LocalDate dateUpdate;

    private String address;


    private LocalDate dateFoundation;

    private int countEmployees;

    private String auditor;

    private String phone;

    private String email;

    private String branch;

    private String activity;


    private URL link;

    public Company(String fullName, String shortName, LocalDate dateUpdate, String address, LocalDate dateFoundation, int countEmployees, String auditor, String phone, String email, String branch, String activity, URL link) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.dateUpdate = dateUpdate;
        this.address = address;
        this.dateFoundation = dateFoundation;
        this.countEmployees = countEmployees;
        this.auditor = auditor;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.activity = activity;
        this.link = link;
    }
    public Company(){

    }

    @XmlAttribute
    public String getFullName() {
        return fullName;
    }

    @XmlElement
    public String getShortName() {
        return shortName;
    }

    @XmlElement
    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    @XmlAttribute
    public String getAddress() {
        return address;
    }

    @XmlElement
    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    @XmlElement
    public int getCountEmployees() {
        return countEmployees;
    }

    @XmlAttribute
    public String getAuditor() {
        return auditor;
    }

    @XmlAttribute
    public String getPhone() {
        return phone;
    }

    @XmlAttribute
    public String getEmail() {
        return email;
    }

    @XmlElement
    public String getBranch() {
        return branch;
    }

    @XmlElement
    public String getActivity() {
        return activity;
    }

    @XmlAttribute
    public URL getLink() {
        return link;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fullName", fullName);
        jsonObject.put("shortName", shortName);
        jsonObject.put("dateUpdate", dateUpdate);
        jsonObject.put("address", address);
        jsonObject.put("dateFoundation", dateFoundation);
        jsonObject.put("countEmployees", countEmployees);
        jsonObject.put("auditor", auditor);
        jsonObject.put("phone", phone);
        jsonObject.put("email", email);
        jsonObject.put("branch", branch);
        jsonObject.put("activity", activity);
        jsonObject.put("link", link);

        return jsonObject;
    }
}