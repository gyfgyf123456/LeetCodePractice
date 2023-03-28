package org.xs.mianshi;

import java.util.*;

/**
 * 1. @ClassDescription:
 * 2. @author: xs
 * 3. @date: 2023年03月13日 19:49
 */
public class mianshi {


    public static void canArrange() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < t; i++) {
            String s = scanner.nextLine();
            if (isBaidu(s)) {
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }
        }
    }

    public static boolean isBaidu(String s) {
        int[] table = new int[55];
        String baidu = "Baidu";
        if (s.length() != 5) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'A'] += 1;
        }
        for (Character c : baidu.toCharArray()) {
            if (table[c - 'A'] != 1) {
                return false;
            }
        }
        return true;
    }

    public void getValue() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String RB = scanner.nextLine();
        int[][] connect = new int[RB.length()][RB.length()];
        for (int i = 0; i < n - 1; i++) {
            String uv = scanner.nextLine();
            int u = uv.charAt(0);
            int v = uv.charAt(2);
            connect[u][v] = 1;
        }
        LinkedList<Integer> queue1 = new LinkedList<>();
        LinkedList<Integer> queue2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (connect[i][j] == 1) {
                    queue1.offer(i);
                    queue2.offer(j);
                    connect[i][j] = 0;
                }
            }
        }
    }


    static int[][] nodes = new int[100001][2];
    static int[] dp = new int[100001];
    static int[] d = new int[100001];
    public static void dfs(int u) {
        int left = nodes[u][0];
        int right = nodes[u][1];
        if (left == -1 && right == -1) {
            dp[u] = 1;
            return;
        }
        if (left != -1) dfs(left);
        if (right != -1) dfs(right);
        if (left != -1 && right != -1 && dp[left] == dp[right] && dp[left] != 0) {
            dp[u] = dp[left] + 1;
        }else {
            dp[u] = 0;
        }
    }

    public static void getNums() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= n; i++) {
            nodes[i][0] = scanner.nextInt();
            nodes[i][1] = scanner.nextInt();
            if (nodes[i][0] != -1) d[nodes[i][0]]++;
            if (nodes[i][1] != -1) d[nodes[i][1]]++;
        }
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                root = i;
            }
        }
        dfs(root);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] != 0) ans++;
        }
        System.out.println(ans);
    }



    public static void getSubCollectionNums() {
        int mod = (int) Math.pow(10, 9) + 7;
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] ints = new int[size];
        scanner.nextLine();
        for (int i = 0; i < size; i++) {
            ints[i] = scanner.nextInt();
        }
        long result = 0L;
        Arrays.sort(ints);
        long[] dp = new long[size];
        dp[0] = 0;
        for (int i = 1; i < size; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                if (ints[i] % ints[j] == 0) {
                    sum += (dp[j] + 1) % mod;
                }
            }
            dp[i] = sum;
        }
        for (int i = 0; i < size; i++) {
            result += dp[i];
        }
        System.out.println(result % mod);
    }

    public static void getSubCollectionNums2() {
        long M = 1000000007;
        int[] a = new int[100010], pos = new int[1000010];
        Arrays.fill(pos, -1);
        long[] dp = new long[100010];
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a, 0, n);
        for(int i = 0; i < n; i++) {
            pos[a[i]] = i;
        }
        long ans = 0;
        for(int i = 0; i < n; i++) {
            int tmp = a[i];
            for(int j = 1; j * j <= tmp; j++) {
                if(tmp % j == 0) {
                    if(j != tmp / j && pos[tmp / j] > -1) {
                        dp[i] = (dp[i] + dp[pos[tmp / j]]) % M;
                    }
                    if(pos[j] > -1) {
                        dp[i] = (dp[i] + dp[pos[j]]) % M;
                    }
                }
            }
            ans += dp[i];
            ans %= M;
            dp[i]++;
        }
        System.out.println(ans);
    }

    public static void getEnemies() {
        int N = 1005;
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int[][] enemies = new int[N][N];
        int[][] preSum = new int[N][N];
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            enemies[scanner.nextInt()][scanner.nextInt()] = 1;
        }
        int max = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + enemies[i][j];
            }
        }
        for (int i = A + 1; i < N; i++) {
            for (int j = B + 1; j < N; j++) {
                int temp = preSum[i][j] - preSum[i][j - B - 1] - preSum[i - A - 1][j] + preSum[i - A - 1][j - B - 1];
                max = Math.max(max, temp);
            }
        }
        System.out.println(max);
    }


    
    public static void getMaxLen() {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine();
        int[] colors = new int[N];
        for (int i = 0; i < N; i++) {
            colors[i] = scanner.nextInt();
        }
        int max = 0;
        int dif = 0;
        HashMap<Integer, Integer> curColors = new HashMap<>();
        for (int i = 0 ,j = 0; i < N; i++) {
            curColors.put(colors[i], curColors.getOrDefault(colors[i], 0) + 1);
            if (curColors.get(colors[i]) == 1) {
                dif += 1;
            }
            while (dif > K) {
                curColors.put(colors[j], curColors.get(colors[j]) - 1);
                if (curColors.get(colors[j]) == 0) {
                    dif -= 1;
                }
                j++;
            }
            max = Math.max(max, i - j + 1);
        }
        System.out.println(max);
    }

    public static void getMinOps() {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        int a = 0,b = 0,c = 0;
        for(Character character : in.toCharArray()) {
            if (character == 'a') a++;
            if (character == 'b') b++;
            if (character == 'c') c++;
        }
        if (a == b + c) System.out.println(0);
        if (a > b + c) System.out.println((a- (b + c)) / 2);
        if (a < b + c) System.out.println(((b + c) - a) / 2);
    }

    static int[][] e;
    static int[] nodeColors;
    static int[] in;
    public static void getOps_changeColor() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String colors = scanner.nextLine();
        e = new int[n + 5][n + 5];
        nodeColors = new int[n + 5];
        in = new int[n + 5];
        for (int i = 1;i <= n;i++) {
            nodeColors[i] = colors.charAt(i - 1);
        }
        for (int i = 1; i <= n - 1; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            e[x][y] = 1;
            e[y][x] = 1;
            in[y]++;
        }
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (in[i] == 0) {
                root = i;
                break;
            }
        }
        dfs_changeColor(root);
    }

    private static void dfs_changeColor(int root) {

    }


    public static void getKSubNums() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        int i = 0, count = 0;
        int[] table = new int[n + 1];
        for (int j = 0; j < n; j++) {
            table[A[j]]++;
            while (table[A[j]] >= k) {
                count += n - j;
                table[A[i]]--;
                i++;
            }
        }
        System.out.println(count);
    }

    public static void getP() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer>[] queues_order = new LinkedList[5];
        for (int i = 0; i < 5; i++) {
            queues_order[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            if (s.equals("p")) {
                for (int j = 0; j < 5; j++) {
                    if (!queues_order[j].isEmpty()) {
                        System.out.println(queues_order[j].poll());
                        break;
                    }
                }
            }else {
                int x = sc.nextInt();
                int y = sc.nextInt();
                queues_order[y - 1].offer(x);
            }
        }
    }

    public static void divide() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int minWeight = 1000002;
        int[] sugar = new int[n];
        for (int i = 0; i < n; i++) {
            sugar[i] = sc.nextInt();
        }
        int oxr = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            oxr ^= sugar[i];
            minWeight = Math.min(minWeight, sugar[i]);
            sum += sugar[i];
        }
        if (oxr != 0) {
            System.out.println("NO");
        }else {
            System.out.println(sum - minWeight);
        }
    }

    public static void main(String[] args) {
        //System.out.println(getNums());
        //getSubCollectionNums2();
        //getEnemies();
        //getMaxLen();
        getP();
    }


}
