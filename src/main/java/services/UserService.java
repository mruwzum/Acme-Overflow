/*
 * Copyright © 2017. All information contained here included the intellectual and technical concepts are property of Null Point Software.
 */

package services;

import domain.*;
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

import java.util.Collection;
import java.util.HashSet;

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
    private MezzageService mezzageService;
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
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CurriculaService curriculaService;
    @Autowired
    private ActorService actorService;

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

    public void flush() {
        userRepository.flush();
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
        Collection<Mezzage> received = new HashSet<>();
        Collection<Mezzage> sended = new HashSet<>();
        Collection<Folder> folders = new HashSet<>();
        Folder inbox = folderService.create();
        inbox.setName("Inbox");
        inbox.setOwner(resu);
        Collection<Mezzage> innnn = new HashSet<>();
        inbox.setMezzages(innnn);
        Folder outbox = folderService.create();
        outbox.setName("Outbox");
        outbox.setOwner(resu);
        Collection<Mezzage> ouuuu = new HashSet<>();
        outbox.setMezzages(ouuuu);
        Folder spambox = folderService.create();
        spambox.setName("Spambox");
        spambox.setOwner(resu);
        Collection<Mezzage> spaaaam = new HashSet<>();
        spambox.setMezzages(spaaaam);
        Folder trashBox = folderService.create();
        trashBox.setName("Trashbox");
        trashBox.setOwner(resu);
        Collection<Mezzage> trashhh = new HashSet<>();
        trashBox.setMezzages(trashhh);
        folders.add(inbox);
        folders.add(outbox);
        folders.add(spambox);
        folders.add(trashBox);
        resu.setFolders(folders);
        resu.setReceivedMezzages(received);
        resu.setSendedMezzages(sended);
        Collection<Answer> answers = new HashSet<>();
        Collection<Question> questions = new HashSet<>();
        Collection<Comment> comments = new HashSet<>();
        resu.setQuestions(questions);
        resu.setAnswers(answers);
        resu.setComments(comments);
        return resu;
    }

    public Actor registerAsTeacher(Teacher teacher) {
        Assert.notNull(teacher);
        Authority autoh = new Authority();
        autoh.setAuthority("TEACHER");
        UserAccount res = new UserAccount();
        res.addAuthority(autoh);
        res.setUsername(teacher.getUserAccount().getUsername());
        Md5PasswordEncoder encoder;
        encoder = new Md5PasswordEncoder();
        String hash = encoder.encodePassword(teacher.getUserAccount().getPassword(), null);
        res.setPassword(hash);
        UserAccount userAccount = userAccountService.save(res);
        teacher.setUserAccount(userAccount);
        Assert.notNull(teacher.getUserAccount().getAuthorities(), "authorities null al registrar");
        teacher.getCurricula().setApprobed(false);
        Teacher resu = teacherService.save(teacher);
        Collection<Mezzage> received = new HashSet<>();
        Collection<Mezzage> sended = new HashSet<>();
        Collection<Folder> folders = new HashSet<>();
        Folder inbox = folderService.create();
        inbox.setName("Inbox");
        inbox.setOwner(resu);
        Collection<Mezzage> innnn = new HashSet<>();
        inbox.setMezzages(innnn);
        Folder outbox = folderService.create();
        outbox.setName("Outbox");
        outbox.setOwner(resu);
        Collection<Mezzage> ouuuu = new HashSet<>();
        outbox.setMezzages(ouuuu);
        Folder spambox = folderService.create();
        spambox.setName("Spambox");
        spambox.setOwner(resu);
        Collection<Mezzage> spaaaam = new HashSet<>();
        spambox.setMezzages(spaaaam);
        Folder trashBox = folderService.create();
        trashBox.setName("Trashbox");
        trashBox.setOwner(resu);
        Collection<Mezzage> trashhh = new HashSet<>();
        trashBox.setMezzages(trashhh);
        folderService.save(inbox);
        folderService.save(outbox);
        folderService.save(spambox);
        folderService.save(trashBox);
        resu.setFolders(folders);
        resu.setReceivedMezzages(received);
        resu.setSendedMezzages(sended);

        Collection<Answer> answers = new HashSet<>();
        Collection<Comment> comments = new HashSet<>();
        Collection<Webinar> webinars = new HashSet<>();
        resu.setWebinars(webinars);
        resu.setAnswers(answers);
        resu.setComments(comments);
        return resu;
    }

    public Boolean banUser(User user) {
        Boolean res = false;
        if (! user.getUserAccount().getAuthorities().isEmpty()) {
            Authority authority = new Authority();
            authority.setAuthority("USER");
            Authority authority2 = new Authority();
            authority2.setAuthority("BAN");
            user.getUserAccount().addAuthority(authority2);
            user.getUserAccount().removeAuthority(authority);
            user.setBanned(true);
            res = true;
        }
        return res;
    }

    public Boolean unbanUser(User user) {
        Boolean res = false;
        if (! user.getUserAccount().getAuthorities().isEmpty()) {


            Authority authority = new Authority();
            authority.setAuthority("USER");
            Authority authority2 = new Authority();
            authority2.setAuthority("BAN");

            user.getUserAccount().addAuthority(authority);
            user.getUserAccount().removeAuthority(authority2);
            user.setBanned(false);
            res = true;

        }
        return res;
    }

    public Collection<Mezzage> myWebbinarMezzages(User user) {
        Collection<Mezzage> res = new HashSet<>();
        res = userRepository.myWebbinarMezzages(user);
        return res;
    }

    public Collection<Mezzage> addMyWebinnarMezzagesToMyImbox(User u) {
        Collection<Mezzage> myWebis = myWebbinarMezzages(u);
        return myWebis;


    }


    public Collection<User> userNotBanned() {

        return userRepository.usersNotBanned();
    }

    public Collection<User> userNotModerator() {

        return userRepository.usersNotModerator();
    }
}



