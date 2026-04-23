---
theme: gaia
_class: lead
paginate: true
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

![bg left:40% 80%](Logo_Sii_Poland.jpg)

# **Zmieniająca się Java**
## **jak na tym skorzystać**


Krzysztof Bardziński

---

# <!--fit--> **ciekawostki**

---
# REPL
```bash
$JAVA_HOME/bin/jshell -v
```
```bash
jshell> void helloWorld() { System.out.println("Hello world"); }
|  created method helloWorld()

jshell> helloWorld()
Hello world

jshell> /help
jshell> /* show status: /var /methods /list /history */

jshell> /save myrepl.java
jshell> /open myrepl.java
jshell> /exit
```
---
# main() bez klasy + java.lang.IO
```Java
void main() {  // Java 25
    IO.println("Hello World");
    String line = IO.readln();
}
```
---
# module imports

```Java
import javax.xml.*; 
import javax.xml.parsers.*; 
import javax.xml.stream.*;
// Instead, just use:

import module java.xml;

```
---
# Markdown documentation comments

```Java
   // Java 23
/// Fetches a **random cat fact** from the API.
/// **Features:**
/// - Uses a remote API
/// - Returns a simple `String`
///
/// @return A random cat fact
/// @throws IOException If the API call fails
///
/// | Latin | Greek |
/// |-------|-------|
/// | a     | alpha |
/// | b     | beta  |
```

---
# Scanner
```Java
        Scanner scanner = new Scanner("hello,1,FF");
        scanner.useDelimiter(",");
        String greeting = scanner.next(); //Numbers
        int one = scanner.nextInt();
        int ff = scanner.nextInt(16);
        scanner.close();

        assertEquals("hello", greeting);
        assertEquals(1, one);
        assertEquals(255,ff);
```
---
# <!--fit--> **java language**
---
# diamond operator
```Java
Map<String, List<String>> map = new HashMap<>(); //Java 9
```
---
# variable type inference
```Java
// Java 10
var x = 17L;
var result = new HashMap<String,Person>();

// does not compile!
var undefined;
var empty = null;
var arr = { 1, 2, 3 }; // array type spec required

// not recommended - reader has to guess type
var data = obj.process();
var result = collection.stream().findFirst().orElse(0);       
```
---
# unnamed variables
```Java
for (Order _ : orders)  { total++; }   // Java 22
        
try { ... }
catch (Exception _) { ... }                // Unnamed variable
catch (Throwable _) { ... }                // Unnamed variable
       
try (var _ = ScopedContext.acquire()) {    // Unnamed variable
... no use of acquired resource ...
}

if (r instanceof ColoredPoint(Point(int x, int y), _)) { ... }
```
---
# multiline text
```Java
//Java 13
    String text = """
        Litwo, Ojczyzno moja! ty jesteś jak zdrowie;
            Ile cię trzeba cenić, ten tylko się dowie,
```
Kompilator usunie wcięcia i wyrówna tekst 
do kolumny najbardziej na lewo.
```
Litwo, Ojczyzno moja! ty jesteś jak zdrowie;
    Ile cię trzeba cenić, ten tylko się dowie,
```
---
# **record** + compact constructor
```Java
// Java 14..19
public record Money(int value, String currency) {

    public Money{
        if(!"PLN".equals(currency)){
            throw new IllegalArgumentException();
        }
    }

    // auto getters: value(), currency()
    // methods: toString(), equals(obj), hashMap()
}
```
---
# **interface** default method,
### static method
```Java
interface MyInterface {
    String message();

    default String wrappedMessage(String prefix, String postfix){
        return prefix + message() + postfix;
    }

    static int doubleValue(int value) {
        return value + value;
    }
}
```

---
# **instanceof** pattern matching

```Java
if (animal instanceof Cat cat) { // Java 14..16
        cat.meow();
}

if (obj instanceof Point(int x, int y)) { //Java 21 dekonstrukcja rekordów
        System.out.println(x+y);
}

if (object instanceof int number) { //Java 25 +typy podstawowe
        int x2 = number + number;
}
```
---
# **switch** expression

```Java
var result = switch (month) { 
              //Java 13
        case JANUARY, JUNE, JULY -> 3;
        case MARCH, MAY -> {
            int monthLength = month.toString().length();
            yield monthLength * 4; //return jest zabronione
        }

        default -> 0; // wymagane jeśli nie wymieniono wszystkich opcji enum 

};
```
---

