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

# **ciekawostki...**

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
jshell> /var
jshell> /methods
jshell> /list
jshell> /history
jshell> /save repl.java
jshell> /open repl.java
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
# **język...**
---
# variable type deduction
```Java
// Java 10
var x = 17L;
var result = new HashMap<String,Person>();

// nie zadziała
var undrfined;
var empty = null;
var arr = { 1, 2, 3 }; // wymagany docelowy typ tablicowy

//nie zalecane
var data = obj.prcoess();
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
Kompilator usunie wcięcia i wyrówna tekst do kolumny najbardziej na lewo.
```
Litwo, Ojczyzno moja! ty jesteś jak zdrowie;
    Ile cię trzeba cenić, ten tylko się dowie,
```
---
# record
```Java
// Java 14..19
public record Money(int value, String currency) {
    public Money{
        if(!"PLN".equals(currency)){
            throw new IllegalArgumentException();
        }
    }
}
```
---
# instanceof pattern matching

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
# switch 1: expression
Konstrukcja `switch() {case _ : _; break;}` na typach podstawowych
enum od Javy 5, String od Javy 7
```Java
var result = switch (month) {           //Java 13
        case JANUARY, JUNE, JULY -> 3;
        case MARCH, MAY -> {
            int monthLength = month.toString().length();
            yield monthLength * 4; //return jest zabronione
        }
        default -> 0; // wymagane jeśli nie wymieniono wszystkich opcji enum
};
```
---

# switch 2: pattern matching + guards
```Java
static double getDoubleUsingSwitch(Object o) {
    return switch (o) {         // Java 17..21
        case Integer i -> i.doubleValue();  
        case Float f -> f.doubleValue();
        case String s when s.length() >0 -> Double.parseDouble(s);
        case null -> -1d;       // dopuszczalne a nawet zalecane przeciw NPE
        case byte b -> 255d;    // Java 25 dopuszcza typy podstawowe
        default -> 0d;
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
        super(age); // opóźnione super()/this()  Java 25
        this.name = name;
    }
}
```
---
# controlled inheritance
```Java
sealed abstract class Ball permits RedBall, BlueBall, GreenBall { }
final  class RedBall   extends Ball { }
final  class BlueBall  extends Ball { }
final  class GreenBall extends Ball { }
```

---
# JVM
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
# Library
- Sequenced Collections // Java 21
- Foreign Function/Memory Api zamiast JNI // Java 22
- usunięcie SecurityManager   //Java 24
- blokowanie dostępu do Java internal API

---
# Stream Gatherers
```Java
// Java 24, gotowce np. windowSliding, windowFixed
stream.gather(Gatherers.windowSliding(2))
      .filter(window -> (window.size() == 2
                && isSuspicious(window.get(0),window.get(1))))
      .toList();
}
```
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
# Vector API

---
