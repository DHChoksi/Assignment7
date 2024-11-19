import java.util.ArrayList;
import java.util.Arrays;
import ChatApp.*;

public class Driver {

    public static void main(String[] args) {
        ChatServer mediator = new ChatServer();
        User Mira = new User(mediator, "Mira");
        User Rishi = new User(mediator, "Rishi");
        User Meghana = new User(mediator, "Meghana");

        // Register users with the mediator
        mediator.registerUser(Mira);
        mediator.registerUser(Rishi);
        mediator.registerUser(Meghana);

        System.out.println("---------------------------------MESSAGING-----------------------------------------------");
        // Users sending messages
        Mira.send(new ArrayList<>(Arrays.asList(Rishi)), "Hello Rishi it's Mira!");
        Mira.send(new ArrayList<>(Arrays.asList(Meghana)), "Hello Meghana it's Mira!");
        Rishi.send(new ArrayList<>(Arrays.asList(Mira, Meghana)), "Hello Mira and Meghana it's Rishi!");
        Meghana.send(new ArrayList<>(Arrays.asList(Mira)), "Hello Mira, How are you doing?");
        Mira.send(new ArrayList<>(Arrays.asList(Meghana)), "I'm doing great!");

        System.out.println("---------------------------UNDO--------------------------------------------------");
        // Users undoing the last message sent
        System.out.println("Current last message for Mira is :-");
        System.out.println(Mira.getLastMessageSent());
        System.out.println("After deleting last message, Current last message for Mira is :-");
        Mira.undoLastMessageSent();
        System.out.println(Mira.getLastMessageSent());

        System.out.println("------------------------------BLOCK--------------------------------------------------");
        // Mira blocks Meghana and Meghana sends a message
        Mira.blockUser(Meghana);
        Meghana.send(new ArrayList<>(Arrays.asList(Mira)), "Did you just block me?");

        System.out.println("-----------------------------CHAT HISTORY------------------------------------------------------");
        // Iterating over Mira's chat history for messages by Rishi
        Iterator<Message> searchHistory = Mira.iterator(Rishi);
        while (searchHistory.hasNext()) {
            Message message = searchHistory.next();
            System.out.println(message.getTimestamp() + " " + message.getSender().getName() + ": " + message.getContent());
        }
    }
}
