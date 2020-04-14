/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entity.User;
import gui.LoginController;
import service.MyServices;

/**
 *
 * @author asus
 */
public class SMS {
    public static int userid;
    private static MyServices myServices = new MyServices();
    User UserConneter;
    
    public static final String ACCOUNT_SID = "AC1fd3d1ed6923bd59959018c3fde2638c";
    public static final String AUTH_TOKEN = "4a2c4f676e47f9034f44397ce62bed00";

    public static void sendSms(){
  // Find your Account Sid and Token at twilio.com/user/account
 
    
    User loggedUser = LoginController.getInstance().getLoggedUser();        
    User e=myServices.chercherUtilisateurByid(pi.Pi.Id_user_connecte);          
        

    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+216"+e.getPhone()),
        new PhoneNumber("+12562914882"), 
        "Bonjour "+e.getPrenom()+" "+e.getNom()+" \nVotre empreinte a ete effectuer avec succses ").create();

    System.out.println(message.getSid());
  }

    
    
}