# **switch** pattern matching + guards
```Java
static double getDoubleUsingSwitch(Object o) {
    
    return switch (o) {         // Java 17..21

        case Integer i                      -> i.doubleValue();  
        case String s when s.length() > 0   -> Double.parseDouble(s);
        case null       -> -1d;     // dopuszczalne a nawet zalecane przeciw NPE
        case byte b     -> 255d;    // Java 25 dopuszcza typy podstawowe

        default -> 0d; //niepotrzebne przy wszystkich opcjach sealed class
    };
}
```
--- 
# internal constructor calls
```Java
class Employee extends Person {
    final String name;

    Employee(String name, int age) {
        if (age < 18 || age > 67)
            throw new IllegalArgumentException("Age must be between 18 and 67");

        super(age); // opóźnione super() / this()  Java 25

        this.name = name;
    }
}
```
---
# controlled inheritance
```Java
// for class or interface
sealed abstract class Ball permits RedBall, BlueBall, GreenBall { }

final  class RedBall   extends Ball { }
final  class BlueBall  extends Ball { }
final  class GreenBall extends Ball { }
```

---
# <!--fit--> **JVM**

---
# nice NullPointerException
Java 14 + debug info

Cannot invoke
"com.example.MyClass$Employee.getName()"
because "employee" is null

---
# Garbage Collectors

- GC1
- ZGC z krótszymi czasami przestojów
- Shenandoah


Compact Object Headers 12->8 bytes


---
# <!--fit--> **library**

---
# Objects
```Java
String v = Objects.requireNonNullElse(value, "default");

//predicates
Objects.isNull(val)
Objects.nonNull(val)

stream.filter(Objects::nonNull).toList();
```
---
# Optional 
```Java
Optional<Config> cfg = primary()
    .or(this::secondary)
    .or(this::defaults);

optional.stream().forEach(IO.println);

optional.ifPresentOrElse(consumer, runnable);
```
---
# easy immutable collections
```Java
var list1 = List.of(1,2,3);

var list2 = List.copyOf(mutableList);

Map<String, Integer> m1 = Map.of("x", 11, "y", 12, "z", 20)

```
---
# sequential collections
```Java
var first = list.getFirst(); // can throw NoSuchElementException

var last = list.getLast();

var listR = list.reversed();
```
---
# functional interfaces:

- Function, BiFunction
- Predicate
- Supplier
- Consumer, BiConsumer
---
# Stream
```Java
Stream<String> s =
    Stream.ofNullable(val); //empty or 1 item stream

stream.flatmap(...) 
+ stream.mapMulti( (items, collector) -> .. collector.accept(x) )

Predicate isSenior = (Person p) -> p.getAge() > 65;
stream.filter( not(isSenior)).toList();
```
---
# Collectors grouping
```Java
Collector<String, ?, Map<Integer,List<String>>> groupingByLength = 
    groupingBy((String name)-> name.length(), 
       mapping((String name)-> name.toUpperCase(), toList()));

var namesByLength = words.stream().collect(groupingByLength);
```
---
# Collectors teeing
```Java
var result = items.stream().collect(
    Collectors.teeing(
        Collectors.counting(),
        Collectors.summingDouble(Item::price),
        Stats::new
    )
);
```
---
# Stream gatherers
```Java
// Java 24, gotowce np. windowSliding, windowFixed
stream.gather(Gatherers.windowSliding(2))
      .filter(window -> (window.size() == 2
                && isSuspicious(window.get(0),window.get(1))))
      .toList();
}
```
---
# "Pair"
- javafx *Pair*, *ImmutablePair* 

- org.apache.commons.lang3.tuple  *MutablePair*, *ImmutablePair* (abstract Pair) 

- vavr *Tuple2* (immutable) 

- jdk *AbstractMap.SimpleEntry*, *AbstractMap.SimpleImmutableEntry*
```Java 
var pair = Map.entry("x", 13)
```
---

- klient HTTP/3
- Foreign Function/Memory Api zamiast JNI // Java 22
- usunięcie SecurityManager   //Java 24
- blokowanie dostępu do Java internal API


---

# <!--fit--> **(near) future**
---
# Virtual Threads (project Loom)
+ ScopedValue (zastąpi ThreadLocal)
+ structured concurrency
```Java
try (var scope = StructuredTaskScope.<String>open()) {
    var userTask = scope.fork(() -> fetchUser());
    var orderTask = scope.fork(() -> fetchOrder());
    scope.join();
    ...
}
```
---
# StableValue / lazy constant
```Java
private final StableValue<User> user =
    StableValue.of(this::loadUser);

user.get();
```
---
# Vector API (Project Valhalla)
Wykorzystanie rozkazów SIMD CPU
do obliczeń wektorowych.

---
# <!--fit--> **not too slow**
- extra paid support for Oracle JDK 8, 11
- Spring Boot requirements
-- 2.xx JDK 8..17
-- 3.xx JDK 17+
-- 4.xx JDK 17+

---
# <!--fit--> **not so fast...**
- flatMap laziness fixed in JDK 10
- list.remove(N) difference between Collection<Integer> and ArrayList<Integer>  
- ARM bugs in JDK
- found bugs in Virtual Threads
- **watch version of JDK in your IDE and pipeline** :)

---
# <!--fit--> **links........**

https://javaalmanac.io/
https://javaevolved.github.io

https://pragprog.com/titles/vscajava/cruising-along-with-java/

---
# <!--fit--> **The End**

Any questions?