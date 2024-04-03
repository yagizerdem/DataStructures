import java.util.HashMap;

public class TrieNode {
    boolean isEndofString;
    HashMap<Character, TrieNode> children;

    public TrieNode() {
        this.isEndofString = false;
        this.children = new HashMap<>();
    }
}
