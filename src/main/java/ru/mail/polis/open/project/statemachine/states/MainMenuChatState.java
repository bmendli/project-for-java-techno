package ru.mail.polis.open.project.statemachine.states;

import ru.mail.polis.open.project.Bot;
import ru.mail.polis.open.project.statemachine.ChatStateMachine;

import java.util.List;

public class MainMenuChatState implements ChatState {

    private final ChatStateMachine stateMachine;

    public MainMenuChatState(ChatStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public String update(
        String message,
        long chatId,
        List<String> buttonsNames
    ) {
        switch (message) {
            case Bot.MENU_COMMAND : {
                buttonsNames.addAll(getButtonsNames());
                return "Ты уже в главном меню!";
            } case Bot.WEATHER_COMMAND: {
                stateMachine.setState(new WeatherChatState(stateMachine));
                return null;
            } case Bot.NEWS_COMMAND: {
                stateMachine.setState(new NewsChatState(stateMachine));
                return null;
            } default : {
                return "У меня нет такой функции (";
            }
        }
    }

    @Override
    public String getInitialData(List<String> buttonsNames) {
        buttonsNames.addAll(getButtonsNames());
        return "Вы в главном меню";
    }

    private List<String> getButtonsNames() {
        return List.of(
            Bot.WEATHER_COMMAND,
            Bot.NEWS_COMMAND
        );
    }
}
