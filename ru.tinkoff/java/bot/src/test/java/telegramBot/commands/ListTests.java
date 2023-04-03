package telegramBot.commands;


import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessorImpl;
import ru.tinkoff.edu.java.bot.telegram.commands.ListCommand;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.LinkResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.implementations.LinksClientImpl;


import java.net.URI;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class ListTests {

    private static final Long CHAT_ID = 123L;

    private static final String INVALID_COMMAND = "/foo";
    @Mock
    private LinksClientImpl client = Mockito.mock(LinksClientImpl.class);
    @InjectMocks
    private ListCommand listCommand = new ListCommand(client);
    @InjectMocks
    private UserManagerProcessorImpl messageProcessor = new UserManagerProcessorImpl(listCommand);


    private Update reflectionArrange(String messageFieldValue, String lastCommandFieldValue) throws IllegalAccessException, NoSuchFieldException {
        var message = new Message();
        var messageClass = message.getClass();
        var messageField = messageClass.getDeclaredField("text");
        messageField.setAccessible(true);
        messageField.set(message, messageFieldValue);


        var chat = new Chat();
        var chatField = Chat.class.getDeclaredField("id");
        chatField.setAccessible(true);
        chatField.set(chat, CHAT_ID);

        var messageChatField = messageClass.getDeclaredField("chat");
        messageChatField.setAccessible(true);
        messageChatField.set(message, chat);

        var update = new Update();
        var updateMessageField = Update.class.getDeclaredField("message");
        updateMessageField.setAccessible(true);
        updateMessageField.set(update, message);


        var lastCommandField = UserManagerProcessorImpl.class.getDeclaredField("lastCommand");
        lastCommandField.setAccessible(true);
        lastCommandField.set(messageProcessor, lastCommandFieldValue);
        return update;
    }


    @Test
    @DisplayName("Send special message if user send invalid command")
    void invalidCommandTest() throws NoSuchFieldException, IllegalAccessException {
        SendMessage messageFromListCommand = messageProcessor.process(reflectionArrange(INVALID_COMMAND, INVALID_COMMAND));
        assertThat(CHAT_ID, equalTo(messageFromListCommand.getParameters().get("chat_id")));
        assertThat("Команда не поддерживается, попробуйте /help", equalTo(messageFromListCommand.getParameters().get("text")));
    }

    @Test
    @DisplayName("List command test OK case")
    void processListCommand_fullList() throws NoSuchFieldException, IllegalAccessException {
        LinkResponse link1 = new LinkResponse(1L, URI.create("https://github.com/sanyarnd/tinkoff-java-course-2022/"));
        LinkResponse link2 = new LinkResponse(2L, URI.create("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"));
        var linksList = new ListLinksResponse(new LinkResponse[]{link1, link2}, 2);
        String messageText = "Список отслеживаемых ссылок:\n" +
                link1.url().toString() + "\n" +
                link2.url().toString() + "\n";


        when(client.getAllLinks(anyLong())).thenReturn(linksList);
        SendMessage messageFromProcessor = messageProcessor.process(reflectionArrange("/list", "/list"));
        assertThat(messageText, equalTo(messageFromProcessor.getParameters().get("text")));
        assertThat(CHAT_ID, equalTo(messageFromProcessor.getParameters().get("chat_id")));
    }

    @Test
    @DisplayName("List command test BAD case(no tracked links)")
    void processListCommand_emptyList() throws NoSuchFieldException, IllegalAccessException {
        String emptyListMessage = "Нет отслеживаемых ссылок";
        ListLinksResponse response = new ListLinksResponse(new LinkResponse[0], 0);


        when(client.getAllLinks(CHAT_ID)).thenReturn(response);

        SendMessage messageFromProcessor = messageProcessor.process(reflectionArrange("/list", "/list"));

        assertThat(emptyListMessage, equalTo(messageFromProcessor.getParameters().get("text")));
        assertThat(CHAT_ID, equalTo(messageFromProcessor.getParameters().get("chat_id")));

    }
}
