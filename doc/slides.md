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
# main() bez klasy
```Java
void main() {  //21..25
    System.out.println("Hello World");
}
```
---
# **język...**
---
# dedukcja typu zmiennej
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
# bloki tekstowe
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

---
# instanceof z rzutowaniem 
### (pattern matching)

```Java
if (animal instanceof Cat cat) { // Java 14..16
        cat.meow();
}
```
```Java
if (object instanceof int number) { //Java 25 +typy podstawowe
        int x2 = number + number;
}
```
---
# switch eexpression
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

# switch pattern matching + guards
### (dopasowanie i rzutowanie typów)
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
# opóźnione wołanie innego konstruktora 
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
# inheritance control
```Java
sealed class Employee permits WhiteCollar, BlueCollar {}
```

---
# JVM
---
# precyzyjny NullPointerException
Java 14


