package fi.rbmk.ticketguru.venue;

import fi.rbmk.ticketguru.event.Event;
import fi.rbmk.ticketguru.postcode.Postcode;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_ID")
    private Long id;

    @NotEmpty(message = "Venue name is required")
    @Length(max = 100)
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Venue address is required")
    @Length(max = 150)
    @Column(name = "streetAddress")
    private String address;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "postcode_ID")
    private Postcode postcode;

    @NotEmpty(message = "Venue phone number is required")
    @Length(max = 25)
    @Column(name = "tel")
    private String tel;

    @NotEmpty(message = "Venue email address is required")
    @Length(max = 150)
    @Column(name = "email")
    private String email;

    @Length(max = 250)
    @Column(name = "www")
    private String www;

    @NotEmpty(message = "Venue contact person is required")
    @Length(max = 150)
    @Column(name = "contactPerson")
    private String contactPerson;

    @JsonIgnore
    @OneToMany(mappedBy = "venue")
    private List<Event> events;

    public Venue() {
    }

    public Venue(Venue venue) {
    }

    public Venue(String name, String address, Postcode postcode, String tel, String email) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.tel = tel;
        this.email = email;
    }

    public Venue(String name, String address, Postcode postcode, String tel, String email, String www, String contactPerson) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.tel = tel;
        this.email = email;
        this.www = www;
        this.contactPerson = contactPerson;
    }

    //Getters
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getAddress() {
        return this.address;
    }
    public Postcode getPostcode() {
        return this.postcode;
    }
    public String getTel() {
        return this.tel;
    }
    public String getEmail() {
        return this.email;
    }
    public String getWWW() {
        return this.www;
    }
    public String getContactPerson() {
        return this.contactPerson;
    }


    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPostcode(Postcode postcode) {
        this.postcode = postcode;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setWWW(String www) {
        this.www = www;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}