package edu.railsync;

import java.util.*;

/** String-search algorithms used for train, station, ticket and alert text. */
public final class StringAlgorithms {
    private StringAlgorithms() {}

    public static List<Integer> kmp(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (pattern.isEmpty()) { for (int i = 0; i <= text.length(); i++) matches.add(i); return matches; }
        int[] lps = new int[pattern.length()];
        for (int i = 1, len = 0; i < pattern.length();) {
            if (pattern.charAt(i) == pattern.charAt(len)) lps[i++] = ++len;
            else if (len != 0) len = lps[len - 1];
            else lps[i++] = 0;
        }
        for (int i = 0, j = 0; i < text.length();) {
            if (text.charAt(i) == pattern.charAt(j)) { i++; j++; }
            if (j == pattern.length()) { matches.add(i - j); j = lps[j - 1]; }
            else if (i < text.length() && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) j = lps[j - 1]; else i++;
            }
        }
        return matches;
    }

    public static int[] zFunction(String s) {
        int n = s.length(); int[] z = new int[n]; int left = 0, right = 0;
        for (int i = 1; i < n; i++) {
            if (i <= right) z[i] = Math.min(right - i + 1, z[i - left]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
            if (i + z[i] - 1 > right) { left = i; right = i + z[i] - 1; }
        }
        if (n > 0) z[0] = n;
        return z;
    }

    public static List<Integer> rabinKarp(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0) { for (int i = 0; i <= n; i++) result.add(i); return result; }
        if (m > n) return result;
        final long mod = 1_000_000_007L, base = 257L;
        long target = 0, window = 0, power = 1;
        for (int i = 0; i < m; i++) {
            target = (target * base + pattern.charAt(i)) % mod;
            window = (window * base + text.charAt(i)) % mod;
            if (i < m - 1) power = (power * base) % mod;
        }
        for (int i = 0; i <= n - m; i++) {
            if (target == window && text.regionMatches(i, pattern, 0, m)) result.add(i);
            if (i < n - m) {
                window = (window - (text.charAt(i) * power) % mod + mod) % mod;
                window = (window * base + text.charAt(i + m)) % mod;
            }
        }
        return result;
    }

    /** A compact Aho-Corasick implementation for lowercase/uppercase English text. */
    public static Map<String, List<Integer>> ahoCorasick(String text, List<String> patterns) {
        class Node {
            Map<Character, Node> next = new HashMap<>();
            Node fail;
            List<String> output = new ArrayList<>();
        }
        Node root = new Node(); root.fail = root;
        for (String p : patterns) {
            Node cur = root;
            for (char ch : p.toLowerCase(Locale.ROOT).toCharArray())
                cur = cur.next.computeIfAbsent(ch, k -> new Node());
            cur.output.add(p);
        }
        Queue<Node> q = new ArrayDeque<>();
        for (Node child : root.next.values()) { child.fail = root; q.add(child); }
        while (!q.isEmpty()) {
            Node v = q.remove();
            for (var edge : v.next.entrySet()) {
                char ch = edge.getKey(); Node u = edge.getValue(); Node f = v.fail;
                while (f != root && !f.next.containsKey(ch)) f = f.fail;
                if (f.next.containsKey(ch) && f.next.get(ch) != u) u.fail = f.next.get(ch);
                else u.fail = root;
                u.output.addAll(u.fail.output); q.add(u);
            }
        }
        Map<String, List<Integer>> found = new LinkedHashMap<>();
        for (String p : patterns) found.put(p, new ArrayList<>());
        Node cur = root;
        String lower = text.toLowerCase(Locale.ROOT);
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            while (cur != root && !cur.next.containsKey(ch)) cur = cur.fail;
            cur = cur.next.getOrDefault(ch, root);
            for (String p : cur.output) found.get(p).add(i - p.length() + 1);
        }
        return found;
    }

    public static void demo() {
        String text = RailwayData.ALERT_TEXT;
        String pattern = "12627";
        System.out.println("Sample alert text: " + text);
        System.out.println("KMP positions for '" + pattern + "': " + kmp(text, pattern));
        System.out.println("Rabin-Karp positions for 'SERVICE ALERT': " + rabinKarp(text, "SERVICE ALERT"));
        System.out.println("Z-function for 'traintrainx': " + Arrays.toString(zFunction("traintrainx")));
        System.out.println("Aho-Corasick keyword matches: " +
            ahoCorasick(text, List.of("delayed", "platform", "maintenance")));
    }
}
