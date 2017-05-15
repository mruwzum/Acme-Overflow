package services;

import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class MessageService {

    // Constructors--------------------------------------------------------------------------------------

    public MessageService() {
        super();
    }

    // Managed repository--------------------------------------------------------------------------------

    @Autowired
    private repositories.MessageRepository MessageRepository;


    // Suporting services --------------------------------------------------------------------------------

    // Simple CRUD method --------------------------------------------------------------------------------

    public Message create() {
        Message res = new Message();
        return res;
    }

    public Collection<Message> findAll() {

        Collection<Message> res = MessageRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public Message findOne(int Message) {
        domain.Message res = MessageRepository.findOne(Message);
        Assert.notNull(res);
        return res;
    }

    public Message save(Message a) {
        Assert.notNull(a);
        Message res = MessageRepository.save(a);
        return res;
    }

    public void delete(Message a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        MessageRepository.delete(a);
    }

    // Other business methods -------------------------------------------------------------------------------

}



