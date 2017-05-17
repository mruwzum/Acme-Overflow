package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import java.util.*;

/**
 * Created by david on 05/11/2016.
 * Copyright © 2016 NullPoint Software
 */

@Service
@Transactional
public class UserService {

    // Constructors--------------------------------------------------------------------------------------

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CreditCardService creditCardService;
    @Autowired
    private WebinarService webinarService;


    // Managed repository--------------------------------------------------------------------------------


    // Suporting services --------------------------------------------------------------------------------


    public UserService() {
        super();
    }


    // Simple CRUD method --------------------------------------------------------------------------------

    public User create() {
        User res = new User();
        return res;
    }

    public Collection<User> findAll() {
        Collection<User> res = userRepository.findAll();
        Assert.notNull(res);
        return res;
    }

    public User findOne(int User) {
        domain.User res = userRepository.findOne(User);
        Assert.notNull(res);
        return res;
    }

    public User save(User a) {
        Assert.notNull(a);
        User res = userRepository.save(a);
        return res;
    }

    public void delete(User a) {
        Assert.notNull(a);
        Assert.isTrue(a.getId() != 0);
        userRepository.delete(a);

    }

    // Other business methods -------------------------------------------------------------------------------


    public User findByPrincipal() {
        User result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    public User findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        User result;

        result = userRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

    public Actor registerAsUser(User u) {
        Assert.notNull(u);
        Authority autoh = new Authority();
        autoh.setAuthority("USER");
        UserAccount res = new UserAccount();
        res.addAuthority(autoh);
        res.setUsername(u.getUserAccount().getUsername());
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(u.getUserAccount().getPassword(), null);
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        u.setUserAccount(userAccount);
        Assert.notNull(u.getUserAccount().getAuthorities(), "authorities null al registrar");
        User resu = userRepository.save(u);
        Collection<Message> received = new HashSet<>();
        Collection<Message> sended = new HashSet<>();
        Collection<Folder> folders = new HashSet<>();
        Folder inbox = folderService.create();
        inbox.setName("inbox");
        inbox.setOwner(resu);
        Collection<Message> innnn = new HashSet<>();
        inbox.setMessages(innnn);
        Folder outbox = folderService.create();
        outbox.setName("outbox");
        outbox.setOwner(resu);
        Collection<Message> ouuuu = new HashSet<>();
        outbox.setMessages(ouuuu);
        Folder spambox = folderService.create();
        spambox.setName("spambox");
        spambox.setOwner(resu);
        Collection<Message> spaaaam = new HashSet<>();
        spambox.setMessages(spaaaam);
        Folder trashBox = folderService.create();
        trashBox.setName("trashbox");
        trashBox.setOwner(resu);
        Collection<Message> trashhh = new HashSet<>();
        trashBox.setMessages(trashhh);
        folders.add(inbox);
        folders.add(outbox);
        folders.add(spambox);
        folders.add(trashBox);
        folderService.save(inbox);
        folderService.save(outbox);
        folderService.save(spambox);
        folderService.save(trashBox);
        resu.setFolders(folders);
        resu.setReceivedMessages(received);
        resu.setSendedMessages(sended);

        return resu;
    }


}



