import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {
        for (int j = 0; j < 1000; j++) {
            new Thread(() -> {
                String map = generateRoute("RLRFR", 100);
                System.out.println(map);
                int number = 0;
                for (int i = 0; i < map.length(); i++) {
                    if (String.valueOf(map.charAt(i)).equals("R")) {
                        number++;
                    }
                }
                System.out.println(number);
                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(number)) {
                        sizeToFreq.put(number, sizeToFreq.get(number) + 1);
                    } else {
                        sizeToFreq.put(number, 1);
                    }
                }
            }).start();
        }
        System.out.println(result());
    }

    public static String result() {
        Map.Entry<Integer, Integer> max = sizeToFreq
                        .entrySet()
                        .stream().max(Map.Entry.comparingByValue())
                    .get();
        System.out.println("Самое частое количество повторений " + max.getKey() +
                " (встретилось " + max.getValue() + " раз)");
        System.out.println("Другие размеры:");
        sizeToFreq
                .entrySet()
                .forEach(o -> System.out.println("- "
                        + o.getKey() + " (" + o.getValue() + " раз)"));
        return "--end--";
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}