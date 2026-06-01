# 📌 30. Substring with Concatenation of All Words

---

# 📝 Problem Statement

You are given a string `s` and an array of strings `words`. All strings in `words` are of the same length.

A concatenated substring in `s` is a substring that contains all the strings of any permutation of `words` concatenated.

For example, if `words = ["ab","cd","ef"]`, then `"abcdef"`, `"abefcd"`, `"cdabef"`, `"cdefab"`, `"efabcd"`, and `"efcdab"` are all concatenated strings. `"acdbef"` is not a concatenated substring because it is not the concatenation of any permutation of `words`.

Return the starting indices of all the concatenated substrings in `s`. You can return the answer in any order.

**Constraints:**
- `1 <= s.length <= 10^4`
- `1 <= words.length <= 5000`
- `1 <= words[i].length <= 30`
- `s` and `words[i]` consist of lowercase English letters.

---

# 💡 Intuition

The problem requires finding all starting indices in string `s` where a concatenation of all words from the `words` array appears as a substring. The key insight is that we need to check all possible starting positions in `s` where a concatenated substring of all words could begin, and verify if the substring at that position contains all words exactly once.

The optimal approach involves using a sliding window technique combined with hash maps to efficiently track word frequencies and maintain the window state. This allows us to check for valid substrings in linear time relative to the length of `s`.

---

# 🐌 Brute Force Approach

## 🔹 Approach

The brute force approach involves checking every possible starting position in `s` where a concatenated substring of all words could begin. For each starting position, we extract substrings of length equal to the total length of all words concatenated, split them into individual words, and check if the frequency of each word matches the frequency in the `words` array.

## 🔹 Algorithm

1. Calculate the total length of all words concatenated (`totalLen`).
2. Iterate over each possible starting index `i` in `s` from `0` to `s.length() - totalLen`.
3. For each starting index `i`, extract the substring `s[i..i+totalLen-1]`.
4. Split the substring into words of length equal to the length of words in the `words` array.
5. Count the frequency of each word in the substring.
6. Compare the frequency map of the substring with the frequency map of the `words` array.
7. If they match, add the starting index `i` to the result list.

## 🔹 Code

```java
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;
        int totalLength = wordLength * totalWords;

        if (s.length() < totalLength) {
            return result;
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLength; i++) {
            String substring = s.substring(i, i + totalLength);
            Map<String, Integer> seenWords = new HashMap<>();
            boolean valid = true;

            for (int j = 0; j < totalWords; j++) {
                String currentWord = substring.substring(j * wordLength, (j + 1) * wordLength);
                if (!wordCount.containsKey(currentWord)) {
                    valid = false;
                    break;
                }
                seenWords.put(currentWord, seenWords.getOrDefault(currentWord, 0) + 1);
                if (seenWords.get(currentWord) > wordCount.get(currentWord)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                result.add(i);
            }
        }

        return result;
    }
}
```

## 🔹 Dry Run

Let's dry run the brute force approach with `s = "barfoothefoobarman"` and `words = ["foo","bar"]`.

1. **Initialization:**
   - `wordLength = 3`, `totalWords = 2`, `totalLength = 6`.
   - `wordCount = {"foo": 1, "bar": 1}`.

2. **Iteration over starting indices:**
   - **i = 0:**
     - Substring: "barfoo".
     - Split into ["bar", "foo"].
     - `seenWords = {"bar": 1, "foo": 1}`.
     - Valid substring, add `0` to result.
   - **i = 1:**
     - Substring: "arfoot".
     - Split into ["arf", "oot"].
     - "arf" not in `wordCount`, invalid.
   - **i = 2:**
     - Substring: "rfooth".
     - Split into ["rfo", "oth"].
     - "rfo" not in `wordCount`, invalid.
   - **i = 3:**
     - Substring: "foothe".
     - Split into ["foo", "the"].
     - "the" not in `wordCount`, invalid.
   - **i = 4:**
     - Substring: "othefo".
     - Split into ["oth", "efo"].
     - "oth" not in `wordCount`, invalid.
   - **i = 5:**
     - Substring: "thefoo".
     - Split into ["the", "foo"].
     - "the" not in `wordCount`, invalid.
   - **i = 6:**
     - Substring: "hefoob".
     - Split into ["hef", "oob"].
     - "hef" not in `wordCount`, invalid.
   - **i = 7:**
     - Substring: "efooba".
     - Split into ["efo", "oba"].
     - "efo" not in `wordCount`, invalid.
   - **i = 8:**
     - Substring: "foobar".
     - Split into ["foo", "bar"].
     - `seenWords = {"foo": 1, "bar": 1}`.
     - Valid substring, add `8` to result.
   - **i = 9:**
     - Substring: "oobarm".
     - Split into ["oob", "arm"].
     - "oob" not in `wordCount`, invalid.
   - **i = 10:**
     - Substring: "obarma".
     - Split into ["obar", "rman"].
     - "obar" not in `wordCount`, invalid.

