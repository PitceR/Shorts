# Shorts [![Build Status](https://travis-ci.org/PitceR/shorts.svg?branch=master)](https://travis-ci.org/PitceR/shorts)
Shorts is a simple utility using Java 1.8 functional interfaces which allows you to reduce boilerplate code.
## Example
Normal:
```java
for(int index = 0; index < 50; index++)
{
    System.out.println(index);
}
```
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
File file = new File("foobar");
BufferedImage image;
try
{
    image = ImageIO.read(file);
}
catch(IOException exception)
{	
    exception.printStackTrace();
    image = null;
}
```
Simplified:
```java
Loops.loop(50, System.out::println);
```
```java
String text = "Foo";
Conditions.ifElse(text.isEmpty(), () -> System.out.println("Bar"), () -> System.out.println("Foo Bar"));
```
```java
File file = new File("foobar");
BufferedImage image = Throwables.tryCatch(() -> ImageIO.read(file), Throwable::printStackTrace).orElse(null);
```
## Building
Download source and run `mvn clean install`. Make sure you have installed latest version of Maven.
