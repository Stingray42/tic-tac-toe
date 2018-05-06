package com.myproject.tictactoe.ai.pattern;

import com.myproject.tictactoe.ai.BotImpl;

/**
 * Алгоритм поиска по регулярным выражениям для определения наилучшего места хода.
 */
public class PatternBot extends BotImpl {
    /**
     * Регулярные выражения, соответствующие всем комбинациям на игровом поле,
     * которым не хватает одного хода для победы.
     */
    private final static String[] PATTERNS = {
            "#@@......", "@#@......", "@@#......",
            "...#@@...", "...@#@...", "...@@#...",
            "......#@@", "......@#@", "......@@#",
            "#..@..@..", "@..#..@..", "@..@..#..",
            ".#..@..@.", ".@..#..@.", ".@..@..#.",
            "..#..@..@", "..@..#..@", "..@..@..#",
            "#...@...@", "@...#...@", "@...@...#",
            "..#.@.@..", "..@.#.@..", "..@.@.#.."};

    /**
     * Определяет лучшее место для хода бота исходя из текущей ситуации на игровом поле.
     *
     * @param field игровое поле в виде строки
     * @return  место для завершения выигрышнной комбинации, если это возможно,
     *          место, в которое должен совершить ход игрок, если он может выиграть,
     *          случайное свободное место на поле, если ни один из предыдущих вариантов не подходит.
     */
    @Override
    public int getPlaceToStep(String field) {
        String pattern;
        int proposedPlace;

        switch (getCondition(field)) {
            case 1:
                pattern = getMatchingPattern(field, mySymbol);
                return pattern.indexOf('#');
            case 2:
                pattern = getMatchingPattern(field, enemySymbol);
                return pattern.indexOf('#');
            default:
                while (true) {
                    proposedPlace = (int) (Math.random() * field.length());
                    if (field.charAt(proposedPlace) == EMPTY) {
                        return proposedPlace;
                    }
                }
        }
    }

    /**
     * Определяет ситуацию на игровом поле
     *
     * @param field игровое поле в виде строки
     * @return  <code>1</code>, если бот может выиграть игру следующим ходом,
     *          <code>2</code>, если игрок может выиграть игру следующим ходом,
     *          <code>0</code> во всех остальных случаях.
     */
    private int getCondition(String field) {
        for (String pattern : PATTERNS) {
            if (field.matches(pattern.replace('@', mySymbol).replace('#', EMPTY))) {
                return 1;
            }
        }
        for (String pattern : PATTERNS) {
            if (field.matches(pattern.replace('@', enemySymbol).replace('#', EMPTY))) {
                return 2;
            }
        }
        return 0;
    }

    /**
     * Определяет регулярное выражение, соответствующее ситуации на игровом поле.
     *
     * @param field игровое поле в виде строки
     * @param symbol проверяемый символ игрока или бота
     * @return регулярное выражение из списка возможных комбинаций
     */
    private String getMatchingPattern(String field, char symbol) {
        for (String pattern : PATTERNS) {
            if (field.matches(pattern.replace('@', symbol).replace('#', EMPTY))) {
                return pattern;
            }
        }
        return "";
    }
}