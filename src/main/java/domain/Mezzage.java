/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Mezzage extends DomainEntity {


    private String senderEmail;
    private String receiverEmail;
    private Actor sender;
    private Actor receiver;
    private Date sendDate;
    private String subject;
    private String body;
    private Priority priority;
    private Folder folder;
    private Webinar webinar;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @ManyToOne(targetEntity = Actor.class, cascade = CascadeType.ALL)
    public Actor getSender() {
        return sender;
    }

    public void setSender(Actor sender) {
        this.sender = sender;
    }

    @ManyToOne(targetEntity = Actor.class, cascade = CascadeType.ALL)
    public Actor getReceiver() {
        return receiver;
    }

    public void setReceiver(Actor receiver) {
        this.receiver = receiver;
    }

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Webinar getWebinar() {
        return webinar;
    }

    public void setWebinar(Webinar webinar) {
        this.webinar = webinar;
    }

    @Override
    public String toString() {
        return "Mezzage{" +
                "senderEmail='" + senderEmail + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", sendDate=" + sendDate +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", priority=" + priority +
                '}';
    }
}
