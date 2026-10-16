package edu.railsync;

import java.util.*;

/** Educational suffix indexing tools. The suffix array is built by straightforward sorting. */
public final class SuffixStructures {
    private SuffixStructures() {}

    public static int[] suffixArray(String s) {
        Integer[] order = new Integer[s.length()];
        for (int i = 0; i < s.length(); i++) order[i] = i;
        Arrays.sort(order, Comparator.comparing(s::substring));
        return Arrays.stream(order).mapToInt(Integer::intValue).toArray();
    }

    public static int[] kasaiLcp(String s, int[] sa) {
        int n = s.length(); int[] rank = new int[n], lcp = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r == 0) continue;
            int j = sa[r - 1];
            while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
            lcp[r] = h;
            if (h > 0) h--;
        }
        return lcp;
    }

    /** Suffix automaton membership test. */
    public static boolean containsSubstring(String text, String query) {
        class State {
            int link = -1, length = 0;
            Map<Character, Integer> next = new HashMap<>();
        }
        List<State> states = new ArrayList<>(); states.add(new State());
        int last = 0;
        for (char ch : text.toCharArray()) {
            int cur = states.size(); states.add(new State()); states.get(cur).length = states.get(last).length + 1;
            int p = last;
            while (p != -1 && !states.get(p).next.containsKey(ch)) {
                states.get(p).next.put(ch, cur); p = states.get(p).link;
            }
            if (p == -1) states.get(cur).link = 0;
            else {
                int q = states.get(p).next.get(ch);
                if (states.get(p).length + 1 == states.get(q).length) states.get(cur).link = q;
                else {
                    int clone = states.size(); State copied = new State();
                    copied.length = states.get(p).length + 1;
                    copied.link = states.get(q).link; copied.next.putAll(states.get(q).next);
                    states.add(copied);
                    while (p != -1 && Objects.equals(states.get(p).next.get(ch), q)) {
                        states.get(p).next.put(ch, clone); p = states.get(p).link;
                    }
                    states.get(q).link = states.get(cur).link = clone;
                }
            }
            last = cur;
        }
        int state = 0;
        for (char ch : query.toCharArray()) {
            Integer next = states.get(state).next.get(ch);
            if (next == null) return false;
            state = next;
        }
        return true;
    }

    public static void demo() {
        String text = RailwayData.DOCUMENT_TEXT;
        int[] sa = suffixArray(text);
        int[] lcp = kasaiLcp(text, sa);
        System.out.println("Document text: " + text);
        System.out.println("Suffix array positions: " + Arrays.toString(sa));
        System.out.println("Kasai LCP array:       " + Arrays.toString(lcp));
        System.out.println("Substring 'service' exists: " + containsSubstring(text, "service"));
        System.out.println("Substring 'platform' exists: " + containsSubstring(text, "platform"));
        System.out.println("Note: SA-IS is discussed in the report; this demo uses a clear suffix-sort implementation.");
    }
}
