/*
 * InputManager.java
 *
 * version 1.0
 *
 * 20191215
 *
 * copyright by jiwonSong(s26788761@naver.com)
 *
 */

package domain.game;

import domain.user.Player;

import java.util.*;

public class InputManager {
    private static int MINIMUM_NAME_COUNT = 1;
    private static int MINIMUM_NAME_LENGTH = 1;
    public static String YES = "y";
    public static String NO = "n";

    Message message = new Message();
    Scanner scanner = new Scanner(System.in);

    public ArrayList<String> getPlayerNames() {
        String[] nameArray;

        do {
            System.out.println(message.makeMessageAskPlayerNames());
            nameArray = scanner.nextLine().split(",");
            trimWhiteSpace(nameArray);
        } while (checkNamesValid(nameArray));

        return makeArrayToArrayList(nameArray);
    }

    public boolean checkNamesValid(String[] nameArray) {
        if (checkNamesCountValid(nameArray)
                && checkNamesLengthValid(nameArray)
                && !checkNamesDuplicated(makeArrayToArrayList(nameArray))) {
            return true;
        }
        return false;
    }

    public ArrayList<String> makeArrayToArrayList(String[] input) {
        return new ArrayList<>(Arrays.asList(input));
    }

    public void trimWhiteSpace(String[] nameArray) {

        for (int i = 0; i < nameArray.length; i++) {
            nameArray[i] = nameArray[i].trim();
        }
    }

    public boolean checkNamesCountValid(String[] nameArray) {
        if (nameArray.length < MINIMUM_NAME_COUNT) {
            //플레이어가 입력되지 않았습니다 에러 출력
            return false;
        }
        return true;
    }

    public boolean checkNamesLengthValid(String[] nameArray) {
        boolean isValid = true;

        for (String string : nameArray) {
            isValid &= checkNameLengthValid(string);
        }
        if (!isValid) {
            //문자열 길이 에러 메시지 출력
        }
        return isValid;
    }

    public boolean checkNameLengthValid(String input) {
        if (input.length() < MINIMUM_NAME_LENGTH) {
            return false;
        }
        return true;
    }

    public boolean checkNamesDuplicated(ArrayList<String> names) {
        boolean isDuplicated = false;

        for (String name : names) {
            isDuplicated |= listContainsString(names, name);
        }
        if (isDuplicated) {
            //이름 중복 에러 메시지 출
        }
        return isDuplicated;
    }

    public boolean listContainsString(ArrayList<String> list, String string) {
        if (list.contains(string)) {
            return true;
        }
        return false;
    }

    public double getPlayerBattingMoney(Player player) {
        String battingMoneyString;

        do {
            System.out.println(message.makeMessageAskPlayerBattingAmout(player));
            battingMoneyString = scanner.nextLine().trim();
        } while (checkBattingMoneyValid(battingMoneyString));

        return Double.parseDouble(battingMoneyString);
    }

    public boolean checkBattingMoneyValid(String battingMoneyString) {
        if (!checkBattingMoneyContainsChar(battingMoneyString)
                && !checkBattingMoneyIsMinus(battingMoneyString)
                && !checkBattingMoneyIsZero(battingMoneyString)) {
            return true;
        }
        return false;
    }

    public boolean checkBattingMoneyContainsChar(String battingMoneyString) {
        try {
            Integer.parseInt(battingMoneyString);
        } catch (NumberFormatException e) {
            //입력한 문자열에 문자 포함 에러 메시지 출력
            return true;
        }
        return false;
    }

    public boolean checkBattingMoneyIsMinus(String battingMoneyString) {
        if (Integer.parseInt(battingMoneyString) < 0) {
            //입력한 베팅금액이 음수 에러 메시지 출력
            return true;
        }
        return false;
    }

    public boolean checkBattingMoneyIsZero(String battingMoneyString) {
        if (Integer.parseInt(battingMoneyString) == 0) {
            //입력한 베팅금액이 0 에러 메시지 출력
            return true;
        }
        return false;
    }

    public boolean getPlayerChoiceDrawOneMoreOrNot(Player player) {
        String playerChoice;

        do {
            System.out.println(player.getName() + message.makeMessagePlayerWantMoreCard(player));
            playerChoice = scanner.nextLine().trim();
        } while (!checkPlayerInputIsYesOrNo(playerChoice));

        if (playerChoice == YES) {
            return true;
        }
        return false;
    }

    public boolean checkPlayerInputIsYesOrNo(String playerChoice) {
        if ((playerChoice == YES) || (playerChoice == NO)) {
            return true;
        }
        //y, n 중 하나가 입력되지 않았습니다. 에러 메시지 출력
        return false;
    }
}
