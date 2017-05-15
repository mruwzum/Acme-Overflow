package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by daviddelatorre on 15/5/17.
 */
@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {


    private Actor sender;
    private Actor receiver;
    private Date sendDate;
    private String subject;
    private String body;
    private Priority priority;
    private Folder folder;

    public Message() {
    }
}
