package ru.tinkoff.edu.java.bot.telegram.bot;

import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.List;


public interface Bot extends AutoCloseable, UpdatesListener {


    @Override
    int process(List<Update> updates);

    @Override
    void close();
}
