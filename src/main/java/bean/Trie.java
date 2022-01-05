package bean;

/**
 * @author Javen
 * @create 2021-12-28
 * @Description 字典树
 */
public class Trie {

    public Trie[] children = new Trie[26];
    public boolean isWord = false;

    public Trie() {
    }

    public Trie(Trie[] children, boolean isWord) {
        this.children = children;
        this.isWord = isWord;
    }

    public void insert(String word) {
        Trie root = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (root.children[index] == null) root.children[index] = new Trie();
            root = root.children[index];
        }
        root.isWord = true;
    }

    public boolean isInTree(String word) {
        return dfs(word, 0, this);
    }

    public static boolean dfs(String word, int index, Trie root) {
        if (index == word.length()) return root.isWord;
        if (root.children[word.charAt(index) - 'a'] == null) return false;
        return dfs(word, index + 1, root.children[word.charAt(index) - 'a']);
    }
}
