package com.myproject.tictactoe.ai.minimax;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Алгоритм альфа-бета отсечения для определения наилучшего места хода.
 */
public class AlphaBetaPruningBot extends MinimaxBot {

    @Override
    public int getPlaceToStep(String field) {
        Move bestMove = alphaBeta(null, field, Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0);
        return bestMove.getIndex();
    }

    /**
     * Сам алгоритм альфа-бета отсечения. Представляет собой оптимизированный алгоритм минимакса,
     * при котором оценивание ветви дерева поиска может быть досрочно прекращено
     * (без вычисления всех значений оценивающей функции), если было найдено, что для этой ветви значение
     * оценивающей функции в любом случае хуже, чем вычисленное для предыдущей ветви.
     *
     * @param lastMove последний совершенный ход
     * @param field игровое поле в виде строки
     * @param a альфа
     * @param b бета
     * @param isMyTurn <p>true если проверяется ход бота,
     *                 <p>false если проверяется ход игрока
     * @param depth текущая глубина проверки
     * @return  последний совершенный ход, если игра на текущем поле завершилась,
     *          ход с наибольшим количеством очков, если проверяется ход бота,
     *          ход с наименьшим количеством очков, если проверяется ход игрока
     */
    private Move alphaBeta(Move lastMove, String field, int a, int b, boolean isMyTurn, int depth){
        int score = calculateScore(field, depth);
        char stepSymbol = (isMyTurn) ? mySymbol : enemySymbol;

        if (score != 0) {
            lastMove.setScore(score);
            return lastMove;
        } else {
            ArrayList<Move> possibleMoves = getEmptySpaces(field);
            Move bestMove;
            int newScore;

            if (possibleMoves.isEmpty()) {
                lastMove.setScore(0);
                return lastMove;
            }

            if (isMyTurn) {
                for (Move move : possibleMoves) {
                    String newField = doStep(field, stepSymbol, move.getIndex());
                    newScore = alphaBeta(move, newField, a, b, false, depth + 1).getScore();
                    move.setScore(newScore);
                    a = max(a, newScore);
                    if (a >= b) break;
                }
                bestMove = Collections.max(possibleMoves);
            } else {
                for (Move move : possibleMoves) {
                    String newField = doStep(field, stepSymbol, move.getIndex());
                    newScore = alphaBeta(move, newField, a, b, true, depth + 1).getScore();
                    move.setScore(newScore);
                    b = min(b, newScore);
                    if (a >= b) break;
                }
                bestMove = Collections.min(possibleMoves);
            }
            return bestMove;
        }
    }

}
