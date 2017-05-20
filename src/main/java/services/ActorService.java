package services;

import domain.Actor;
import domain.Folder;
import domain.Message;
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
   private MessageService messageService;

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

    public Message sendMessage(Message message) {
        Actor u;
        u = findByPrincipal();
        Assert.notNull(u, "El actor no existe");
        recieveMessage(message, message.getReceiver());
        return message;
    }

    public Message recieveMessage(Message message, Actor a) {


        List<Folder> folders = new ArrayList<>(a.getFolders());
        Assert.notEmpty(folders, "carpetas vacias");
        folders.get(2).getMessages().add(message);
        a.setFolders(folders);
        return message;
    }

   public Message textMessage(String subject, String body, Actor recipient, Priority priority) {
      Assert.notNull(subject, "El subject no existe");
      Assert.notNull(body, "El body no existe");
      Assert.notNull(recipient, "El actor no existe");
      Actor u;
      u = findByPrincipal();
      Assert.notNull(u, "El actor no existe");
      //Comprobaciones
      List<Folder> auxFolder = new ArrayList<>(u.getFolders());
      Message aux = messageService.create();
      aux.setSender(u);
      aux.setSubject(subject);
      aux.setBody(body);
      aux.setReceiver(recipient);
      aux.setPriority(priority);
      aux.setSendDate(new Date(System.currentTimeMillis() - 100));
      aux.setBody(body);
      aux.setFolder(auxFolder.get(0));
      Message copy = aux;

      Message res1 = messageService.save(aux);
      Message res2 = messageService.save(copy);


      //Guardar en carpeta outbox de sender

      Collection<Folder> folders = u.getFolders();

      for (Folder f : folders) {
         if (f.getName().equals("Inbox")) {
            f.getMessages().add(res1);
         }
      }


      //Guardar en carpeta inbox de recipient


      Collection<Folder> folders2 = recipient.getFolders();

      for (Folder f : folders2) {
         if (f.getName().equals("Outbox")) {
            f.getMessages().add(res2);
         }
      }


      return res1;
   }
}