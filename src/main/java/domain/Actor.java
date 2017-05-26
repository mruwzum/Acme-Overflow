package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import security.UserAccount;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {


    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
   private Collection<Mezzage> receivedMezzages;
   private Collection<Mezzage> sendedMezzages;
    private Collection<Folder> folders;

    private UserAccount userAccount;
   private Collection<Answer> answers;
   private Collection<Search> searches;
   private Collection<Comment> comments;
   private CreditCard creditCard;


    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "(\\+\\d{1,3})?(\\(\\d{3}\\))?([0-9a-zA-z][ -]?){4,}")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    public Collection<Mezzage> getReceivedMezzages() {
       return receivedMezzages;
    }

   public void setReceivedMezzages(Collection<Mezzage> receivedMezzages) {
      this.receivedMezzages = receivedMezzages;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    public Collection<Mezzage> getSendedMezzages() {
       return sendedMezzages;
    }

   public void setSendedMezzages(Collection<Mezzage> sendedMezzages) {
      this.sendedMezzages = sendedMezzages;
    }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    public Collection<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Collection<Folder> folders) {
        this.folders = folders;
    }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
   public Collection<Answer> getAnswers() {
      return answers;
   }

   public void setAnswers(Collection<Answer> answers) {
      this.answers = answers;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
   public Collection<Search> getSearches() {
      return searches;
   }

   public void setSearches(Collection<Search> searches) {
      this.searches = searches;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
   public Collection<Comment> getComments() {
      return comments;
   }

   public void setComments(Collection<Comment> comments) {
      this.comments = comments;
   }

   @OneToOne(cascade = CascadeType.ALL)
   public CreditCard getCreditCard() {
      return creditCard;
   }

   public void setCreditCard(CreditCard creditCard) {
      this.creditCard = creditCard;
   }
}
