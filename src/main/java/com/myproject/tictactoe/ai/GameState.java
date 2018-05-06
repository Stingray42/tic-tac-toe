package com.myproject.tictactoe.ai;

/**
 * Отображает текущее состояние игры и последний ход бота.
 * Если игра окончена, то также содержит номер выигрышной комбинации.
 */
public class GameState {
    private GameStatus status;
    private int place;
    private int winningIndex;

    /**
     * Стандартный конструктор.
     *
     * @see GameState#GameState(GameStatus, int, int)
     */
    public GameState() {
    }

    /**
     * Конструктор с заданием значений всех полей.
     *
     * @param status состояние игры
     * @param place  место последнего хода
     * @param winningIndex  номер выигрышной комбинации
     * @see GameState#GameState()
     * @see GameStatus
     */
    public GameState(GameStatus status, int place, int winningIndex) {
        this.status = status;
        this.place = place;
        this.winningIndex = winningIndex;
    }

    /** Возвращает текущее состояние игры. */
    public GameStatus getStatus() {
        return status;
    }

    /** Устанавливает текущее состояние игры. */
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    /** Возвращает место последнего хода бота. */
    public int getPlace() {
        return place;
    }

    /** Устанавливает место последнего хода бота. */
    public void setPlace(int place) {
        this.place = place;
    }

    /** Возвращает номер выигрышной комбинации. */
    public int getWinningIndex() {
        return winningIndex;
    }

    /** Устанавливает номер выигрышной комбинации. */
    public void setWinningIndex(int winningIndex) {
        this.winningIndex = winningIndex;
    }

    /**
     * Перечисление возможных вариантов состояния игры.
     * <ul>
     * <li>{@link #WIN}</li>
     * <li>{@link #LOSS}</li>
     * <li>{@link #DRAW}</li>
     * <li>{@link #CONTINUE}</li>
     * </ul>
     */
    public enum GameStatus {
        /** Победа игрока. */
        WIN,
        /** Поражение игрока. */
        LOSS,
        /** Ничья. */
        DRAW,
        /** Игра не окончена. */
        CONTINUE
    }
}
