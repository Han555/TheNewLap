/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author catherinexiong
 */
@Entity
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String email;
    private String mobileNumber;
    private String backgroundColor;
    @OneToMany(orphanRemoval = true,mappedBy="company")
    private List<Event> events = new ArrayList<Event>();
    @OneToMany(orphanRemoval = true,mappedBy="company")
    private List<SubEvent> subevents = new ArrayList<SubEvent>();
    @OneToMany(orphanRemoval = true,mappedBy="company")
    private List<PropertyEntity> properties = new ArrayList<PropertyEntity>();
    @OneToMany(orphanRemoval = true,mappedBy="company")
    private List<UserEntity> users = new ArrayList<UserEntity>();
    @OneToMany(orphanRemoval = true,mappedBy="company")
    private List<TicketTakingsEntity> takings = new ArrayList<TicketTakingsEntity>();
    @OneToOne(orphanRemoval= true, mappedBy = "company")
    private CompanyProfile profile;
    //add entity behind later
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<SubEvent> getSubevents() {
        return subevents;
    }

    public void setSubevents(List<SubEvent> subevents) {
        this.subevents = subevents;
    }

    public List<PropertyEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyEntity> properties) {
        this.properties = properties;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<TicketTakingsEntity> getTakings() {
        return takings;
    }

    public void setTakings(List<TicketTakingsEntity> takings) {
        this.takings = takings;
    }

    public CompanyProfile getProfile() {
        return profile;
    }

    public void setProfile(CompanyProfile profile) {
        this.profile = profile;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyEntity)) {
            return false;
        }
        CompanyEntity other = (CompanyEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompanyEntity[ id=" + id + " ]";
    }
    
}
