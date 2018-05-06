package com.myproject.tictactoe.ai.minimax;

import com.myproject.tictactoe.ai.BotImpl;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Алгоритм минимакса для определения наилучшего места хода.
 */
public class MinimaxBot extends BotImpl {

    @Override
    public int getPlaceToStep(String field) {
        Move bestMove = minimax(null, field, true, 0);
        return bestMove.getIndex();
    }

    /**
     * Сам алгоритм минимакса. Принимает решение на основе минимизации возможных потерь из тех,
     * которые боту нельзя предотвратить при развитии событий по наихудшему для него сценарию.
     *
     * @param lastMove последний совершенный ход
     * @param field игровое поле в виде строки
     * @param isMyTurn true если проверяется ход бота,
     *                 false если проверяется ход игрока
     * @param depth текущая глубина проверки
     * @return  последний совершенный ход, если игра на текущем поле завершилась,
     *          ход с наибольшим количеством очков, если проверяется ход бота,
     *          ход с наименьшим количеством очков, если проверяется ход игрока
     * @see Move
     */
    private Move minimax(Move lastMove, String field, boolean isMyTurn, int depth) {
        int score = calculateScore(field, depth);
        char stepSymbol = (isMyTurn) ? mySymbol : enemySymbol;

        if (score != 0) {
            lastMove.setScore(score);
            return lastMove;
        } else {
            ArrayList<Move> possibleMoves = getEmptySpaces(field);
            Move bestMove;

            if (possibleMoves.isEmpty()) {
                lastMove.setScore(0);
                return lastMove;
            }

            for (Move move : possibleMoves) {
                String newField = doStep(field, stepSymbol, move.getIndex());
                int newScore = minimax(move, newField, !isMyTurn, depth + 1).getScore();
                move.setScore(newScore);
            }

            if (isMyTurn) {
                bestMove = Collections.max(possibleMoves);
            } else {
                bestMove = Collections.min(possibleMoves);
            }
            return bestMove;
        }
    }

    /**
     * Подсчитывает количество очков, которое набрал бот на заданном игрового поле.
     * Также, для улучшения работы алгоритма, при подсчете очков используется текущая глубина.
     *
     * @param field игровое поле в виде строки
     * @param depth текущая глубина проверки
     * @return 10 - depth, если бот выиграл,
     *         -10 + depth, если бот проиграл,
     *         0 если игра завершилась ничьей.
     */
    protected int calculateScore(String field, int depth) {
        if (winning(field, mySymbol)) {
            return +10 - depth;
        } else if (winning(field, enemySymbol)) {
            return -10 + depth;
        } else {
            return 0;
        }
    }

    /**
     * Возвращает доступные для хода ячейки поля
     *
     * @param field игровое поле в виде строки
     * @return доступные для хода ячейки поля
     * @see Move
     */
    protected ArrayList<Move> getEmptySpaces(String field) {
        ArrayList<Move> emptySpaces = new ArrayList<>();
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == EMPTY) {
                emptySpaces.add(new Move(i, 0));
            }
        }
        return emptySpaces;
    }
}
