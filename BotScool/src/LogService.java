import com.pengrad.telegrambot.model.Update;

public class LogService {
    public void log(Update update){
        String username = update.message().from().username() != null ? update.message().from().username() : "";
        System.out.println("New Message");
        System.out.println("Username:" + username);
        System.out.println("text: " + update.message().text());
        System.out.println("-----------------------");
    }
}
