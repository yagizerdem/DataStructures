import javax.naming.PartialResultException;
import javax.naming.spi.StateFactory;

public class Trie {

    public TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode parent = this.root;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            TrieNode current = parent.children.get(ch);
            if (current == null) {
                current = new TrieNode();
                parent.children.put(ch, current);
            }
            parent = current;
        }
        parent.isEndofString = true;
    }

    public boolean exist(String word) {
        TrieNode parent = this.root;
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            TrieNode cur = parent.children.get(ch);
            if (cur == null) {
                System.out.println("word not exist");
                return false;
            }
            parent = cur;
        }
        if (!parent.isEndofString) {
            System.out.println("word is substring of another word");
            return false;
        }
        return true;
    }

    private boolean delete(TrieNode parent, String word, int index) {
        Character ch = word.charAt(index);
        TrieNode current = parent.children.get(ch);
        boolean canDelete;

        if (current.children.size() > 1) {
            delete(current, word, index + 1);
            return false;
        }
        if (index == word.length() - 1) {
            if (current.children.size() >= 1) {
                current.isEndofString = false;
                return false;
            } else {
                parent.children.remove(ch);
                return true;
            }
        }
        if (current.isEndofString) {
            delete(current, word, index + 1);
            return false;
        }
        canDelete = delete(current, word, index + 1);
        if (canDelete) {
            parent.children.remove(ch);
            return true;
        } else return false;
    }

    public void delete(String word) {
        if (exist(word)) {
            delete(this.root, word, 0);
        }
    }

}
