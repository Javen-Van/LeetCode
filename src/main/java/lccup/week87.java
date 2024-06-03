package lccup;

import java.util.Arrays;

public class week87 {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, months = new int[13];
        for (int i = 0; i < 12; i++) {
            months[i + 1] = months[i] + month[i];
        }
        String[] arriveA = arriveAlice.split("-"), leaveA = leaveAlice.split("-"), arriveB = arriveBob.split("-"), leaveB = leaveBob.split("-");
        int aA = months[Integer.parseInt(arriveA[0]) - 1] + Integer.parseInt(arriveA[1]), lA = months[Integer.parseInt(leaveA[0]) - 1] + Integer.parseInt(leaveA[1]);
        int aB = months[Integer.parseInt(arriveB[0]) - 1] + Integer.parseInt(arriveB[1]), lB = months[Integer.parseInt(leaveB[0]) - 1] + Integer.parseInt(leaveB[1]);
        return Math.max(0, Math.min(lA, lB) - Math.max(aA, aB));
    }

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int t = 0, res = 0;
        for (int player : players) {
            while (t < trainers.length && trainers[t] < player) t++;
            if (t == trainers.length) break;
            t++;
            res++;
        }
        return res;
    }
}
