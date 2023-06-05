import java.util.Objects;

public class Test4 {
    public static void main(String[] args) {
        int hash1 = Objects.hash("Hello", "World");
        int hash2 = Objects.hash("Hello", "World");
        int hash3 = Objects.hash("Hello", "World");
        System.out.println(hash1);
        System.out.println(hash2);
        System.out.println(hash3);
    }
}
