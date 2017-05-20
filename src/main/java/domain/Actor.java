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
    private Collection<Message> receivedMessages;
    private Collection<Message> sendedMessages;
    private Collection<Folder> folders;

    private UserAccount userAccount;


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

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "receiver")
    public Collection<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(Collection<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sender")
    public Collection<Message> getSendedMessages() {
        return sendedMessages;
    }

    public void setSendedMessages(Collection<Message> sendedMessages) {
        this.sendedMessages = sendedMessages;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "owner")
    public Collection<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Collection<Folder> folders) {
        this.folders = folders;
    }
}
