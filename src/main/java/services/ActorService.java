package services;

import domain.Actor;
import domain.Folder;
import domain.Mezzage;
import domain.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActorService {

    // Managed Repository ------------------------
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private FolderService folderService;
   @Autowired
   private MezzageService mezzageService;

    // Supporting services -----------------------

    // Constructor -------------------------------
    public ActorService() {
        super();
    }

    // Simple CRUD methods -----------------------

    public Actor findOne(int actorId) {
        Actor result;

        result = actorRepository.findOne(actorId);

        return result;
    }

    public Collection<Actor> findAll() {
        Collection<Actor> result;

        result = actorRepository.findAll();

        return result;
    }

    public Actor save(Actor actor) {
        Assert.notNull(actor);
        return actorRepository.save(actor);
    }

    public void delete(Actor actor) {
        Assert.notNull(actor);
        Assert.isTrue(actorRepository.exists(actor.getId()));
        actorRepository.delete(actor);
    }

    // Other business methods -----------------------


    public Actor findByPrincipal() {
        Actor result;
        UserAccount userAccount;

        userAccount = LoginService.getPrincipal();
        Assert.notNull(userAccount);
        result = findByUserAccount(userAccount);
        Assert.notNull(result);

        return result;
    }

    private Actor findByUserAccount(UserAccount userAccount) {
        Assert.notNull(userAccount);

        Actor result;

        result = actorRepository.findByUserAccountId(userAccount.getId());

        return result;
    }

    public void flush() {
        actorRepository.flush();
    }

    //Manage Folder

    public Folder createNewFolder() {
        Folder aux = folderService.create();
        aux.setName("generic");
        Folder res = folderService.save(aux);
        return res;
    }

    public Folder createFolder(String name) {
        Assert.notNull(name, "El nombre es nulo");
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Folder aux = folderService.create();
        aux.setName(name);
        Folder res = folderService.save(aux);
        u.getFolders().add(res);
        return res;
    }

   public Actor findByName(String name) {
      Assert.notNull(name);
      Actor res = actorRepository.findByName(name);
      return res;
   }



    public Collection<Folder> getFolders() {

        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Collection<Folder> res = actorRepository.getFolder(u.getId());
        return res;
    }

    public Folder modifyFolder(Folder f, String name) {
        Assert.notNull(f);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(u.getFolders().contains(f));
        f.setName(name);
        return f;
    }

    public void deleteFolder(Folder f) {
        Assert.notNull(f);
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        Assert.isTrue(u.getFolders().contains(f), "El actor no contiene la carpeta ");
        folderService.delete(f);
    }

//   public Mezzage sendMessage(Mezzage mezzage) {
//        Actor u;
//        u = findByPrincipal();
//        Assert.notNull(u, "El actor no existe");
//      recieveMessage(mezzage, mezzage.getReceiver());
//      return mezzage;
//    }

   public Mezzage recieveMessage(Mezzage mezzage, Actor a) {


        List<Folder> folders = new ArrayList<>(a.getFolders());
        Assert.notEmpty(folders, "carpetas vacias");
      folders.get(2).getMezzages().add(mezzage);
        a.setFolders(folders);
      return mezzage;
    }

   public void textMessage(Mezzage mezzage) {

       Assert.notNull(mezzage.getSubject(), "El subject no existe");
       Assert.notNull(mezzage.getBody(), "El body no existe");
      Assert.notNull(mezzage.getReceiverEmail(), "El recipient no existe");
      // Assert.notNull(mezzage.getSender(), "El sender es null ");

     Actor receiver = actorRepository.findUserByEmail(mezzage.getReceiverEmail());

       Actor sender = actorRepository.findUserByEmail(mezzage.getSenderEmail());


      mezzage.setReceiver(receiver);
      Mezzage mezzage1 = mezzageService.create();
      mezzage1.setSendDate(mezzage.getSendDate());
      mezzage1.setSenderEmail(mezzage.getSenderEmail());
      //mezzage1.setSender(mezzage.getSender());
      mezzage1.setBody(mezzage.getBody());
      mezzage1.setSubject(mezzage.getSubject());
      mezzage1.setPriority(mezzage.getPriority());
      mezzage1.setReceiver(mezzage.getReceiver());
      mezzage1.setReceiverEmail(mezzage.getReceiverEmail());


      Folder f = actorRepository.folderByName(receiver, "Inbox");
      mezzage1.setFolder(f);
      //f.getMezzages().add(mezzage1);



       Folder fs = actorRepository.folderByName(sender, "Outbox");
       mezzage.setFolder(fs);
       //fs.getMezzages().add(mezzage);

       mezzageService.save(mezzage);
       mezzageService.save(mezzage1);

//       mezzageService.save(mezzage1);


   }

   public Folder folderByName(Actor a, String name){

        return actorRepository.folderByName(a,name);
   }

}