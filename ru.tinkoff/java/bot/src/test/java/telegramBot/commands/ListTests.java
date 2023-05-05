package telegramBot.commands;


import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import ru.tinkoff.edu.java.bot.telegram.MessageProcessor.UserManagerProcessorImpl;
import ru.tinkoff.edu.java.bot.telegram.commands.ListCommand;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.LinkResponse;
import ru.tinkoff.edu.java.bot.webclients.dto.link.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.webclients.implementations.ScrapperClientImpls;


import java.net.URI;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class ListTests {

    @Mock
    private ScrapperClientImpls client = Mockito.mock(ScrapperClientImpls.class);
    @InjectMocks
    private ListCommand listCommand = new ListCommand(client);
    @InjectMocks
    private UserManagerProcessorImpl messageProcessor = new UserManagerProcessorImpl(client, listCommand);


    @Test
    public void invalidCommandTest() throws NoSuchFieldException, IllegalAccessException {
        long chatId = 123L;

        var message = new Message();
        var messageClass = message.getClass();
        var messageField = messageClass.getDeclaredField("text");
        messageField.setAccessible(true);
        messageField.set(message, "/foo");


        var chat = new Chat();
        var chatField = Chat.class.getDeclaredField("id");
        chatField.setAccessible(true);
        chatField.set(chat, chatId);

        var messageChatField = messageClass.getDeclaredField("chat");
        messageChatField.setAccessible(true);
        messageChatField.set(message, chat);

        var update = new Update();
        var updateMessageField = Update.class.getDeclaredField("message");
        updateMessageField.setAccessible(true);
        updateMessageField.set(update, message);


        SendMessage messageFromListCommand = messageProcessor.process(update);
        assertThat(chatId, equalTo(messageFromListCommand.getParameters().get("chat_id")));
        assertThat("Команда не поддерживается, попробуйте /help", equalTo(messageFromListCommand.getParameters().get("text")));
    }

    @Test
    public void processListCommand_fullList() throws NoSuchFieldException, IllegalAccessException {
        Long chatId = 123L;
        LinkResponse link1 = new LinkResponse(1L, URI.create("https://github.com/sanyarnd/tinkoff-java-course-2022/"));
        LinkResponse link2 = new LinkResponse(2L, URI.create("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"));
        var linksList = new ListLinksResponse(new LinkResponse[]{link1, link2}, 2);
        String messageText = "Список отслеживаемых ссылок:\n" +
            "1) " + link1.url().toString() + "\n\n" +
            "2) " + link2.url().toString() + "\n\n";

        var message = new Message();
        var messageClass = message.getClass();
        var messageField = messageClass.getDeclaredField("text");
        messageField.setAccessible(true);
        messageField.set(message, "/list");


        var chat = new Chat();
        var chatField = Chat.class.getDeclaredField("id");
        chatField.setAccessible(true);
        chatField.set(chat, chatId);

        var messageChatField = messageClass.getDeclaredField("chat");
        messageChatField.setAccessible(true);
        messageChatField.set(message, chat);

        var update = new Update();
        var updateMessageField = Update.class.getDeclaredField("message");
        updateMessageField.setAccessible(true);
        updateMessageField.set(update, message);


        when(client.getAllLinks(anyLong())).thenReturn(linksList);
        SendMessage messageFromProcessor = messageProcessor.process(update);
        assertThat(messageText, equalTo(messageFromProcessor.getParameters().get("text")));
        assertThat(chatId, equalTo(messageFromProcessor.getParameters().get("chat_id")));
    }

    @Test
    public void processListCommand_emptyList() throws NoSuchFieldException, IllegalAccessException {
        Long chatId = 1234L;
        String emptyListMessage = "Нет отслеживаемых ссылок";
        ListLinksResponse response = new ListLinksResponse(new LinkResponse[0], 0);

        var message = new Message();
        var messageClass = message.getClass();
        var messageField = messageClass.getDeclaredField("text");
        messageField.setAccessible(true);
        messageField.set(message, "/list");


        var chat = new Chat();
        var chatField = Chat.class.getDeclaredField("id");
        chatField.setAccessible(true);
        chatField.set(chat, chatId);

        var messageChatField = messageClass.getDeclaredField("chat");
        messageChatField.setAccessible(true);
        messageChatField.set(message, chat);

        var update = new Update();
        var updateMessageField = Update.class.getDeclaredField("message");
        updateMessageField.setAccessible(true);
        updateMessageField.set(update, message);


        when(client.getAllLinks(chatId)).thenReturn(response);

        SendMessage messageFromProcessor = messageProcessor.process(update);

        assertThat(emptyListMessage, equalTo(messageFromProcessor.getParameters().get("text")));
        assertThat(chatId, equalTo(messageFromProcessor.getParameters().get("chat_id")));

    }
}
