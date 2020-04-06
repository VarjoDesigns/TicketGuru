package fi.rbmk.ticketguru.venue;

import fi.rbmk.ticketguru.event.Event;
import fi.rbmk.ticketguru.postcode.Postcode;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Venues")
public class Venue extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venue_ID")
    private Long venue_ID;

    @NotEmpty(message = "Venue name is required")
    @Length(max = 100)
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Venue address is required")
    @Length(max = 150)
    @Column(name = "streetAddress")
    private String address;

    @ManyToOne
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

    @Column(name = "valid")
    private LocalDateTime valid;

    @Column(name = "invalid")
    private LocalDateTime invalid;

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

    public Venue(String name, String address, Postcode postcode, String tel, String email, String www,
            String contactPerson) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.tel = tel;
        this.email = email;
        this.www = www;
        this.contactPerson = contactPerson;
    }

    // Getters
    public Long getVenue_ID() { return this.venue_ID; }
    public String getName() { return this.name; }
    public String getAddress() { return this.address; }
    public Postcode getPostcode() { return this.postcode; }
    public String getTel() { return this.tel; }
    public String getEmail() { return this.email; }
    public String getWWW() { return this.www; }
    public String getContactPerson() { return this.contactPerson; }
    public LocalDateTime getValid() { return valid; }
    public LocalDateTime getInvalid() { return invalid; }
    public List<Event> getEvents() { return this.events; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setPostcode(Postcode postcode) { this.postcode = postcode; }
    public void setTel(String tel) { this.tel = tel; }
    public void setEmail(String email) { this.email = email; }
    public void setWWW(String www) { this.www = www; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    public void setInvalid() { this.invalid = LocalDateTime.now(); }
}