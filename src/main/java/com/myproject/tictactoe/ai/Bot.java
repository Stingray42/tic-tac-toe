package com.myproject.tictactoe.ai;

/**
 * Описывает поведение бота.
 */
public interface Bot {
    /**
     * Обрабатывает предоставленное в виде строки игровое поле.
     *
     * @param field игровое поле в виде строки
     * @return состояние игры после хода бота
     */
    GameState play(String field);

    /**
     * Определяет лучшее место для хода бота исходя из текущей ситуации на игровом поле.
     *
     * @param field игровое поле в виде строки
     * @return место для хода бота
     */
    int getPlaceToStep(String field);
}