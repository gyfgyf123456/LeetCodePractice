package org.xs.HashTable;

public class hashTableQuestion {

    public hashTableQuestion() {
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int sTable[] = new int[26];
        int tTable[] = new int[26];
        char ac[] = s.toCharArray();
        int j = ac.length;
        for (int k = 0; k < j; k++) {
            Character c = Character.valueOf(ac[k]);
            sTable[c.charValue() - 97]++;
        }

        ac = t.toCharArray();
        j = ac.length;
        for (int l = 0; l < j; l++) {
            Character c = Character.valueOf(ac[l]);
            tTable[c.charValue() - 97]++;
        }

        for (int i = 0; i < sTable.length; i++)
            if (sTable[i] != tTable[i])
                return false;

        return true;
    }

    public void isAnagramtest() {
        System.out.println(isAnagram("aacc", "ccac"));
    }
}
