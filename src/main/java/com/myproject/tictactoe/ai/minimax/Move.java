package com.myproject.tictactoe.ai.minimax;

/**
 * Отображает ход с определенным количеством очков для использования в алгоритме минимакса
 */
public class Move implements Comparable<Move> {
    private int index;
    private int score = 0;

    /**
     * Стандартный конструктор.
     *
     * @see Move#Move(int, int)
     */
    public Move() {
    }

    /**
     * Конструктор с заданием значений всех полей.
     *
     * @param index место хода
     * @param score количество очков для данного хода
     */
    public Move(int index, int score) {
        this.index = index;
        this.score = score;
    }

    /** Возвращает место данного хода. */
    public int getIndex() {
        return index;
    }

    /** Устанавливает место данного хода. */
    public void setIndex(int index) {
        this.index = index;
    }

    /** Возвращает количество очков данного хода. */
    public int getScore() {
        return score;
    }

    /** Устанаваливает количество очков данного хода. */
    public void setScore(int score) {
        this.score = score;
    }

    /** Возвращает строковое представление хода. */
    @Override
    public String toString() {
        return "Move{" +
                "index=" + index +
                ", score=" + score +
                '}';
    }

    /** Сравнивает ходы по количеству очков. */
    @Override
    public int compareTo(Move move) {
        return (this.score - move.score);
    }
}