3. **Result:**
   - `result = [0, 8]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O((n - m) * m * k) where `n` is the length of `s`, `m` is the total length of all words, and `k` is the number of words. |
| Space Complexity | O(k) for storing the frequency map of words. |

---

# ⚡ Optimal Approach

## 🔹 Approach

The optimal approach uses a sliding window technique combined with hash maps to efficiently track word frequencies and maintain the window state. This approach reduces the time complexity by avoiding the repeated splitting and counting of words in the brute force method.

1. **Initialization:**
   - Create a frequency map `wordCount` for the words in the `words` array.
   - Calculate the length of each word (`wordLength`) and the total number of words (`totalWords`).
   - Calculate the total length of the concatenated substring (`totalLength`).

2. **Sliding Window:**
   - Iterate over each possible starting position in `s` where a concatenated substring could begin.
   - For each starting position, use a sliding window to check if the substring contains all words exactly once.
   - Use a frequency map `windowCount` to keep track of the words in the current window.
   - Adjust the window by moving the left pointer when a word exceeds its frequency in `wordCount`.

3. **Validation:**
   - If the window contains all words with the correct frequencies, add the starting index to the result list.

## 🔹 Why This Works

The sliding window approach efficiently checks for valid substrings by maintaining a window of words and adjusting it dynamically. This ensures that we only check valid substrings and avoid unnecessary computations.

## 🔹 Algorithm

1. Initialize `wordCount` with the frequency of each word in `words`.
2. Calculate `wordLength`, `totalWords`, and `totalLength`.
3. Iterate over each possible starting index `i` in `s` from `0` to `s.length() - totalLength`.
4. For each starting index `i`, initialize `windowCount` and `count` to `0`.
5. Iterate over the substring starting at `i` with length `totalLength`, processing words of length `wordLength`.
6. For each word in the substring:
   - If the word is in `wordCount`, update `windowCount` and `count`.
   - If the word's frequency in `windowCount` exceeds its frequency in `wordCount`, move the left pointer to the right until the frequencies match.
   - If `count` equals `totalWords`, add the starting index `i` to the result list and adjust the window.
7. If a word is not in `wordCount`, reset the window and move the left pointer to the end of the current word.

## 🔹 Code

```java
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalWords = words.length;
        int totalLength = wordLength * totalWords;

        if (s.length() < totalLength) {
            return result;
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLength; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> windowCount = new HashMap<>();

            for (int right = i; right <= s.length() - wordLength; right += wordLength) {
                String currentWord = s.substring(right, right + wordLength);

                if (wordCount.containsKey(currentWord)) {
                    windowCount.put(currentWord, windowCount.getOrDefault(currentWord, 0) + 1);
                    count++;

                    while (windowCount.get(currentWord) > wordCount.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        count--;
                        left += wordLength;
                    }

                    if (count == totalWords) {
                        result.add(left);
                        String leftWord = s.substring(left, left + wordLength);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        count--;
                        left += wordLength;
                    }
                } else {
                    windowCount.clear();
                    count = 0;
                    left = right + wordLength;
                }
            }
        }

        return result;
    }
}
```

## 🔹 Detailed Dry Run

Let's dry run the optimal approach with `s = "barfoothefoobarman"` and `words = ["foo","bar"]`.

1. **Initialization:**
   - `wordLength = 3`, `totalWords = 2`, `totalLength = 6`.
   - `wordCount = {"foo": 1, "bar": 1}`.

2. **Iteration over starting indices:**
   - **i = 0:**
     - **right = 0:**
       - Current word: "bar".
       - `windowCount = {"bar": 1}`, `count = 1`.
     - **right = 3:**
       - Current word: "foo".
       - `windowCount = {"bar": 1, "foo": 1}`, `count = 2`.
       - Valid substring, add `0` to result.
       - Adjust window: `windowCount = {"foo": 1}`, `count = 1`, `left = 3`.
   - **i = 1:**
     - **right = 1:**
       - Current word: "ar".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 4`.
     - **right = 4:**
       - Current word: "foo".
       - `windowCount = {"foo": 1}`, `count = 1`.
     - **right = 7:**
       - Current word: "the".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 10`.
   - **i = 2:**
     - **right = 2:**
       - Current word: "rf".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 5`.
     - **right = 5:**
       - Current word: "oth".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 8`.
     - **right = 8:**
       - Current word: "foo".
       - `windowCount = {"foo": 1}`, `count = 1`.
     - **right = 11:**
       - Current word: "bar".
       - `windowCount = {"foo": 1, "bar": 1}`, `count = 2`.
       - Valid substring, add `8` to result.
       - Adjust window: `windowCount = {"bar": 1}`, `count = 1`, `left = 11`.
   - **i = 3:**
     - **right = 3:**
       - Current word: "foo".
       - `windowCount = {"foo": 1}`, `count = 1`.
     - **right = 6:**
       - Current word: "the".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 9`.
     - **right = 9:**
       - Current word: "oob".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 12`.
   - **i = 4:**
     - **right = 4:**
       - Current word: "foo".
       - `windowCount = {"foo": 1}`, `count = 1`.
     - **right = 7:**
       - Current word: "the".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 10`.
     - **right = 10:**
       - Current word: "oba".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 13`.
   - **i = 5:**
     - **right = 5:**
       - Current word: "oth".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 8`.
     - **right = 8:**
       - Current word: "foo".
       - `windowCount = {"foo": 1}`, `count = 1`.
     - **right = 11:**
       - Current word: "bar".
       - `windowCount = {"foo": 1, "bar": 1}`, `count = 2`.
       - Valid substring, add `8` to result.
       - Adjust window: `windowCount = {"bar": 1}`, `count = 1`, `left = 11`.
   - **i = 6:**
     - **right = 6:**
       - Current word: "the".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 9`.
     - **right = 9:**
       - Current word: "oob".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 12`.
   - **i = 7:**
     - **right = 7:**
       - Current word: "efo".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 10`.
     - **right = 10:**
       - Current word: "oba".
       - Not in `wordCount`, reset window: `windowCount = {}`, `count = 0`, `left = 13`.

3. **Result:**
   - `result = [0, 8]`.

## 🔹 Complexity Analysis

| Complexity | Value |
|---|---|
| Time Complexity | O(n * k) where `n` is the length of `s` and `k` is the number of words. |
| Space Complexity | O(k) for storing the frequency maps. |

---

# 🔍 Edge Cases

- **Empty Input:** If `s` is empty or `words` is empty, return an empty list.
- **Single Word:** If `words` contains only one word, check for all occurrences of that word in `s`.
- **No Match:** If no concatenated substring is found, return an empty list.
- **Duplicate Words:** Handle cases where `words` contains duplicate words.
- **Large Input:** Ensure the solution handles large input sizes efficiently.

---

# 📚 Key Takeaways

- The brute force approach checks all possible substrings and splits them into words, which is inefficient.
- The optimal approach uses a sliding window technique to efficiently check for valid substrings, reducing the time complexity.
- Understanding the frequency of words and maintaining a window state is crucial for solving this problem efficiently.

---

# 🚀 Interview Tips

- **Follow-up Questions:**
  - What if the words in `words` can be repeated multiple times?
  - How would you optimize the solution further?
- **Common Pitfalls:**
  - Not handling edge cases properly.
  - Inefficiently checking for valid substrings.
- **Alternative Approaches:**
  - Using a trie to store the words and checking for valid substrings.
  - Using a rolling hash to quickly compare substrings.

---

# ✅ Conclusion

The optimal approach using a sliding window technique is more efficient and scalable for large input sizes. It reduces the time complexity by avoiding repeated splitting and counting of words, making it suitable for interview scenarios. Understanding the frequency of words and maintaining a window state is key to solving this problem efficiently.