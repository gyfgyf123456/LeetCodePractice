package org.xs.Series;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年02月09日 15:13
 */
class AuthenticationManager {

    int timeToLive = 0;

    HashMap<String, Integer> authenticationList = new HashMap<>();

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void generate(String tokenId, int currentTime) {
        this.authenticationList.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        if (authenticationList.size() == 0) {

        }
        else if (authenticationList.containsKey(tokenId) && currentTime - authenticationList.get(tokenId) < timeToLive) {
            authenticationList.put(tokenId, currentTime);
        }
    }

//    public int countUnexpiredTokens(int currentTime) {
//        int count = 0;
//        for (Map.Entry<String, Integer> entry : authenticationList.entrySet()) {
//            if (currentTime - entry.getValue() < timeToLive) {
//                count++;
//            }
//        }
//        return count;
//    }

    public int countUnexpiredTokens(int currentTime) {
        Iterator<Map.Entry<String, Integer>> iterator = authenticationList.entrySet().iterator();
        while (iterator.hasNext()) {
            if (currentTime - iterator.next().getValue() >= timeToLive) {
                iterator.remove();
            }
        }
        return authenticationList.size();
    }
}
