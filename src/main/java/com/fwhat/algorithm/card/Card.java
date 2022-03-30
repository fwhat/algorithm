package com.fwhat.algorithm.card;

public class Card {
    public static void main(String[] args) {
        System.out.println(longChain("3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A", "4-5-6-7-8-8-8"));
        System.out.println(longChain("3-3-3-3-4-4-5-5-6-7-9-9-9-9-10-J-Q-K-A-A-A", "4-5-6-7-8-8-8-K-K"));
    }

    /**
     * 【最长的顺子】斗地主起源于湖北十堰房县，据说是一位叫吴修全的年轻人根据当地流行的扑克玩法“跑得快”改编的，如今已风靡整个中国，并流行于互联网上。
     * 牌型:单顺，又称顺子，最少5张牌，最多12张牌(3...A)不能有2，也不能有大小王，不计花色。
     * 例如 3-4-5-6-7-8，7-8-9-10-J-Q，3-4-5-6-7-8-9-10-J-Q-K-A
     * 可用的牌 3<4<5<6<7<8<9<10<J<Q<K<A<2<B(小王)<C(大王)，每种牌除大小王外有四种花色(共有13*4+2张牌)
     * 输入：1、手上有的牌 2、已经出过的牌(包括对手出的和自己出的牌)
     * 输出：对手可能构成的最长的顺子(如果有相同长度的顺子，输出牌面最大的那个那一个)，如果无法构成顺子，则输出 NO-CHAIN
     * 输入描述:
     * 输入的第一行为当前手中的牌
     * 输入的第二行为已经出过的牌
     * 输出描述:
     * 最长的顺子
     * 示例:
     * 输入
     * 3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A
     * 4-5-6-7-8-8-8
     * 输出
     * 9-10-J-Q-K-A
     */
    public static String longChain(String myCards, String usedCards) {
        // 总数 忽略大小王+2 12 *4 [3 ~ A] J->11 Q->12 K->13 A->14
        int[] cards = new int[15];

        if (usedCards != null && usedCards.length() != 0) {
            myCards = myCards + "-" + usedCards;
        }

        String[] myCardArr = myCards.split("-");
        for (String s : myCardArr) {
            int num = 0;
            switch (s) {
                case "J":
                    num = 11;
                    break;
                case "Q":
                    num = 12;
                    break;
                case "K":
                    num = 13;
                    break;
                case "A":
                    num = 14;
                    break;
                case "B":
                case "C":
                    break;
                default:
                    num = Integer.parseInt(s);
            }
            cards[num]++;
        }

        // 存储可以成功的顺子 // 顺子
        StringBuilder maxChain = new StringBuilder();
        StringBuilder tempChain = new StringBuilder();
        for (int i = 3; i < 15; i++) {
            if (cards[i] < 4) {
                String card;
                switch (i) {
                    case 11:
                        card = "J";
                        break;
                    case 12:
                        card = "Q";
                        break;
                    case 13:
                        card = "K";
                        break;
                    case 14:
                        card = "A";
                        break;
                    default:
                        card = Integer.toString(i);
                }

                if (tempChain.length() != 0) {
                    tempChain.append("-");
                }
                tempChain.append(card);
            } else {
                if (tempChain.length() >= 5 && tempChain.length() >= maxChain.length()) {
                    maxChain = tempChain;
                }
                tempChain = new StringBuilder();
            }
        }

        if (tempChain.length() >= 5 && tempChain.length() >= maxChain.length()) {
            maxChain = tempChain;
        }

        if (maxChain.length() == 0) {
            return "NO-CHAIN";
        }
        return maxChain.toString();
    }
}
