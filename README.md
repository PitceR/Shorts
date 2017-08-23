# Shorts
Shorts is a simple API using Java 1.8 functional interfaces which allows you to reduce boilerplate code.
## Example
Normal:
```java
String text = "Foo";
if(text.isEmpty())
{
    System.out.println("Bar");
}
else
{
    System.out.println("Foo Bar");
}
```
```java
for(int index = 0; index < 50; index++)
{
    System.out.println(index);
}
```
Simplified:
```java
String text = "Foo";
Conditions.ifElse(text.isEmpty(), () -> System.out.println("Bar"), () -> System.out.println("Foo Bar"));
```
```java
Loops.loop(50, System.out::println);
```
## Building
Download source and run `mvn clean install`. Make sure you have installed latest version of Maven.
